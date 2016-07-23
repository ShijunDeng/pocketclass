package com.pocketclass.web.controller;

import java.io.IOException;
import java.security.SecureRandom;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.MailPropertiesDao;
import com.pocketclass.dao.impl.ProjectDaoImpl;
import com.pocketclass.utils.EmailUtils;
import com.pocketclass.utils.EncryptionUtils;
import com.pocketclass.utils.WebUtils;
import com.pocketclass.web.beans.RegisterForm;

/**
 * 
 * ע��������
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class RegisterEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 201210131933L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession httpSession = request.getSession();

			RegisterForm form = WebUtils.requestToBean(request,
					RegisterForm.class);
			boolean isOk = form.validate();// ���ύ����Ϣ�Ƿ�Ϸ�
			// У����֤���Ƿ���ȷ
			String c_vercode = request.getParameter("vercode");
			String s_vercode = (String) httpSession.getAttribute("vercode");
			if (c_vercode == null || s_vercode == null
					|| !c_vercode.equalsIgnoreCase(s_vercode)) {
				form.getTips().put("vercode", "��֤�����");
				isOk = false;
			}

			if (isOk == false) {
				// ��֤��ͨ�����ص������д���棬���û���ʾ������Ϣ
				request.setAttribute("form", form);
				request.getRequestDispatcher("/jsp/register_jsp/register.jsp")
						.forward(request, response);
				return;
			}

			// �û���д����ע�������
			String toMail = request.getParameter("email");
			// �û���д���û���
			String registerName = request.getParameter("username");
			SecureRandom idRandom = new SecureRandom();
			String rRandomId = "" + idRandom.nextInt();
			String registerId = EncryptionUtils.md5(rRandomId);

			// ���ӵ���Чʱ��,Ĭ������Ϊ10����
			int validTime = httpSession.getMaxInactiveInterval();
			// �û���������ַ�ص���վ,URL��д
			String url = "http://" + ProjectDaoImpl.getDomainName() + ":"
					+ ProjectDaoImpl.getPort() + request.getContextPath();
			String checkUrl = url + "/servlet"
					+ "/RegisterEmailBackServlet;jsessionid="
					+ httpSession.getId() + "?registerId=" + registerId;
			EmailUtils email = new EmailUtils();
			try {
				email.sendEmail(
						toMail,
						"ע��-���Ͽ���",
						"<FONT size=+0>"
								+ "<DIV>"
								+ "<TABLE style=\"LINE-HEIGHT: 20px; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif; FONT-SIZE: 12px\" border=0 "
								+ "cellSpacing=3 cellPadding=0 width=620 align=center>"
								+ "<TBODY>" + "<TR>"
								+ "<TD width=162><A href=\""
								+ url
								+ "\" target=_blank><IMG border=0"
								+ " src=\""
								+ url
								+ "/images/register_logo/register_logo001.gif\" width=160 height=80></A></TD>"
								+ "<TD style=\"BORDER-BOTTOM: #000 1px solid\">"
								+ "<P style=\"WIDTH: 449px\" align=right><SPAN style=\"WIDTH: 449px\"><A title=PocketClass��ҳ "
								+ "href=\""
								+ url
								+ "/jsp/main.jsp\" target=_blank>���Ͽ�����ҳ</A></SPAN></P></TD></TR>"
								+ "<TR>"
								+ "<TD height=60 width=611 colSpan=2><SPAN style=\"FONT-SIZE: 14px; "
								+ "FONT-WEIGHT: bold\"><SPAN style=\"FONT-SIZE: 14px; FONT-WEIGHT: bold\">�𾴵����Ͽ��ÿͻ�</SPAN>��"
								+ registerName
								+ "</SPAN></TD></TR>"
								+ "<TR>"
								+ "<TD colSpan=2>"
								+ "<P>���ã���ӭע�����Ͽ��ã�����һ���������ע��</P>"
								+ "<P>�������ӣ����"
								+ "<a href='"
								+ checkUrl
								+ "'>"
								+ checkUrl
								+ "</a>���ע��.<P>��������"
								+ validTime
								/ 60
								+ "��������Ч"
								+ " </P></P></TD></TR>"
								+ "<TR>"
								+ "<TD colSpan=2>"
								+ "<P>------------------------------------------------------------------------------------------------------------------</P>"
								+ "<P>���ʼ���ϵͳ����������ֱ�ӻظ���</P><A href=\""
								+ url
								+ "/\" target=_blank>"
								+ url
								+ "/</A> רҵ������ѧϰƽ̨"
								+ "<P>���ߣ�000-000-0000<BR>�ͷ�����:"
								+ MailPropertiesDao.getEmailAddress()
								+ "<BR >С������<a "
								+ "href=\"http://www.dengshijun.icoc.cc/\">http://www.dengshijun.icoc.cc/</a> </P></TD></TR>"
								+ "<TR>"
								+ "<TD height=40 vAlign=bottom colSpan=2>"
								+ "<P>PocketClass��Ȩ����(C) 2012-2022</P></TD></TR></TBODY></TABLE></DIV></FONT>");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			// ��form������session��
			httpSession.setAttribute("form", form);
			httpSession.setAttribute("rRandomId", rRandomId);
			request.getRequestDispatcher("/jsp/register_jsp/registerNext.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "������!");
			request.getRequestDispatcher("/jsp/message_jsp/errorMessage.jsp")
					.forward(request, response);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

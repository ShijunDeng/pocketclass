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
import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.utils.EmailUtils;
import com.pocketclass.utils.EncryptionUtils;

/**
 * ��������������֤,�Լ�����û����������Ƿ�ƥ��
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-18 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PasswordEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 201210192143L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// �û���д����ע�������
			String toMail = request.getParameter("email");
			HttpSession httpSession = request.getSession();

			// �û���д���û���
			String username = (String) httpSession.getAttribute("username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "���ȵ�¼���ٽ��в���");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			if (new UserDaoImpl().rightInformation("email", toMail, username) == false) {
				request.setAttribute("error", "�û�������ϵ������Ϣ��ƥ�䣬��������д!");
				request.getRequestDispatcher("/jsp/password/forgetPassword.jsp")
						.forward(request, response);
				return;
			}
			SecureRandom idRandom = new SecureRandom();
			String pRandomId = "" + idRandom.nextInt();
			String pswId = EncryptionUtils.md5(pRandomId);

			// ���ӵ���Чʱ��,Ĭ������Ϊ10����
			int validTime = httpSession.getMaxInactiveInterval();
			// �û���������ַ�ص���վ,URL��д
			String url = "http://" + ProjectDaoImpl.getDomainName() + ":"
					+ ProjectDaoImpl.getPort() + request.getContextPath();
			String checkUrl = url + "/servlet"
					+ "/PasswordEmailBackServlet;jsessionid="
					+ httpSession.getId() + "?pswId=" + pswId + "&username="
					+ username;
			EmailUtils email = new EmailUtils();
			try {
				email.sendEmail(
						toMail,
						"������������-���Ͽ���",
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
								+ username
								+ "</SPAN></TD></TR>"
								+ "<TR>"
								+ "<TD colSpan=2>"
								+ "<P>���յ�����������ʼ�����Ϊ�� (Ҳ������ĳ��ð����������) ������һ���µ����롣�����ⲻ��������������, �벻������������ʼ�,"
								+ " ��������������յ�������ż�ɧ��, ���������������Ա��</P>"
								+ "<P>Ҫʹ���µ�����, ��ʹ�����������������롣"
								+ "���<a href='"
								+ checkUrl
								+ "'>"
								+ checkUrl
								+ "</a>�����������,<P>��������"
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

			char[] mailArr = toMail.toCharArray();
			String showMail = "";
			int atIndex = toMail.indexOf("@");
			for (int i = 0; i < mailArr.length; i++) {
				if (i > 0 && i < atIndex)
					showMail += "*";
				else
					showMail += mailArr[i];
			}
			httpSession.setAttribute("pRandomId", pRandomId);
			request.setAttribute("message", "�����������ύ�ɹ�����鿴��������" + showMail);
			request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
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

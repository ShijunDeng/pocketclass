package com.pocketclass.web.controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
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
 * ��֤�����Ƿ����û�Ԥ����Ǹ����� �޸�����
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-21 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ModifyEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 201210211537L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			// �õ���������
			String operation = request.getParameter("operation");
			if (operation == null || operation.trim().equals("")) {
				request.getRequestDispatcher("/jsp/customer/modifyEmail.jsp")
						.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();

			String username = (String) session.getAttribute("username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "���ȵ�¼���ٽ��в���");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			PrintWriter out = response.getWriter();
			if (operation.equals("queryold")) {
				// ��֤�������Ƿ���ȷ
				String email = request.getParameter("email");
				if (username != null && username.trim().equals("") == false
						&& email != null && email.trim().equals("") == false) {
					if (new UserDaoImpl().rightInformation("email",
							email.trim(), username.trim())) {
						out.print("true");
						out.flush();
						out.close();
						return;
					}
				}
				out.print("false");
				out.flush();
				out.close();
				return;
			} else if (operation.equals("querynew")) {
				// ��֤�������Ƿ����
				String email = request.getParameter("email");
				if (username != null && username.trim().equals("") == false
						&& email != null && email.trim().equals("") == false) {
					if (new UserDaoImpl().existEmail(email)) {
						out.print("true");
						out.flush();
						out.close();
						return;
					}
				}
				out.print("false");
				out.flush();
				out.close();
				return;
			} else if (operation.equals("modify")) {
				// �����޸�
				try {
					String email = request.getParameter("newemailname");
					this.sendCheckEmail(request, response, email);
					char[] mailArr = email.toCharArray();
					String showMail = "";
					int atIndex = email.indexOf("@");
					for (int i = 0; i < mailArr.length; i++) {
						if (i > 0 && i < atIndex)
							showMail += "*";
						else
							showMail += mailArr[i];
					}
					request.setAttribute("message", "�����������ύ�ɹ�����֤��Ϣ�Ѿ��·�����������"
							+ showMail);
					request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
							.forward(request, response);
				} catch (Exception e) {
					request.setAttribute("message", "��֤���ʼ�����ʧ�ܣ�δ������޸Ĳ����������²���!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					throw new RuntimeException(e);
				}
			}
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

	private void sendCheckEmail(HttpServletRequest request,
			HttpServletResponse response, String toEmail)
			throws MessagingException {
		HttpSession httpSession = request.getSession();
		EmailUtils email = new EmailUtils();
		// �û���д���û���
		String username = (String) httpSession.getAttribute("username");
		SecureRandom idRandom = new SecureRandom();
		String eRandomId = "" + idRandom.nextInt();
		String emailId = EncryptionUtils.md5(eRandomId);
		// ���ӵ���Чʱ��,Ĭ������Ϊ10����
		int validTime = httpSession.getMaxInactiveInterval();
		// �û���������ַ�ص���վ,URL��д
		String url = "http://" + ProjectDaoImpl.getDomainName() + ":"
				+ ProjectDaoImpl.getPort() + request.getContextPath();
		String checkUrl = url + "/servlet"
				+ "/EmailMailBackServlet;jsessionid=" + httpSession.getId()
				+ "?emailId=" + emailId;

		email.sendEmail(
				toEmail,
				"����������-���Ͽ���",
				"<FONT size=+0>"
						+ "<DIV>"
						+ "<TABLE style=\"LINE-HEIGHT: 20px; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif; FONT-SIZE: 12px\" border=0 "
						+ "cellSpacing=3 cellPadding=0 width=620 align=center>"
						+ "<TBODY>" + "<TR>" + "<TD width=162><A href=\""
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
						+ "<P>���յ�����������ʼ�����Ϊ�� (Ҳ������ĳ��ð����������) �������������䡣�����ⲻ��������������, �벻������������ʼ�,"
						+ " ��������������յ�������ż�ɧ��, ���������������Ա��</P>"
						+ "<P>Ҫʹ���µ�����, �����������������µ����䡣"
						+ "���<a href='"
						+ checkUrl
						+ "'>"
						+ checkUrl
						+ "</a>�����������,<P>��������"
						+ validTime
						/ 60
						+ "��������Ч(���Ը���Ϣ�����������䲻�ᱻ���)"
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

		httpSession.setAttribute("eRandomId", eRandomId);
		httpSession.setAttribute("newEmail", toEmail);

	}

}

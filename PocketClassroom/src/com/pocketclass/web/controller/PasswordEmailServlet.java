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
 * 重置密码邮箱验证,以及检测用户名和邮箱是否匹配
 * 
 * @author 邓仕军 wust_dengshijun@163.com
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
			// 用户填写用来注册的邮箱
			String toMail = request.getParameter("email");
			HttpSession httpSession = request.getSession();

			// 用户填写的用户名
			String username = (String) httpSession.getAttribute("username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "请先登录后再进行操作");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			if (new UserDaoImpl().rightInformation("email", toMail, username) == false) {
				request.setAttribute("error", "用户名和联系邮箱信息不匹配，请重新填写!");
				request.getRequestDispatcher("/jsp/password/forgetPassword.jsp")
						.forward(request, response);
				return;
			}
			SecureRandom idRandom = new SecureRandom();
			String pRandomId = "" + idRandom.nextInt();
			String pswId = EncryptionUtils.md5(pRandomId);

			// 链接的有效时间,默认设置为10分钟
			int validTime = httpSession.getMaxInactiveInterval();
			// 用户点击这个地址回到网站,URL重写
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
						"重置密码申请-掌上课堂",
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
								+ "<P style=\"WIDTH: 449px\" align=right><SPAN style=\"WIDTH: 449px\"><A title=PocketClass首页 "
								+ "href=\""
								+ url
								+ "/jsp/main.jsp\" target=_blank>掌上课堂首页</A></SPAN></P></TD></TR>"
								+ "<TR>"
								+ "<TD height=60 width=611 colSpan=2><SPAN style=\"FONT-SIZE: 14px; "
								+ "FONT-WEIGHT: bold\"><SPAN style=\"FONT-SIZE: 14px; FONT-WEIGHT: bold\">尊敬的掌上课堂客户</SPAN>："
								+ username
								+ "</SPAN></TD></TR>"
								+ "<TR>"
								+ "<TD colSpan=2>"
								+ "<P>您收到这封这封电子邮件是因为您 (也可能是某人冒充您的名义) 申请了一个新的密码。假如这不是您本人所申请, 请不用理会这封电子邮件,"
								+ " 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。</P>"
								+ "<P>要使用新的密码, 请使用以下链接启用密码。"
								+ "点击<a href='"
								+ checkUrl
								+ "'>"
								+ checkUrl
								+ "</a>完成密码重置,<P>此链接在"
								+ validTime
								/ 60
								+ "分钟内有效"
								+ " </P></P></TD></TR>"
								+ "<TR>"
								+ "<TD colSpan=2>"
								+ "<P>------------------------------------------------------------------------------------------------------------------</P>"
								+ "<P>此邮件由系统发出，请勿直接回复！</P><A href=\""
								+ url
								+ "/\" target=_blank>"
								+ url
								+ "/</A> 专业的网络学习平台"
								+ "<P>热线：000-000-0000<BR>客服邮箱:"
								+ MailPropertiesDao.getEmailAddress()
								+ "<BR >小邓在线<a "
								+ "href=\"http://www.dengshijun.icoc.cc/\">http://www.dengshijun.icoc.cc/</a> </P></TD></TR>"
								+ "<TR>"
								+ "<TD height=40 vAlign=bottom colSpan=2>"
								+ "<P>PocketClass版权所有(C) 2012-2022</P></TD></TR></TBODY></TABLE></DIV></FONT>");
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
			request.setAttribute("message", "您的申请已提交成功，请查看您的邮箱" + showMail);
			request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "出错啦!");
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

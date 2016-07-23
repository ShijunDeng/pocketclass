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
 * 注册请求处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
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
			boolean isOk = form.validate();// 表单提交的信息是否合法
			// 校验验证码是否正确
			String c_vercode = request.getParameter("vercode");
			String s_vercode = (String) httpSession.getAttribute("vercode");
			if (c_vercode == null || s_vercode == null
					|| !c_vercode.equalsIgnoreCase(s_vercode)) {
				form.getTips().put("vercode", "验证码错误");
				isOk = false;
			}

			if (isOk == false) {
				// 验证不通过，回到表格填写界面，给用户提示错误信息
				request.setAttribute("form", form);
				request.getRequestDispatcher("/jsp/register_jsp/register.jsp")
						.forward(request, response);
				return;
			}

			// 用户填写用来注册的邮箱
			String toMail = request.getParameter("email");
			// 用户填写的用户名
			String registerName = request.getParameter("username");
			SecureRandom idRandom = new SecureRandom();
			String rRandomId = "" + idRandom.nextInt();
			String registerId = EncryptionUtils.md5(rRandomId);

			// 链接的有效时间,默认设置为10分钟
			int validTime = httpSession.getMaxInactiveInterval();
			// 用户点击这个地址回到网站,URL重写
			String url = "http://" + ProjectDaoImpl.getDomainName() + ":"
					+ ProjectDaoImpl.getPort() + request.getContextPath();
			String checkUrl = url + "/servlet"
					+ "/RegisterEmailBackServlet;jsessionid="
					+ httpSession.getId() + "?registerId=" + registerId;
			EmailUtils email = new EmailUtils();
			try {
				email.sendEmail(
						toMail,
						"注册-掌上课堂",
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
								+ registerName
								+ "</SPAN></TD></TR>"
								+ "<TR>"
								+ "<TD colSpan=2>"
								+ "<P>您好！欢迎注册掌上课堂，请点击一下连接完成注册</P>"
								+ "<P>本次链接：点击"
								+ "<a href='"
								+ checkUrl
								+ "'>"
								+ checkUrl
								+ "</a>完成注册.<P>此链接在"
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
			// 将form表单置于session中
			httpSession.setAttribute("form", form);
			httpSession.setAttribute("rRandomId", rRandomId);
			request.getRequestDispatcher("/jsp/register_jsp/registerNext.jsp")
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

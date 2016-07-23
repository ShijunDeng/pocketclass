package com.pocketclass.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.utils.EncryptionUtils;

/**
 * 重置邮箱返回验证
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-02 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class EmailMailBackServlet extends HttpServlet {

	private static final long serialVersionUID = 201211022221L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// 不用HttpSession
			// session=request.getSession(false);是因为要通过session传递message信息
			// 但是新创建的session(当用户不是第一次点击验证链接的时候会新创建session)中是没有封装验证
			// 信息的，因此可以通过ranDomId是否为空来判断是不是验证过期
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "请先登录后再进行操作");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			String emailId = request.getParameter("emailId").replace(' ', '+');// 得到问号后面加密的emailId
			String eRandomId = (String) session.getAttribute("eRandomId");
			if (eRandomId == null) {
				request.setAttribute("message", "对不起，验证失败，此链接已经过期！");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			if (emailId == null) {
				request.setAttribute("message", "未知验证错误！");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}

			String checkId = EncryptionUtils.md5(eRandomId);
			// 如果两个加密的id对的上,就通过
			if (checkId.trim().equalsIgnoreCase(emailId.trim())) {
				try {
					String email = (String) session.getAttribute("newEmail");
					session.removeAttribute(("eRandomId"));// 让ranDomId失效，防止用户多次点击这个链接
					session.removeAttribute("newEmail");
					new UserDaoImpl()
							.updateProperties("email", email, username);
					request.setAttribute("message", "邮箱已经成功变更!");
					request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
							.forward(request, response);
				} catch (SQLException e) {
					request.setAttribute("message", "邮箱变更失败!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					throw new RuntimeException(e);
				}
				return;

			} else {
				request.setAttribute("message", "验证失败，验证信息错误！");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
			}
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

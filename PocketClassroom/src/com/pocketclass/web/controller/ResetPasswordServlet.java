package com.pocketclass.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pocketclass.dao.impl.UserDaoImpl;

/**
 * 重置密码-操作数据库
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-19 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ResetPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 201210190019L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String username = (String) request.getSession().getAttribute(
					"username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "请先登录后再进行操作");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			String password = request.getParameter("password");
			try {
				new UserDaoImpl().updatePassword(username, password);
				request.setAttribute("message", "密码重置成功，请使用新密码登陆!");
				request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
						.forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("message", "修改失败，请重新修改!");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				throw new RuntimeException(e);
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

package com.pocketclass.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.domain.User;
import com.pocketclass.utils.WebUtils;

/**
 * 显示用户信息之前，从数据库中读取用户信息，存入Session
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ReadUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 201210222132L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username == null || username.trim().equals("")) {
				request.setAttribute("error", "请先登录后再进行操作");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User user = userDaoImpl.query(username);
			if (user == null) {
				request.setAttribute("error", "请先登录后再进行操作");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			WebUtils.userToSession(user, session);
			request.getRequestDispatcher("/jsp/customer/userInfo.jsp").forward(
					request, response);
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

		response.setContentType("text/html");

	}

}

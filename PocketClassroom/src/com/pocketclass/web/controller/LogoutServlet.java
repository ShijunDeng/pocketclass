package com.pocketclass.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 201211011900L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			if (username != null) {
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.removeAttribute("username");
					Cookie[] cookies = request.getCookies();
					for (int i = 0; cookies != null && i < cookies.length; i++) {
						if (cookies[i].getName().equals("username")) {// 说明用户点击了记住密码,退出时清除cookie
							Cookie userCookie = new Cookie("username", "0");
							userCookie.setMaxAge(0);
							userCookie.setPath("/");
							response.addCookie(userCookie);

							Cookie pswCookie = new Cookie("password", "0");
							pswCookie.setMaxAge(0);
							pswCookie.setPath("/");
							response.addCookie(pswCookie);
						}
					}
					response.sendRedirect(request.getContextPath() + "/");
					return;
				}
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
		this.doGet(request, response);
	}

}

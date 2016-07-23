package com.pocketclass.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pocketclass.dao.impl.UserDaoImpl;

/**
 * ��������-�������ݿ�
 * 
 * @author ���˾� wust_dengshijun@163.com
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
				request.setAttribute("error", "���ȵ�¼���ٽ��в���");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			String password = request.getParameter("password");
			try {
				new UserDaoImpl().updatePassword(username, password);
				request.setAttribute("message", "�������óɹ�����ʹ���������½!");
				request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
						.forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("message", "�޸�ʧ�ܣ��������޸�!");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				throw new RuntimeException(e);
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

}

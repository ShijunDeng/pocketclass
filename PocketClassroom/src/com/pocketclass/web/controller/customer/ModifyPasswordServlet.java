package com.pocketclass.web.controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.pocketclass.dao.impl.UserDaoImpl;

/**
 * ��֤�Ǹ������Ƿ���ȷ �޸�����
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-21 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ModifyPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 201210210304L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			// �õ���������
			String operation = request.getParameter("operation");
			if (operation == null || "".equals(operation)) {
				request.getRequestDispatcher("/jsp/customer/modifypwd.jsp")
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
			String password = request.getParameter("password");
			if (operation.equals("query")) {
				PrintWriter out = response.getWriter();
				if (username != null && username.trim().equals("") == false
						&& password != null
						&& password.trim().equals("") == false) {
					if ((new UserDaoImpl()).query(username, password) != null) {
						out.print("true");
						return;
					}
				}
				out.print("false");
				out.flush();
				out.close();
				return;
			} else if (operation.equals("modify")) {
				try {
					new UserDaoImpl().updatePassword(username, password);
					request.setAttribute("message", "�����޸ĳɹ�!");
					request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
							.forward(request, response);
				} catch (SQLException e) {
					request.setAttribute("message", "�����޸�ʧ�ܣ��������޸�!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					throw new RuntimeException(e);
				}
				return;
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

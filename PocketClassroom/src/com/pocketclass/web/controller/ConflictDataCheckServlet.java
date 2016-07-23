package com.pocketclass.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.dao.impl.UserDaoImpl;

/**
 * 
 * ����û�ע��ʱ�ύ���û����������Ƿ�����ݿ����Ѿ����ڵ����ݿ��ͻ
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-20 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ConflictDataCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 201210201243L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String data = request.getParameter("data");
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			if (data != null && "".equals(data) == false) {
				if (userDaoImpl.existData(data, data) == false) {// �ύ����Ϣû�к����ݿ����ظ�
					out.print("notExist");
					out.flush();
					out.close();
				} else {
					out.print("exist");
					out.flush();
					out.close();
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

}

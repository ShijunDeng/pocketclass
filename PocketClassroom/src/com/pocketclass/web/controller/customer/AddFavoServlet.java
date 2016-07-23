package com.pocketclass.web.controller.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.customer.CourseFavoDaoImpl;

/**
 * 1.查询数据库中是否有收藏记录 2.向数据库中插入请求添加的收藏记录
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-01 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class AddFavoServlet extends HttpServlet {

	private static final long serialVersionUID = 201211010944L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "请先登录后再进行操作");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			CourseFavoDaoImpl courseFavoDaoLmpl = new CourseFavoDaoImpl();
			String option = request.getParameter("option");
			if (option != null && option.trim().equals("add")) {// 添加一条收藏记录
				String idVideoSeries = request.getParameter("idVideoSeries");
				if (idVideoSeries != null
						&& idVideoSeries.trim().equals("") == false) {
					if (courseFavoDaoLmpl.add(username, idVideoSeries)) {
						request.getRequestDispatcher(
								"SeriesDetailServlet?seriesID=" + idVideoSeries)
								.forward(request, response);
					} else {
						request.setAttribute("error", "操作失败!");
						request.getRequestDispatcher("/jsp/login/login.jsp")
								.forward(request, response);
					}
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
		doGet(request, response);
	}

}

package com.pocketclass.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.utils.EncryptionUtils;

/**
 * 邮箱返回验证
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class RegisterEmailBackServlet extends HttpServlet {
	private static final long serialVersionUID = 201210161951L;

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
			String registerId = request.getParameter("registerId").replace(' ',
					'+');// 得到问号后面加密的registerid
			String rRandomId = (String) session.getAttribute("rRandomId");
			if (rRandomId == null) {
				request.getSession().setAttribute("message",
						"对不起，验证失败，此链接已经过期！");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			if (registerId == null) {
				request.setAttribute("message", "未知验证错误！");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			String checkRegidId = EncryptionUtils.md5(rRandomId);

			if (checkRegidId.trim().equalsIgnoreCase(registerId.trim())) {
				session.removeAttribute("rRandomId");// 不去掉后面表单转化的时候会受影响
				request.getRequestDispatcher(
						"/servlet/RegisterServlet;jsessionid="
								+ session.getId()).forward(request, response);
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

package com.pocketclass.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.domain.User;
import com.pocketclass.exception.EmailRepeatException;
import com.pocketclass.exception.UserExistException;
import com.pocketclass.service.impl.BusinessServiceImpl;
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
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 201210081822L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			RegisterForm form = (RegisterForm) session.getAttribute("form");
			if (form == null) {// 表单为空
				request.setAttribute("form", form);
				request.getRequestDispatcher("/jsp/register_jsp/register.jsp")
						.forward(request, response);
				return;
			}
			BusinessServiceImpl service = new BusinessServiceImpl();
			User user = new User();
			user.setUsername(form.getUsername());
			user.setPassword(form.getPassword());
			user.setEmail(form.getEmail());
			user.setHeadPorAdd("no_img.jpg");
			try {
				service.register(user);
				request.getRequestDispatcher(
						"/jsp/register_jsp/registerSuccess.jsp").forward(
						request, response);
				session.invalidate();// 让session失效，防止用户多次点击这个链接
				return;
			} catch (UserExistException e) {
				form.getTips().put("username", e.getMessage());
				request.setAttribute("form", form);
				request.getRequestDispatcher("/jsp/register_jsp/register.jsp")
						.forward(request, response);
				session.invalidate();// 让session失效，防止用户多次点击这个链接
				return;
			} catch (EmailRepeatException e) {
				form.getTips().put("email", e.getMessage());
				request.setAttribute("form", form);
				request.getRequestDispatcher("/jsp/register_jsp/register.jsp")
						.forward(request, response);
				session.invalidate();// 让session失效，防止用户多次点击这个链接
				return;
			} catch (Exception e) {
				// 5.service处理不成功，原因是其他问题 ，跳转到全局消息处理界面，显示友好错误页面
				e.printStackTrace();
				request.setAttribute("message", "服务器出现未知错误!");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				session.invalidate();// 让session失效，防止用户多次点击这个链接
				return;
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

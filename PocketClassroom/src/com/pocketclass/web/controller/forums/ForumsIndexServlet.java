package com.pocketclass.web.controller.forums;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.forums.PostInfoDaoImpl;
import com.pocketclass.domain.post.ForumIndexBean;

/**
 * 交流首页展示请求处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-12 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumsIndexServlet extends HttpServlet {

	private static final long serialVersionUID = 201211122352L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username != null || "".equals(username)) {
				request.setAttribute("lastestLoginTime", "2012-11-16 12:06:12");
			}
			PostInfoDaoImpl postInfoDaoImpl = new PostInfoDaoImpl();
			ServletContext application = session.getServletContext();
			@SuppressWarnings("unchecked")
			List<ForumIndexBean> forumIndexBeans = (List<ForumIndexBean>) application
					.getAttribute("forumIndexBeans");
			if (forumIndexBeans == null || forumIndexBeans.size() == 0) {
				application.setAttribute("forumIndexBeans",
						postInfoDaoImpl.getForumIndexBeans());
			} else {
				for (ForumIndexBean forumIndexBean : forumIndexBeans) {
					// 刷新
					postInfoDaoImpl.refreshForumIndexBean(forumIndexBean);
				}
			}
			request.setAttribute("time", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			request.getRequestDispatcher("/jsp/forums/forumsIndex.jsp")
					.forward(request, response);
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

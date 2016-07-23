package com.pocketclass.web.controller.videos;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import com.pocketclass.domain.Comment;
import com.pocketclass.service.impl.CommentServiceImpl;

public class AddCommentServlet extends HttpServlet {

	private static final long serialVersionUID = 201210311948L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String idSeries = request.getParameter("seriesId");
			String content = request.getParameter("content");
			if (content == null) {
				request.getRequestDispatcher(
						"/servlet/SeriesDetailServlet?seriesID=" + idSeries)
						.forward(request, response);
				return;
			} else {
				String c_token = request.getParameter("token");
				HttpSession session = request.getSession(false);
				if (c_token != null
						&& session != null
						&& c_token.equalsIgnoreCase((String) session
								.getAttribute("token"))) {
					session.removeAttribute("token");
					String username = request.getParameter("username");
					Date now = new Date();

					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Comment comment = new Comment();
					try {
						BeanUtils.setProperty(comment, "idVideoSeries",
								idSeries);
						BeanUtils.setProperty(comment, "username", username);
						BeanUtils.setProperty(comment, "content",
								content.trim());
						BeanUtils.setProperty(comment, "time", sdf.format(now));
						CommentServiceImpl service = new CommentServiceImpl();

						service.addComment(comment);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}

			}
			request.getRequestDispatcher(
					"/servlet/SeriesDetailServlet?seriesID=" + idSeries)
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "³ö´íÀ²!");
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

package com.pocketclass.web.controller.videos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.VideoSeries;
import com.pocketclass.service.VideoSeriesService;
import com.pocketclass.service.impl.VideoSeriesServiceImpl;

public class Main extends HttpServlet {

	private static final long serialVersionUID = 201211042210L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// ½â¾öÂÒÂë
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			VideoSeriesService service = new VideoSeriesServiceImpl();
			List<VideoSeries> newestlist = service.getNewestSeries();

			request.setAttribute("newestlist", newestlist);
			request.getRequestDispatcher("/jsp/main.jsp").forward(request,
					response);
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

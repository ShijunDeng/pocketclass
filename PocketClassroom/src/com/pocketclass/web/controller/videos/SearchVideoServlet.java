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
import com.pocketclass.utils.SplitPageUtils;

public class SearchVideoServlet extends HttpServlet {

	private static final long serialVersionUID = 201211012321L;

	private static int currentPage = 1;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			String keyword = request.getParameter("keyword");
			if (request.getMethod().toLowerCase().equals("get")) {
				keyword = new String(keyword.getBytes("ISO8859-1"), "UTF-8");
			}
			VideoSeriesService service = new VideoSeriesServiceImpl();

			{// 2.key的值非空
				SplitPageUtils sp = new SplitPageUtils();
				int rowcount = service.getRowCountByKeyword(keyword);
				if (rowcount != 0) {// 没有找到相关的记录
					sp.setTotalRows(rowcount);

					String option = request.getParameter("option");
					sp.setCurrentPage(currentPage);
					if (option != null) {
						currentPage = sp.confirmPage(option);
					}
				}
				List<VideoSeries> list = service.videoSeriesSearch(keyword, sp);

				request.setAttribute("keyword", keyword);
				request.setAttribute("splitpage", sp);
				request.setAttribute("serieslist", list);
				request.getRequestDispatcher("/jsp/video/coursesshow.jsp")
						.forward(request, response);
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
		this.doGet(request, response);
	}

}

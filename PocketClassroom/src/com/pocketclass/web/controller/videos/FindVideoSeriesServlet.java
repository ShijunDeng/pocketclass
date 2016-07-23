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

public class FindVideoSeriesServlet extends HttpServlet {

	private static final long serialVersionUID = 201210232028L;

	private static int currentPage = 1;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {// 解决中文乱码问题
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			VideoSeriesService service = new VideoSeriesServiceImpl();
			String categoryId = request.getParameter("id");
			if (categoryId == null) {
				// 出错误了
				return;
			}

			SplitPageUtils sp = new SplitPageUtils();
			int rowcount = service.getRowCountById(categoryId);
			if (rowcount != 0) {// 找到相关的记录
				sp.setTotalRows(rowcount);

				String option = request.getParameter("option");
				sp.setCurrentPage(currentPage);
				if (option != null) {
					currentPage = sp.confirmPage(option);
				}
			}
			String categoryName = service.getCategoryNameById(categoryId);
			if (categoryName == null) {
				// 跳转到错误页面
			}
			List<VideoSeries> list = service.findAllById(categoryId, sp);

			request.setAttribute("id", categoryId);
			request.setAttribute("categoryName", categoryName);
			request.setAttribute("splitpage", sp);
			request.setAttribute("serieslist", list);
			request.getRequestDispatcher("/jsp/video/coursesshow.jsp").forward(
					request, response);
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

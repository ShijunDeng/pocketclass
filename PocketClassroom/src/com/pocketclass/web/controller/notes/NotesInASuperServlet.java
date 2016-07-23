package com.pocketclass.web.controller.notes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.Note;
import com.pocketclass.service.NoteService;
import com.pocketclass.service.VideoSeriesService;
import com.pocketclass.service.impl.NoteServiceImpl;
import com.pocketclass.service.impl.VideoSeriesServiceImpl;
import com.pocketclass.utils.SplitPageUtils;

public class NotesInASuperServlet extends HttpServlet {

	private static final long serialVersionUID = 201211082349L;

	private static int currentPage = 1;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idSuper = request.getParameter("idSuper");
			NoteService service = new NoteServiceImpl();

			SplitPageUtils sp = new SplitPageUtils();

			int rowcount = service.getCountInASuper(idSuper);// 得到所有的笔记的总数目
			if (rowcount != 0) {// 找到相关的记录
				sp.setTotalRows(rowcount);

				String option = request.getParameter("option");
				sp.setCurrentPage(currentPage);
				if (option != null) {
					currentPage = sp.confirmPage(option);
				}
			}
			// 在此getCategoryNameById（）方法不应该是属于video一个方面的方法，还应当属于note
			// 应当独立出来的
			VideoSeriesService videoseriesservice = new VideoSeriesServiceImpl();
			String categoryName = videoseriesservice
					.getCategoryNameById(idSuper);
			if (categoryName == null) {
				// 跳转到错误页面
			}
			List<Note> notelist = service.getAllInASuper(idSuper, sp);
			List<Note> hotestlist = service.getHotest();

			request.setAttribute("categoryName", categoryName);
			request.setAttribute("idSuper", idSuper);
			request.setAttribute("splitpage", sp);
			request.setAttribute("hotestlist", hotestlist);
			request.setAttribute("notelist", notelist);
			request.getRequestDispatcher("/jsp/blog/notecenter.jsp").forward(
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

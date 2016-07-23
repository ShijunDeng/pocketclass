package com.pocketclass.web.controller.notes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.Note;
import com.pocketclass.service.NoteService;
import com.pocketclass.service.impl.NoteServiceImpl;
import com.pocketclass.utils.SplitPageUtils;

public class NoteCenterServlet extends HttpServlet {

	private static final long serialVersionUID = 201211081838L;
	private static int currentPage = 1;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			NoteService service = new NoteServiceImpl();
			SplitPageUtils sp = new SplitPageUtils();
			int rowcount = service.getCount();// 得到所有的笔记的总数目
			if (rowcount != 0) {// 找到相关的记录
				sp.setTotalRows(rowcount);

				String option = request.getParameter("option");
				sp.setCurrentPage(currentPage);
				if (option != null) {
					currentPage = sp.confirmPage(option);
				}
			}
			List<Note> notelist = service.getAll(sp);
			List<Note> hotestlist = service.getHotest();

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

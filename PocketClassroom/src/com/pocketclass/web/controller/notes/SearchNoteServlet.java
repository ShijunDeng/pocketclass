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

public class SearchNoteServlet extends HttpServlet {

	private static final long serialVersionUID = 201211100140L;

	private static int currentPage = 1;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			String keyword = request.getParameter("keyword");

			if (keyword == null || keyword.trim().equals("")) {
				// 说明出错误了；应当跳转到错误页面
				return;
			}

			if (request.getMethod().toLowerCase().equals("get")) {
				keyword = new String(keyword.getBytes("ISO8859-1"), "UTF-8");
			}
			keyword = keyword.trim();// 去掉关键字两边的空格

			NoteService service = new NoteServiceImpl();

			SplitPageUtils sp = new SplitPageUtils();

			int rowcount = service.getSearchCount(keyword);// 得到所有的笔记的总数目
			if (rowcount != 0) {// 找到相关的记录
				sp.setTotalRows(rowcount);

				String option = request.getParameter("option");
				sp.setCurrentPage(currentPage);
				if (option != null) {
					currentPage = sp.confirmPage(option);
				}
			}

			List<Note> notelist = service.searchNote(keyword, sp);
			List<Note> hotestlist = service.getHotest();

			request.setAttribute("keyword", keyword);
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

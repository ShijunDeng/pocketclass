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

			int rowcount = service.getCountInASuper(idSuper);// �õ����еıʼǵ�����Ŀ
			if (rowcount != 0) {// �ҵ���صļ�¼
				sp.setTotalRows(rowcount);

				String option = request.getParameter("option");
				sp.setCurrentPage(currentPage);
				if (option != null) {
					currentPage = sp.confirmPage(option);
				}
			}
			// �ڴ�getCategoryNameById����������Ӧ��������videoһ������ķ�������Ӧ������note
			// Ӧ������������
			VideoSeriesService videoseriesservice = new VideoSeriesServiceImpl();
			String categoryName = videoseriesservice
					.getCategoryNameById(idSuper);
			if (categoryName == null) {
				// ��ת������ҳ��
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
			request.setAttribute("message", "������!");
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

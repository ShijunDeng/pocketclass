package com.pocketclass.web.controller.notes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.CustomCategory;
import com.pocketclass.domain.Note;
import com.pocketclass.domain.SpaceInfo;
import com.pocketclass.service.NoteService;
import com.pocketclass.service.impl.NoteServiceImpl;
import com.pocketclass.utils.SplitPageUtils;

public class SearchUserNoteServlet extends HttpServlet {

	private static final long serialVersionUID = 201211100124L;

	private static int currentPage = 1;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String keyword = request.getParameter("keyword");
			NoteService service = new NoteServiceImpl();

			String username = request.getParameter("username");
			if (keyword == null || keyword.trim().equals("")) {
				// �����������ˣ�Ӧ��ת������ҳ��
				return;
			}
			if (request.getMethod().toLowerCase().equals("get")) {
				keyword = new String(keyword.getBytes("ISO8859-1"), "UTF-8");
			}
			keyword = keyword.trim();// ȥ���ؼ������ߵĿհ׷�
			SplitPageUtils sp = new SplitPageUtils();
			int rowcount = service.getUserSearchCount(username, keyword);
			if (rowcount != 0) {// û���ҵ���صļ�¼
				sp.setTotalRows(rowcount);

				String option = request.getParameter("option");
				sp.setCurrentPage(currentPage);
				if (option != null) {
					currentPage = sp.confirmPage(option);
				}
			}
			SpaceInfo spaceinfo = service.getUserSpaceInfo(username);
			List<CustomCategory> customlist = service
					.getUserCustomCategory(username);
			Map<String, Integer> map = service
					.getNoteAmountOrderByMonth(username);
			List<Note> list = service.searchNote(username, keyword, sp);

			request.setAttribute("keyword", keyword);
			request.setAttribute("spaceinfo", spaceinfo);// Ϊ�յĻ��᲻�������??????
			request.setAttribute("customlist", customlist);
			request.setAttribute("yyyyMMmap", map);
			request.setAttribute("splitpage", sp);
			request.setAttribute("notelist", list);
			request.getRequestDispatcher("/jsp/blog/usernotecenter.jsp")
					.forward(request, response);
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

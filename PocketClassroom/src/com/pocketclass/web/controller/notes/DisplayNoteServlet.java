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
import com.pocketclass.utils.TokenProcessor;

public class DisplayNoteServlet extends HttpServlet {

	private static final long serialVersionUID = 201211112023L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String idNote = request.getParameter("idNote");
			if (idNote == null) {
				// ˵��������
				return;
			}
			NoteService service = new NoteServiceImpl();
			Note note = service.getNote(idNote);

			String username = note.getUsername();
			SpaceInfo spaceinfo = service.getUserSpaceInfo(username);
			List<CustomCategory> customlist = service
					.getUserCustomCategory(username);
			Map<String, Integer> map = service
					.getNoteAmountOrderByMonth(username);

			request.setAttribute("spaceinfo", spaceinfo);// Ϊ�յĻ��᲻�������??????
			request.setAttribute("customlist", customlist);
			request.setAttribute("yyyyMMmap", map);
			request.setAttribute("note", note);

			String oper = request.getParameter("oper");
			if (oper != null && oper.equals("edit")) {
				request.getRequestDispatcher("/jsp/blog/editnote.jsp").forward(
						request, response);
			} else {
				// Ϊ�˷�ֹ�û�ͨ��ˢ�µ����ظ��ύ��Ϊ����form����һ�����ƣ��Է�ֹ�ظ��ύ
				TokenProcessor tp = TokenProcessor.getInstance();
				String token = tp.generateToken();
				request.getSession().setAttribute("token", token);

				request.getRequestDispatcher("/jsp/blog/displaynote.jsp")
						.forward(request, response);
			}
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

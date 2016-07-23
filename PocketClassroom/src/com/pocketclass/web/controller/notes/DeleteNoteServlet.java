package com.pocketclass.web.controller.notes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.service.NoteService;
import com.pocketclass.service.impl.NoteServiceImpl;

public class DeleteNoteServlet extends HttpServlet {

	private static final long serialVersionUID = 201211140006L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = (String) request.getSession(false).getAttribute(
					"username");
			String idNote = request.getParameter("idNote");
			if (idNote == null) {
				return;
			}
			NoteService service = new NoteServiceImpl();
			service.deleteNote(idNote);
			request.getRequestDispatcher(
					"/servlet/UserNoteCenterServlet?username=" + username)
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

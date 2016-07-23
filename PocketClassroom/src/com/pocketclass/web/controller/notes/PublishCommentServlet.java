package com.pocketclass.web.controller.notes;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.domain.NoteComment;
import com.pocketclass.service.impl.NoteServiceImpl;
import com.pocketclass.utils.WebUtils;

public class PublishCommentServlet extends HttpServlet {

	private static final long serialVersionUID = 201211180228L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String c_token = request.getParameter("token");
			HttpSession session = request.getSession(false);
			String idNote = request.getParameter("idNote");
			if (c_token != null
					&& session != null
					&& c_token.equalsIgnoreCase((String) session
							.getAttribute("token"))) {
				session.removeAttribute("token");
				String username = request.getParameter("username");
				String parentId = request.getParameter("parentId");
				String content = request.getParameter("content");

				NoteComment noteComm = new NoteComment();

				noteComm.setIdNote(Integer.parseInt(idNote));
				noteComm.setUsername(username);
				noteComm.setParentId(Integer.parseInt(parentId));
				noteComm.setContent(content);
				noteComm.setPostIP(WebUtils.getIpAddr(request));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				noteComm.setTime(sdf.format(new Timestamp(new Date().getTime())));

				NoteServiceImpl service = new NoteServiceImpl();
				service.publishNoteComment(noteComm);
			}
			request.getRequestDispatcher(
					"/servlet/DisplayNoteServlet?idNote=" + idNote).forward(
					request, response);
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

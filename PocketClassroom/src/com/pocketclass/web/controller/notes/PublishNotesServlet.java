package com.pocketclass.web.controller.notes;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.Note;
import com.pocketclass.service.NoteService;
import com.pocketclass.service.impl.NoteServiceImpl;
import com.pocketclass.utils.WebUtils;

public class PublishNotesServlet extends HttpServlet {

	private static final long serialVersionUID = 201211142128L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String username = (String) request.getSession().getAttribute(
					"username");
			if (username == null) {// 说明出错误了
				return;
			}

			Note note = new Note();
			note.setUsername(username);
			note.setTitle(request.getParameter("title"));
			note.setIdSuper(request.getParameter("idSuper"));
			note.setIdCustom(Integer.parseInt(request.getParameter("idCustom")));
			note.setCmtallow(Integer.parseInt(request.getParameter("cmtallow")));
			note.setContent(request.getParameter("content"));
			// String tags = request.getParameter("tags"); 标签暂时不用
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			note.setAddTime(sdf.format(new Timestamp(new Date().getTime())));
			note.setUpdateTime(sdf.format(new Timestamp(new Date().getTime())));
			note.setPostIP(WebUtils.getIpAddr(request));
			NoteService service = new NoteServiceImpl();
			service.insertNote(note);

			request.getRequestDispatcher(
					"/servlet/UserNoteCenterServlet?username=" + username)
					.forward(request, response);
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

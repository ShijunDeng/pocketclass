package com.pocketclass.web.controller.notes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.CustomCategory;
import com.pocketclass.domain.SpaceInfo;
import com.pocketclass.service.NoteService;
import com.pocketclass.service.impl.NoteServiceImpl;

public class MakeNotesServlet extends HttpServlet {

	private static final long serialVersionUID = 201211110232L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = (String) request.getSession().getAttribute(
					"username");
			if (username == null) {
				// 出错误了
				return;
			}
			NoteService service = new NoteServiceImpl();

			SpaceInfo spaceinfo = service.getUserSpaceInfo(username);
			List<CustomCategory> customlist = service
					.getUserCustomCategory(username);
			Map<String, Integer> map = service
					.getNoteAmountOrderByMonth(username);

			request.setAttribute("spaceinfo", spaceinfo);// 为空的话会不会出错误??????
			request.setAttribute("yyyyMMmap", map);
			request.setAttribute("customlist", customlist);
			request.getRequestDispatcher("/jsp/blog/makenotes.jsp").forward(
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

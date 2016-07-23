package com.pocketclass.web.controller.videos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.Video;
import com.pocketclass.service.VideoService;
import com.pocketclass.service.impl.VideoServiceImpl;

public class VideoPlayServlet extends HttpServlet {

	private static final long serialVersionUID = 201211031023L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// �������
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			String seriesId = request.getParameter("seriesId");
			String idVideo = request.getParameter("videoId");

			if (seriesId != null && idVideo != null) {
				VideoService service = new VideoServiceImpl();
				List<Video> videolist = service.findAllInASeries(seriesId);
				Video video = service.findById(idVideo);

				if (video == null) {
					// ��ʾ��������
				}
				request.setAttribute("seriesId", seriesId);
				request.setAttribute("videolist", videolist);
				request.setAttribute("video", video);

				request.getRequestDispatcher("/jsp/video/videoplay.jsp")
						.forward(request, response);
			} else {
				// ��ʾ��������
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

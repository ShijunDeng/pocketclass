package com.pocketclass.web.controller.videos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.domain.Video;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.service.VideoSeriesService;
import com.pocketclass.service.impl.VideoSeriesServiceImpl;
import com.pocketclass.service.impl.VideoServiceImpl;

public class VideoDownLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 201210290101L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username == null || username.trim().equals("")) {
				request.setAttribute("error", "请先登录后再进行操作");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			String option = request.getParameter("option");
			if (option != null && "".equals(option) == false) {
				String idVideoSeries = request.getParameter("id");
				VideoServiceImpl videoService = new VideoServiceImpl();
				List<Video> videos = videoService
						.findAllInASeries(idVideoSeries);
				String path = "E:\\pocketclass\\videos\\"
						+ videos.get(0).getLinkAddress();
				String filename = path.substring(path.lastIndexOf("\\") + 1);
				InputStream in = null;
				OutputStream out = null;
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(filename, "UTF-8"));
				try {
					in = new FileInputStream(path);
					int len = 0;
					byte buffer[] = new byte[1024];
					out = response.getOutputStream();
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
				} finally {
					if (in != null) {
						try {

							in.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
			VideoSeriesService service = new VideoSeriesServiceImpl();
			Map<String, List<VideoSeries>> map = service.getAllGroupBySuper();
			request.setAttribute("all", map);
			request.getRequestDispatcher("/jsp/video/download.jsp").forward(
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

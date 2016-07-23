package com.pocketclass.web.controller.videos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.customer.CourseFavoDaoImpl;
import com.pocketclass.domain.Comment;
import com.pocketclass.domain.Middle;
import com.pocketclass.domain.Super;
import com.pocketclass.domain.Video;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.service.VideoSeriesService;
import com.pocketclass.service.VideoService;
import com.pocketclass.service.impl.CommentServiceImpl;
import com.pocketclass.service.impl.VideoSeriesServiceImpl;
import com.pocketclass.service.impl.VideoServiceImpl;
import com.pocketclass.utils.TokenProcessor;

public class SeriesDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 201210242205L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			String seriesID = request.getParameter("seriesID");

			request.setAttribute("exist",
					new CourseFavoDaoImpl().exist(username, seriesID));

			VideoSeriesService seriesservice = new VideoSeriesServiceImpl();
			VideoSeries series = seriesservice.findByID(seriesID);
			Super sup = seriesservice.getSuperBySeriesID(seriesID);
			if (sup == null) {
				// 跳转到错误页面
				return;
			}
			List<Middle> list = seriesservice.getMiddleBySuper(seriesID);
			List<VideoSeries> seriesList = seriesservice
					.getRelativeSeries(seriesID);

			CommentServiceImpl commentseries = new CommentServiceImpl();
			List<Comment> commentlist = commentseries
					.getCommentsBySeriesID(seriesID);

			VideoService service = new VideoServiceImpl();
			List<Video> videolist = service.findAllInASeries(seriesID);
			request.setAttribute("serieslist", seriesList);
			request.setAttribute("super", sup);
			request.setAttribute("middlelist", list);
			request.setAttribute("series", series);
			request.setAttribute("videolist", videolist);
			request.setAttribute("commentlist", commentlist);

			// 为了防止用户通过刷新导致重复提交，为评论form产生一个令牌，以防止重复提交
			TokenProcessor tp = TokenProcessor.getInstance();
			String token = tp.generateToken();
			request.getSession().setAttribute("token", token);

			request.getRequestDispatcher("/jsp/video/videoseriesDetail.jsp")
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

package com.pocketclass.web.controller.forums;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.forums.PostDaoImpl;
import com.pocketclass.dao.impl.forums.PostInfoDaoImpl;
import com.pocketclass.dao.impl.forums.PostsPageDaoImpl;
import com.pocketclass.domain.post.Post;

/**
 * 呈现某一类的交流情况
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumsServlet extends HttpServlet {

	private static final long serialVersionUID = 201211161027L;
	private int currentPage;// 当前页
	private final int PAGERECORDSNUM = 10;// 每页显示的记录的条数
	final int pageTagsNum = 10;// 显示页面链接个数（类似百度下面的123456...20）
	private int startPageTag;// 显示页面链接的其起始
	private int endPageTag;
	private int pageCount;

	@Override
	public void init() throws ServletException {
		this.currentPage = 1;
		this.startPageTag = 1;
		this.endPageTag = this.startPageTag + this.pageTagsNum - 1;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username != null || "".equals(username)) {
				request.setAttribute("lastestLoginTime", "2012-11-16 12:06:12");
			}
			String idSuper = "";
			String idMiddle = request.getParameter("idMiddle");
			if (idMiddle != null && "".equals(idMiddle) == false) {
				idSuper = new PostDaoImpl().getIdSuperByIdMiddle(idMiddle);
				session.setAttribute("cateGory",
						new PostDaoImpl().getCateGoryByIdSuper(idSuper));
				session.setAttribute("idSuper", idSuper);
			} else {
				idSuper = request.getParameter("idSuper");
				session.setAttribute("cateGory",
						new PostDaoImpl().getCateGoryByIdSuper(idSuper));
				session.setAttribute("idSuper", idSuper);
			}
			if (idSuper == null || "".equals(idSuper)) {
				idSuper = "S01";// 篡改URL参数将自动跳转到S01类
			}
			ServletContext application = session.getServletContext();
			// 发表帖子时要选择的分类
			@SuppressWarnings("unchecked")
			Map<String, String> middlesOfSuper = (Map<String, String>) application
					.getAttribute(idSuper);
			if (middlesOfSuper == null || middlesOfSuper.size() == 0) {// 还没有就从数据中读取
				// System.out.println("执行了");
				middlesOfSuper = new PostInfoDaoImpl()
						.getMiddleOfSuper(idSuper);
				application.setAttribute(idSuper, middlesOfSuper);
			}
			request.setAttribute("middlesOfSuper", middlesOfSuper);
			PostsPageDaoImpl postsPageDaoImpl = new PostsPageDaoImpl();
			postsPageDaoImpl.setPageRecordsNum(PAGERECORDSNUM);
			String option = request.getParameter("option");
			// 计算（查询）共有多少页
			this.pageCount = postsPageDaoImpl.getTotalPagesNum(idSuper);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("recordNums",
					postsPageDaoImpl.getRecordsNum(idSuper));

			if (option != null && "".equals(option) == false) {
				if (this.pageCount > 0) {
					if (option.trim().equals("next")) {// 下一页
						if (currentPage < pageCount)
							currentPage++;
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));

					} else if (option.trim().equals("prior")) {// 上一页
						if (currentPage > 1)
							currentPage--;
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));
					} else if (option.trim().equals("first")) {// 首页
						currentPage = 1;
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));
					} else if (option.trim().equals("end")) {// 尾页
						currentPage = pageCount;
						request.setAttribute("posts",
								postsPageDaoImpl.getPageData(pageCount,
										PAGERECORDSNUM, idSuper));
					} else if (option.trim().equals("jump")) {// 跳跃
						currentPage = Integer.parseInt(request
								.getParameter("page"));
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));
					}
				}
				if (option.trim().equals("newpost")) {
					if (username == null || "".equals(username)) {
						request.setAttribute("error", "请先登录后再进行操作");
						request.getRequestDispatcher("/jsp/login/login.jsp")
								.forward(request, response);
						return;
					}
					String content = request.getParameter("content");
					String selectedOption = request
							.getParameter("selectedOption");
					String title = request.getParameter("title");
					idSuper = new PostDaoImpl()
							.getIdSuperByIdMiddle(selectedOption);
					new PostDaoImpl().addNewPost(this.createPost(username,
							title, content, selectedOption));
					request.setAttribute("posts", postsPageDaoImpl.getPageData(
							currentPage, PAGERECORDSNUM, idSuper));
				}
			} else {
				request.setAttribute("posts", postsPageDaoImpl.getPageData(
						currentPage, PAGERECORDSNUM, idSuper));
			}
			this.startPageTag = this.currentPage - this.pageTagsNum / 2 > 0 ? this.currentPage
					- this.pageTagsNum / 2
					: 1;
			this.endPageTag = this.currentPage + this.pageTagsNum / 2 < this.pageCount ? this.currentPage
					+ this.pageTagsNum / 2
					: this.pageCount;
			request.setAttribute("startPageTag", this.startPageTag);
			request.setAttribute("endPageTag", this.endPageTag);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("/jsp/forums/display.jsp").forward(
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

		doGet(request, response);

	}

	/**
	 * 
	 * @param username
	 * @param content
	 * @return 以发帖人username和内容content钩造一个帖子
	 */
	private Post createPost(String username, String title, String content,
			String idMiddle) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Post newPost = new Post();
		newPost.setUsername(username);
		newPost.setTitle(title);
		newPost.setBrowNum(0);
		newPost.setReplyNum(0);
		newPost.setContent(content);
		newPost.setIdMiddle(idMiddle);
		newPost.setPostTime(sdf.format(new Date()));
		return newPost;
	}
}

package com.pocketclass.web.controller.forums;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.forums.ForumListPageDaoImpl;
import com.pocketclass.dao.impl.forums.PostDaoImpl;
import com.pocketclass.domain.post.ForumListElement;
import com.pocketclass.domain.post.Post;
import com.pocketclass.domain.post.PostReply;

/**
 * 显示帖子请求处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumsListServlet extends HttpServlet {

	private static final long serialVersionUID = 201211162004L;
	private int currentPage;// 当前页
	private final int PAGERECORDSNUM = 5;// 每页显示的记录的条数
	final int pageTagsNum = 10;// 显示页面链接个数（类似百度下面的123456...20）
	private int startPageTag;// 显示页面链接的其起始
	private int endPageTag;
	private int pageCount;
	private ArrayList<ForumListElement> forumListElements = null;
	Post post = null;
	String idPost;

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
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username != null || "".equals(username)) {
				request.setAttribute("lastestLoginTime", "2012-11-16 12:06:12");
			}
			String aIdPost = request.getParameter("idPost");
			if (aIdPost != null) {
				this.idPost = aIdPost;
				new PostDaoImpl().addBrowNum(aIdPost);// 浏览次数加一
			}
			String option = request.getParameter("option");
			ForumListPageDaoImpl forumListPageDaoImpl = new ForumListPageDaoImpl();
			forumListPageDaoImpl.setPageRecordsNum(PAGERECORDSNUM);
			// 计算（查询）共有多少页
			this.pageCount = forumListPageDaoImpl.getTotalPagesNum(idPost);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("recordNums",
					forumListPageDaoImpl.getRecordsNum(idPost));
			post = new PostDaoImpl().getPostByPostID(idPost);
			request.setAttribute("post", post);

			if (option != null && "".equals(option) == false) {
				// 处理分页
				if (this.pageCount > 0) {
					if (option.trim().equals("next")) {// 下一页
						if (currentPage < pageCount)
							currentPage++;
					} else if (option.trim().equals("prior")) {// 上一页
						if (currentPage > 1)
							currentPage--;
					} else if (option.trim().equals("first")) {// 首页
						currentPage = 1;
					} else if (option.trim().equals("end")) {// 尾页
						currentPage = pageCount;
						forumListElements = forumListPageDaoImpl.getPageData(
								pageCount, PAGERECORDSNUM, idPost);
					} else if (option.trim().equals("jump")) {// 跳跃
						currentPage = Integer.parseInt(request
								.getParameter("page"));

					}
				}
				if (option.trim().equals("newmpost")
						|| option.trim().equals("newlpost")) {
					if (username == null || "".equals(username)) {
						request.setAttribute("error", "请先登录后再进行操作");
						request.getRequestDispatcher("/jsp/login/login.jsp")
								.forward(request, response);
						return;
					}
					String content = request.getParameter("content");
					if (option.trim().equals("newmpost")) {// 二级帖
						new PostDaoImpl().addNewMPostReply(this
								.createPostReply(username, content,
										post.getIdPost()));
					} else if (option.trim().equals("newlpost")) {// 三级帖
						String parentPostId = request
								.getParameter("parentPostId");
						String parentPostUsername = request
								.getParameter("parentPostUsername");
						if (parentPostUsername != null) {// 避免地一个三级贴出现：pocketclass01
															// 回复了：null 又发帖了
							new PostDaoImpl().addNewLPostReply(this
									.createPostReply(username, "回复了："
											+ parentPostUsername
											+ "&nbsp;&nbsp;&nbsp;" + content,
											parentPostId));
						} else {
							new PostDaoImpl().addNewLPostReply(this
									.createPostReply(username, content,
											parentPostId));
						}
					}
				}
			}
			forumListElements = forumListPageDaoImpl.getPageData(currentPage,
					PAGERECORDSNUM, idPost);
			request.setAttribute("forumListElements", forumListElements);
			this.startPageTag = this.currentPage - this.pageTagsNum / 2 > 0 ? this.currentPage
					- this.pageTagsNum / 2
					: 1;
			this.endPageTag = this.currentPage + this.pageTagsNum / 2 < this.pageCount ? this.currentPage
					+ this.pageTagsNum / 2
					: this.pageCount;
			request.setAttribute("startPageTag", this.startPageTag);
			request.setAttribute("endPageTag", this.endPageTag);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("/jsp/forums/forumsList.jsp").forward(
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
	private PostReply createPostReply(String username, String content,
			String parentPostId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PostReply newMPostReply = new PostReply();
		newMPostReply.setUsername(username);
		newMPostReply.setTitle(post.getTitle());
		newMPostReply.setBrowNum(0);
		newMPostReply.setReplyNum(0);
		newMPostReply.setContent(content);
		newMPostReply.setParentPostId(parentPostId);
		newMPostReply.setPostTime(sdf.format(new Date()));
		return newMPostReply;
	}

}

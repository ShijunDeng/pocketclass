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
 * ����ĳһ��Ľ������
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumsServlet extends HttpServlet {

	private static final long serialVersionUID = 201211161027L;
	private int currentPage;// ��ǰҳ
	private final int PAGERECORDSNUM = 10;// ÿҳ��ʾ�ļ�¼������
	final int pageTagsNum = 10;// ��ʾҳ�����Ӹ��������ưٶ������123456...20��
	private int startPageTag;// ��ʾҳ�����ӵ�����ʼ
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
				idSuper = "S01";// �۸�URL�������Զ���ת��S01��
			}
			ServletContext application = session.getServletContext();
			// ��������ʱҪѡ��ķ���
			@SuppressWarnings("unchecked")
			Map<String, String> middlesOfSuper = (Map<String, String>) application
					.getAttribute(idSuper);
			if (middlesOfSuper == null || middlesOfSuper.size() == 0) {// ��û�оʹ������ж�ȡ
				// System.out.println("ִ����");
				middlesOfSuper = new PostInfoDaoImpl()
						.getMiddleOfSuper(idSuper);
				application.setAttribute(idSuper, middlesOfSuper);
			}
			request.setAttribute("middlesOfSuper", middlesOfSuper);
			PostsPageDaoImpl postsPageDaoImpl = new PostsPageDaoImpl();
			postsPageDaoImpl.setPageRecordsNum(PAGERECORDSNUM);
			String option = request.getParameter("option");
			// ���㣨��ѯ�����ж���ҳ
			this.pageCount = postsPageDaoImpl.getTotalPagesNum(idSuper);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("recordNums",
					postsPageDaoImpl.getRecordsNum(idSuper));

			if (option != null && "".equals(option) == false) {
				if (this.pageCount > 0) {
					if (option.trim().equals("next")) {// ��һҳ
						if (currentPage < pageCount)
							currentPage++;
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));

					} else if (option.trim().equals("prior")) {// ��һҳ
						if (currentPage > 1)
							currentPage--;
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));
					} else if (option.trim().equals("first")) {// ��ҳ
						currentPage = 1;
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));
					} else if (option.trim().equals("end")) {// βҳ
						currentPage = pageCount;
						request.setAttribute("posts",
								postsPageDaoImpl.getPageData(pageCount,
										PAGERECORDSNUM, idSuper));
					} else if (option.trim().equals("jump")) {// ��Ծ
						currentPage = Integer.parseInt(request
								.getParameter("page"));
						request.setAttribute("posts", postsPageDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										idSuper));
					}
				}
				if (option.trim().equals("newpost")) {
					if (username == null || "".equals(username)) {
						request.setAttribute("error", "���ȵ�¼���ٽ��в���");
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
			request.setAttribute("message", "������!");
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
	 * @return �Է�����username������content����һ������
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

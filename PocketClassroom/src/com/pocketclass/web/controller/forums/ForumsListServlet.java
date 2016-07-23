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
 * ��ʾ����������
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumsListServlet extends HttpServlet {

	private static final long serialVersionUID = 201211162004L;
	private int currentPage;// ��ǰҳ
	private final int PAGERECORDSNUM = 5;// ÿҳ��ʾ�ļ�¼������
	final int pageTagsNum = 10;// ��ʾҳ�����Ӹ��������ưٶ������123456...20��
	private int startPageTag;// ��ʾҳ�����ӵ�����ʼ
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
				new PostDaoImpl().addBrowNum(aIdPost);// ���������һ
			}
			String option = request.getParameter("option");
			ForumListPageDaoImpl forumListPageDaoImpl = new ForumListPageDaoImpl();
			forumListPageDaoImpl.setPageRecordsNum(PAGERECORDSNUM);
			// ���㣨��ѯ�����ж���ҳ
			this.pageCount = forumListPageDaoImpl.getTotalPagesNum(idPost);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("recordNums",
					forumListPageDaoImpl.getRecordsNum(idPost));
			post = new PostDaoImpl().getPostByPostID(idPost);
			request.setAttribute("post", post);

			if (option != null && "".equals(option) == false) {
				// �����ҳ
				if (this.pageCount > 0) {
					if (option.trim().equals("next")) {// ��һҳ
						if (currentPage < pageCount)
							currentPage++;
					} else if (option.trim().equals("prior")) {// ��һҳ
						if (currentPage > 1)
							currentPage--;
					} else if (option.trim().equals("first")) {// ��ҳ
						currentPage = 1;
					} else if (option.trim().equals("end")) {// βҳ
						currentPage = pageCount;
						forumListElements = forumListPageDaoImpl.getPageData(
								pageCount, PAGERECORDSNUM, idPost);
					} else if (option.trim().equals("jump")) {// ��Ծ
						currentPage = Integer.parseInt(request
								.getParameter("page"));

					}
				}
				if (option.trim().equals("newmpost")
						|| option.trim().equals("newlpost")) {
					if (username == null || "".equals(username)) {
						request.setAttribute("error", "���ȵ�¼���ٽ��в���");
						request.getRequestDispatcher("/jsp/login/login.jsp")
								.forward(request, response);
						return;
					}
					String content = request.getParameter("content");
					if (option.trim().equals("newmpost")) {// ������
						new PostDaoImpl().addNewMPostReply(this
								.createPostReply(username, content,
										post.getIdPost()));
					} else if (option.trim().equals("newlpost")) {// ������
						String parentPostId = request
								.getParameter("parentPostId");
						String parentPostUsername = request
								.getParameter("parentPostUsername");
						if (parentPostUsername != null) {// �����һ�����������֣�pocketclass01
															// �ظ��ˣ�null �ַ�����
							new PostDaoImpl().addNewLPostReply(this
									.createPostReply(username, "�ظ��ˣ�"
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

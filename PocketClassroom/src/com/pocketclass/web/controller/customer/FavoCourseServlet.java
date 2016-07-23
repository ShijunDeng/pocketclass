package com.pocketclass.web.controller.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.customer.CourseFavoDaoImpl;

/**
 * 
 * �û�ϵ�пγ��ղ�
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-27 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class FavoCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 201210272042L;
	private int currentPage;// ��ǰҳ
	private final int PAGERECORDSNUM = 5;// ÿҳ��ʾ�ļ�¼������
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
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "���ȵ�¼���ٽ��в���");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}

			CourseFavoDaoImpl courseFavoDaoLmpl = new CourseFavoDaoImpl();
			courseFavoDaoLmpl.setPageRecordsNum(PAGERECORDSNUM);
			String option = request.getParameter("option");
			// ���㣨��ѯ�����ж���ҳ
			this.pageCount = courseFavoDaoLmpl.getTotalPagesNum(username);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("recordNums",
					courseFavoDaoLmpl.getRecordsNum(username));

			if (option != null && option.trim().equals("") == false
					&& this.pageCount > 0) {
				if (option.trim().equals("next")) {// ��һҳ
					if (currentPage < pageCount)
						currentPage++;
					request.setAttribute("courseFavorites", courseFavoDaoLmpl
							.getPageData(currentPage, PAGERECORDSNUM, username));

				} else if (option.trim().equals("prior")) {// ��һҳ
					if (currentPage > 1)
						currentPage--;
					request.setAttribute("courseFavorites", courseFavoDaoLmpl
							.getPageData(currentPage, PAGERECORDSNUM, username));
				} else if (option.trim().equals("first")) {// ��ҳ
					currentPage = 1;
					request.setAttribute("courseFavorites", courseFavoDaoLmpl
							.getPageData(currentPage, PAGERECORDSNUM, username));
				} else if (option.trim().equals("end")) {// βҳ
					currentPage = pageCount;
					request.setAttribute("courseFavorites", courseFavoDaoLmpl
							.getPageData(pageCount, PAGERECORDSNUM, username));
				} else if (option.trim().equals("jump")) {// ��Ծ
					currentPage = Integer
							.parseInt(request.getParameter("page"));
					request.setAttribute("courseFavorites", courseFavoDaoLmpl
							.getPageData(currentPage, PAGERECORDSNUM, username));
				} else if (option.trim().equals("delete")) {// ɾ��
					String idVideoSeries = request
							.getParameter("idVideoSeries");
					if (idVideoSeries != null
							&& idVideoSeries.trim().equals("") == false) {
						if (courseFavoDaoLmpl.delete(username, idVideoSeries) == true) {
							request.setAttribute("courseFavorites",
									courseFavoDaoLmpl.getPageData(currentPage,
											PAGERECORDSNUM, username));
						} else {
							request.setAttribute("message", "����ʧ��!");
							request.getRequestDispatcher(
									"/jsp/message_jsp/errorMessage.jsp")
									.forward(request, response);
						}
					}
				} else if (option.trim().equals("flush")) {// ���
					if (courseFavoDaoLmpl.delete(username) == true) {
						request.setAttribute("courseFavorites", null);
					} else {
						request.setAttribute("message", "����ʧ��!");
						request.getRequestDispatcher(
								"/jsp/message_jsp/errorMessage.jsp").forward(
								request, response);
					}
				}
			} else {
				request.setAttribute("courseFavorites",
						courseFavoDaoLmpl.CurrentPageData(username));
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
			request.getRequestDispatcher("/jsp/customer/favoCourse.jsp")
					.forward(request, response);
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

}

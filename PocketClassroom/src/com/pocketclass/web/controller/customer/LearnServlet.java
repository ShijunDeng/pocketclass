package com.pocketclass.web.controller.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.customer.LearnDaoImpl;
import com.pocketclass.dao.impl.customer.MessageDaoImpl;

/**
 * 
 * 学习中心
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-29 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class LearnServlet extends HttpServlet {
	private static final long serialVersionUID = 201210292254L;

	private int currentPage;// 当前页
	private final int PAGERECORDSNUM = 5;// 每页显示的记录的条数
	final int pageTagsNum = 10;// 显示页面链接个数
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String username = (String) session.getAttribute("username");
		if (username == null || "".equals(username)) {
			request.setAttribute("error", "请登录后再进行操作!");
			request.getRequestDispatcher("/jsp/login/login.jsp").forward(
					request, response);
			this.destroy();
			return;
		}
		LearnDaoImpl learnDaoImpl = new LearnDaoImpl();
		// 标记查询学完或者正在学
		String isEnd = request.getParameter("isEnd");
		learnDaoImpl.setIsEnd(isEnd);

		learnDaoImpl.setPageRecordsNum(PAGERECORDSNUM);
		String option = request.getParameter("option");
		// 计算（查询）共有多少页
		this.pageCount = learnDaoImpl.getTotalPagesNum(username);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("recordNums", learnDaoImpl.getRecordsNum(username));
		response.setContentType("text/plain");
		session.setAttribute("unReadNum",new MessageDaoImpl().getUnReadMessageNum(username));
		if (option != null && option.trim().equals("") == false
				&& this.pageCount > 0) {
			 if (option.trim().equals("next")) {// 下一页
				if (currentPage < pageCount)
					currentPage++;
				request.setAttribute("learnProgresses", learnDaoImpl
						.getPageData(currentPage, PAGERECORDSNUM, username));

			} else if (option.trim().equals("prior")) {// 上一页
				if (currentPage > 1)
					currentPage--;
				request.setAttribute("learnProgresses", learnDaoImpl
						.getPageData(currentPage, PAGERECORDSNUM, username));
			} else if (option.trim().equals("first")) {// 首页
				currentPage = 1;
				request.setAttribute("learnProgresses", learnDaoImpl
						.getPageData(currentPage, PAGERECORDSNUM, username));
			} else if (option.trim().equals("end")) {// 尾页
				currentPage = pageCount;
				request.setAttribute("learnProgresses", learnDaoImpl
						.getPageData(pageCount, PAGERECORDSNUM, username));
			} else if (option.trim().equals("jump")) {// 跳跃
				currentPage = Integer.parseInt(request.getParameter("page"));
				request.setAttribute("learnProgresses", learnDaoImpl
						.getPageData(currentPage, PAGERECORDSNUM, username));
			} else if (option.trim().equals("changeToLearn")) {// 标记为未学完（重新开始学习）
				String idVideoSeries = request.getParameter("idVideoSeries");
				if (idVideoSeries != null
						&& idVideoSeries.trim().equals("") == false) {
					if (learnDaoImpl.delete(username, idVideoSeries) == true
							&& learnDaoImpl.add(username, idVideoSeries)) {
						request.setAttribute("learnProgresses", learnDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										username));
					} else {
						request.setAttribute("message", "操作失败!");
						request.getRequestDispatcher(
								"/jsp/message_jsp/errorMessage.jsp").forward(
								request, response);
					}
				}
			} else if (option.trim().equals("changeToLearned")) {// 标记为学完
				String idVideoSeries = request.getParameter("idVideoSeries");
				if (idVideoSeries != null
						&& idVideoSeries.trim().equals("") == false) {
					if (learnDaoImpl.changeLearnState(username, idVideoSeries,
							"YES") == true) {
						request.setAttribute("learnProgresses", learnDaoImpl
								.getPageData(currentPage, PAGERECORDSNUM,
										username));
					} else {
						request.setAttribute("message", "操作失败!");
						request.getRequestDispatcher(
								"/jsp/message_jsp/errorMessage.jsp").forward(
								request, response);
					}
				}
			} else if (option.trim().equals("flush")) {// 清空
				if (learnDaoImpl.delete(username) == true) {
					request.setAttribute("learnProgresses", null);
				} else {
					request.setAttribute("message", "操作失败!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
				}
			}
		} else {
			request.setAttribute("learnProgresses",
					learnDaoImpl.CurrentPageData(username));
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
		if(isEnd==null){
			request.setAttribute("message", "出错啦，你请求的页面不存在!");
			request.getRequestDispatcher("/jsp/message_jsp/errorMessage.jsp").forward(
					request, response);
			return;
		}
		else if (isEnd.trim().equals("NO"))
			request.getRequestDispatcher("/jsp/customer/learn.jsp").forward(
					request, response);
		else
			request.getRequestDispatcher("/jsp/customer/learned.jsp").forward(
					request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.pocketclass.web.controller.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.customer.MessageDaoImpl;
import com.pocketclass.domain.message.Message;

/**
 * վ��ͨ����Ϣ����
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-06 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class MsgServlet extends HttpServlet {

	private static final long serialVersionUID = 201211060946L;
	private int currentPage;// ��ǰҳ
	private final int PAGERECORDSNUM = 5;// ÿҳ��ʾ����Ϣ����
	final int pageTagsNum = 10;// ��ʾҳ����Ϣ���������ưٶ������123456...20��
	private int startPageTag;// ��ʾҳ����Ϣ����������ʼ
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
			MessageDaoImpl msgDapImpl = new MessageDaoImpl();
			msgDapImpl.setPageRecordsNum(PAGERECORDSNUM);
			String option = request.getParameter("option");
			String idLoca = request.getParameter("idLoca");
			// ���㣨��ѯ�����ж���ҳ

			this.pageCount = msgDapImpl.getTotalPagesNum(username, idLoca);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("recordNums",
					msgDapImpl.getRecordsNum(username, idLoca));

			if (((option == null || option.trim().equals("")) && (idLoca == null || idLoca
					.trim().equals("")))) {
				request.setAttribute("message", "���������������ҳ�治����!");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			if (option != null && "".equals(option) == false
					&& this.pageCount > 0) {
				if (option.trim().equals("next")) {// ��һҳ
					if (currentPage < pageCount)
						currentPage++;
					request.setAttribute("msgs", msgDapImpl.getPageData(
							username, idLoca, currentPage, PAGERECORDSNUM));

				} else if (option.trim().equals("prior")) {// ��һҳ
					if (currentPage > 1)
						currentPage--;
					request.setAttribute("msgs", msgDapImpl.getPageData(
							username, idLoca, currentPage, PAGERECORDSNUM));
				} else if (option.trim().equals("first")) {// ��ҳ
					currentPage = 1;
					request.setAttribute("msgs", msgDapImpl.getPageData(
							username, idLoca, currentPage, PAGERECORDSNUM));
				} else if (option.trim().equals("end")) {// βҳ
					currentPage = pageCount;
					request.setAttribute("msgs", msgDapImpl.getPageData(
							username, idLoca, pageCount, PAGERECORDSNUM));
				} else if (option.trim().equals("jump")) {// ��Ծ
					currentPage = Integer
							.parseInt(request.getParameter("page"));
					request.setAttribute("msgs", msgDapImpl.getPageData(
							username, idLoca, currentPage, PAGERECORDSNUM));
				} else if (option.trim().equals("flush")) {// ���
					if (msgDapImpl.deleteMsgByLocationId(username, idLoca) == true) {
						request.setAttribute("msgs", null);
					} else {
						request.setAttribute("message", "����ʧ��!");
						request.getRequestDispatcher(
								"/jsp/message_jsp/errorMessage.jsp").forward(
								request, response);
					}

				} else {//
					String[] checkedMsgsStrArr = request
							.getParameterValues("uuids");
					if (checkedMsgsStrArr != null) {
						if (option.trim().equals("toSpambox")) {
							for (String checkedMsgsStr : checkedMsgsStrArr) {
								if (msgDapImpl.messageOperate(username,
										this.stringToMesssage(checkedMsgsStr),
										idLoca, "004") == false) {
									request.setAttribute("message", "����ʧ��!");
									request.getRequestDispatcher(
											"/jsp/message_jsp/errorMessage.jsp")
											.forward(request, response);
									return;
								}
							}
						} else if (option.trim().equals("toFavobox")) {
							for (String checkedMsgsStr : checkedMsgsStrArr) {
								if (msgDapImpl.messageOperate(username,
										this.stringToMesssage(checkedMsgsStr),
										idLoca, "003") == false) {
									request.setAttribute("message", "����ʧ��!");
									request.getRequestDispatcher(
											"/jsp/message_jsp/errorMessage.jsp")
											.forward(request, response);
									return;
								}
							}
						} else if (option.trim().equals("delete")) {
							for (String checkedMsgsStr : checkedMsgsStrArr) {
								if (msgDapImpl.deleteMsgByLocationId(username,
										this.stringToMesssage(checkedMsgsStr),
										idLoca) == false) {
									request.setAttribute("message", "����ʧ��!");
									request.getRequestDispatcher(
											"/jsp/message_jsp/errorMessage.jsp")
											.forward(request, response);
									return;
								}
							}
						}

					}
					request.setAttribute("msgs", msgDapImpl.getPageData(
							username, idLoca, currentPage, PAGERECORDSNUM));
				}
			} else {
				// idLoca = "001";// ��һ�������ִ��001����������
				this.pageCount = msgDapImpl.getTotalPagesNum(username, idLoca);
				request.setAttribute("msgs",
						msgDapImpl.CurrentPageData(username, idLoca));
				request.setAttribute("pageCount", pageCount);
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
			forward(request, response, idLoca);
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

	private void forward(HttpServletRequest request,
			HttpServletResponse response, String idLoca)
			throws ServletException, IOException {
		if (idLoca == null || idLoca.isEmpty()) {
			return;
		}
		if (idLoca.trim().equals("000")) {// ������Ϣ
			request.getRequestDispatcher("/jsp/customer/newMsg.jsp").forward(
					request, response);
			return;
		} else if (idLoca.trim().equals("001")) {// ������
			request.getRequestDispatcher("/jsp/customer/msg.jsp").forward(
					request, response);
			return;
		} else if (idLoca.trim().equals("002")) {// ������
			request.getRequestDispatcher("/jsp/customer/outbox.jsp").forward(
					request, response);
			return;
		} else if (idLoca.trim().equals("003")) {// �ղ���
			request.getRequestDispatcher("/jsp/customer/favobox.jsp").forward(
					request, response);
			return;
		} else if (idLoca.trim().equals("004")) {// ������
			request.getRequestDispatcher("/jsp/customer/spambox.jsp").forward(
					request, response);
			return;
		} else {
			request.setAttribute("message", "���������������ҳ�治����!");
			request.getRequestDispatcher("/jsp/message_jsp/errorMessage.jsp")
					.forward(request, response);
		}

	}

	private Message stringToMesssage(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		Message msg = new Message();
		String[] strArr = str.split("&");
		msg.setSendUserName(strArr[0]);
		msg.setSendTime(strArr[1]);
		return msg;
	}
}

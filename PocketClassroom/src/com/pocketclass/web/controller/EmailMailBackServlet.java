package com.pocketclass.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.utils.EncryptionUtils;

/**
 * �������䷵����֤
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-02 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class EmailMailBackServlet extends HttpServlet {

	private static final long serialVersionUID = 201211022221L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// ����HttpSession
			// session=request.getSession(false);����ΪҪͨ��session����message��Ϣ
			// �����´�����session(���û����ǵ�һ�ε����֤���ӵ�ʱ����´���session)����û�з�װ��֤
			// ��Ϣ�ģ���˿���ͨ��ranDomId�Ƿ�Ϊ�����ж��ǲ�����֤����
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "���ȵ�¼���ٽ��в���");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			String emailId = request.getParameter("emailId").replace(' ', '+');// �õ��ʺź�����ܵ�emailId
			String eRandomId = (String) session.getAttribute("eRandomId");
			if (eRandomId == null) {
				request.setAttribute("message", "�Բ�����֤ʧ�ܣ��������Ѿ����ڣ�");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			if (emailId == null) {
				request.setAttribute("message", "δ֪��֤����");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}

			String checkId = EncryptionUtils.md5(eRandomId);
			// ����������ܵ�id�Ե���,��ͨ��
			if (checkId.trim().equalsIgnoreCase(emailId.trim())) {
				try {
					String email = (String) session.getAttribute("newEmail");
					session.removeAttribute(("eRandomId"));// ��ranDomIdʧЧ����ֹ�û���ε���������
					session.removeAttribute("newEmail");
					new UserDaoImpl()
							.updateProperties("email", email, username);
					request.setAttribute("message", "�����Ѿ��ɹ����!");
					request.getRequestDispatcher("/jsp/message_jsp/message.jsp")
							.forward(request, response);
				} catch (SQLException e) {
					request.setAttribute("message", "������ʧ��!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					throw new RuntimeException(e);
				}
				return;

			} else {
				request.setAttribute("message", "��֤ʧ�ܣ���֤��Ϣ����");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
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
		doGet(request, response);
	}
}

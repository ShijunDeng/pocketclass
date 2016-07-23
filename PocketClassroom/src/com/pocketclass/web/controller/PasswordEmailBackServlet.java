package com.pocketclass.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.pocketclass.utils.EncryptionUtils;

/**
 * �����������䷵����֤
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-18 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PasswordEmailBackServlet extends HttpServlet {

	private static final long serialVersionUID = 201210182133L;

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
			String pawId = request.getParameter("pswId").replace(' ', '+');// �õ��ʺź�����ܵ�registerid
			String pRandomId = (String) session.getAttribute("pRandomId");

			if (pRandomId == null) {
				request.setAttribute("message", "�Բ�����֤ʧ�ܣ��������Ѿ����ڣ�");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			if (pawId == null) {
				request.setAttribute("message", "δ֪��֤����");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			String checkId = EncryptionUtils.md5(pRandomId);
			// ����������ܵ�id�Ե���,��ͨ��
			if (checkId.trim().equalsIgnoreCase(pawId.trim())) {
				request.getRequestDispatcher(
						"/jsp/password/resetPassword.jsp?username=" + username)
						.forward(request, response);
				session.removeAttribute(("pRandomId"));// ��ranDomIdʧЧ����ֹ�û���ε���������
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

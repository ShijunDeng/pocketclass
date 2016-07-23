package com.pocketclass.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.utils.EncryptionUtils;

/**
 * ���䷵����֤
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class RegisterEmailBackServlet extends HttpServlet {
	private static final long serialVersionUID = 201210161951L;

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
			String registerId = request.getParameter("registerId").replace(' ',
					'+');// �õ��ʺź�����ܵ�registerid
			String rRandomId = (String) session.getAttribute("rRandomId");
			if (rRandomId == null) {
				request.getSession().setAttribute("message",
						"�Բ�����֤ʧ�ܣ��������Ѿ����ڣ�");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			if (registerId == null) {
				request.setAttribute("message", "δ֪��֤����");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}
			String checkRegidId = EncryptionUtils.md5(rRandomId);

			if (checkRegidId.trim().equalsIgnoreCase(registerId.trim())) {
				session.removeAttribute("rRandomId");// ��ȥ�������ת����ʱ�����Ӱ��
				request.getRequestDispatcher(
						"/servlet/RegisterServlet;jsessionid="
								+ session.getId()).forward(request, response);
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

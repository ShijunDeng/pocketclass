package com.pocketclass.web.controller.customer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.dao.impl.customer.MessageDaoImpl;
import com.pocketclass.domain.message.Message;

/**
 * ������Ϣ����
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-09 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class MsgContentServlet extends HttpServlet {

	private static final long serialVersionUID = 201211091934L;
	private String idLoca = "";
	/*
	 * idLoca��Ҳ��������ã��ﵽ���û����ĸ���������п���Ϣ���ղء����������䡢ɾ���������һ�����������Ϣ��¼����
	 */
	private String messageID = "";

	/*
	 * messageID:��"��������"��һ����Ϣ����ʾ�����Ⱦ�Ҫ������ȻҪ�������MsgContentServlet����
	 * �Ƚ���ID��������^����ʱ�䣩��ס�����Ҫ����ת�����߶���ظ�����Ȼ�ǶԵ�ǰ�����
	 * ������Ϣ���еģ��Ϳ���ֱ����ID��ȡ������ݣ������һ����Ϣ��ʱ��
	 * ��ID�����µ�ֵ���ǣ���Ȼ�����ֱ�Ӽ�ס��ϢҲ����ʵ��ͬ����Ч�������Ҳ���ȥ�����ݿ�
	 * ���ٶȽ����죬���ǿ��ǵ���Ϣʵ��Ƚϴ��Ҳ��Ǿ�����ת���ͻظ���Ҫ�󣬹ʶ������ID������Ϣʵ��
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			String option = request.getParameter("option");
			String username = (String) session.getAttribute("username");
			if (username == null || "".equals(username)) {
				request.setAttribute("error", "���ȵ�¼���ٽ��в���");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
			String checkedMsgsStr = request.getParameter("info");
			if (request.getParameter("idLoca") != null)
				this.idLoca = request.getParameter("idLoca");
			if ("content".equals(option)) {
				this.messageID = request.getParameter("msg");
				Message msg = messageDaoImpl.getMessage(this
						.stringToMesssage(this.messageID));
				request.setAttribute("msg", msg);
				// �������˵ı��˶���
				// �����Լ����Լ�����
				if (username.equals(msg.getAddreUserName())
						|| msg.getSendUserName().equals(msg.getAddreUserName())) {
					// �����ϢΪ�Ѷ�
					messageDaoImpl.changeMsgToRead(msg);
					// δ����Ϣ������һ
					int unReadNum = Integer.parseInt((session
							.getAttribute("unReadNum") + ""));
					if (unReadNum > 0)
						session.setAttribute("unReadNum", unReadNum--);
				}
			} else if ("toSpambox".equals(option)) {
				if (messageDaoImpl.messageOperate(username,
						this.stringToMesssage(checkedMsgsStr), idLoca, "004") == false) {
					request.setAttribute("message", "����ʧ��!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					return;
				} else {
					forward(request, response, idLoca);
					return;
				}

			} else if ("toFavobox".equals(option)) {
				if (messageDaoImpl.messageOperate(username,
						this.stringToMesssage(checkedMsgsStr), idLoca, "003") == false) {
					request.setAttribute("message", "����ʧ��!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					return;
				} else {
					forward(request, response, idLoca);
					return;
				}
			} else if ("newMsg".equals(option)) {// ����Ϣ
				// ��һ����֤
				String addreUserName = request.getParameter("addreUserName");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				if (new UserDaoImpl().existUser(addreUserName)
						&& title.matches(".{0,50}") && content.length() < 2001) {
					Message newMessage = new Message();
					newMessage.setSendUserName(username);
					newMessage.setAddreUserName(addreUserName);
					newMessage.setTitle(title);
					newMessage.setContent(content);
					newMessage.setSendTime(new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(new Date()));
					newMessage.setIsRead("NO");
					if (new MessageDaoImpl().sendMesage(newMessage)) {
						forward(request, response, "002");// ����������
						return;
					} else {
						request.setAttribute("message", "��Ϣ����ʧ��!");
						request.getRequestDispatcher(
								"/jsp/message_jsp/errorMessage.jsp").forward(
								request, response);
						return;
					}
				} else {// �򵥵ķ������֤
					request.setAttribute("message", "��Ϣ����ʧ�ܣ���ʽ������Ҫ����������˲�����!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					return;
				}

			} else if ("forward".equals(option)) {// ת��
				Message msg = messageDaoImpl.getMessage(this
						.stringToMesssage(this.messageID));
				String sendUserName = msg.getSendUserName();
				String addreUserName = msg.getAddreUserName();
				String title = msg.getTitle();
				String content = msg.getContent();
				request.setAttribute("titleDef", "ת��:" + title);
				request.setAttribute("contentDef", "ԭ������:" + sendUserName
						+ "<br>" + "ԭ�ռ���:" + addreUserName + "<br>" + "ԭ����:"
						+ title + "<br>" + content);
				request.getRequestDispatcher("/servlet/MsgServlet?idLoca=000")
						.forward(request, response);
				return;
			} else if ("reply".equals(option)) {// �ظ�
				Message msg = messageDaoImpl.getMessage(this
						.stringToMesssage(this.messageID));
				String sendUserName = msg.getSendUserName();
				String title = msg.getTitle();
				request.setAttribute("sendUserNameDef", sendUserName);
				request.setAttribute("titleDef", "�ظ�:" + title);
				request.getRequestDispatcher("/servlet/MsgServlet?idLoca=000")
						.forward(request, response);
				return;
			}

			request.getRequestDispatcher("/jsp/customer/msgContent.jsp")
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

	private Message stringToMesssage(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		Message msg = new Message();
		msg.setSendUserName(str.substring(0, str.indexOf("^")));
		// System.out.println(strArr[0]);
		msg.setSendTime(str.substring(str.indexOf("^") + 1));
		return msg;
	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response, String idLoca)
			throws ServletException, IOException {
		if (idLoca == null || idLoca.isEmpty()) {
			return;
		}
		if (idLoca.trim().equals("000")) {// ������Ϣ
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=000")
					.forward(request, response);
			return;
		} else if (idLoca.trim().equals("001")) {// ������
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=001")
					.forward(request, response);
			return;
		} else if (idLoca.trim().equals("002")) {// ������
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=002")
					.forward(request, response);
			return;
		} else if (idLoca.trim().equals("003")) {// �ղ���
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=003")
					.forward(request, response);
			return;
		} else {// ������
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=004")
					.forward(request, response);
			return;
		}

	}
}

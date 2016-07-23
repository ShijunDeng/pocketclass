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
 * 处理消息内容
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-09 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class MsgContentServlet extends HttpServlet {

	private static final long serialVersionUID = 201211091934L;
	private String idLoca = "";
	/*
	 * idLoca，也起记忆作用，达到：用户从哪个信箱分类中看消息，收藏、移入垃圾箱、删除就针对哪一个分类里的信息记录操作
	 */
	private String messageID = "";

	/*
	 * messageID:起"记忆作用"：一条消息被显示，首先就要读，必然要经过这里（MsgContentServlet），
	 * 先将其ID（发信人^发信时间）记住，如果要将其转发或者对其回复，必然是对当前正在浏
	 * 览的消息进行的，就可以直接以ID获取相关内容，浏览下一条消息的时候
	 * ，ID将被新的值覆盖，当然，如果直接记住消息也可以实现同样的效果，而且不用去读数据库
	 * ，速度将更快，但是考虑到消息实体比较大且不是经常有转发和回复的要求，故而这里存ID不存消息实体
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
				request.setAttribute("error", "请先登录后再进行操作");
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
				// 发给别人的别人读了
				// 发给自己的自己读了
				if (username.equals(msg.getAddreUserName())
						|| msg.getSendUserName().equals(msg.getAddreUserName())) {
					// 标记消息为已读
					messageDaoImpl.changeMsgToRead(msg);
					// 未读消息条数减一
					int unReadNum = Integer.parseInt((session
							.getAttribute("unReadNum") + ""));
					if (unReadNum > 0)
						session.setAttribute("unReadNum", unReadNum--);
				}
			} else if ("toSpambox".equals(option)) {
				if (messageDaoImpl.messageOperate(username,
						this.stringToMesssage(checkedMsgsStr), idLoca, "004") == false) {
					request.setAttribute("message", "操作失败!");
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
					request.setAttribute("message", "操作失败!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					return;
				} else {
					forward(request, response, idLoca);
					return;
				}
			} else if ("newMsg".equals(option)) {// 发信息
				// 进一步验证
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
						forward(request, response, "002");// 跳到发信箱
						return;
					} else {
						request.setAttribute("message", "消息发送失败!");
						request.getRequestDispatcher(
								"/jsp/message_jsp/errorMessage.jsp").forward(
								request, response);
						return;
					}
				} else {// 简单的服务端验证
					request.setAttribute("message", "消息发送失败：格式不符合要求或者收信人不存在!");
					request.getRequestDispatcher(
							"/jsp/message_jsp/errorMessage.jsp").forward(
							request, response);
					return;
				}

			} else if ("forward".equals(option)) {// 转发
				Message msg = messageDaoImpl.getMessage(this
						.stringToMesssage(this.messageID));
				String sendUserName = msg.getSendUserName();
				String addreUserName = msg.getAddreUserName();
				String title = msg.getTitle();
				String content = msg.getContent();
				request.setAttribute("titleDef", "转发:" + title);
				request.setAttribute("contentDef", "原发件人:" + sendUserName
						+ "<br>" + "原收件人:" + addreUserName + "<br>" + "原标题:"
						+ title + "<br>" + content);
				request.getRequestDispatcher("/servlet/MsgServlet?idLoca=000")
						.forward(request, response);
				return;
			} else if ("reply".equals(option)) {// 回复
				Message msg = messageDaoImpl.getMessage(this
						.stringToMesssage(this.messageID));
				String sendUserName = msg.getSendUserName();
				String title = msg.getTitle();
				request.setAttribute("sendUserNameDef", sendUserName);
				request.setAttribute("titleDef", "回复:" + title);
				request.getRequestDispatcher("/servlet/MsgServlet?idLoca=000")
						.forward(request, response);
				return;
			}

			request.getRequestDispatcher("/jsp/customer/msgContent.jsp")
					.forward(request, response);
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
		if (idLoca.trim().equals("000")) {// 发新消息
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=000")
					.forward(request, response);
			return;
		} else if (idLoca.trim().equals("001")) {// 收信箱
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=001")
					.forward(request, response);
			return;
		} else if (idLoca.trim().equals("002")) {// 发信箱
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=002")
					.forward(request, response);
			return;
		} else if (idLoca.trim().equals("003")) {// 收藏箱
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=003")
					.forward(request, response);
			return;
		} else {// 垃圾箱
			request.getRequestDispatcher("/servlet/MsgServlet?idLoca=004")
					.forward(request, response);
			return;
		}

	}
}

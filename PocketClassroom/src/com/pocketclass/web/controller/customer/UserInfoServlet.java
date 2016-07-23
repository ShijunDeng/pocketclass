package com.pocketclass.web.controller.customer;

import java.io.IOException;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.dao.impl.SavePathDaoImpl;
import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.domain.User;
import com.pocketclass.picture.PicCompression;

/**
 * �ύ�û���Ϣ
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class UserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 201210222129L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			response.setHeader("Pragma","No-cache");     
			response.setHeader("Cache-Control","no-cache");   	  
			response.setDateHeader("Expires", 0);  
			
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username == null || "".equals(username)) {

				request.setAttribute("error", "���ȵ�¼���ٽ��в���");
				request.getRequestDispatcher("/jsp/login/login.jsp").forward(
						request, response);
				return;
			}
			User user = null;
			try {
				user = modifyRequestToUser(request, response);
			} catch (SmartUploadException e1) {
				request.setAttribute("message", "�ϴ�ͼƬ��ʽ���Ϸ�,�����޸�ʧ��!");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				return;
			}

			UserDaoImpl userDaoImpl = new UserDaoImpl();
			try {
				DBConnectorImpl.setCharacterEncoding("utf8");
			} catch (SQLException e) {
				request.setAttribute("message", "�����޸�ʧ��!");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				throw new RuntimeException(e);
			}
			try {
				userDaoImpl.update(user);
			} catch (Exception e) {
				request.setAttribute("message", "�����޸�ʧ��!");
				request.getRequestDispatcher(
						"/jsp/message_jsp/errorMessage.jsp").forward(request,
						response);
				throw new RuntimeException(e);
			}

			request.getRequestDispatcher("/jsp/customer/modifySuccess.jsp")
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

	public User modifyRequestToUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			SmartUploadException, ServletException {
		User user = new User();
		SmartUpload smartUpload = new SmartUpload();
		ServletConfig config = this.getServletConfig();
		smartUpload.initialize(config, request, response);
		smartUpload.upload();
		// �ڻ�ȡ������Ϊ����ʱҪ��Integer.parseInt()
		// Ҫ����jsp���ö���request��com.jspsmart.upload.Request ������requestȥ��ȡ������
		HttpSession session = request.getSession();
		Request smartRequest = smartUpload.getRequest();
		user.setUsername((String) session.getAttribute("username") + "");
		user.setEmail((String) (session.getAttribute("email")) + "");
		user.setNickname(smartRequest.getParameter("nickname") + "");
		user.setPhoneNum(smartRequest.getParameter("phoneNum") + "");
		user.setQQ(smartRequest.getParameter("QQ") + "");
		user.setName(smartRequest.getParameter("name") + "");
		user.setCollege(smartRequest.getParameter("college") + "");
		user.setAcademy(smartRequest.getParameter("academy") + "");
		user.setGender(smartRequest.getParameter("gender") + "");
		// ��֤����λ����λ����λ
		String dateStr = smartRequest.getParameter("year")
				+ String.format("%02d",
						Integer.parseInt(smartRequest.getParameter("month")))
				+ String.format("%02d",
						Integer.parseInt(smartRequest.getParameter("day")));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			user.setDateOfBirth(df.parse(dateStr));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		// ����ͷ��
		smartUpload.setMaxFileSize(1024 * 512);// �ϴ�ͷ��Ĵ�С����Ϊ512KB
		smartUpload.setAllowedFilesList("png,jpg,gif,bmp,jpeg");
		com.jspsmart.upload.File file = smartUpload.getFiles().getFile(0);
		if (file != null && smartUpload.getFiles().getSize() > 0) {// �û��ϴ���ͼƬ
			String saveName = user.getUsername() + "." + file.getFileExt();
			String relativeSavePath=SavePathDaoImpl.getHeadsSavePth() + File.separator
					+ saveName;
			file.saveAs(relativeSavePath, SmartUpload.SAVE_AUTO);
			PicCompression pc = new PicCompression();
			/**
			 * pc.zoomJPEG(this.getServletContext().getRealPath("")+
			 * File.separator + saveName, 100,100, 1);
			 **/
			// ѹ��ͼƬ
			if (pc.zoomJPEG(getServletContext().getRealPath(File.separator) + relativeSavePath, 100, 100, 1) == true)
				user.setHeadPorAdd(saveName);
		} else {
			user.setHeadPorAdd((String) session.getAttribute("headPorAdd"));
		}
		return user;
	}

}

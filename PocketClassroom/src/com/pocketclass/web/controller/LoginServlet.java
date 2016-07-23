package com.pocketclass.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pocketclass.dao.impl.BlackListDaoImpl;
import com.pocketclass.domain.User;
import com.pocketclass.service.impl.BusinessServiceImpl;
import com.pocketclass.service.impl.LoginLogServiceImpl;
import com.pocketclass.utils.EncryptionUtils;
import com.pocketclass.utils.WebUtils;
import com.pocketclass.web.beans.LoginLogBean;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 201210162323L;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		if(!service.existUser(username))
		{
			//ָ�����û�����������û�������
			request.setAttribute("error","�û���������");
			request.setAttribute("username", username);
		//	request.setAttribute("password", password);
			request.getRequestDispatcher("/jsp/login/login.jsp").forward(request,response);
			return;
		}
		password = EncryptionUtils.md5(password);
		User user = service.login(username, password);
		if(user == null)
		{//ָ�����û�����������û�������
			request.setAttribute("error","�û������������");
			request.setAttribute("username", username);
		//	request.setAttribute("password", password);
			request.getRequestDispatcher("/jsp/login/login.jsp").forward(request,response);
			return;
		}else if(new BlackListDaoImpl().exist(username)){
			request.setAttribute("error","�Բ��������˻���ǰ��������״̬������ϵ����Ա����!");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/jsp/login/login.jsp").forward(request,response);
			return;
		}
		else
		{//��¼�ɹ�����ת����ҳ
			String rememberPsw = request.getParameter("rememberme");
				LoginLogBean loginLogBean =new LoginLogBean();
			loginLogBean.setUsername(username);
			loginLogBean.setLoginTime(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			new WebUtils();
			loginLogBean.setLoginIP(WebUtils.getIpAddr(request));
					new LoginLogServiceImpl().add(loginLogBean);
			if(rememberPsw != null && rememberPsw.equals("yes"))
			{//��ס����
				//����cookie
				Cookie userCookie = new Cookie("username",username);
				Cookie pswCookie  = new Cookie("password",password);
				//����cookie��Ч��
				userCookie.setMaxAge(7*24*60*60);
				pswCookie.setMaxAge(7*24*60*60);
				//������Ч·��
				userCookie.setPath("/");
				pswCookie.setPath("/");
				
				response.addCookie(userCookie);
				response.addCookie(pswCookie);
			}
			//��½֮��ĻỰ��������Ҫ֪���û���
				HttpSession session=request.getSession();
				session.setAttribute("username", username);
			response.sendRedirect(request.getContextPath()+"/");
		}	
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "������!");
			request.getRequestDispatcher("/jsp/message_jsp/errorMessage.jsp")
					.forward(request, response);
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

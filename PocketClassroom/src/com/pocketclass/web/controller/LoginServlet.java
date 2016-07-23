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
			//指定的用户名和密码的用户不存在
			request.setAttribute("error","用户名不存在");
			request.setAttribute("username", username);
		//	request.setAttribute("password", password);
			request.getRequestDispatcher("/jsp/login/login.jsp").forward(request,response);
			return;
		}
		password = EncryptionUtils.md5(password);
		User user = service.login(username, password);
		if(user == null)
		{//指定的用户名和密码的用户不存在
			request.setAttribute("error","用户名或密码错误");
			request.setAttribute("username", username);
		//	request.setAttribute("password", password);
			request.getRequestDispatcher("/jsp/login/login.jsp").forward(request,response);
			return;
		}else if(new BlackListDaoImpl().exist(username)){
			request.setAttribute("error","对不起，您的账户当前处于锁定状态，请联系管理员解锁!");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/jsp/login/login.jsp").forward(request,response);
			return;
		}
		else
		{//登录成功，跳转到首页
			String rememberPsw = request.getParameter("rememberme");
				LoginLogBean loginLogBean =new LoginLogBean();
			loginLogBean.setUsername(username);
			loginLogBean.setLoginTime(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			new WebUtils();
			loginLogBean.setLoginIP(WebUtils.getIpAddr(request));
					new LoginLogServiceImpl().add(loginLogBean);
			if(rememberPsw != null && rememberPsw.equals("yes"))
			{//记住密码
				//生成cookie
				Cookie userCookie = new Cookie("username",username);
				Cookie pswCookie  = new Cookie("password",password);
				//设置cookie有效期
				userCookie.setMaxAge(7*24*60*60);
				pswCookie.setMaxAge(7*24*60*60);
				//设置有效路径
				userCookie.setPath("/");
				pswCookie.setPath("/");
				
				response.addCookie(userCookie);
				response.addCookie(pswCookie);
			}
			//登陆之后的会话，操作需要知道用户名
				HttpSession session=request.getSession();
				session.setAttribute("username", username);
			response.sendRedirect(request.getContextPath()+"/");
		}	
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "出错啦!");
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

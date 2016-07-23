package com.pocketclass.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pocketclass.domain.User;
import com.pocketclass.service.impl.BusinessServiceImpl;

public class AutoLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		 Cookie[] cookies = httpRequest.getCookies();
		 
		 String username = "";
		 String password = "";
		 for(int i=0;cookies != null && i<cookies.length;i++)
		 {
			if(cookies[i].getName().equals("username"))
			{
				username = cookies[i].getValue();
//System.out.println("用户名："+username);
			}
			else if(cookies[i].getName().equals("password"))
			{
				password = cookies[i].getValue();
//System.out.println("密码："+password);				
			}
		 }
		 
		 BusinessServiceImpl service = new BusinessServiceImpl();
		 if(service.existUser(username))
		 {
			 User user = service.login(username, password);
			 if(user != null)
			 {
				HttpSession session=httpRequest.getSession();
				if(null == session.getAttribute("username"))
				{
//System.out.println("设置session");			 
					session.setAttribute("username", username);
				}
			 }
		 }
		 
		 chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}

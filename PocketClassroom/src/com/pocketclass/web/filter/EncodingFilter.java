package com.pocketclass.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 字符编码过滤器类：用于解决中文乱码问题
 * 
 * @author zhanghan
 */
public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//HttpServletRequest httpRequest = (HttpServletRequest) request;// 将请求强制转换为http协议的请求


	}

	@Override
	public void destroy() {

	}

}

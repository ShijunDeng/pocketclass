package com.pocketclass.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * �ַ�����������ࣺ���ڽ��������������
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
		//HttpServletRequest httpRequest = (HttpServletRequest) request;// ������ǿ��ת��ΪhttpЭ�������


	}

	@Override
	public void destroy() {

	}

}

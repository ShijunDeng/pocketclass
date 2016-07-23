package com.pocketclass.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import com.pocketclass.domain.User;

/**
 * 
 * 数据转换工具类： 表单toBean
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class WebUtils {
	/**
	 * 
	 * @param request
	 * @param beanClass
	 * @return:表单数据转换成的bean
	 */
	public static <T> T requestToBean(HttpServletRequest request,
			Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();
			Enumeration<?> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将user中的信息存入session中
	 * 
	 * @param user
	 *            :userBean
	 * @param session
	 *            :存放user中的键值对
	 */
	public static void userToSession(User user, HttpSession session) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(User.class,
					Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				Method f = pd.getReadMethod();
				try {
					session.setAttribute(pd.getName(), f.invoke(user));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
/**
 * 
 * @param request
 * @return 获得客户端真实IP地址
 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}

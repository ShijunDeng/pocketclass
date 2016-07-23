package com.pocketclass.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.pocketclass.dao.DBProperties;

/**
 * 
 * 读取工程的配置信息：域名，端口号等
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-26 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */

public class ProjectDaoImpl implements DBProperties {
	private static Properties proConfig = new Properties();
	static {
		/*
		 * InputStream in = DBProperties.class.getClassLoader()
		 * .getResourceAsStream("db.properties"); try { dbconfig.load(in); }
		 * catch (IOException e) { throw new ExceptionInInitializerError(e); }
		 */
		// 上面代码类装载器只装载一次,下面代码用类装载方式得到文件位置
		URL url = ProjectDaoImpl.class.getClassLoader().getResource(
				"project.properties");
		String str = url.getPath();
		try {
			InputStream in = new FileInputStream(str);
			try {
				proConfig.load(in);
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			}
		} catch (FileNotFoundException e1) {
			throw new ExceptionInInitializerError(e1);
		}
	}

	public static String getDomainName() {
		if (proConfig == null) {
			return null;
		}
		String domainName = proConfig.getProperty("domainname") + "";
		return domainName;
	}

	public static String getPort() {
		if (proConfig == null) {
			return null;
		}
		String port = proConfig.getProperty("port") + "";
		return port;
	}

	/**
	 * 
	 * @return:Returns file project name which dependent on a configuration, may
	 *                 cause a null pointer exception or can not get the right
	 *                 results, therefore is not recommended for use
	 */
	@Deprecated
	public static String getProjectName() {
		if (proConfig == null) {
			return null;
		}
		String projectName = proConfig.getProperty("projectName") + "";
		return projectName;
	}

}

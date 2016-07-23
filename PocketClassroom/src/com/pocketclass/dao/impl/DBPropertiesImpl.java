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
 * ��ȡ���ݿ�������Ϣ���û���,����,����url
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */

public class DBPropertiesImpl implements DBProperties {
	private static Properties dbconfig = new Properties();
	static {
		/*
		 * InputStream in = DBProperties.class.getClassLoader()
		 * .getResourceAsStream("db.properties"); try { dbconfig.load(in); }
		 * catch (IOException e) { throw new ExceptionInInitializerError(e); }
		 */
		// ���������װ����ֻװ��һ��,�����������װ�ط�ʽ�õ��ļ�λ��
		URL url = DBPropertiesImpl.class.getClassLoader().getResource(
				"db.properties");
		String str = url.getPath();
		try {
			InputStream in = new FileInputStream(str);
			try {
				dbconfig.load(in);
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			}
		} catch (FileNotFoundException e1) {
			throw new ExceptionInInitializerError(e1);
		}
	}

	public static String getUserName() {
		if(dbconfig==null){
			return null;
		}
		String username = dbconfig.getProperty("username");
		return username;
	}

	public static String getPassword() {
		if(dbconfig==null){
			return null;
		}
		String password = dbconfig.getProperty("password");
		return password;
	}

	public static String getURLPath() {
		if(dbconfig==null){
			return null;
		}
		String urlPath = dbconfig.getProperty("url");
		return urlPath;
	}
}

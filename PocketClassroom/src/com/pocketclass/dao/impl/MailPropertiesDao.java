package com.pocketclass.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
/**
 * 
 * 读取email的配置信息(用来验证的邮箱)
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */

public class MailPropertiesDao {
	private static Properties emailconfig = new Properties();
	static {
		URL url = MailPropertiesDao.class.getClassLoader().getResource(
				"email.properties");
		String str = url.getPath();
		try {
			InputStream in = new FileInputStream(str);
			try {
				emailconfig.load(in);
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			}
		} catch (FileNotFoundException e1) {
			throw new ExceptionInInitializerError(e1);
		}
	}

	public static String getEmailAddress() {
		if(emailconfig==null){
			return null;
		}
		String username = emailconfig.getProperty("address")+"";
		return username;
	}

	public static String getPassword() {
		if(emailconfig==null){
			return null;
		}
		String password = emailconfig.getProperty("password")+"";
		return password;
	}

	public static String getMailSmtpHost() {
		if(emailconfig==null){
			return null;
		}
		String mailSmtpHost = emailconfig.getProperty("mail.smtp.host")+"";
		return mailSmtpHost;
	}
	public static String geEmailSmtpAuth() {
		if(emailconfig==null){
			return null;
		}
		String mailSmtpAuth = emailconfig.getProperty("mail.smtp.auth")+"";
		return mailSmtpAuth;
	}
}

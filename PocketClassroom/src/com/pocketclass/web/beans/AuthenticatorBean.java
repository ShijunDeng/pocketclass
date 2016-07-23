package com.pocketclass.web.beans;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * AuthenticatorBean:保存用户名及密码
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class AuthenticatorBean extends Authenticator {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}

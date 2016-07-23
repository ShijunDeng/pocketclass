package com.pocketclass.web.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 注册信息(用户名，密码，邮箱地址)
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class RegisterForm {

	private String username;
	private String password;
	private String confPassword;// 确认密码
	private String email;
	private String vercode;
	private Map<String, String> tips = new HashMap<String, String>();

	public RegisterForm(){
	//	tips.put("username", "用户名为5-25位字母、数字或下划线组合，首字符必须为字母!");
	//	tips.put("password", "密码为6-16位字母或者数字!");
	//	tips.put("email", "请输入有效的邮件地址，如admin@163.com，将用来找回密码!");
	}
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

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, String> getTips() {
		return tips;
	}

	public String getVercode() {
		return vercode;
	}
	public void setVercode(String vercode) {
		this.vercode = vercode;
	}
	public boolean validate() {
		boolean isOk = true;
	
		if (username == null || username.trim().equals("")) {
			isOk = false;
			tips.put("username", "用户名不能为空!");
		} else {// 用户名为5-25位字母、数字或下划线组合，首字符必须为字母
			if (!username.matches("[a-zA-Z]\\w{4,24}")) {
				isOk = false;
				tips.put("username", "用户名格式不正确!");
			}
		}

		if (password == null || password.trim().equals("")) {
			isOk = false;
			tips.put("password", "密码不能为空!");
		} else {// 密码：6-16位字母或者数字
			if (!password.matches("[a-zA-Z0-9]{6,16}")) {
				isOk = false;
				if (password.trim().length() < 6
						|| password.trim().length() > 16) {
					tips.put("password", "密码长度为6-16位!");
				} else {
					tips.put("password", "密码必须由字母或者数字构成!");
				}

			}
		}
		if (confPassword == null || confPassword.trim().equals("")) {
			isOk = false;
			tips.put("confPassword", "确认密码不能为空!");
		} else {
			if (!password.equals(confPassword)) {
				isOk = false;
				tips.put("confPassword", "两次输入密码不一致!");
			}
		}
		if (email == null || email.trim().equals("")) {
			isOk = false;
			tips.put("email", "邮箱不能为空!");
		} else {
			//
			if (!email
					.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
				isOk = false;
				tips.put("email", "邮箱格式不合法!");
			}
		}

		return isOk;
	}

}

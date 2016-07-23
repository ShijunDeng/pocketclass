package com.pocketclass.web.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ע����Ϣ(�û��������룬�����ַ)
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class RegisterForm {

	private String username;
	private String password;
	private String confPassword;// ȷ������
	private String email;
	private String vercode;
	private Map<String, String> tips = new HashMap<String, String>();

	public RegisterForm(){
	//	tips.put("username", "�û���Ϊ5-25λ��ĸ�����ֻ��»�����ϣ����ַ�����Ϊ��ĸ!");
	//	tips.put("password", "����Ϊ6-16λ��ĸ��������!");
	//	tips.put("email", "��������Ч���ʼ���ַ����admin@163.com���������һ�����!");
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
			tips.put("username", "�û�������Ϊ��!");
		} else {// �û���Ϊ5-25λ��ĸ�����ֻ��»�����ϣ����ַ�����Ϊ��ĸ
			if (!username.matches("[a-zA-Z]\\w{4,24}")) {
				isOk = false;
				tips.put("username", "�û�����ʽ����ȷ!");
			}
		}

		if (password == null || password.trim().equals("")) {
			isOk = false;
			tips.put("password", "���벻��Ϊ��!");
		} else {// ���룺6-16λ��ĸ��������
			if (!password.matches("[a-zA-Z0-9]{6,16}")) {
				isOk = false;
				if (password.trim().length() < 6
						|| password.trim().length() > 16) {
					tips.put("password", "���볤��Ϊ6-16λ!");
				} else {
					tips.put("password", "�����������ĸ�������ֹ���!");
				}

			}
		}
		if (confPassword == null || confPassword.trim().equals("")) {
			isOk = false;
			tips.put("confPassword", "ȷ�����벻��Ϊ��!");
		} else {
			if (!password.equals(confPassword)) {
				isOk = false;
				tips.put("confPassword", "�����������벻һ��!");
			}
		}
		if (email == null || email.trim().equals("")) {
			isOk = false;
			tips.put("email", "���䲻��Ϊ��!");
		} else {
			//
			if (!email
					.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
				isOk = false;
				tips.put("email", "�����ʽ���Ϸ�!");
			}
		}

		return isOk;
	}

}

package com.pocketclass.domain;

import java.util.Date;

/**
 * 用户实体
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-05 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class User {
	private String username = " ";
	private String password = " ";
	private String name = " ";
	private String nickname = " ";
	private String gender = " ";
	private Date dateOfBirth;
	private String phoneNum = " ";
	private String email = " ";
	private String QQ = " ";
	private String college = " ";
	private String academy = " ";
	private String access = " ";
	private String headPorAdd = " ";// 头像地址

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null)
			this.username = username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password!=null)
			this.password = password.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		if (nickname != null)
			this.nickname = nickname.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if (gender != null)
			this.gender = gender.trim();
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		if (phoneNum != null)
			this.phoneNum = phoneNum.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null)
			this.email = email.trim();
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		if (qQ != null)
			this.QQ = qQ.trim();
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		if (college != null)
			this.college = college.trim();
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		if (academy != null)
			this.academy = academy.trim();
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		if (access != null)
			this.access = access.trim();
	}

	public String getHeadPorAdd() {
		return headPorAdd;
	}

	public void setHeadPorAdd(String headPorAdd) {
		if (headPorAdd != null)
			this.headPorAdd = headPorAdd.trim();
	}
}

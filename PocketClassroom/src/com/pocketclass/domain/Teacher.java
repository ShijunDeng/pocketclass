package com.pocketclass.domain;

/**
 * 讲师实体
 * 数据库表如下：
 *   CREATE TABLE Teacher( idTeacher CHAR(16) PRIMARY KEY, name VARCHAR(32),
 * age INT, gender CHAR(6), resume TEXT );
 **/
public class Teacher {
	
	private String id;// 讲师编号
	private String name;
	private int age;
	private String gender;// 性别
	private String resume;// 讲师简介

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}

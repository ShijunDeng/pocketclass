package com.pocketclass.dao;

import com.pocketclass.domain.User;
public interface UserDao {
	void add(User user);
	User query(String username, String password);
	void update(User user);
	//查找注册的用户是否在数据库中存在
	boolean existUser(String username);
	//查找注册的email是否在数据库中存在
	public boolean existEmail(String email);
}
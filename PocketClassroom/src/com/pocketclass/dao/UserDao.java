package com.pocketclass.dao;

import com.pocketclass.domain.User;
public interface UserDao {
	void add(User user);
	User query(String username, String password);
	void update(User user);
	//����ע����û��Ƿ������ݿ��д���
	boolean existUser(String username);
	//����ע���email�Ƿ������ݿ��д���
	public boolean existEmail(String email);
}
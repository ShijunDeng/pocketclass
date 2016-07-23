 package com.pocketclass.service.impl;

import com.pocketclass.dao.UserDao;
import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.domain.User;
import com.pocketclass.exception.EmailRepeatException;
import com.pocketclass.exception.UserExistException;

public class BusinessServiceImpl {

	private UserDao dao = new UserDaoImpl();// 工厂模式 spring

	// 对web层提供注册服务
	public void register(User user) throws UserExistException,
			EmailRepeatException {
		if (dao.existUser(user.getUsername())) {
			throw new UserExistException("该用户名已被占用!");
		}
		if (dao.existEmail(user.getEmail())) {
			throw new EmailRepeatException("该邮箱已被注册!");
		}
		dao.add(user);
	}

	// 对web层提供登录服务
	public User login(String username, String password) {
		return dao.query(username, password);
	}
	
	public boolean existUser(String username)
	{
		return dao.existUser(username);
	}
}

 package com.pocketclass.service.impl;

import com.pocketclass.dao.UserDao;
import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.domain.User;
import com.pocketclass.exception.EmailRepeatException;
import com.pocketclass.exception.UserExistException;

public class BusinessServiceImpl {

	private UserDao dao = new UserDaoImpl();// ����ģʽ spring

	// ��web���ṩע�����
	public void register(User user) throws UserExistException,
			EmailRepeatException {
		if (dao.existUser(user.getUsername())) {
			throw new UserExistException("���û����ѱ�ռ��!");
		}
		if (dao.existEmail(user.getEmail())) {
			throw new EmailRepeatException("�������ѱ�ע��!");
		}
		dao.add(user);
	}

	// ��web���ṩ��¼����
	public User login(String username, String password) {
		return dao.query(username, password);
	}
	
	public boolean existUser(String username)
	{
		return dao.existUser(username);
	}
}

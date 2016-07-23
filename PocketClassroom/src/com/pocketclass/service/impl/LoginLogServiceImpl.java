package com.pocketclass.service.impl;

import java.sql.SQLException;
import java.text.ParseException;

import com.pocketclass.dao.impl.LoginLogDaoImpl;
import com.pocketclass.web.beans.LoginLogBean;

public class LoginLogServiceImpl {
	LoginLogDaoImpl dao = new LoginLogDaoImpl();

	public void add(LoginLogBean loginLogBean) throws ParseException,
			SQLException {
		dao.add(loginLogBean);
	}

	public void delete(LoginLogBean loginLogBean) throws ParseException,
			SQLException {
		dao.delete(loginLogBean);
	}

	public LoginLogBean query(String username, String loginTime)
			throws SQLException, ParseException {
		return dao.query(username, loginTime);
	}
}

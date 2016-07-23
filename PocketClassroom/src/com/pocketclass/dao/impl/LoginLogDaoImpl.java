package com.pocketclass.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.pocketclass.web.beans.LoginLogBean;

public class LoginLogDaoImpl {
	public void add(LoginLogBean loginLogBean) throws ParseException,
			SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlInsert = "INSERT INTO LoginLog(username,loginTime,loginIP) VALUES('"
				+ loginLogBean.getUsername()
				+ "','"
				+ sdf.format(sdf.parse(loginLogBean.getLoginTime()))
				+ "','"
				+ loginLogBean.getLoginIP() + "')";
		DBConnectorImpl.getStatement().execute(sqlInsert);
	}

	public void delete(LoginLogBean loginLogBean) throws ParseException,
			SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlDelete = "DELETE FROM  LoginLog WHERE username='"
				+ loginLogBean.getUsername() + "' AND loginTime='"
				+ sdf.format(sdf.parse(loginLogBean.getLoginTime())) + "'";
		DBConnectorImpl.getStatement().execute(sqlDelete);
	}

	public LoginLogBean query(String username, String loginTime)
			throws SQLException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT loginIP,cancellationTime FROM  LoginLog WHERE username='"
				+ username
				+ "' AND loginTime='"
				+ sdf.format(sdf.parse(loginTime)) + "'";
		ResultSet rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
		if (rs.next()) {
			LoginLogBean loginLogBean = new LoginLogBean();
			loginLogBean.setUsername(username);
			loginLogBean.setLoginTime(loginTime);
			loginLogBean.setCancellationTime(sdf.format(sdf.parse((rs
					.getString("cancellationTime")))));
			loginLogBean.setLoginIP(rs.getString("loginIP"));
			return loginLogBean;
		}
		return null;
	}
}

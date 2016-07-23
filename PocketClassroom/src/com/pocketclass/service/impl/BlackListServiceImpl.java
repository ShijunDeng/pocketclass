package com.pocketclass.service.impl;

import java.sql.SQLException;
import java.text.ParseException;

import com.pocketclass.dao.impl.BlackListDaoImpl;
import com.pocketclass.domain.BlackListUser;

public class BlackListServiceImpl {
	BlackListDaoImpl blackListDaoImpl = new BlackListDaoImpl();

	/**
	 * 将username添加到黑名单中
	 * 
	 * @param blackListUser
	 *            :要添加到黑名单的用户
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void add(BlackListUser blackListUser) throws ParseException,
			SQLException {
		blackListDaoImpl.add(blackListUser);
	}

	/**
	 * 将username从黑名单中删除
	 * 
	 * @param blackListUser
	 *            :要删除黑名单的用户
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void delete(BlackListUser blackListUser) throws ParseException,
			SQLException {
		blackListDaoImpl.delete(blackListUser);
	}

	public void delete(String username) throws SQLException {
		blackListDaoImpl.delete(username);
	}

	/**
	 * 
	 * @param username
	 *            :查询username的黑名单信息
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public BlackListUser query(String username) throws SQLException,
			ParseException {
		return blackListDaoImpl.query(username);
	}

	/**
	 * 
	 * @param username
	 *            :查询username是否存在黑名单中
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean exist(String username) throws SQLException, ParseException {
		return blackListDaoImpl.exist(username);
	}
}

package com.pocketclass.service.impl;

import java.sql.SQLException;
import java.text.ParseException;

import com.pocketclass.dao.impl.BlackListDaoImpl;
import com.pocketclass.domain.BlackListUser;

public class BlackListServiceImpl {
	BlackListDaoImpl blackListDaoImpl = new BlackListDaoImpl();

	/**
	 * ��username��ӵ���������
	 * 
	 * @param blackListUser
	 *            :Ҫ��ӵ����������û�
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void add(BlackListUser blackListUser) throws ParseException,
			SQLException {
		blackListDaoImpl.add(blackListUser);
	}

	/**
	 * ��username�Ӻ�������ɾ��
	 * 
	 * @param blackListUser
	 *            :Ҫɾ�����������û�
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
	 *            :��ѯusername�ĺ�������Ϣ
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
	 *            :��ѯusername�Ƿ���ں�������
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean exist(String username) throws SQLException, ParseException {
		return blackListDaoImpl.exist(username);
	}
}

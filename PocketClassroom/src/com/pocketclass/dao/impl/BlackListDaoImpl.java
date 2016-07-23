package com.pocketclass.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.pocketclass.domain.BlackListUser;

public class BlackListDaoImpl {
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlInsert = "INSERT INTO BlackList(username,time,causeTag) VALUES('"
				+ blackListUser.getUsername()
				+ "','"
				+ sdf.format(sdf.parse(blackListUser.getTime()))
				+ "',"
				+ blackListUser.getCauseTag() + ")";
		DBConnectorImpl.getStatement().execute(sqlInsert);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlDelete = "DELETE FROM BlackList WHERE username='"
				+ blackListUser.getUsername() + "' AND time='"
				+ sdf.format(sdf.parse(blackListUser.getTime())) + "'";
		DBConnectorImpl.getStatement().execute(sqlDelete);
	}

	/**
	 * ��username�Ӻ�������ɾ��
	 * 
	 * @param username
	 *            :Ҫɾ�����������û�
	 * @throws SQLException
	 */
	public void delete(String username) throws SQLException 
		 {
		String sqlDelete = "DELETE FROM BlackList WHERE username='"
				+username + "'";
		DBConnectorImpl.getStatement().execute(sqlDelete);
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
		BlackListUser blackListUser = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT time,causeTag FROM BlackList WHERE username=? ";
		Connection conn = DBConnectorImpl.getConnection();
		PreparedStatement pstemt=conn.prepareStatement(sqlQuery);
		pstemt.setString(1, username);
		ResultSet rs=pstemt.executeQuery();
		if (rs.next()) {
			blackListUser = new BlackListUser();
			blackListUser.setUsername(username);
			blackListUser.setTime(sdf.format(sdf.parse(rs.getString("time"))));
			blackListUser.setCauseTag(rs.getInt("causeTag"));
		}
		return blackListUser;
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
		return query(username) != null;
	}
}

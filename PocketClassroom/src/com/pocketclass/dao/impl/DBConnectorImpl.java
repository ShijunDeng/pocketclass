package com.pocketclass.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.pocketclass.dao.DBConnector;

/**
 * 
 * 数据库连接器：连接数据库,静态对象保存Statement
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */

public class DBConnectorImpl implements DBConnector {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static String charSet = "utf8";
//	private static PreparedStatement pstmt;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		try {
			conn = DriverManager.getConnection(DBPropertiesImpl.getURLPath()
					+ "?user=" + DBPropertiesImpl.getUserName() + "&password="
					+ DBPropertiesImpl.getPassword());

		} catch (SQLException ex) {
			throw new ExceptionInInitializerError(ex);
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e1) {
					throw new ExceptionInInitializerError(e1);
				}
			}
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 
	 * @param charSet
	 *            :设置数据库字符集 如GBK，utf8
	 * @throws SQLException
	 */
	public static void setCharacterEncoding(String charSet) throws SQLException {
		DBConnectorImpl.charSet = charSet;
		getStatement().execute("SET NAMES " + DBConnectorImpl.charSet);
	}

	public static String getCharacterEncoding() throws SQLException {
		return DBConnectorImpl.charSet;
	}

	public static Connection getConnection()
	{
		return conn;
	}
	
	public static Statement getStatement() {
		return stmt;
	}
	
	public static void close() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) {
			} // ignore
			stmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

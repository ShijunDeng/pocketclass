package com.pocketclass.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;


import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.dao.impl.DBPropertiesImpl;

/**
 * 测试数据库连接
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-05 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class DBTest {
	@Test
	public void testDBconnect() {
		try {
			ResultSet rs = DBConnectorImpl.getStatement().executeQuery(
					"SELECT * FROM Account where username='TWT946057490'");
			while (rs.next()) {
				Date date=rs.getDate("dateOfBirth");
				if(date!=null){
					System.out.println(date.toString());
				}
			}
			if(rs!=null){
				rs.close();
				DBConnectorImpl.close();
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	@Test
	public void testDBProperties() {
		System.out.println(DBPropertiesImpl.getUserName());
		System.out.println(DBPropertiesImpl.getPassword());
		System.out.println(DBPropertiesImpl.getURLPath());
	}
}

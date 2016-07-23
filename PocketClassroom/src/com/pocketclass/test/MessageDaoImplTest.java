package com.pocketclass.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.message.Message;

/**
 * 测试处理消息数据库dao方法
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-07 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class MessageDaoImplTest {
	/**
	 * 测试自增并发影响
	 */
	@Test
	public void testAutoInset() {
		//MessageDaoImpl msgDaoImpl = new MessageDaoImpl();
		Message msg = new Message();
		msg.setAddreUserName("pocketclass01");
		msg.setSendUserName("pocketclass01");
		msg.setIsRead("NO");
		msg.setTitle("gfdf");
		msg.setContent("aaaaaaaaaa");
		msg.setSendTime("2012-10-11 09:48:55");
		
		try {System.out.println(DBConnectorImpl.getStatement().executeUpdate(
				"INSERT INTO testauto(data) values('dfs5f')",
				Statement.RETURN_GENERATED_KEYS));
			 ResultSet rs=	DBConnectorImpl.getStatement().getGeneratedKeys();
			if(rs.next())
			System.out.println(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testCancel(){
		try {
			DBConnectorImpl.getStatement().execute("INSERT INTO Video VALUES('Vi01020110','总结',1,20,'11:25:00', 'S01/M02/V01/9.mp4','V010201','2012-10-01 10:28:55')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


	
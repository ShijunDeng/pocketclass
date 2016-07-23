package com.pocketclass.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.pocketclass.dao.UserDao;
import com.pocketclass.dao.impl.UserDaoImpl;
import com.pocketclass.domain.User;

/**
 * 测试UserDaoImpl中的业务处理方法
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-09 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class UserDaoImplTest {
	//SELECT * FROM Account;delete from Account where username='TWT946057495'; commit;
	@Test
	public void testExistUser(){
		UserDao userDao=new UserDaoImpl();
		User user=new User();
		user.setUsername("111' or 1='1'-+1+1+-");
		System.out.println(userDao.existUser(user.getUsername()));
	}
	@Test
	public void testExistEmail(){
		UserDao userDao=new UserDaoImpl();
		System.out.println(userDao.existEmail("946057490@qq.com"));
		System.out.println(userDao.existEmail("946056490@qq.com"));
	}
	@Test
	public void testAdd(){
		UserDao userDao=new UserDaoImpl();
		User user=new User();
		user.setUsername("TWT946057495");
		user.setPassword("11111111");
		user.setEmail("946057495@qq.com");
		if(userDao.existUser(user.getUsername())==false){
			userDao.add(user);
		}
		System.out.println(userDao.existUser(user.getUsername()));
	}
	@Test
	public void testUpdate(){
		UserDao userDao=new UserDaoImpl();
		User user=new User();
		user.setUsername("TWT946057495");
		user.setPassword("11111111");
		user.setEmail("946057495@qq.com");
		if(userDao.existUser(user.getUsername())==false){
			userDao.add(user);
		}
		user.setPassword("2222222");
		try {
			user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1992-05-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userDao.update(user);
	}
	@Test
	public void testQuery(){
		UserDao userDao=new UserDaoImpl();
		User user=new User();
		user.setUsername("TWT946057495");
		user.setPassword("583660");
		user.setEmail("946057495@qq.com");
		if(userDao.existUser(user.getUsername())==false){
			userDao.add(user);
		}	
		User qUser=userDao.query("TWT946057495", "583660");//cyB9q6/3qcISMDv4pOfQCg==
		if(qUser!=null)//
		System.out.println(qUser.getUsername()+"找到了");
		else
			System.out.println("null");
	}
}

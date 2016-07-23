package com.pocketclass.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

/**
 * 
 * 普通函数的测试
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class GeneralTest {
	@Test
	public void testInt() {
		// 测试201210131933L有没有达到最大long
		long aa = 201210131933L;
		System.out.println(aa + 1);
	}

	@Test
	public void testRandom() {
		Random idRandom = new Random(System.currentTimeMillis());
		System.out.println(idRandom.nextInt());
		System.out.println(idRandom.nextInt());
		System.out.println(idRandom.nextInt());
	}

	@Test
	public void testDate() {
		Date now = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(now));
	}
	
	@Test
	public void testSplit()
	{
		String str = "123,234,345,";
		
		String strs[] = str.split(",");
		
		for(String s : strs)
		{
			System.out.println("串为：" + s);
		}
	}
	
	@Test
	public void testReplaceAll()
	{
		String str = "1991年10月";
		str = str.replaceAll("年|月","");
		System.out.println(str);
	}
	
	@Test
	public void testTimeStamp()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		
		System.out.println(sdf.format(now));
		
		String str = "2002-09-08 13:14:15";
		Timestamp ts = Timestamp.valueOf(str);
		System.out.println(ts);
	}
}

package com.pocketclass.test;

import org.junit.Test;

import com.pocketclass.utils.EncryptionUtils;

/**
 * 
 * 加密工具类测试
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class EncryptionTest {
	@Test
	public void testMd5(){
		System.out.println(EncryptionUtils.md5("123456"));
	}
	@Test
	public void testSHA1(){
		EncryptionUtils.sha1("TWT583");
		System.out.println(EncryptionUtils.sha1("TWT583"));
	}

}

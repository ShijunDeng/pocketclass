package com.pocketclass.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * 测试正则表达式
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class RegularExpressionTest {

	@Test
	public void testReEx() {
		String username = "3sfa243fsfsgdf32";
		System.out.println(username.matches("[a-zA-Z]\\w{4,24}"));
		String password = "TT44334";
		System.out.println(password.length());
		System.out.println(password.matches("[a-zA-Z0-9]{6,16}"));
		String email = "556sf@163.com";
		System.out
				.println(email
						.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"));
	}

	@Test
	public void testRex1() {
		String str = "SUPerfadfadf01";
		Pattern pattern = Pattern.compile("Super.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}

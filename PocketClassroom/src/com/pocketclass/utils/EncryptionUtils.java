package com.pocketclass.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 
 * º”√‹
 * 
 * @author µÀ Àæ¸ wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-08 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class EncryptionUtils {
	public static String md5(String str) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] md5 = md.digest(str.getBytes());
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(md5);
	}

	public static String sha1(String str) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] md5 = md.digest(str.getBytes());
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(md5);
	}
}

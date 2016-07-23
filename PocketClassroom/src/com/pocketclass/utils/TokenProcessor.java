package com.pocketclass.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import sun.misc.BASE64Encoder;

/**
 * ���Ʋ�����
 * 1.���췽��˽��
 * 2.�Լ�����һ��
 * 3.�����ṩһ�������������ȡ�����Ķ���
 * @author zhanghan
 */
public class TokenProcessor {

	private static final TokenProcessor instance = new TokenProcessor();
	
	public static TokenProcessor getInstance()
	{
		return instance;
	}
	
	public String generateToken()
	{
		String token = System.currentTimeMillis() + new SecureRandom().nextInt() +"";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte []md5 = md.digest(token.getBytes());
			BASE64Encoder base64 = new BASE64Encoder();
			return base64.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} 
	}
}

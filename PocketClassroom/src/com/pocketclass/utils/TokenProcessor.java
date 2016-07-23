package com.pocketclass.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import sun.misc.BASE64Encoder;

/**
 * 令牌产生器
 * 1.构造方法私有
 * 2.自己创建一个
 * 3.对外提供一个方法，允许获取创建的对象
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

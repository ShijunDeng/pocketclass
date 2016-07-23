package com.pocketclass.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 
 * ��ȡ�û��ϴ���ͼƬ����Ƶ���ļ��ı���·��������Ϣ
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-24 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class SavePathDaoImpl {
	private static Properties savePathProp = new Properties();
	static {
		URL url = SavePathDaoImpl.class.getClassLoader().getResource(
				"savePath.properties");
		String str = url.getPath();
		try {
			InputStream in = new FileInputStream(str);
			try {
				savePathProp.load(in);
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			}
		} catch (FileNotFoundException e1) {
			throw new ExceptionInInitializerError(e1);
		}
	}

	/**
	 * 
	 * @return �������ļ��ж�ȡͷ��Ĵ洢λ��
	 */
	public static String getHeadsSavePth() {
		if (savePathProp == null) {
			return null;
		}
		String savePath = savePathProp.getProperty("headImagePath");
		return savePath;
	}

	/**
	 * 
	 * @return �������ļ��ж�ȡ��Ƶ��ͼ�Ĵ洢λ��
	 */
	public static String getVideoImagesSavePth() {
		if (savePathProp == null) {
			return null;
		}
		String savePath = savePathProp.getProperty("videoImagePath");
		return savePath;
	}

	/**
	 * 
	 * @return �õ�һ�����ԵĴ洢��ַ������ӳ�䵽�κ�һ���洢�ռ�λ��
	 */
	public static String getAbsoluteSavePth() {
		if (savePathProp == null) {
			return null;
		}
		String savePath = savePathProp.getProperty("absolutePath");
		return savePath;
	}

}

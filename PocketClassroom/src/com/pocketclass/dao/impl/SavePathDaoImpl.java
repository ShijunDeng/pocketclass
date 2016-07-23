package com.pocketclass.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 
 * 读取用户上传的图片、视频等文件的保存路径配置信息
 * 
 * @author 邓仕军 wust_dengshijun@163.com
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
	 * @return 从配置文件中读取头像的存储位置
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
	 * @return 从配置文件中读取视频截图的存储位置
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
	 * @return 得到一个绝对的存储地址，可以映射到任何一个存储空间位置
	 */
	public static String getAbsoluteSavePth() {
		if (savePathProp == null) {
			return null;
		}
		String savePath = savePathProp.getProperty("absolutePath");
		return savePath;
	}

}

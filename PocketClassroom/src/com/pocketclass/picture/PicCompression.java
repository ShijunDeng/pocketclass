package com.pocketclass.picture;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * 图像处理类
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-24 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PicCompression {

	/**
	 * 压缩图片方法
	 * 
	 * @param oldFile
	 *            将要压缩的图片 的绝对地址
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩长
	 * @param quality
	 *            压缩清晰度 <b>建议为1.0</b>
	 * @return
	 */
	public boolean zoomJPEG(String oldFile, int width, int height, float quality) {

		if (oldFile == null) {
			return false;
		}

		try {
			File file = new File(oldFile);
			if (!file.exists()) // 文件不存在时
				return false;
			/* 对服务器上的临时文件进行处理 */
			Image srcFile = ImageIO.read(file);
			if (srcFile == null)
				return false;
			// 为等比缩放计算输出的图片宽度及高度
			double rate1 = ((double) srcFile.getWidth(null)) / (double) width
					+ 0.1;
			double rate2 = ((double) srcFile.getHeight(null)) / (double) height
					+ 0.1;
			double rate = rate1 > rate2 ? rate1 : rate2;
			int new_w = (int) (srcFile.getWidth(null) / rate);
			int new_h = (int) (srcFile.getHeight(null) / rate);
			/** 宽,高设定 */
			BufferedImage tag = new BufferedImage(new_w, new_h,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
			FileOutputStream out = new FileOutputStream(oldFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			/** 压缩质量 */
			jep.setQuality(quality, true);
			encoder.encode(tag, jep);
			out.close();
			srcFile.flush();

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

}

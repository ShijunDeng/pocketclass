package com.pocketclass.exception;

/**
 *与数据库中存在的数据冲突异常： email重复
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-10 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class EmailRepeatException extends Exception {

	private static final long serialVersionUID = 201210100011L;

	public EmailRepeatException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailRepeatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmailRepeatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmailRepeatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	

}

package com.pocketclass.exception;
/**
 *与数据库中存在的数据冲突异常：用户名重复
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-09 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class UserExistException extends Exception {
	private static final long serialVersionUID = 201210091618L;

	public UserExistException() {
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		// TODO Auto-generated constructor stub
	}

}

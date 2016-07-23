package com.pocketclass.domain.post;

/**
 * 交流首页展示的实体类
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumIndexBean extends PostInfo{
	public String getIdSuper() {
		return idSuper;
	}
	public void setIdSuper(String idSuper) {
		this.idSuper = idSuper;
	}
	public String getCateGory() {
		return cateGory;
	}
	public void setCateGory(String cateGory) {
		this.cateGory = cateGory;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getLatestPostTime() {
		return latestPostTime;
	}

	public void setLatestPostTime(String latestPostTime) {
		this.latestPostTime = latestPostTime;
	}

	public String[] getMiddles() {
		return middles;
	}

	public void setMiddles(String[] middles) {
		this.middles = middles;
	}
	private String idSuper="";
	private String cateGory="";
	private String username="";//最后发帖人
	private String latestPostTime="";//最后发帖时间
	private String[] middles;
 
}

package com.pocketclass.web.beans;

/**
 * 收藏记录bean(不是直接数据库中的seriesfavorite,而是基于seriesfavorite,videoseries),
 * 而是直接显示在表单中的“* 收藏”实体
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-27 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class CourseFavorites {
	private String idVideoSeries;// 系列课程编号
	private String idMiddle;//所属分类编号
	private String middleCateGory;// 所属分类名称
	private String topic;// 系列课程名称
	private String teacher;// 讲师
	private String addTime;// 收藏 添加的时间
	private String thumbnail;//截图
	
	public String getIdVideoSeries() {
		return idVideoSeries;
	}
	public void setIdVideoSeries(String idVideoSeries) {
		this.idVideoSeries = idVideoSeries;
	}
	public String getIdMiddle() {
		return idMiddle;
	}
	public void setIdMiddle(String idMiddle) {
		this.idMiddle = idMiddle;
	}
	public String getMiddleCateGory() {
		return middleCateGory;
	}
	public void setMiddleCateGory(String middleCateGory) {
		this.middleCateGory = middleCateGory;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}

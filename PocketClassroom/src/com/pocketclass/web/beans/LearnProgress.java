package com.pocketclass.web.beans;


/**
 * 
 * 学习进度：不完全等同于数据库中的进度表，而是基于Progress和VideoSeries以及Middle
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-29 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class LearnProgress {
	private String idMiddle;//所属课程分类编号
	private String middleCateGory;// 所属分类(Middle级分类)
	private String idVideoSeries;
	private String topic;// 系列课程名称
	private int size;//学时
	private String teacher;
	private String startTime;
	private String endTime;
	private String isEnd;//是否结束
	private String thumbnail;//截图
	public String getIdMiddle() {
		return idMiddle;
	}
	public void setIdMiddle(String idMiddle) {
		this.idMiddle = idMiddle;
	}
	public String getIdVideoSeries() {
		return idVideoSeries;
	}
	public void setIdVideoSeries(String idVideoSeries) {
		this.idVideoSeries = idVideoSeries;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}

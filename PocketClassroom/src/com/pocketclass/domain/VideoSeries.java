package com.pocketclass.domain;

/**
 * 课程系列
 *  数据库表如下：
 *   CREATE TABLE VideoSeries
 *   ( idVideoSeries CHAR(16) PRIMARY KEY,
 * topic CHAR(32) NOT NULL, 
 * idTeacher CHAR(16) NOT NULL, 
 * size int,//系列视频的集数
 * introduction TEXT,
 *  linkAddress VARCHAR(256) NOT NULL,
 *   idMiddle CHAR(16) NOT NULL, 
 *   uploadTime DATETIME,
 *   thumbnail CHAR(16),
 *    CONSTRAINT FK_VideoSeries_idMiddle FOREIGN KEY (idMiddle) REFERENCES Middle(idMiddle),
 *     CONSTRAINT FK_VideoSeries_idTeacher FOREIGN KEY (idTeacher) REFERENCES Teacher(idTeacher) );
 */
public class VideoSeries {

	private String id;       //视频系列ID
	private String topic;    //视频系列名称
	private String teacherId;//讲师ID
	private int size;        //视频系列中含有的视频数目
	private String introduction;//视频系列简介
	private String linkAddress; //连接地址，下载的使用用得到
	private String parentId;  //二层分类的ID
	private String uploadTime;  //上线时间
	private String thumbnail; //视频缩略图
	
	private String teacName;
	private String middleCategory;
	
	public String getTeacName() {
		return teacName;
	}
	public void setTeacName(String teacName) {
		this.teacName = teacName;
	}
	public String getMiddleCategory() {
		return middleCategory;
	}
	public void setMiddleCategory(String middleCategory) {
		this.middleCategory = middleCategory;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
}

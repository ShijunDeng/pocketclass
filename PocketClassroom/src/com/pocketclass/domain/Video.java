package com.pocketclass.domain;

/**
 *单个课程实体
 *数据库表如下： 
 *CREATE TABLE Video(
idVideo CHAR(16) PRIMARY KEY,
title VARCHAR(40) NOT NULL,
position TINYINT NOT NULL,//第几集
size DOUBLE NOT NULL,//这集视频的大小
duration TIME NOT NULL,//视频时长
linkAddress VARCHAR(256) NOT NULL,
idVideoSeries CHAR(16) NOT NULL,
uploadTime DATETIME,
CONSTRAINT FK_Video_idVideoSeries FOREIGN KEY (idVideoSeries) REFERENCES VideoSeries(idVideoSeries)
);

 */
public class Video {

	private String id;//视频编号
	private String title;//视频名称
	private int position;//在整个视频系列中的位置
	private double size;//视频的大小
	private String duration; //视频时长
	private String linkAddress; //下载连接地址(视频播放地址)
	private String parentId; //视频系列的编号
	private String uploadTime; //上线时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
}

package com.pocketclass.domain;

/**
 *�����γ�ʵ��
 *���ݿ�����£� 
 *CREATE TABLE Video(
idVideo CHAR(16) PRIMARY KEY,
title VARCHAR(40) NOT NULL,
position TINYINT NOT NULL,//�ڼ���
size DOUBLE NOT NULL,//�⼯��Ƶ�Ĵ�С
duration TIME NOT NULL,//��Ƶʱ��
linkAddress VARCHAR(256) NOT NULL,
idVideoSeries CHAR(16) NOT NULL,
uploadTime DATETIME,
CONSTRAINT FK_Video_idVideoSeries FOREIGN KEY (idVideoSeries) REFERENCES VideoSeries(idVideoSeries)
);

 */
public class Video {

	private String id;//��Ƶ���
	private String title;//��Ƶ����
	private int position;//��������Ƶϵ���е�λ��
	private double size;//��Ƶ�Ĵ�С
	private String duration; //��Ƶʱ��
	private String linkAddress; //�������ӵ�ַ(��Ƶ���ŵ�ַ)
	private String parentId; //��Ƶϵ�еı��
	private String uploadTime; //����ʱ��
	
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

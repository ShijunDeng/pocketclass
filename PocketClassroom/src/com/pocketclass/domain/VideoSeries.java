package com.pocketclass.domain;

/**
 * �γ�ϵ��
 *  ���ݿ�����£�
 *   CREATE TABLE VideoSeries
 *   ( idVideoSeries CHAR(16) PRIMARY KEY,
 * topic CHAR(32) NOT NULL, 
 * idTeacher CHAR(16) NOT NULL, 
 * size int,//ϵ����Ƶ�ļ���
 * introduction TEXT,
 *  linkAddress VARCHAR(256) NOT NULL,
 *   idMiddle CHAR(16) NOT NULL, 
 *   uploadTime DATETIME,
 *   thumbnail CHAR(16),
 *    CONSTRAINT FK_VideoSeries_idMiddle FOREIGN KEY (idMiddle) REFERENCES Middle(idMiddle),
 *     CONSTRAINT FK_VideoSeries_idTeacher FOREIGN KEY (idTeacher) REFERENCES Teacher(idTeacher) );
 */
public class VideoSeries {

	private String id;       //��Ƶϵ��ID
	private String topic;    //��Ƶϵ������
	private String teacherId;//��ʦID
	private int size;        //��Ƶϵ���к��е���Ƶ��Ŀ
	private String introduction;//��Ƶϵ�м��
	private String linkAddress; //���ӵ�ַ�����ص�ʹ���õõ�
	private String parentId;  //��������ID
	private String uploadTime;  //����ʱ��
	private String thumbnail; //��Ƶ����ͼ
	
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

package com.pocketclass.web.beans;

/**
 * �ղؼ�¼bean(����ֱ�����ݿ��е�seriesfavorite,���ǻ���seriesfavorite,videoseries),
 * ����ֱ����ʾ�ڱ��еġ�* �ղء�ʵ��
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-27 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class CourseFavorites {
	private String idVideoSeries;// ϵ�пγ̱��
	private String idMiddle;//����������
	private String middleCateGory;// ������������
	private String topic;// ϵ�пγ�����
	private String teacher;// ��ʦ
	private String addTime;// �ղ� ��ӵ�ʱ��
	private String thumbnail;//��ͼ
	
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

package com.pocketclass.domain.message;

/**
 * վ��ͨ����Ϣʵ��
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-06 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class Message {
	private String sendUserName="";/* �������˺� */
	private String addreUserName="";/* �������˺� */
	private String sendTime;/* ����ʱ�� */
	private String title="";
	private String content="";
	private String isRead="";
	
	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getAddreUserName() {
		return addreUserName;
	}

	public void setAddreUserName(String addreUserName) {
		this.addreUserName = addreUserName;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
}

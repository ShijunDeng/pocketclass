package com.pocketclass.domain.message;
/**
 * ��Ϣ��λ�ã��緢���䣬�����䣬�ղ����
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-07 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class MessageLocation {

	
	private String idLocationCategory ;
	private String sendUserName;/*�������˺�*/
	private String username;/*�����˭*/
	private String sendTime ;/*����ʱ��*/
	public String getIdLocationCategory() {
		return idLocationCategory;
	}
	public void setIdLocationCategory(String idLocationCategory) {
		this.idLocationCategory = idLocationCategory;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}

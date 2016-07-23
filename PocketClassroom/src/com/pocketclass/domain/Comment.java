package com.pocketclass.domain;


/**
 * �γ�ϵ�е�������
 * @author zhanghan
 *CREATE TABLE Comment(
idVideoSeries CHAR(16) ,
username VARCHAR(32) ,
content TEXT NOT NULL,
time DATETIME NOT NULL,
CONSTRAINT PK_Comment PRIMARY KEY(idVideoSeries,username),
CONSTRAINT FK_Comment_username FOREIGN KEY (username) REFERENCES Account(username),
CONSTRAINT FK_Comment_idVideoSeries FOREIGN KEY (idVideoSeries) REFERENCES VideoSeries(idVideoSeries)
);
 */
public class Comment {

	private String idVideoSeries;//��Ƶϵ�б��
	private String username;//�û���
	private String content;//��������
	private String time;//����ʱ��
	public String getIdVideoSeries() {
		return idVideoSeries;
	}
	public void setIdVideoSeries(String idVideoSeries) {
		this.idVideoSeries = idVideoSeries;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}

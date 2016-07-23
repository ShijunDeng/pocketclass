package com.pocketclass.domain;


/**
 * �ʼ�
 * @author zhanghan
 *CREATE TABLE Note(
idNote INT NOT NULL AUTO_INCREMENT,
username VARCHAR(32) NOT NULL,
idSuper CHAR(16) NOT NULL,//ϵͳ���� 
idCustom INT,//�û��Զ������ 
title VARCHAR(128) NOT NULL,
content TEXT NOT NULL,
isdraft ENUM('0','1') DEFAULT '0',//�Ƿ�Ϊ�ݸ壬0���ǣ�1�ǣ�Ĭ��0 
cmtallow ENUM('0','1') DEFAULT '1',//�Ƿ���������,0������1����Ĭ��1 
viewAmount INT DEFAULT '0',//����� 
cmtAmount INT DEFAULT '0',//������Ŀ 
tags VARCHAR(200),//��ǩ���ԣ����� 
postIP VARCHAR(20) NOT NULL,//������IP��ַ 
addTime DATETIME NOT NULL,//�����ʼ�ʱ�� 
updateTime DATETIME NOT NULL,//���һ�α༭�ʼ�ʱ�� 
CONSTRAINT PK_Note PRIMARY KEY(idNote),
CONSTRAINT FK_Note_username FOREIGN KEY (username) REFERENCES Account(username),
CONSTRAINT FK_Note_cateGory FOREIGN KEY (idSuper) REFERENCES Super(idSuper),
CONSTRAINT FK_Note_customName FOREIGN KEY (idCustom) REFERENCES CustomCategory(idCustom)
);
 */
public class Note {

	private int idNote;
	private String username;
	private String idSuper;
	private int idCustom;
	private String title;
	private String content;
	private int isdraft = 0;//�Ƿ�Ϊ�ݸ�,tureΪ�ǣ�falseΪ���ǣ�Ĭ��Ϊ����
	private int cmtallow = 1;//�Ƿ��������ۣ�trueΪ����falseΪ������Ĭ��Ϊ����
	private int viewAmount = 0;
	private int cmtAmount = 0;
	private String tags = " ";
	private String postIP;
	private String addTime;
	private String updateTime;
	
	private String headPorAdd;//�û�ͷ��
	private String customName;//�Զ�������
	private String categoryName;//ϵͳ�Զ������
	
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getHeadPorAdd() {
		return headPorAdd;
	}
	public void setHeadPorAdd(String headPorAdd) {
		this.headPorAdd = headPorAdd;
	}
	public int getIdNote() {
		return idNote;
	}
	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdSuper() {
		return idSuper;
	}
	public void setIdSuper(String idSuper) {
		this.idSuper = idSuper;
	}
	public int getIdCustom() {
		return idCustom;
	}
	public void setIdCustom(int idCustom) {
		this.idCustom = idCustom;
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
	
	public int getIsdraft() {
		return isdraft;
	}
	public void setIsdraft(int isdraft) {
		this.isdraft = isdraft;
	}
	public int getCmtallow() {
		return cmtallow;
	}
	public void setCmtallow(int cmtallow) {
		this.cmtallow = cmtallow;
	}
	public int getViewAmount() {
		return viewAmount;
	}
	public void setViewAmount(int viewAmount) {
		this.viewAmount = viewAmount;
	}
	public int getCmtAmount() {
		return cmtAmount;
	}
	public void setCmtAmount(int cmtAmount) {
		this.cmtAmount = cmtAmount;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getPostIP() {
		return postIP;
	}
	public void setPostIP(String postIP) {
		this.postIP = postIP;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}

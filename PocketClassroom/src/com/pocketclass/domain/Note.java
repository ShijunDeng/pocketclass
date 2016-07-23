package com.pocketclass.domain;


/**
 * 笔记
 * @author zhanghan
 *CREATE TABLE Note(
idNote INT NOT NULL AUTO_INCREMENT,
username VARCHAR(32) NOT NULL,
idSuper CHAR(16) NOT NULL,//系统分类 
idCustom INT,//用户自定义分类 
title VARCHAR(128) NOT NULL,
content TEXT NOT NULL,
isdraft ENUM('0','1') DEFAULT '0',//是否为草稿，0不是，1是，默认0 
cmtallow ENUM('0','1') DEFAULT '1',//是否允许评论,0不允许，1允许，默认1 
viewAmount INT DEFAULT '0',//浏览量 
cmtAmount INT DEFAULT '0',//评论数目 
tags VARCHAR(200),//标签：以，隔开 
postIP VARCHAR(20) NOT NULL,//发布者IP地址 
addTime DATETIME NOT NULL,//发布笔记时间 
updateTime DATETIME NOT NULL,//最后一次编辑笔记时间 
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
	private int isdraft = 0;//是否为草稿,ture为是，false为不是，默认为不是
	private int cmtallow = 1;//是否允许评论，true为允许，false为不允许，默认为允许
	private int viewAmount = 0;
	private int cmtAmount = 0;
	private String tags = " ";
	private String postIP;
	private String addTime;
	private String updateTime;
	
	private String headPorAdd;//用户头像
	private String customName;//自定义名称
	private String categoryName;//系统自定义分类
	
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

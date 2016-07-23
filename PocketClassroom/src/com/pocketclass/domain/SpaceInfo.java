package com.pocketclass.domain;
/**
 * 空间信息
 * @author Administrator
 *CREATE TABLE SpaceInfo(
	username VARCHAR(32) NOT NULL,
	viewAmount INT DEFAULT '0',//总访问量
	cmtAmount INT DEFAULT '0',//笔记总数目
	noteAmount INT DEFAULT '0',//评论总数目
	CONSTRAINT PK_SpaceInfo PRIMARY KEY(username),
	CONSTRAINT FK_SpaceInfo_username FOREIGN KEY (username) REFERENCES Account(username)
);
 */
public class SpaceInfo {

	private String username;
	private String headPorAdd;//用户的头像
	private int viewAmount =0;
	private int cmtAmount =0;
	private int noteAmount =0;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public int getNoteAmount() {
		return noteAmount;
	}
	public void setNoteAmount(int noteAmount) {
		this.noteAmount = noteAmount;
	}
	public String getHeadPorAdd() {
		return headPorAdd;
	}
	public void setHeadPorAdd(String headPorAdd) {
		this.headPorAdd = headPorAdd;
	}
	
}

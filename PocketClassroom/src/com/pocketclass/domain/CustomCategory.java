package com.pocketclass.domain;
/**
 * 用户自定义分类
 * @author zhanghan
 *CREATE TABLE CustomCategory(
	idCustom INT NOT NULL AUTO_INCREMENT,
	customName CHAR(32) NOT NULL,
	username VARCHAR(32) NOT NULL,
	isopen ENUM('0','1') DEFAULT '0',//来访者是否可见，0表示所有人，1表示仅自己，默认为0
	CONSTRAINT PK_CustomCategory PRIMARY KEY(idCustom),
	CONSTRAINT FK_CustomCategory_username FOREIGN KEY (username) REFERENCES Account(username)
);
 */
public class CustomCategory {

	private String idCustom;
	private String customName;
	private String username; 
	private int isopen = 1;//是否是公开的,0表示不公开，1表示公开，默认公开
	private int noteAmount = 0;//自定义分类下的笔记数目，默认为0
	
	public String getIdCustom() {
		return idCustom;
	}
	public void setIdCustom(String idCustom) {
		this.idCustom = idCustom;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getIsopen() {
		return isopen;
	}
	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}
	public int getNoteAmount() {
		return noteAmount;
	}
	public void setNoteAmount(int noteAmount) {
		this.noteAmount = noteAmount;
	}
}

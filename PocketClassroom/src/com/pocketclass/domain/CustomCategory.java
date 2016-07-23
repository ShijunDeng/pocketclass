package com.pocketclass.domain;
/**
 * �û��Զ������
 * @author zhanghan
 *CREATE TABLE CustomCategory(
	idCustom INT NOT NULL AUTO_INCREMENT,
	customName CHAR(32) NOT NULL,
	username VARCHAR(32) NOT NULL,
	isopen ENUM('0','1') DEFAULT '0',//�������Ƿ�ɼ���0��ʾ�����ˣ�1��ʾ���Լ���Ĭ��Ϊ0
	CONSTRAINT PK_CustomCategory PRIMARY KEY(idCustom),
	CONSTRAINT FK_CustomCategory_username FOREIGN KEY (username) REFERENCES Account(username)
);
 */
public class CustomCategory {

	private String idCustom;
	private String customName;
	private String username; 
	private int isopen = 1;//�Ƿ��ǹ�����,0��ʾ��������1��ʾ������Ĭ�Ϲ���
	private int noteAmount = 0;//�Զ�������µıʼ���Ŀ��Ĭ��Ϊ0
	
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

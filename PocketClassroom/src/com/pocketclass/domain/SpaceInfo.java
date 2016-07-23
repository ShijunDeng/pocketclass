package com.pocketclass.domain;
/**
 * �ռ���Ϣ
 * @author Administrator
 *CREATE TABLE SpaceInfo(
	username VARCHAR(32) NOT NULL,
	viewAmount INT DEFAULT '0',//�ܷ�����
	cmtAmount INT DEFAULT '0',//�ʼ�����Ŀ
	noteAmount INT DEFAULT '0',//��������Ŀ
	CONSTRAINT PK_SpaceInfo PRIMARY KEY(username),
	CONSTRAINT FK_SpaceInfo_username FOREIGN KEY (username) REFERENCES Account(username)
);
 */
public class SpaceInfo {

	private String username;
	private String headPorAdd;//�û���ͷ��
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

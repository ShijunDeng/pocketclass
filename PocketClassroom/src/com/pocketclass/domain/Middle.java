package com.pocketclass.domain;
/**
 * �ڶ�������
 * CREATE TABLE Middle(
 *idMiddle CHAR(16) PRIMARY KEY,
 *middleCateGory CHAR(32) NOT NULL,
 *idSuper CHAR(16) NOT NULL,
 *CONSTRAINT FK_Middle_idSuper FOREIGN KEY (idSuper) REFERENCES Super(idSuper) 
 *);
 */
public class Middle {

	private String id;//�ڶ��������id
	private String middleCategoryName;//�������
	private String parentId;//�������id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMiddleCategoryName() {
		return middleCategoryName;
	}
	public void setMiddleCategoryName(String middleCategoryName) {
		this.middleCategoryName = middleCategoryName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}

package com.pocketclass.domain;
/**
 * ��һ������
 * ���ݿ�����£�
 * CREATE TABLE SUPER(
 * idSuper CHAR(16) PRIMARY KEY,
 * cateGory CHAR(32) NOT NULL
 * );
 */
public class Super {

	private String id;//������ID
	private String categoryName;//�������
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	} 
}

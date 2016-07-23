package com.pocketclass.domain;
/**
 * 第一级分类
 * 数据库表如下：
 * CREATE TABLE SUPER(
 * idSuper CHAR(16) PRIMARY KEY,
 * cateGory CHAR(32) NOT NULL
 * );
 */
public class Super {

	private String id;//主分类ID
	private String categoryName;//类别名称
	
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

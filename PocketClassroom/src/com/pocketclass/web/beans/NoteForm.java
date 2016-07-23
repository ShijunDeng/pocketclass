package com.pocketclass.web.beans;


public class NoteForm {

	private String username;
	private String idSuper;
	private	String idCustom;
	private String title;
	private String content;
	private int cmtallow = 1;//是否允许评论，true为允许，false为不允许，默认为允许
	private String tags;
//	private Map<String, String> tips = new HashMap<String, String>();
	
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
	public String getIdCustom() {
		return idCustom;
	}
	public void setIdCustom(String idCustom) {
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
	public int getCmtallow() {
		return cmtallow;
	}
	public void setCmtallow(int cmtallow) {
		this.cmtallow = cmtallow;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
}

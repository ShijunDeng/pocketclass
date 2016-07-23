package com.pocketclass.domain;


/**
 * 笔记评论
 * @author zhanghan
 * CREATE TABLE NoteComment(
idNoteComment INT AUTO_INCREMENT,
idNote INT NOT NULL,
username VARCHAR(32) NOT NULL,
parentIdNoteComment INT NOT NULL DEFAULT '0',//父评论ID 
content TEXT NOT NULL,
replyedTimes INT DEFAULT '0',//被评论次数
postIP VARCHAR(20) NOT NULL,
time DATETIME NOT NULL,
CONSTRAINT PK_NoteComment PRIMARY KEY(idNoteComment),
CONSTRAINT FK_NoteComment_username FOREIGN KEY (username) REFERENCES Account(username),
CONSTRAINT FK_NoteComment_idNote FOREIGN KEY (idNote) REFERENCES Note(idNote)
);
 */
public class NoteComment {

	private int idNoteComment;
	private int idNote;
	private String username;
	private int parentId = 0;
	private String content;
	private int replyedTimes = 0;
	private String postIP;
	private String time;
	public int getIdNoteComment() {
		return idNoteComment;
	}
	public void setIdNoteComment(int idNoteComment) {
		this.idNoteComment = idNoteComment;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReplyedTimes() {
		return replyedTimes;
	}
	public void setReplyedTimes(int replyedTimes) {
		this.replyedTimes = replyedTimes;
	}
	public String getPostIP() {
		return postIP;
	}
	public void setPostIP(String postIP) {
		this.postIP = postIP;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}

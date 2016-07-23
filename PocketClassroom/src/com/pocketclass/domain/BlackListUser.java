package com.pocketclass.domain;
/**
 * 黑名单实体
 * @author 邓仕军
 *
 */
public class BlackListUser {
private String username;
private String time;
private int causeTag ;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getCauseTag() {
	return causeTag;
}
public void setCauseTag(int causeTag) {
	this.causeTag = causeTag;
}


}

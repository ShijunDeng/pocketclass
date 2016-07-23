package com.pocketclass.domain.post;

/**
 * 一类帖子的信息（如java分类的帖子）
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-15 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PostInfo {
	private String idPostInfo="";
	private int postsNum=0;/* 一种分类下帖子的总数 */
	private int topicNum=0;/* 一种分类下帖子的主题数 */
	private String latestIdPost="";/* 最近一次帖子id */

	public String getIdPostInfo() {
		return idPostInfo;
	}

	public void setIdPostInfo(String idPostInfo) {
		this.idPostInfo = idPostInfo;
	}

	public int getPostsNum() {
		return postsNum;
	}

	public void setPostsNum(int postsNum) {
		this.postsNum = postsNum;
	}

	public int getTopicNum() {
		return topicNum;
	}

	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}

	public String getLatestIdPost() {
		return latestIdPost;
	}

	public void setLatestIdPost(String latestIdPost) {
		this.latestIdPost = latestIdPost;
	}
}

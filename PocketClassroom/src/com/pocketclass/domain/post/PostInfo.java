package com.pocketclass.domain.post;

/**
 * һ�����ӵ���Ϣ����java��������ӣ�
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-15 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PostInfo {
	private String idPostInfo="";
	private int postsNum=0;/* һ�ַ��������ӵ����� */
	private int topicNum=0;/* һ�ַ��������ӵ������� */
	private String latestIdPost="";/* ���һ������id */

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

package com.pocketclass.domain.post;

/**
 * ��������ʵ�壺һ������
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-14 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class Post {
	// �û���+����ʱ����ʵ�Ѿ�������Ϊ����������postId��Ϊ�����ݲ����ͳ���ʵ�ֵ�ʱ�򷽱�
	private String idPost;
	private String username;
	private String title;
	private String idMiddle;
	private String content;
	private String postTime;
	private int browNum = 0;// �������
	private int replyNum = 0;
	private String headPorAdd="no_img.jpg";// ͷ���ַ

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdMiddle() {
		return idMiddle;
	}

	public void setIdMiddle(String idMiddle) {
		this.idMiddle = idMiddle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public int getBrowNum() {
		return browNum;
	}

	public void setBrowNum(int browNum) {
		this.browNum = browNum;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public String getHeadPorAdd() {
		return headPorAdd;
	}

	public void setHeadPorAdd(String headPorAdd) {
		this.headPorAdd = headPorAdd;
	}
}

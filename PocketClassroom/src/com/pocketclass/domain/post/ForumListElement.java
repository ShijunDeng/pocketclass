package com.pocketclass.domain.post;

import java.util.ArrayList;

/**
 * 帖子页面的元素：形式为二级帖及其附属的子帖（三级）链表
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumListElement {
	private int order;//楼层时使用
	private PostReply mPostReply;// 二级帖
	private ArrayList<PostReply> lPostReplys;// 二级帖对应的回复

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public PostReply getmPostReply() {
		return mPostReply;
	}

	public void setmPostReply(PostReply mPostReply) {
		this.mPostReply = mPostReply;
	}

	public ArrayList<PostReply> getlPostReplys() {
		return lPostReplys;
	}

	public void setlPostReplys(ArrayList<PostReply> lPostReplys) {
		this.lPostReplys = lPostReplys;
	}

}

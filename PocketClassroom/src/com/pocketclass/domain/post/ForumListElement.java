package com.pocketclass.domain.post;

import java.util.ArrayList;

/**
 * ����ҳ���Ԫ�أ���ʽΪ���������丽��������������������
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class ForumListElement {
	private int order;//¥��ʱʹ��
	private PostReply mPostReply;// ������
	private ArrayList<PostReply> lPostReplys;// ��������Ӧ�Ļظ�

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

package com.pocketclass.domain.post;

/**
 * ��������ʵ�壺���������ظ����ӣ��̳���Post���Ӹ���id����
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-15 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PostReply extends Post {
	private String parentPostId;// �������
	private String tableCategory;// �����������߶���

	public String getParentPostId() {
		return parentPostId;
	}

	public void setParentPostId(String parentPostId) {
		this.parentPostId = parentPostId;
	}

	public String getTableCategory() {
		return tableCategory;
	}

	public void setTableCategory(String tableCategory) {
		this.tableCategory = tableCategory;
	}

}

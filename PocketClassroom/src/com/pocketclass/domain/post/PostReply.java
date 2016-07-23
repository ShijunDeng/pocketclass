package com.pocketclass.domain.post;

/**
 * 子贴帖子实体：二级三级回复帖子，继承自Post增加父贴id属性
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-15 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PostReply extends Post {
	private String parentPostId;// 父贴编号
	private String tableCategory;// 表：三级级或者二级

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

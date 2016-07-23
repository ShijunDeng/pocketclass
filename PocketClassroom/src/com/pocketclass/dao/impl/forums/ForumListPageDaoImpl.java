package com.pocketclass.dao.impl.forums;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.pocketclass.dao.PageDataDao;
import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.post.ForumListElement;
import com.pocketclass.domain.post.PostReply;

/**
 * 帖子分页显示请求处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-16 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
@SuppressWarnings("unchecked")
public class ForumListPageDaoImpl implements PageDataDao {
	private int pageRecordsNum = 10;// 页面记录条数
	private int currentPageTag = 1;// 当前页面标记

	public int getPageRecordsNum() {
		return pageRecordsNum;
	}

	public void setPageRecordsNum(int pageRecordsNum) {
		this.pageRecordsNum = pageRecordsNum;
	}

	public int getCurrentPageTag() {
		return currentPageTag;
	}

	public void setCurrentPageTag(int currentPageTag) {
		this.currentPageTag = currentPageTag;
	}

	@Override
	public ArrayList<ForumListElement> getPageData(int currentPage,
			int recordNum, String keyword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<ForumListElement> forumListElements = new ArrayList<ForumListElement>();
		PostDaoImpl postDaoImpl = new PostDaoImpl();
		if (currentPage < 1)
			throw new IllegalArgumentException("参数不合法：" + currentPage);
		String sqlQuery;
		int order = 0;
		// content
		if (recordNum < 0) {
			sqlQuery = "SELECT MPostReply.idMPostReply,MPostReply.username,MPostReply.title,MPostReply.content,MPostReply.postTime,MPostReply.browNum,MPostReply.replyNum,headPorAdd"
					+ " FROM MPostReply,Account "
					+ " WHERE MPostReply.parentPostId='"
					+ keyword
					+ "' AND MPostReply.username=Account.username ORDER BY MPostReply.postTime ASC  limit "
					+ Math.max(0, (currentPage - 1) * this.getPageRecordsNum()
							+ recordNum) + "," + Math.abs(recordNum);
			order = Math.max(0, (currentPage - 1) * this.getPageRecordsNum());
		} else {
			sqlQuery = "SELECT MPostReply.idMPostReply,MPostReply.username,MPostReply.title,MPostReply.content,MPostReply.postTime,MPostReply.browNum,MPostReply.replyNum,headPorAdd "
					+ " FROM MPostReply,Account"
					+ " WHERE MPostReply.parentPostId='"
					+ keyword
					+ "' AND MPostReply.username=Account.username ORDER BY MPostReply.postTime ASC  limit "
					+ (currentPage - 1)
					* this.getPageRecordsNum()
					+ ","
					+ recordNum;
			order = (currentPage - 1) * this.getPageRecordsNum()+1; 
		}
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			while (rs.next()) {

				PostReply mPostReply = new PostReply();
				ForumListElement forumListElement = new ForumListElement();
				forumListElement.setOrder(order++);
				mPostReply.setIdPost(rs.getString("idMPostReply"));
				mPostReply.setUsername(rs.getString("username"));
				mPostReply.setTitle(rs.getString("title"));
				// mPostReply.setIdMiddle(rs.getString("idMiddle"));
				mPostReply.setPostTime(sdf.format(sdf.parse(rs
						.getString("postTime"))));
				mPostReply.setContent(rs.getString("content"));
				mPostReply.setReplyNum(rs.getInt("replyNum"));
				mPostReply.setBrowNum(rs.getInt("browNum"));
				mPostReply.setParentPostId(keyword);
				mPostReply.setHeadPorAdd(rs.getString("headPorAdd"));
				forumListElement.setmPostReply(mPostReply);
				forumListElements.add(forumListElement);
			}
			for (ForumListElement forumListElement : forumListElements) {
				forumListElement.setlPostReplys(postDaoImpl
						.getLPostReplysByParentId(forumListElement
								.getmPostReply().getIdPost()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return forumListElements;
		}
		return forumListElements;
	}

	@Override
	public ArrayList<ForumListElement> CurrentPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag(),
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * 返回下一页数据
	 */
	@Override
	public ArrayList<ForumListElement> NextPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() + 1,
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * 返回前一页数据
	 */
	@Override
	public ArrayList<ForumListElement> PriorPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() - 1,
				this.getPageRecordsNum(), keyword);
	}

	@Override
	public int getTotalPagesNum(String keyword) {
		if (this.getRecordsNum(keyword) % this.getPageRecordsNum() == 0) {
			return this.getRecordsNum(keyword) / this.getPageRecordsNum();
		}
		return (this.getRecordsNum(keyword) / this.getPageRecordsNum()) + 1;
	}

	@Override
	public int getRecordsNum(String keyword) {
		return new PostDaoImpl().getPostReplyNum(keyword);
	}

}

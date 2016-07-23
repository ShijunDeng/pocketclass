package com.pocketclass.dao.impl.forums;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.pocketclass.dao.PageDataDao;
import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.post.Post;

/**
 * 帖子分页显示请求处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-15 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
@SuppressWarnings("unchecked")
public class PostsPageDaoImpl implements PageDataDao {
	private int pageRecordsNum = 10;// 页面记录条数
	private int currentPageTag = 1;// 当前页面标记

	@Override
	public ArrayList<Post> getPageData(int currentPage, int recordNum,
			String keyword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<Post> posts = new ArrayList<Post>();
		if (currentPage < 1)
			throw new IllegalArgumentException("参数不合法：" + currentPage);
		String sqlQuery;
		//content
		if (recordNum < 0) {
			sqlQuery = "SELECT Post.idPost,Post.username,Post.title,Post.idMiddle,Post.postTime,Post.browNum,Post.replyNum " +
					" FROM Post,Middle" +
					" WHERE Post.idMiddle=Middle.idMiddle AND Middle.idSuper='"+keyword
					+ "' ORDER BY Post.postTime DESC  limit "
					+ Math.max(0, (currentPage - 1) * this.getPageRecordsNum()
							+ recordNum) + "," + Math.abs(recordNum);
		} else {
			sqlQuery = "SELECT Post.idPost,Post.username,Post.title,Post.idMiddle,Post.postTime,Post.browNum,Post.replyNum " +
					" FROM Post,Middle" +
					" WHERE Post.idMiddle=Middle.idMiddle AND Middle.idSuper='"+keyword
					+ "' ORDER BY Post.postTime DESC  limit "
					+ (currentPage - 1) * this.getPageRecordsNum() + ","
					+ recordNum;
		}
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			if (rs != null) {
				while (rs.next()) {
					Post post = new Post();
					post.setIdPost(rs.getString("idPost"));
					post.setIdMiddle(rs.getString("idMiddle"));
					post.setUsername(rs.getString("username"));
					post.setTitle(rs.getString("title"));
					//内容先不设置
					post.setPostTime(sdf.format(sdf.parse(rs.getString("postTime"))));
					post.setBrowNum(rs.getInt("browNum"));
					post.setReplyNum(rs.getInt("replyNum"));
					posts.add(post);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return posts;
		}
		return posts;
	}

	public void setPageRecordsNum(int pageRecordsNum) {
		this.pageRecordsNum = pageRecordsNum;
	}

	/**
	 * 返回当前页数据
	 */
	@Override
	public ArrayList<Post> CurrentPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag(),
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * 返回下一页数据
	 */
	@Override
	public ArrayList<Post> NextPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() + 1,
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * 返回前一页数据
	 */
	@Override
	public ArrayList<Post> PriorPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() - 1,
				this.getPageRecordsNum(), keyword);
	}


	public int getCurrentPageTag() {
		return currentPageTag;
	}

	public void setCurrentPageTag(int currentPageTag) {
		this.currentPageTag = currentPageTag;
	}

	public int getPageRecordsNum() {
		return pageRecordsNum;
	}

	/**
	 * 返回一共多少页
	 */
	@Override
	public int getTotalPagesNum(String keyword) {
		if (this.getRecordsNum(keyword) % this.getPageRecordsNum() == 0) {
			return this.getRecordsNum(keyword) / this.getPageRecordsNum();
		}
		return (this.getRecordsNum(keyword) / this.getPageRecordsNum()) + 1;
	}

	@Override
	public int getRecordsNum(String keyword) {
		return new PostInfoDaoImpl().gettopicNum(keyword);
	}

}

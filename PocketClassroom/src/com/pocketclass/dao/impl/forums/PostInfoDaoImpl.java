package com.pocketclass.dao.impl.forums;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.Super;
import com.pocketclass.domain.post.ForumIndexBean;
import com.pocketclass.domain.post.Post;
import com.pocketclass.domain.post.PostInfo;

/**
 * 一类帖子信息的处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-15 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PostInfoDaoImpl {

	public PostInfo getPostInfo(String idPostInfo) {
		String sqlQuery = "SELECT * FROM PostInfo WHERE idPostInfo='"
				+ idPostInfo + "'";
		PostInfo postInfo;
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			postInfo = new PostInfo();
			while (rs.next()) {
				postInfo.setIdPostInfo(rs.getString("idPostInfo"));
				postInfo.setLatestIdPost(rs.getString("latestIdPost"));
				postInfo.setPostsNum(rs.getInt("postsNum"));
				postInfo.setTopicNum(rs.getInt("topicNum"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return postInfo;
	}

	/**
	 * 
	 * @param idPostInfo
	 * @return 返回分类编号为idPostInfo类的帖子回复数
	 */
	public int gettopicNum(String idPostInfo) {
		String sqlQuery = "SELECT topicNum FROM PostInfo WHERE idPostInfo='"
				+ idPostInfo + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("topicNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * 
	 * @param idPostInfo
	 * @return 返回分类编号为idPostInfo类的帖子话题数
	 */
	public int getPostsNum(String idPostInfo) {
		String sqlQuery = "SELECT postsNum FROM PostInfo WHERE idPostInfo='"
				+ idPostInfo + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("postsNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * 
	 * @param idPostInfo
	 * @return 返回分类编号为idPostInfo类的最近发帖
	 */
	public Post getLatestPost(String idPostInfo) {
		String sqlQuery = "SELECT latestIdPost FROM PostInfo WHERE idPostInfo='"
				+ idPostInfo + "'";
		String idPost = null;
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				idPost = rs.getString("latestIdPost");
			}
			if (idPost == null)
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return new PostDaoImpl().getPostByPostID(idPost);
	}

	/**
	 * 
	 * @param idSuper
	 * @return 返回分类编号为idPostInfo=idSuper类的PostInfo信息以及Super信息
	 */
	public ForumIndexBean getForumIndexBean(String idSuper) {
		ForumIndexBean forumIndexBean = null;
		String sqlQuery = "SELECT idSuper,cateGory FROM Super WHERE idSuper='"
				+ idSuper + "'";
		Super supe = null;
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				supe = new Super();
				supe.setId(idSuper);
				supe.setCategoryName(rs.getString("cateGory"));
				PostInfo postInfo = getPostInfo(idSuper);
				if (postInfo != null) {
					forumIndexBean = new ForumIndexBean();
					forumIndexBean.setIdSuper(idSuper);
					forumIndexBean.setCateGory(supe.getCategoryName());
					forumIndexBean.setLatestIdPost(postInfo.getLatestIdPost());
					forumIndexBean.setIdPostInfo(postInfo.getIdPostInfo());
					forumIndexBean.setPostsNum(postInfo.getPostsNum());
					forumIndexBean.setTopicNum(postInfo.getTopicNum());
					Post latestpost = new PostDaoImpl()
							.getPostByPostID(forumIndexBean.getLatestIdPost());
					if(latestpost==null){//PostInfo中的最近帖子有可能被管理员删除
						latestpost=getLatestPost();
					}
					forumIndexBean.setUsername(latestpost.getUsername());
					forumIndexBean.setLatestPostTime(latestpost.getPostTime());
					forumIndexBean
							.setMiddles(getMiddleCategoryOfSuper(idSuper));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return forumIndexBean;
	}

	/**
	 * 
	 * @return 返回Super表中所有记录的PostInfo信息以及Super信息（直接显示在表单中的）
	 */
	public List<ForumIndexBean> getForumIndexBeans() {
		List<ForumIndexBean> forumIndexBeans = new ArrayList<ForumIndexBean>();
		String sqlQuery = "SELECT idSuper FROM Super";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			List<String> idSupers = new ArrayList<String>();
			while (rs.next()) {
				idSupers.add(rs.getString("idSuper"));
			}
			for (String idSuper : idSupers) {
				ForumIndexBean forumIndexBean = getForumIndexBean(idSuper);
				if (forumIndexBean == null) {
					forumIndexBean = new ForumIndexBean();
				}
				forumIndexBeans.add(forumIndexBean);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forumIndexBeans;
	}

	/**
	 * 
	 * @param idSuper
	 * @return 得到Middle中所有idSuper=idSuper的middleCateGory,以字符数组返回
	 */
	public String[] getMiddleCategoryOfSuper(String idSuper) {
		String sqlQuery = "SELECT middleCateGory FROM Middle WHERE idSuper='"
				+ idSuper + "'";
		ArrayList<String> middles = new ArrayList<String>();
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			while (rs.next()) {
				middles.add(rs.getString("middleCateGory"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return middles.toArray(new String[middles.size()]);

	}

	/**
	 * 
	 * @param idSuper
	 * @return 得到Middle中所有idSuper=idSuper的middle,以Map返回
	 */
	public Map<String, String> getMiddleOfSuper(String idSuper) {
		String sqlQuery = "SELECT idMiddle,middleCateGory FROM Middle WHERE idSuper='"
				+ idSuper + "'";
		Map<String, String> middles = new HashMap<String, String>();
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			while (rs.next()) {
				middles.put(rs.getString("idMiddle"),
						rs.getString("middleCateGory"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return middles;

	}

	public boolean refreshForumIndexBean(ForumIndexBean forumIndexBean) {
		String sqlQuery = "SELECT idSuper,cateGory FROM Super WHERE idSuper='"
				+ forumIndexBean.getIdSuper() + "'";
		// 先保存要刷新的量，如果刷新异常则将这些值还原
		String latestIdPost = forumIndexBean.getLatestIdPost();
		String idPostInfo = forumIndexBean.getIdPostInfo();
		int postsNum = forumIndexBean.getPostsNum();
		int topicNum = forumIndexBean.getTopicNum();
		String latestPostTime = forumIndexBean.getLatestPostTime();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				PostInfo postInfo = getPostInfo(forumIndexBean.getIdSuper());
				if (postInfo != null) {
					forumIndexBean.setLatestIdPost(postInfo.getLatestIdPost());
					forumIndexBean.setIdPostInfo(postInfo.getIdPostInfo());
					forumIndexBean.setPostsNum(postInfo.getPostsNum());
					forumIndexBean.setTopicNum(postInfo.getTopicNum());
					Post latestpost = new PostDaoImpl()
							.getPostByPostID(forumIndexBean.getLatestIdPost());
					if(latestpost==null){//PostInfo中的最近帖子有可能被管理员删除
						latestpost=getLatestPost();
					}
					forumIndexBean.setUsername(latestpost.getUsername());
					forumIndexBean.setLatestPostTime(latestpost.getPostTime());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// 刷新失败，还原
			forumIndexBean.setLatestIdPost(latestIdPost);
			forumIndexBean.setIdPostInfo(idPostInfo);
			forumIndexBean.setPostsNum(postsNum);
			forumIndexBean.setTopicNum(topicNum);
			forumIndexBean.setLatestPostTime(latestPostTime);
			return false;
		}
		return true;

	}
/**
 * 
 * @return 得到最新帖子
 */
	public Post getLatestPost() {
		Post post = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT Post.idPost,Post.username,Post.title,Post.idMiddle,Post.content,Post.postTime,Post.browNum,Post.replyNum,headPorAdd"
				+ "  FROM Post,Account WHERE   postTime=( SELECT Max(postTime) FROM Post )  AND Account.username=Post.username";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				post = new Post();
				post.setIdPost(rs.getString("idPost"));
				post.setUsername(rs.getString("username"));
				post.setTitle(rs.getString("title"));
				post.setIdMiddle(rs.getString("idMiddle"));
				post.setPostTime(sdf.format(sdf.parse(rs.getString("postTime"))));
				post.setContent(rs.getString("content"));
				post.setHeadPorAdd(rs.getString("headPorAdd"));
				post.setReplyNum(rs.getInt("replyNum"));
				post.setBrowNum(rs.getInt("browNum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return post;

	}
}

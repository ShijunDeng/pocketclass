package com.pocketclass.dao.impl.forums;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.post.Post;
import com.pocketclass.domain.post.PostReply;

/**
 * 帖子请求处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-15 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class PostDaoImpl {
	/**
	 * 
	 * @param idPost
	 * @return :返回帖子编号为idPost的一级帖
	 */
	public Post getPostByPostID(String idPost) {
		Post post = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT Post.idPost,Post.username,Post.title,Post.idMiddle,Post.content,Post.postTime,Post.browNum,Post.replyNum,headPorAdd"
				+ "  FROM Post,Account WHERE idPost='"
				+ idPost
				+ "' "
				+ " AND Account.username=Post.username";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				post = new Post();
				post.setIdPost(idPost);
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

	/**
	 * 
	 * @param idPost
	 * @return 帖子浏览次数加一
	 */
	public boolean addBrowNum(String idPost) {
		String sqlUpdate = "UPDATE Post SET browNum=browNum+1  WHERE idPost='"
				+ idPost + "' ";
		try {
			DBConnectorImpl.getStatement().executeUpdate(sqlUpdate);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param username
	 * @param postTime
	 * @return ：返回用户username在postTime时发的一级帖,若查询不成功或者没查到返回null，若查询不成功或者没查到返回null
	 */
	public Post getPost(String username, String postTime) {
		Post post = null;
		String sqlQuery = "SELECT Post.idPost,Post.username,Post.title,Post.idMiddle,Post.content,Post.postTime,Post.browNum,Post.replyNum,headPorAdd "
				+ "FROM Post,Account WHERE username='"
				+ username
				+ "' AND postTime='"
				+ postTime
				+ "' "
				+ "AND Account.username=Post.username";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				post = new Post();
				post.setIdPost(rs.getString("idPost"));
				post.setUsername(username);
				post.setTitle(rs.getString("title"));
				post.setIdMiddle(rs.getString("idMiddle"));
				post.setPostTime(postTime);
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

	/**
	 * 
	 * @param idMPostReply
	 * @return 返回编号为idMPostReply的二级帖（即第一级回复贴）
	 */
	public PostReply getMPostReplyByPostID(String idMPostReply) {
		PostReply mPostReply = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT MPostReply.idMPostReply,MPostReply.username,MPostReply.title,MPostReply.content,MPostReply.postTime,MPostReply.browNum,MPostReply.replyNum,MPostReply.parentPostId,headPorAdd "
				+ "FROM MPostReply,Account WHERE MPostReply.idMPostReply='"
				+ idMPostReply + "' AND Account.username=MPostReply.username";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				mPostReply = new PostReply();
				mPostReply.setIdPost(idMPostReply);
				mPostReply.setUsername(rs.getString("username"));
				mPostReply.setTitle(rs.getString("title"));
				mPostReply.setPostTime(sdf.format(sdf.parse(rs
						.getString("postTime"))));
				mPostReply.setContent(rs.getString("content"));
				mPostReply.setHeadPorAdd(rs.getString("headPorAdd"));
				mPostReply.setReplyNum(rs.getInt("replyNum"));
				mPostReply.setBrowNum(rs.getInt("browNum"));
				mPostReply.setParentPostId(rs.getString("parentPostId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mPostReply;
	}

	/**
	 * 
	 * @param username
	 * @param postTime
	 * @return ：返回用户username在postTime时发的二级帖（即第一级回复贴）,若查询不成功或者没查到返回null
	 */
	public PostReply getMPostReply(String username, String postTime) {
		PostReply mPostReply = null;
		String sqlQuery = "SELECT MPostReply.idMPostReply,MPostReply.username,MPostReply.title,MPostReply.content,MPostReply.postTime,MPostReply.browNum,MPostReply.replyNum,MPostReply.parentPostId,headPorAdd "
				+ " FROM  LPostReply,Account WHERE username='"
				+ username
				+ "' AND postTime='"
				+ postTime
				+ "' "
				+ "AND Account.username=Post.username";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				mPostReply = new PostReply();
				mPostReply.setIdPost(rs.getString("idPost"));
				mPostReply.setUsername(username);
				mPostReply.setTitle(rs.getString("title"));
				mPostReply.setPostTime(postTime);
				mPostReply.setContent(rs.getString("content"));
				mPostReply.setHeadPorAdd(rs.getString("headPorAdd"));
				mPostReply.setReplyNum(rs.getInt("replyNum"));
				mPostReply.setBrowNum(rs.getInt("browNum"));
				mPostReply.setParentPostId(rs.getString("parentPostId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mPostReply;
	}

	/**
	 * 
	 * @param idLPostReply
	 * @return 返回编号为idLPostReply的三级帖（即第一级回复贴）
	 */
	public PostReply getLPostReplyByPostID(String idLPostReply) {
		PostReply lPostReply = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT LPostReply.idLPostReply,LPostReply.username,LPostReply.title,LPostReply.content,LPostReply.postTime,LPostReply.browNum,LPostReply.replyNum,LPostReply.parentPostId,headPorAdd FROM  "
				+ "LPostReply,Account LPostReply.idLPostReply='"
				+ idLPostReply
				+ "' AND Account.username=LPostReply.username";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				lPostReply = new PostReply();
				lPostReply.setIdPost(idLPostReply);
				lPostReply.setUsername(rs.getString("username"));
				lPostReply.setTitle(rs.getString("title"));
				lPostReply.setPostTime(sdf.format(sdf.parse(rs
						.getString("postTime"))));
				lPostReply.setContent(rs.getString("content"));
				lPostReply.setHeadPorAdd(rs.getString("headPorAdd"));
				lPostReply.setReplyNum(rs.getInt("replyNum"));
				lPostReply.setBrowNum(rs.getInt("browNum"));
				lPostReply.setParentPostId(rs.getString("parentPostId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lPostReply;
	}

	/**
	 * 
	 * @param username
	 * @param postTime
	 * @return ：返回用户username在postTime时发的二级帖（即第一级回复贴）,若查询不成功或者没查到返回null
	 */
	public PostReply getLPostReply(String username, String postTime) {
		PostReply lPostReply = null;
		String sqlQuery = "SELECT LPostReply.idLPostReply,LPostReply.username,LPostReply.title,LPostReply.content,LPostReply.postTime,LPostReply.browNum,LPostReply.replyNum,LPostReply.parentPostId,headPorAdd "
				+ "FROM  LPostReply,Account "
				+ "WHERE LPostReply.username='"
				+ username
				+ "' "
				+ "AND postTime='"
				+ postTime
				+ "' AND Account.username=LPostReply.username";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				lPostReply = new PostReply();
				lPostReply.setIdPost(rs.getString("idPost"));
				lPostReply.setUsername(username);
				lPostReply.setTitle(rs.getString("title"));
				lPostReply.setPostTime(postTime);
				lPostReply.setContent(rs.getString("content"));
				lPostReply.setHeadPorAdd(rs.getString("headPorAdd"));
				lPostReply.setReplyNum(rs.getInt("replyNum"));
				lPostReply.setBrowNum(rs.getInt("browNum"));
				lPostReply.setParentPostId(rs.getString("parentPostId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lPostReply;
	}

	/**
	 * 
	 * @param parentPostId
	 * @return 返回编号为parentPostId的Post（一级帖）的所有回复
	 */
	public ArrayList<PostReply> getMPostReplysByParentId(String parentPostId) {
		ArrayList<PostReply> mPostReplys = new ArrayList<PostReply>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT MPostReply.idMPostReply,MPostReply.username,MPostReply.title,MPostReply.content,MPostReply.postTime,MPostReply.browNum,MPostReply.replyNum,headPorAdd "
				+ "FROM MPostReply,Account WHERE parentPostId='"
				+ parentPostId
				+ "' AND MPostReply.username=Account.username   ORDER BY LPostReply.postTime ASC";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				PostReply mPostReply = new PostReply();
				mPostReply.setIdPost(rs.getString("idMPostReply"));
				mPostReply.setUsername(rs.getString("username"));
				mPostReply.setTitle(rs.getString("title"));
				mPostReply.setPostTime(sdf.format(sdf.parse(rs
						.getString("postTime"))));
				mPostReply.setContent(rs.getString("content"));
				mPostReply.setHeadPorAdd(rs.getString("headPorAdd"));
				mPostReply.setReplyNum(rs.getInt("replyNum"));
				mPostReply.setBrowNum(rs.getInt("browNum"));
				mPostReply.setParentPostId(parentPostId);
				mPostReplys.add(mPostReply);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mPostReplys;
	}

	/**
	 * 
	 * @param parentPostId
	 * @return 返回编号为parentPostId的MPostReply（二级级帖）的所有回复
	 */
	public ArrayList<PostReply> getLPostReplysByParentId(String parentPostId) {
		ArrayList<PostReply> mPostReplys = new ArrayList<PostReply>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "SELECT LPostReply.idLPostReply,LPostReply.username,LPostReply.title,LPostReply.content,LPostReply.postTime,LPostReply.browNum,LPostReply.replyNum,headPorAdd "
				+ "FROM LPostReply ,Account WHERE parentPostId=' "
				+ parentPostId
				+ "'  AND LPostReply.username=Account.username  ORDER BY LPostReply.postTime ASC";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				PostReply lPostReply = new PostReply();
				lPostReply.setIdPost(rs.getString("idLPostReply"));
				lPostReply.setUsername(rs.getString("username"));
				lPostReply.setTitle(rs.getString("title"));
				lPostReply.setPostTime(sdf.format(sdf.parse(rs
						.getString("postTime"))));
				lPostReply.setContent(rs.getString("content"));
				lPostReply.setHeadPorAdd(rs.getString("headPorAdd"));
				lPostReply.setReplyNum(rs.getInt("replyNum"));
				lPostReply.setBrowNum(rs.getInt("browNum"));
				lPostReply.setParentPostId(parentPostId);
				mPostReplys.add(lPostReply);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mPostReplys;
	}

	/**
	 * 
	 * @param idPost
	 * @return 返回编号为idPost二级帖的回复次数
	 */
	public int getPostReplyNum(String idPost) {
		String sqlQuery = "SELECT replyNum FROM Post WHERE idPost='" + idPost
				+ "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("replyNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * 
	 * @param idPost
	 *            ：增加的次数
	 * @return 增加编号为idPosty一级帖的回复次数
	 */
	public boolean addPostReplyNum(int addNum, String idPost) {
		String sqlUpdate = "UPDATE Post SET replyNum =replyNum+" + addNum
				+ " WHERE idPost='" + idPost + "'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param idPost
	 * @return 返回编号为idMPostReply二级帖的浏览次数
	 */
	public int getPostBrowNum(String idPost) {
		String sqlQuery = "SELECT browNum FROM Post WHERE idPost='" + idPost
				+ "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("browNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * 
	 * @param idMPostReply
	 * @return 返回编号为idMPostReply二级帖的回复次数
	 */
	public int getMPostReplyNum(String idMPostReply) {
		String sqlQuery = "SELECT replyNum FROM MPostReply WHERE idMPostReply='"
				+ idMPostReply + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("replyNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * @param addNum
	 *            增加的次数
	 * @param idMPostReply
	 * @return 增加编号为idMPostReply二级帖的回复次数
	 */
	public boolean addMPostReplyNum(int addNum, String idMPostReply) {
		String sqlUpdate = "UPDATE MPostReply SET replyNum =replyNum+" + addNum
				+ " WHERE idMPostReply='" + idMPostReply + "'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param idMPostReply
	 * @return 返回编号为idMPostReply二级帖的浏览次数
	 */
	public int getMPostBrowNum(String idMPostReply) {
		String sqlQuery = "SELECT browNum FROM MPostReply WHERE idMPostReply='"
				+ idMPostReply + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("browNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * 
	 * @param idMPostReply
	 * @return 返回编号为idLPostReply三级帖的回复次数
	 */
	public int getLPostReplyNum(String idLPostReply) {
		String sqlQuery = "SELECT replyNum FROM MPostReply WHERE idMPostReply='"
				+ idLPostReply + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("replyNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * @param addNum
	 *            增加的次数
	 * @param idMPostReply
	 * @return 增加编号为idMPostReply二级帖的回复次数
	 */
	public boolean addLPostReplyNum(int addNum, String idLPostReply) {
		String sqlUpdate = "UPDATE LPostReply SET replyNum =replyNum+" + addNum
				+ " WHERE idLPostReply='" + idLPostReply + "'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param idMPostReply
	 * @return 返回编号为idLPostReply三级帖的浏览次数
	 */
	public int getLPostBrowNum(String idLPostReply) {
		String sqlQuery = "SELECT browNum FROM MPostReply WHERE idMPostReply='"
				+ idLPostReply + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getInt("browNum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	public String getIdSuperByIdMiddle(String idMiddle) {
		String sqlQuery = "SELECT idSuper FROM Middle WHERE idMiddle='"
				+ idMiddle + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next())
				return rs.getString("idSuper");
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		return "";
	}

	public String getCateGoryByIdSuper(String idSuper) {
		String sqlQuery = "SELECT cateGory FROM Super WHERE idSuper='"
				+ idSuper + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getString("cateGory");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 
	 * @param post
	 * @return 插入一条一级帖
	 */
	public boolean addNewPost(Post post) {
		String sqlInsert = "INSERT INTO Post(username,title,content,browNum ,replyNum,postTime,idMiddle) "
				+ "VALUES('"
				+ post.getUsername()
				+ "','"
				+ post.getTitle()
				+ "','"
				+ post.getContent()
				+ "','"
				+ post.getBrowNum()
				+ "','"
				+ post.getReplyNum()
				+ "','"
				+ post.getPostTime()
				+ "','"
				+ post.getIdMiddle() + "')";
		try {
			DBConnectorImpl.getStatement().execute(sqlInsert);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param postreply
	 * @return 插入一条二级帖
	 */
	public boolean addNewMPostReply(PostReply postreply) {
		String sqlInsert = "INSERT INTO MPostReply(username,title,content,browNum ,replyNum,postTime,parentPostId) "
				+ "VALUES('"
				+ postreply.getUsername()
				+ "','"
				+ postreply.getTitle()
				+ "','"
				+ postreply.getContent()
				+ "','"
				+ postreply.getBrowNum()
				+ "','"
				+ postreply.getReplyNum()
				+ "','"
				+ postreply.getPostTime()
				+ "','"
				+ postreply.getParentPostId() + "')";
		try {
			DBConnectorImpl.getStatement().execute(sqlInsert);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param postreply
	 * @return 插入一条三级帖
	 */
	public boolean addNewLPostReply(PostReply postreply) {
		String sqlInsert = "INSERT INTO LPostReply(username,title,content,browNum ,replyNum,postTime,parentPostId) "
				+ "VALUES('"
				+ postreply.getUsername()
				+ "','"
				+ postreply.getTitle()
				+ "','"
				+ postreply.getContent()
				+ "','"
				+ postreply.getBrowNum()
				+ "','"
				+ postreply.getReplyNum()
				+ "','"
				+ postreply.getPostTime()
				+ "','"
				+ postreply.getParentPostId() + "')";
		try {
			DBConnectorImpl.getStatement().execute(sqlInsert);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param idPost
	 * @return 删除id为idPost的一级帖，操作成功返回true，否则返回false
	 */
	public boolean deletePostById(String idPost) {
		String sqlDelete = "DELETE FROM Post  " + "WHERE Post.idPost='"
				+ idPost + "'";
		try {
			return DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @param idMPostReply
	 * @return 删除id为idMPostReply的二级帖，操作成功返回true，否则返回false
	 */
	public boolean deleteMPostReplyById(String idMPostReply) {
		String sqlDelete = "DELETE FROM MPostReply  "
				+ "WHERE MPostReply.idMPostReply='" + idMPostReply + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param parentPostId
	 * @return 删除父贴id为parentPostId的二级帖，操作成功返回true，否则返回false
	 */
	public boolean deleteMPostReplyByParentPostId(String parentPostId) {
		String sqlDelete = "DELETE FROM MPostReply  "
				+ "WHERE MPostReply.parentPostId='" + parentPostId + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param idLPostReply
	 * @return 删除id为idLPostReply的二级级帖，操作成功返回true，否则返回false
	 */
	public boolean deleteLPostReplyById(String idLPostReply) {
		String sqlDelete = "DELETE FROM LPostReply  "
				+ "WHERE LPostReply.idLPostReply='" + idLPostReply + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param parentPostId
	 * @return 删除父贴id为parentPostId的三级帖，操作成功返回true，否则返回false
	 */
	public boolean deleteLPostReplyByParentPostId(String parentPostId) {
		String sqlDelete = "DELETE FROM LPostReply  "
				+ "WHERE LPostReply.parentPostId='" + parentPostId + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param parentPostId
	 * @return 删除父贴的父贴id为postId的三级帖，操作成功返回true，否则返回false
	 */
	public boolean deleteLPostReplyByPostId(String postId) {
		String sqlDelete = "DELETE FROM LPostReply  WHERE LPostReply.parentPostId IN (SELECT  MPostReply.idMPostReply FROM MPostReply  WHERE  MPostReply.parentPostId='"
				+ postId + "')";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
}

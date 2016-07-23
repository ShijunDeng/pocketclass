package com.pocketclass.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.pocketclass.dao.CommentDao;
import com.pocketclass.domain.Comment;

public class CommentDaoImpl implements CommentDao {

	// 得到对应编号的视频系列的评论
	@Override
	public List<Comment> getCommentsBySeriesID(String idSeries) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery = "select * from comment where idVideoSeries = '"
				+ idSeries + "' order by time desc";
		List<Comment> list = new ArrayList<Comment>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setIdVideoSeries(rs.getString("idVideoSeries"));
				comment.setUsername(rs.getString("username"));
				comment.setTime(sdf.format(rs.getTimestamp("time")));
				comment.setContent(rs.getString("content"));

				list.add(comment);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	// 增加评论
	@Override
	public int addComment(Comment comment) {
		String sqlQuery = "insert into comment values('"
				+ comment.getIdVideoSeries() + "','" + comment.getUsername()
				+ "','" + comment.getContent() + "','" + comment.getTime()
				+ "')";
		try {
			int row = DBConnectorImpl.getStatement().executeUpdate(sqlQuery);
			return row;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

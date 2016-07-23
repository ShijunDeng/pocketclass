package com.pocketclass.dao;

import java.util.List;

import com.pocketclass.domain.Comment;

public interface CommentDao {

	//得到对应编号的视频系列的评论
	List<Comment> getCommentsBySeriesID(String idSeries);

	//增加评论
	int addComment(Comment comment);

}
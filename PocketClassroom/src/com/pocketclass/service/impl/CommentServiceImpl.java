package com.pocketclass.service.impl;

import java.util.List;

import com.pocketclass.dao.CommentDao;
import com.pocketclass.dao.impl.CommentDaoImpl;
import com.pocketclass.domain.Comment;

/**
 * 课程系列相关的业务逻辑类
 * @author zhanghan
 *
 */
public class CommentServiceImpl {

	private CommentDao dao = new CommentDaoImpl();
	
	public List<Comment> getCommentsBySeriesID(String idSeries)
	{
		return dao.getCommentsBySeriesID(idSeries);
	}
	
	public void addComment(Comment comment)
	{
		dao.addComment(comment);
	}
}

package com.pocketclass.dao;

import java.util.List;

import com.pocketclass.domain.Comment;

public interface CommentDao {

	//�õ���Ӧ��ŵ���Ƶϵ�е�����
	List<Comment> getCommentsBySeriesID(String idSeries);

	//��������
	int addComment(Comment comment);

}
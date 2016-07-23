package com.pocketclass.test;

import java.util.List;

import org.junit.Test;

import com.pocketclass.dao.impl.CommentDaoImpl;
import com.pocketclass.domain.Comment;

public class CommentDaoImplTest {

	@Test
	public void testGetCommentsBySeriesID()
	{
		String idSeries = "v010101";
		CommentDaoImpl dao = new CommentDaoImpl();
		List<Comment> list = dao.getCommentsBySeriesID(idSeries);
				
		for(Comment c : list)
		{
			System.out.println(c.getIdVideoSeries());
			System.out.println(c.getContent());
			System.out.println(c.getUsername());
			System.out.println(c.getTime());	
			System.out.println();
		}
	}
}

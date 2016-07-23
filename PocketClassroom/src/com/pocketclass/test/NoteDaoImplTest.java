package com.pocketclass.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.pocketclass.dao.impl.NoteDaoImpl;
import com.pocketclass.domain.CustomCategory;
import com.pocketclass.domain.Note;
import com.pocketclass.domain.NoteComment;
import com.pocketclass.domain.SpaceInfo;
import com.pocketclass.utils.SplitPageUtils;

public class NoteDaoImplTest {
	
	@Test//ͨ��
	public void testInsertCustomCategory()
	{
		CustomCategory custom = new CustomCategory();
		custom.setCustomName("01�ҵķ���5");
		custom.setIdCustom("7");
		custom.setNoteAmount(0);
		custom.setIsopen(0);
		custom.setUsername("pocketclass01");
		
		NoteDaoImpl dao = new NoteDaoImpl();
		dao.insertCustomCategory(custom);
	}
	
	@Test//ͨ��
	public void testDeleteCustomCategory()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		dao.deleteCustomCategory("2");
	}
	
	@Test//ͨ��
	public void testModifyCustomCategory()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		
		dao.modifyCustomCategory("4","�����");
	}
	
	@Test//ͨ��
	public void testGetAllOrderByTime()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<Note> list = dao.getAllOrderByTime(new SplitPageUtils());
		for(Note note : list)
		{
			System.out.println(note.getIdNote());
			System.out.println(note.getUsername());
			System.out.println(note.getIdSuper());
			System.out.println(note.getIdCustom());
			System.out.println(note.getTitle());
			System.out.println(note.getContent());
			System.out.println(note.getIsdraft());
			System.out.println(note.getCmtallow());
			System.out.println(note.getViewAmount());
			System.out.println(note.getCmtAmount());
			System.out.println(note.getTags());
			System.out.println(note.getPostIP());
			System.out.println(note.getAddTime());
			System.out.println(note.getUpdateTime());
			
			System.out.println();
		}
	}
	
	@Test
	public void testGetAllInASuper()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<Note> list = dao.getAllInASuper("s01",new SplitPageUtils());
		
		for(Note note : list)
		{
			System.out.println(note.getIdNote());
			System.out.println(note.getUsername());
			System.out.println(note.getIdSuper());
			System.out.println(note.getIdCustom());
			System.out.println(note.getTitle());
			System.out.println(note.getContent());
			System.out.println(note.getIsdraft());
			System.out.println(note.getCmtallow());
			System.out.println(note.getViewAmount());
			System.out.println(note.getCmtAmount());
			System.out.println(note.getTags());
			System.out.println(note.getPostIP());
			System.out.println(note.getAddTime());
			System.out.println(note.getUpdateTime());
			
			System.out.println();
		}
	}
	@Test//ͨ��
	public void testgGetCountInACategory()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		int count = dao.getCountInACategory("1");
	System.out.println("������ "+count);
	}
	@Test//ͨ��
	public void testGetAllInACategory()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<Note> list = dao.getAllInACategory("1",new SplitPageUtils());
		
		for(Note note : list)
		{
			System.out.println(note.getIdNote());
			System.out.println(note.getUsername());
			System.out.println(note.getIdSuper());
			System.out.println(note.getIdCustom());
			System.out.println(note.getTitle());
			System.out.println(note.getContent());
			System.out.println(note.getIsdraft());
			System.out.println(note.getCmtallow());
			System.out.println(note.getViewAmount());
			System.out.println(note.getCmtAmount());
			System.out.println(note.getTags());
			System.out.println(note.getPostIP());
			System.out.println(note.getAddTime());
			System.out.println(note.getUpdateTime());
			
			System.out.println();
		}
	}
	@Test//ͨ��
	public void testModifyCategoryPermission()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		dao.modifyCategoryPermission(7, 0);
	}
	
	@Test//////////////////��ͨ��
	public void testInsertNote()
	{
		Note note = new Note();
		note.setIdNote(8);
		note.setUsername("pocketclass01");
		note.setIdSuper("S01");
		note.setIdCustom(6);
		note.setTitle("����");
		note.setContent("����");
		note.setIsdraft(0);
		note.setCmtallow(0);
		note.setViewAmount(8);
		note.setCmtAmount(8);
		note.setTags("a,b,c");
		note.setPostIP("123.123.123.123");
		//note.setAddTime(new Date(2001,10,12));
		//note.setUpdateTime(new Date(2001,10,12));
		
		NoteDaoImpl dao = new NoteDaoImpl();
		dao.insertNote(note);
	}
	@Test//ͨ��
	public void testUpdateNote()
	{
		Note note = new Note();
		note.setIdNote(10);
		note.setUsername("pocketclass01");
		note.setIdSuper("S01");
		note.setIdCustom(6);
		note.setTitle("����hahaha");
		note.setContent("����hnhnhn");
		note.setIsdraft(0);
		note.setCmtallow(0);
		note.setViewAmount(0);
		note.setCmtAmount(0);
		note.setTags("a,b,c");
		note.setPostIP("123.123.180.123");
		//note.setAddTime(new Date(2001,10,12));
		//note.setUpdateTime(new Date(2001,10,12));
		
		NoteDaoImpl dao = new NoteDaoImpl();
		dao.updateNote(note);
	}
	@Test//ͨ��(������Լ�������)
	public void testDeleteNote()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		dao.deleteNote("10");
	}
	
	@Test//ͨ��
	public void testSerachNote()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<Note> list = dao.searchNote("����", new SplitPageUtils());
		
		for(Note note : list)
		{
			System.out.println(note.getIdNote());
			System.out.println(note.getUsername());
			System.out.println(note.getIdSuper());
			System.out.println(note.getIdCustom());
			System.out.println(note.getTitle());
			System.out.println(note.getContent());
			System.out.println(note.getIsdraft());
			System.out.println(note.getCmtallow());
			System.out.println(note.getViewAmount());
			System.out.println(note.getCmtAmount());
			System.out.println(note.getTags());
			System.out.println(note.getPostIP());
			System.out.println(note.getAddTime());
			System.out.println(note.getUpdateTime());
			
			System.out.println();
		}
	}
	
	@Test//ͨ��
	public void testModifyNotePermission()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		dao.modifyNotePermission(1, false);
	}
	
	@Test//ͨ��
	public void testGetHotest()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<Note> list = dao.getHotest();
		
		for(Note note : list)
		{
			System.out.println(note.getIdNote());
			System.out.println(note.getTitle());
			System.out.println(note.getViewAmount());
			
			System.out.println();
		}
	}
	@Test//ͨ��
	public void testGetUserNotesCount()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		int count = dao.getUserNotesCount("pocketclass01");
		System.out.println("��Ŀ��"+ count);
	}
	@Test//ͨ��
	public void testGetUserNotes()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<Note> list = dao.getUserNotes("pocketclass01",new SplitPageUtils());
		
		for(Note note : list)
		{
			System.out.println(note.getIdNote());
			System.out.println(note.getTitle());
			System.out.println(note.getViewAmount());
			
			System.out.println();
		}
	}
	@Test//ͨ��
	public void testGetUserSpaceInfo()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		SpaceInfo space = dao.getUserSpaceInfo("pocketclass01");
		if(space!=null)
		{
			
			System.out.println(space.getUsername());
			System.out.println(space.getCmtAmount());
			System.out.println(space.getNoteAmount());
			System.out.println(space.getViewAmount());
			System.out.println(space.getHeadPorAdd());
			
		}
		else
		{
			System.out.println("�յ�");
		}
	}
	@Test//ͨ��
	public void testGetUserCustomCategory()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<CustomCategory> list = dao.getUserCustomCategory("pocketclass01");
		
		for(CustomCategory custom : list)
		{
			System.out.println(custom.getIdCustom());
			System.out.println(custom.getCustomName());
			System.out.println(custom.getIsopen());
			System.out.println(custom.getNoteAmount());
			System.out.println(custom.getUsername());
			
			System.out.println();
		}
	}
	
	@Test//ͨ��
	public void testGetNote()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		Note note = dao.getNote("7");
		
		System.out.println(note.getIdNote());
		System.out.println(note.getUsername());
		System.out.println(note.getIdSuper());
		System.out.println(note.getIdCustom());
		System.out.println(note.getTitle());
		System.out.println(note.getContent());
		System.out.println(note.getIsdraft());
		System.out.println(note.getCmtallow());
		System.out.println(note.getViewAmount());
		System.out.println(note.getCmtAmount());
		System.out.println(note.getTags());
		System.out.println(note.getPostIP());
		System.out.println(note.getAddTime());
		System.out.println(note.getUpdateTime());
	}
	
	@Test//ͨ��
	public void testGetNoteAmountOrderByMonth()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		Map<String,Integer> map = dao.getNoteAmountOrderByMonth("pocketclass01");
		
		for(Map.Entry<String, Integer> entry:map.entrySet())
		{
			System.out.println("key:"+entry.getKey()+"value:"+entry.getValue());
		}
	
	}
	
	@Test
	public void testGetNoteOrderByMonth()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		List<Note> list = dao.getNoteOrderByMonth("pocketclass01","201203",new SplitPageUtils());
		

		for(Note note : list)
		{
			System.out.println(note.getIdNote());
			System.out.println(note.getUsername());
			System.out.println(note.getIdSuper());
			System.out.println(note.getIdCustom());
			System.out.println(note.getTitle());
			System.out.println(note.getContent());
			System.out.println(note.getIsdraft());
			System.out.println(note.getCmtallow());
			System.out.println(note.getViewAmount());
			System.out.println(note.getCmtAmount());
			System.out.println(note.getTags());
			System.out.println(note.getPostIP());
			System.out.println(note.getAddTime());
			System.out.println(note.getUpdateTime());
			
			System.out.println();
		}
	}
	
	@Test//ͨ��
	public void testPublishNoteComment() 
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		NoteComment comment = new NoteComment();
		comment.setIdNote(1);
		comment.setUsername("pocketclass02");
		comment.setParentId(0);
		comment.setContent("�����µ�����");
		comment.setPostIP("185.033.034.033");
		comment.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		System.out.println(dao.publishNoteComment(comment));
	}
	@Test
	public void testGetAllNoteComments()
	{
		NoteDaoImpl dao = new NoteDaoImpl();
		Map<Integer,Integer> map = dao.getAllNoteComments(1,0,0);
		for(Map.Entry<Integer, Integer> entry : map.entrySet())
		{
			System.out.format("ID:"+entry.getKey()+"����:"+entry.getValue());
		}
	}
}

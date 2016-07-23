package com.pocketclass.service.impl;

import java.util.List;
import java.util.Map;

import com.pocketclass.dao.impl.NoteDaoImpl;
import com.pocketclass.domain.CustomCategory;
import com.pocketclass.domain.Note;
import com.pocketclass.domain.NoteComment;
import com.pocketclass.domain.SpaceInfo;
import com.pocketclass.service.NoteService;
import com.pocketclass.utils.SplitPageUtils;
/**
 * 处理与笔记相关的业务逻辑类
 * @author zhanghan
 */
public class NoteServiceImpl implements NoteService {

	private NoteDaoImpl dao = new NoteDaoImpl();
	
	@Override
	public List<Note> getAll(SplitPageUtils sp)
	{
		return dao.getAllOrderByTime(sp);
	}
	@Override
	public int getCount()
	{
		return dao.getCount();
	}
	@Override
	public List<Note> getAllInASuper(String idSuper,SplitPageUtils sp)
	{
		return dao.getAllInASuper(idSuper, sp);
	}
	@Override
	public int getCountInASuper(String idSuper)
	{
		return dao.getCountInASuper(idSuper);
	}
	
	@Override
	public List<Note> getHotest()
	{
		return dao.getHotest();
	}
	@Override
	public int getUserNotesCount(String username)
	{
		return dao.getUserNotesCount(username);
	}
	@Override
	public List<Note> getUserNotes(String username,SplitPageUtils sp)
	{
		return dao.getUserNotes(username,sp);
	}
	
	@Override
	public List<Note> searchNote(String username,String keyword,SplitPageUtils sp)
	{
		return dao.searchNote(username, keyword, sp);
	}
	
	@Override
	public int getUserSearchCount(String username,String keyword)
	{
		return dao.getUserSearchCount(username,keyword);
	}
	
	@Override
	public int getSearchCount(String keyword)
	{
		return dao.getSearchCount(keyword);
	}
	@Override
	public List<Note> searchNote(String keyword, SplitPageUtils sp) 
	{
		return dao.searchNote(keyword, sp);
	}
	
	@Override
	public SpaceInfo getUserSpaceInfo(String username)
	{
		return dao.getUserSpaceInfo(username);
	}
	
	@Override
	public List<CustomCategory> getUserCustomCategory(String username)
	{
		return dao.getUserCustomCategory(username);
	}
	
	@Override
	public List<Note> getAllInACategory(String idCustom,SplitPageUtils sp)
	{
		return dao.getAllInACategory(idCustom, sp);
	}
	
	@Override
	public int getCountInACategory(String idCustom)
	{
		return dao.getCountInACategory(idCustom);
	}
	
	@Override
	public Note getNote(String idNote)
	{
		return dao.getNote(idNote);
	}
	
	@Override
	public void modifyCustomCategory(String idCustom,String customName)
	{
		dao.modifyCustomCategory(idCustom, customName);
	}
	@Override
	public boolean insertCustomCategory(CustomCategory custom)
	{
		return dao.insertCustomCategory(custom);
	}
	@Override
	public void deleteCustomCategory(String idCustom) {
		dao.deleteCustomCategory(idCustom);
	}
	
	@Override
	public boolean deleteNote(String idNote)
	{
		return dao.deleteNote(idNote);
	}
	
	@Override
	public int insertNote(Note note)
	{
		return dao.insertNote(note);
	}
	
	@Override
	public boolean updateNote(Note note)
	{
		return dao.updateNote(note);
	}
	
	@Override
	public Map<String,Integer> getNoteAmountOrderByMonth(String username)
	{
		return dao.getNoteAmountOrderByMonth(username);
	}
	
	@Override
	public List<Note> getNoteOrderByMonth(String username,String yyyyMM,SplitPageUtils sp)
	{
		return dao.getNoteOrderByMonth(username, yyyyMM,sp);
	}
	
	@Override
	public int publishNoteComment(NoteComment comment)
	{
		return dao.publishNoteComment(comment);
	}
}

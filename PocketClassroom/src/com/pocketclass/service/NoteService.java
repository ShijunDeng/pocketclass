package com.pocketclass.service;

import java.util.List;
import java.util.Map;

import com.pocketclass.domain.CustomCategory;
import com.pocketclass.domain.Note;
import com.pocketclass.domain.NoteComment;
import com.pocketclass.domain.SpaceInfo;
import com.pocketclass.utils.SplitPageUtils;

public interface NoteService {

	List<Note> getAll(SplitPageUtils sp);
	
	List<Note> getAllInASuper(String idSuper,SplitPageUtils sp);
	
	int getCount();
	
	int getCountInASuper(String idSuper);
	
	 List<Note> getHotest();
	 
	 List<Note> getUserNotes(String username,SplitPageUtils sp);
	 
	 List<Note> searchNote(String username,String keyword,SplitPageUtils sp);
	 
	 int getUserSearchCount(String username,String keyword);

	List<Note> searchNote(String keyword, SplitPageUtils sp);

	int getSearchCount(String keyword);

	int getUserNotesCount(String username);

	SpaceInfo getUserSpaceInfo(String username);

	List<CustomCategory> getUserCustomCategory(String username);

	List<Note> getAllInACategory(String idCustom,SplitPageUtils sp);

	int getCountInACategory(String idCustom);
	
	Note getNote(String idNote);
	
	void modifyCustomCategory(String idCustom,String customName);
	
	boolean insertCustomCategory(CustomCategory custom);
	
	void deleteCustomCategory(String idCustom);
	
	boolean deleteNote(String idNote);
	
	int insertNote(Note note);
	
	boolean updateNote(Note note);
	
	Map<String,Integer> getNoteAmountOrderByMonth(String username);
	
	List<Note> getNoteOrderByMonth(String username,String yyyyMM,SplitPageUtils sp);
	
	int publishNoteComment(NoteComment comment);
}
package com.pocketclass.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.pocketclass.domain.CustomCategory;
import com.pocketclass.domain.Note;
import com.pocketclass.domain.NoteComment;
import com.pocketclass.domain.SpaceInfo;
import com.pocketclass.utils.SplitPageUtils;

/**
 * 笔记Dao类:进行与笔记有关的数据库操作
 * @author zhanghan
 */
public class NoteDaoImpl {

	//增加自定义分类
	public boolean insertCustomCategory(CustomCategory custom)
	{
		if(null == custom)
		{
			return false;
		}
		try {
			PreparedStatement prestat = DBConnectorImpl.getConnection()
		.prepareStatement("INSERT INTO CustomCategory(customName,username) VALUES(?,?)");
			prestat.setString(1, custom.getCustomName());
			prestat.setString(2, custom.getUsername());
			
			boolean isOK = prestat.execute();
			return isOK;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//删除自定义分类(1.将该分类下的全部笔记移动到默认分类下 2.将该分类删除)
	public void deleteCustomCategory(String idCustom)
	{
		try {
			DBConnectorImpl.getConnection().setAutoCommit(false);//1.禁止自动提交
			String upSql = "update CustomCategory set noteAmount = noteAmount + (select count(*) from note where idCustom = '"+idCustom+"') where customName ='默认分类'";
			DBConnectorImpl.getStatement().executeUpdate(upSql);
			String updSql = "update note set idCustom = ("+"select c1.idCustom from CustomCategory c1,CustomCategory c2 where c1.username = c2.username and c1.customName = '默认分类' and c2.idCustom = '"+idCustom+"'"+") where idCustom ='"+idCustom+"'";
			DBConnectorImpl.getStatement().executeUpdate(updSql);
			String delSql = "delete from CustomCategory where idCustom ='"+idCustom+"'";
			DBConnectorImpl.getStatement().executeUpdate(delSql);
			DBConnectorImpl.getConnection().commit();
			DBConnectorImpl.getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			try {
				DBConnectorImpl.getConnection().rollback();
				DBConnectorImpl.getConnection().setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	//修改自定义分类
	public void modifyCustomCategory(String idCustom,String customName)
	{
		String updSql = "update CustomCategory set customName = '"+customName+"' where idCustom ='"+idCustom+"'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(updSql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//得到某一用户的所有分类
	public List<CustomCategory> getUserCustomCategory(String username)
	{
		String sqlQuery = "select * from customcategory where username = '"+username+"'";
		List<CustomCategory> list = new ArrayList<CustomCategory>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				CustomCategory custom = new CustomCategory();
				custom.setCustomName(rs.getString("customName"));
				custom.setIdCustom(rs.getInt("idCustom")+"");
				custom.setIsopen(rs.getInt("isopen"));
				custom.setUsername(username);
				custom.setNoteAmount(rs.getInt("noteAmount"));
				list.add(custom);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//得到全部的笔记数目
	public int getCount()
	{
		String sqlQuery = "select count(*) from note";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	//得到所有的笔记（按照时间）
	public List<Note> getAllOrderByTime(SplitPageUtils sp)
	{
		String sqlQuery = "select headPorAdd,idNote,n.username,title,content,isdraft,cmtallow,viewAmount,cmtAmount,tags,postIP,addTime,updateTime,cateGory,customName from note n,account a,CustomCategory c,SUPER s where n.username = a.username and n.idcustom = c.idcustom and n.idsuper = s.idsuper order by addTime desc" +" limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<Note> list = new ArrayList<Note>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Note note = new Note();
				note.setHeadPorAdd(rs.getString("headPorAdd"));
				note.setIdNote(rs.getInt("idNote"));
				note.setUsername(rs.getString("username"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setIsdraft(rs.getInt("isdraft"));
				note.setCmtallow(rs.getInt("cmtallow"));
				note.setViewAmount(rs.getInt("viewAmount"));
				note.setCmtAmount(rs.getInt("cmtAmount"));
				note.setTags(rs.getString("tags"));
				note.setPostIP(rs.getString("postIP"));
				note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
				
				note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
				note.setCategoryName(rs.getString("cateGory"));
				note.setCustomName("customName");
				
				list.add(note);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//得到系统分类下的所有笔记的数目
	public int getCountInASuper(String idSuper)
	{
		String sqlQuery = "select count(*) from note where idSuper = '"+idSuper+"'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	//得到系统分类下的全部笔记
	public List<Note> getAllInASuper(String idSuper,SplitPageUtils sp)
	{
		String sqlQuery = "select headPorAdd,idNote,n.username,title,content,isdraft,cmtallow,viewAmount,cmtAmount,tags,postIP,addTime,updateTime,cateGory,customName from note n,account a,CustomCategory c,SUPER s where n.username = a.username and n.idcustom = c.idcustom and n.idsuper = s.idsuper and s.idSuper = '"+idSuper+"'order by addTime desc" +" limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<Note> list = new ArrayList<Note>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Note note = new Note();
				note.setHeadPorAdd(rs.getString("headPorAdd"));
				note.setIdNote(rs.getInt("idNote"));
				note.setUsername(rs.getString("username"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setIsdraft(rs.getInt("isdraft"));
				note.setCmtallow(rs.getInt("cmtallow"));
				note.setViewAmount(rs.getInt("viewAmount"));
				note.setCmtAmount(rs.getInt("cmtAmount"));
				note.setTags(rs.getString("tags"));
				note.setPostIP(rs.getString("postIP"));
				note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
				note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
				note.setCategoryName(rs.getString("cateGory"));
				note.setCustomName(rs.getString("customName"));
				
				list.add(note);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//得到最热的分类(显示最新的40个)
	public List<Note> getHotest()
	{
		String sqlQuery = "select idNote,viewAmount,title from note order by viewAmount desc limit 0,40";
		List<Note> list = new ArrayList<Note>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Note note = new Note();
				note.setIdNote(rs.getInt("idNote"));
				note.setTitle(rs.getString("title"));
				note.setViewAmount(rs.getInt("viewAmount"));
				
				list.add(note);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//得到某一用户的空间信息(如果用户不存在会返回null)
	public SpaceInfo getUserSpaceInfo(String username)
	{
		String sqlQuery = "select s.username,headPorAdd,cmtAmount,noteAmount,viewAmount from spaceInfo s,account a where s.username = a.username and s.username = '"+username+"'";
		
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				    SpaceInfo space = new SpaceInfo();
					space.setUsername(username);
					space.setCmtAmount(rs.getInt("cmtAmount"));
					space.setNoteAmount(rs.getInt("noteAmount"));
					space.setViewAmount(rs.getInt("viewAmount"));
					space.setHeadPorAdd(rs.getString("headPorAdd"));
					return space;
				}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	//得到某一用户的全部笔记的数目
	public int getUserNotesCount(String username)
	{
		String sqlQuery = "select count(*) from note where username = '"+username+"'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
    //得到某一用户的全部笔记
	public List<Note> getUserNotes(String username,SplitPageUtils sp)
	{
		String sqlQuery = "select * from note where username = '"
				+ username + "' order by addTime desc" +" limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<Note> list = new ArrayList<Note>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Note note = new Note();
				note.setIdNote(rs.getInt("idNote"));
				note.setUsername(rs.getString("username"));
				note.setIdSuper(rs.getString("idSuper"));
				note.setIdCustom(rs.getInt("idCustom"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setIsdraft(rs.getInt("isdraft"));
				note.setCmtallow(rs.getInt("cmtallow"));
				note.setViewAmount(rs.getInt("viewAmount"));
				note.setCmtAmount(rs.getInt("cmtAmount"));
				note.setTags(rs.getString("tags"));
				note.setPostIP(rs.getString("postIP"));
				note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
				note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
				
				list.add(note);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;

	}
	//得到某一自定义分类下的全部笔记的数目
	public int getCountInACategory(String idCustom)
	{
		String sqlQuery = "select count(*) from note where idCustom = '"+idCustom+"'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	//得到某一自定义分类下的全部笔记
	public List<Note> getAllInACategory(String idCustom,SplitPageUtils sp)
	{
		String sqlQuery = "select * from note where idCustom = '"
				+ idCustom + "' order by addTime desc"+" limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<Note> list = new ArrayList<Note>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Note note = new Note();
				note.setIdNote(rs.getInt("idNote"));
				note.setUsername(rs.getString("username"));
				note.setIdSuper(rs.getString("idSuper"));
				note.setIdCustom(rs.getInt("idCustom"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setIsdraft(rs.getInt("isdraft"));
				note.setCmtallow(rs.getInt("cmtallow"));
				note.setViewAmount(rs.getInt("viewAmount"));
				note.setCmtAmount(rs.getInt("cmtAmount"));
				note.setTags(rs.getString("tags"));
				note.setPostIP(rs.getString("postIP"));
				note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
				note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
				
				list.add(note);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//得到指定编号的笔记（若没有，则返回空）
	public Note getNote(String idNote)
	{
		String sqlQuery = "select idNote,idSuper,n.idCustom,n.username,title,content,cmtallow,viewAmount,cmtAmount,tags,addTime,updateTime,customName from note n,CustomCategory c where n.idcustom = c.idcustom and n.idNote = '"+idNote+"'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				Note note = new Note();
				note.setIdNote(rs.getInt("idNote"));
				note.setIdSuper(rs.getString("idSuper"));
				note.setIdCustom(rs.getInt("idCustom"));
				note.setUsername(rs.getString("username"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCmtallow(rs.getInt("cmtallow"));
				note.setViewAmount(rs.getInt("viewAmount"));
				note.setCmtAmount(rs.getInt("cmtAmount"));
				note.setTags(rs.getString("tags"));
				note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
				note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
				note.setCustomName(rs.getString("customName"));
					
				return note;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	//修改分类浏览权限
	public void modifyCategoryPermission(int idCustom,int permission)
	{
		String updSql = "update CustomCategory set isopen = '"+permission+"' where idCustom ='"+idCustom+"'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(updSql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//发表笔记(待完善,设计不合理)测试不通过，setboolean的错误
	public int insertNote(Note note)
	{
		try {
			PreparedStatement prestat = DBConnectorImpl.getConnection()
		.prepareStatement("INSERT INTO Note(username,idSuper,idCustom,title,content,isdraft,cmtallow,viewAmount,cmtAmount,tags,postIP,addTime,updateTime)"
		+" values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			prestat.setString(1, note.getUsername());
			prestat.setString(2, note.getIdSuper());
			prestat.setInt(3, note.getIdCustom());
			prestat.setString(4,note.getTitle());
			prestat.setString(5, note.getContent());
			prestat.setInt(6, note.getIsdraft());
			prestat.setInt(7, note.getCmtallow());
			prestat.setInt(8, note.getViewAmount());
			prestat.setInt(9, note.getCmtAmount());
			prestat.setString(10, note.getTags());
			prestat.setString(11, note.getPostIP());
			prestat.setTimestamp(12,Timestamp.valueOf(note.getAddTime()));
			prestat.setTimestamp(13, Timestamp.valueOf(note.getUpdateTime()));

			int isOK = prestat.executeUpdate();
			return isOK;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//删除笔记(定义级联操作，将评论一并删除)
	public boolean deleteNote(String idNote)
	{
		String updSql = "delete from note where idNote = '"+idNote+"'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(updSql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	//编辑笔记
	public boolean updateNote(Note note)
	{
		String sql = "update note set username = ?,idSuper = ?,idCustom = ?,title = ?,content = ?,isdraft = ?,cmtallow = ?,viewAmount = ?,cmtAmount = ?,tags = ?,postIP = ?,addTime = ?,updateTime = ? where idNote = '"+note.getIdNote()+"'";
		
		try {
			PreparedStatement prestat =  DBConnectorImpl.getConnection().prepareStatement(sql);
				prestat.setString(1, note.getUsername());
				prestat.setString(2, note.getIdSuper());
				prestat.setInt(3, note.getIdCustom());
				prestat.setString(4,note.getTitle());
				prestat.setString(5, note.getContent());
				prestat.setInt(6,note.getIsdraft());
				prestat.setInt(7,note.getCmtallow());
				prestat.setInt(8, note.getViewAmount());
				prestat.setInt(9, note.getCmtAmount());
				prestat.setString(10, note.getTags());
				prestat.setString(11, note.getPostIP());
				prestat.setTimestamp(12,Timestamp.valueOf(note.getAddTime()));
				prestat.setTimestamp(13, Timestamp.valueOf(note.getUpdateTime()));
				
				boolean isOK = prestat.execute();
				return isOK;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//得到符合搜索条件的笔记总数目
	public int getSearchCount(String keyword)
	{
		String sqlQuery = "select count(*) from note where title like '%"+keyword+"%'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	//搜寻笔记
	public List<Note> searchNote(String keyword,SplitPageUtils sp)
	{
		
		List<Note> list = new ArrayList<Note>();
		if(keyword == null || keyword.equals(""))
			return list;
		keyword = keyword.trim();
		String sqlQuery = "select headPorAdd,idNote,n.username,title,content,isdraft,cmtallow,viewAmount,cmtAmount,tags,postIP,addTime,updateTime,cateGory,customName from note n,account a,CustomCategory c,SUPER s where n.username = a.username and n.idcustom = c.idcustom and n.idsuper = s.idsuper and title like '%"+keyword+"%'" +"order by addTime desc limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs!=null)
			{
				while(rs.next())
				{
					Note note = new Note();
					note.setHeadPorAdd(rs.getString("headPorAdd"));
					note.setIdNote(rs.getInt("idNote"));
					note.setUsername(rs.getString("username"));
					note.setTitle(rs.getString("title"));
					note.setContent(rs.getString("content"));
					note.setIsdraft(rs.getInt("isdraft"));
					note.setCmtallow(rs.getInt("cmtallow"));
					note.setViewAmount(rs.getInt("viewAmount"));
					note.setCmtAmount(rs.getInt("cmtAmount"));
					note.setTags(rs.getString("tags"));
					note.setPostIP(rs.getString("postIP"));
					note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
					note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
					note.setCategoryName(rs.getString("cateGory"));
					note.setCustomName("customName");
					
					list.add(note);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//按照关键字搜索指定用户的笔记的数目
	public int getUserSearchCount(String username,String keyword)
	{
		String sqlQuery = "select count(*) from note where username = '"+username+"' and title like '%"+keyword+"%'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	//按照关键字搜索指定用户的笔记
	public List<Note> searchNote(String username,String keyword,SplitPageUtils sp)
	{
		if(keyword == null || keyword.trim().equals(""))
			throw new NullPointerException("请输入搜索关键关键字！");
		String sqlQuery = "select * from note where username = '"+username+"' and title like '%"+keyword+"%'" +"limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<Note> list = new ArrayList<Note>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs!=null)
			{
				while(rs.next())
				{
					Note note = new Note();
					
					note.setIdNote(rs.getInt("idNote"));
					note.setUsername(rs.getString("username"));
					note.setIdSuper(rs.getString("idSuper"));
					note.setIdCustom(rs.getInt("idCustom"));
					note.setTitle(rs.getString("title"));
					note.setContent(rs.getString("content"));
					note.setIsdraft(rs.getInt("isdraft"));
					note.setCmtallow(rs.getInt("cmtallow"));
					note.setViewAmount(rs.getInt("viewAmount"));
					note.setCmtAmount(rs.getInt("cmtAmount"));
					note.setTags(rs.getString("tags"));
					note.setPostIP(rs.getString("postIP"));
					note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
					note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
					
					list.add(note);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//修改笔记评论权限
	public void modifyNotePermission(int idNote,boolean permission)
	{
		String updSql = "update note set cmtallow = '"+permission+"' where idNote ='"+idNote+"'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(updSql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//得到指定用户发表笔记的年月及笔记此年月发表的笔记数目
	public Map<String,Integer> getNoteAmountOrderByMonth(String username)
	{
		String sql = "select count(*),EXTRACT(YEAR_MONTH FROM addTime) as yyyyMM from note where username = '"+username+"'group by yyyyMM order by yyyyMM desc";
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sql);
				while(rs.next())
				{
					map.put(rs.getString(2),rs.getInt(1));
				}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return map;
	}
	//按照发表的月份对笔记进行归档
	public List<Note> getNoteOrderByMonth(String username,String yyyyMM,SplitPageUtils sp)
	{
		String sql1 = "SELECT * from note where EXTRACT(YEAR_MONTH FROM addTime) = '"+yyyyMM+"' and username = '"+username+"'" +" limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		ArrayList<Note> list = new ArrayList<Note>(); 
		try {
			ResultSet rs = DBConnectorImpl.getStatement().executeQuery(sql1);
			while(rs.next())
			{
				Note note = new Note();
				
				note.setIdNote(rs.getInt("idNote"));
				note.setUsername(rs.getString("username"));
				note.setIdSuper(rs.getString("idSuper"));
				note.setIdCustom(rs.getInt("idCustom"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setIsdraft(rs.getInt("isdraft"));
				note.setCmtallow(rs.getInt("cmtallow"));
				note.setViewAmount(rs.getInt("viewAmount"));
				note.setCmtAmount(rs.getInt("cmtAmount"));
				note.setTags(rs.getString("tags"));
				note.setPostIP(rs.getString("postIP"));
				note.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("addTime")));
				note.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("updateTime")));
				
				list.add(note);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	//针对某一文章发表评论(一级评论)
	public int publishNoteComment(NoteComment comment)
	{
		try {
			PreparedStatement prestat = DBConnectorImpl.getConnection()
		.prepareStatement("INSERT INTO NoteComment(idNote,username,parentId,content,postIp,time)"
		+" values(?,?,?,?,?,?)");
			
			prestat.setInt(1, comment.getIdNote());
			prestat.setString(2, comment.getUsername());      
			prestat.setInt(3, comment.getParentId());
			prestat.setString(4, comment.getContent());
			prestat.setString(5, comment.getPostIP());
			prestat.setTimestamp(6, Timestamp.valueOf(comment.getTime()));
			
			int isOK = prestat.executeUpdate();
			return isOK;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//得到某一笔记的全部评论
	public void getAllComment()
	{
		
	}
	//得到某一笔记的全部评论的id及其所处的层次
	public Map<Integer,Integer> getAllNoteComments(int idNote,int id,int level)
	{
		String sql = "select idNoteComment from NoteComment where idNote = '"+idNote+"' and parentId = '"+id+"'";
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		try {
			Statement stat = DBConnectorImpl.getStatement();
			ResultSet rs = stat.executeQuery(sql);
			level++;
			while(rs.next())
			{
				int idNoteComment = rs.getInt("idNoteComment");
				map.put(idNoteComment,level);
				map.putAll(getAllNoteComments(idNote,idNoteComment,level));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return map;
	}
	//我的笔记的评论的删除
}

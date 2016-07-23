package com.pocketclass.dao.impl.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pocketclass.dao.VideoSeriesDao;
import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.Middle;
import com.pocketclass.domain.Super;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.utils.SplitPageUtils;
/**
 * 视频系列ＤＡＯ类
 * 
 * @author zhanghan 
 * @version 1.0 <br/>         
 */
public class VideoSeriesDaoImpl implements VideoSeriesDao {

	/**
	 * 找到对应id的视频系列
	 * @param idVideoSeries 视频系列的ID号
	 * @return 对应的视频系列
	 */
	@Override
	public VideoSeries findByID(String idVideoSeries)
	{
		if(idVideoSeries == null || idVideoSeries.trim().equals(""))
			return null;
		String sqlQuery = "select * from videoseries where idVideoSeries = '"+idVideoSeries+"'";
		
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs!=null)
			{
					while(rs.next())
				{//只能搜索到一个视频系列，用if(rs.next())也可	
						
					VideoSeries series = new VideoSeries();
					
					series.setId(rs.getString("idVideoSeries"));
					series.setIntroduction(rs.getString("introduction"));
					series.setLinkAddress(rs.getString("linkAddress"));
					series.setParentId(rs.getString("idMiddle"));
					series.setSize(rs.getInt("size"));
					series.setTeacherId(rs.getString("idTeacher"));
					series.setTopic(rs.getString("topic"));
					series.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("uploadTime")));
					series.setThumbnail(rs.getString("thumbnail"));
		
					return series;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	@Override
	public String getCategoryNameById(String categoryId)
	{
		Pattern pattern = Pattern.compile("S.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(categoryId);
		
		String sql = "";
		
		if(matcher.matches())
		{//说明categoryId是一级分类的ID
			sql = "select category from Super where idSuper = '"+categoryId+"'";
		}
		else
		{//categoryId是二级分类的ID
			//首先得到总的记录数
			sql = "select middlecategory from Middle where idMiddle = '"+categoryId+"'";
		}
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sql);
			if(rs.next())
			{
				return rs.getString(1);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	//得到符合条件的记录数
	@Override
	public int getRowCountById(String categoryId)
	{
		Pattern pattern = Pattern.compile("S.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(categoryId);

		//首先得到总的记录数
		String sql = "";
		
		if(matcher.matches())
		{//说明categoryId是一级分类的ID
			sql = "select count(*) from videoseries v,Middle m where v.idMiddle = m.idMiddle and m.idSuper = '"+categoryId+"'";
		}
		else
		{//categoryId是二级分类的ID
			//首先得到总的记录数
			sql = "select count(*) from videoseries where idMiddle = '"+categoryId+"'";
		}
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sql);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	//得到给定分类(一级分类，二级分类都可)下的所有的视频系列
	@Override
	public List<VideoSeries> findAllById(String categoryId,SplitPageUtils sp)
	{
		Pattern pattern = Pattern.compile("S.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(categoryId);

		if(matcher.matches())
		{//说明categoryId是一级分类的ID
			return findAllByPrimaryID(categoryId, sp);
		}
		else
		{//categoryId是二级分类的ID
			return findAllBySecondaryID(categoryId, sp);
		}
	}
	/**
	 * 得到给定一级分类下的所有的视频系列
	 * @param idSuper 一级分类编号
	 * @return Map<String,VideoSeries> 一级分类下的所有视频系列的map集合
	 */
	@Override
	public List<VideoSeries> findAllByPrimaryID(String idSuper,SplitPageUtils sp)
	{
		if(idSuper == null || idSuper.trim().equals(""))
			return null;
		
		String sqlQuery = "select * from videoseries v,Middle m where v.idMiddle = m.idMiddle and m.idSuper = '"+idSuper+"'" +"limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<VideoSeries> list = new ArrayList<VideoSeries>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs!=null)
			{
					while(rs.next())
				{
					VideoSeries series = new VideoSeries();
					String id = rs.getString("idVideoSeries");
					series.setId(id);
					series.setIntroduction(rs.getString("introduction"));
					series.setLinkAddress(rs.getString("linkAddress"));
					series.setParentId(rs.getString("idMiddle"));
					series.setSize(rs.getInt("size"));
					series.setTeacherId(rs.getString("idTeacher"));
					series.setTopic(rs.getString("topic"));
					series.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("uploadTime")));
					series.setThumbnail(rs.getString("thumbnail"));
					
					list.add(series);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * 得到给定二级分类下的所有的视频系列
	 * @param idMiddle 二级分类编号
	 * @return Map<String,VideoSeries> 二级分类下的所有视频系列的map集合
	 */
	@Override
	public List<VideoSeries> findAllBySecondaryID(String idMiddle,SplitPageUtils sp)
	{	
		if(idMiddle == null || idMiddle.trim().equals(""))
			return null;
		
		String sqlQuery = "select * from videoseries where idMiddle = '"+idMiddle+"'" +"limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<VideoSeries> list = new ArrayList<VideoSeries>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs!=null)
			{
					while(rs.next())
				{
					VideoSeries series = new VideoSeries();
					String id = rs.getString("idVideoSeries");
					series.setId(id);
					series.setIntroduction(rs.getString("introduction"));
					series.setLinkAddress(rs.getString("linkAddress"));
					series.setParentId(rs.getString("idMiddle"));
					series.setSize(rs.getInt("size"));
					series.setTeacherId(rs.getString("idTeacher"));
					series.setTopic(rs.getString("topic"));
					series.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("uploadTime")));
					series.setThumbnail(rs.getString("thumbnail"));
					
					list.add(series);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	//根据关键字得到符合条件的记录数
	@Override
	public int getRowCountByKeyword(String keyword)
	{
		String sql;
		if(keyword == null || keyword.trim().equals(""))
		{
			sql = "select count(*) from videoseries";
		}
		else
		{
			sql = "select count(*) from videoseries where topic like '%"+keyword+"%'";
		}
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sql);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	/**
	 * 根据用户输入的关键字搜索视频 
	 * @param keyword 关键字
	 * @return Map<String,VideoSeries>符合搜索条件的所有视频系列的map集合
	 */
	@Override
	public List<VideoSeries> videoSeriesSearch(String keyword,SplitPageUtils sp)
	{	
		if(keyword == null || keyword.trim().equals(""))
			return getAll(sp);
		String sqlQuery = "select * from videoseries where topic like '%"+keyword+"%'" +"limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<VideoSeries> list = new ArrayList<VideoSeries>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs!=null)
			{
					while(rs.next())
				{
					VideoSeries series = new VideoSeries();
					String id = rs.getString("idVideoSeries");
					series.setId(id);
					series.setIntroduction(rs.getString("introduction"));
					series.setLinkAddress(rs.getString("linkAddress"));
					series.setParentId(rs.getString("idMiddle"));
					series.setSize(rs.getInt("size"));
					series.setTeacherId(rs.getString("idTeacher"));
					series.setTopic(rs.getString("topic"));
					series.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("uploadTime")));
					series.setThumbnail(rs.getString("thumbnail"));
					
					list.add(series);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	@Override
	public List<VideoSeries> getAll(SplitPageUtils sp)
	{
		String sqlQuery = "select idVideoSeries,topic,v.idTeacher,size,introduction,linkAddress,v.idMiddle,uploadTime,thumbnail,name,middleCategory from videoseries v,teacher t,middle m where v.idTeacher = t.idTeacher and v.idMiddle = m.idMiddle order by uploadTime desc" +" limit " +  sp.getPageRows()*(sp.getCurrentPage()-1) +"," + sp.getPageRows();
		List<VideoSeries> list = new ArrayList<VideoSeries>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while(rs.next())
			{
				VideoSeries series = new VideoSeries();
				series.setId(rs.getString("idVideoSeries"));
				series.setTopic(rs.getString("topic"));
				series.setTeacherId(rs.getString("idTeacher"));
				series.setSize(rs.getInt("size"));
				series.setIntroduction(rs.getString("introduction"));
				series.setLinkAddress(rs.getString("linkAddress"));
				series.setParentId(rs.getString("idMiddle"));
				series.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("uploadTime")));
				series.setThumbnail(rs.getString("thumbnail"));
				series.setTeacName(rs.getString("name"));
				series.setMiddleCategory(rs.getString("middleCategory"));
				
				list.add(series);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	//根据视频系列的编号得到主分类的名称
	@Override
	public Super getSuperBySeriesID(String idSeries)
	{
		if(idSeries == null || idSeries.trim().equals(""))
			return null;
		String sqlQuery = "select category,s.idSuper from videoseries v,middle m,super s where v.idMiddle = m.idMiddle and m.idSuper = s.idSuper and v.idVideoSeries = '"+idSeries+"'" ;
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if(rs.next())
			{
				Super sup = new Super();
				sup.setCategoryName(rs.getString("category"));
				sup.setId(rs.getString("idSuper"));
				return sup;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	//根据主分类的名称得到次级分类的集合
	@Override
	public List<Middle> getMiddleBySuper(String idSeries)
	{
		List<Middle> list = new ArrayList<Middle>();
		Super sup = getSuperBySeriesID(idSeries);
		if(sup ==null )
		{
			return list;
		}
		String sqlQuery = "select * from middle m,super s where m.idSuper = s.idSuper and s.category ='"+sup.getCategoryName()+"'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while(rs.next())
			{
				Middle middle = new Middle();
				middle.setMiddleCategoryName(rs.getString("middlecategory"));
				middle.setId(rs.getString("idMiddle"));
				middle.setParentId(rs.getString("idSuper"));
				
				list.add(middle);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	//找出最新上线的视频系列
	@Override
	public List<VideoSeries> getNewestSeries()
	{
		String sqlQuery = "select * from videoseries order by uploadTime desc limit 0,10";
		List<VideoSeries> list = new ArrayList<VideoSeries>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while(rs.next())
			{
				VideoSeries series = new VideoSeries();
				series.setId(rs.getString("idVideoSeries"));
				series.setThumbnail(rs.getString("thumbnail"));
				series.setTopic(rs.getString("topic"));
				
				list.add(series);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	//找出观看量最多的视频系列
		
	//找出相关分类的视频	
	@Override
	public List<VideoSeries> getRelativeSeries(String idSeries)
	{
		if(idSeries == null || idSeries.trim().equals(""))
			return null;
		String sqlQuery = "select s.idSuper from super s,middle m,videoseries v where s.idSuper = m.idSuper and m.idMiddle = v.idMiddle and v.idVideoSeries = '"+idSeries+"'";
		List<VideoSeries> list = new ArrayList<VideoSeries>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while(rs.next())
			{
				String idSuper = rs.getString("idSuper");
				sqlQuery = "select * from videoseries v,Middle m where v.idMiddle = m.idMiddle and m.idSuper = '"+idSuper+"'" +"and v.idVideoSeries != '"+idSeries+"'limit 0,5";
				rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
				while(rs.next())
				{
					VideoSeries series = new VideoSeries();
					series.setId(rs.getString("idVideoSeries"));
					series.setThumbnail(rs.getString("thumbnail"));
					series.setTopic(rs.getString("topic"));
					
					list.add(series);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	//得到视频系列的总数目
	@Override
	public int getCount()
	{
		String sql = "select count(*) from videoseries";
		
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sql);
			if(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	//得到全部的视频系列
	@Override
	public Map<String,List<VideoSeries>> getAllGroupBySuper()
	{
		Map<String,List<VideoSeries>> map = new LinkedHashMap<String,List<VideoSeries>>();
		String sqlQuery = "select * from super";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			List<String> list = new ArrayList<String>();
			while(rs.next())
			{
				list.add(rs.getString("category"));
			}
			for(int i=0;i<list.size();i++)
			{
				String category = list.get(i);
				String subsql = "select idVideoSeries,topic,thumbnail from videoseries v,middle m,super s where v.idMiddle = m.idMiddle and m.idSuper = s.idSuper and s.category = '"+category+"'";
				rs = DBConnectorImpl.getStatement()
						.executeQuery(subsql);
				List<VideoSeries> serieslist = new ArrayList<VideoSeries>();
				while(rs.next())
				{
					VideoSeries series = new VideoSeries();
					series.setId(rs.getString("idVideoSeries"));
					series.setTopic(rs.getString("topic"));
					series.setThumbnail(rs.getString("thumbnail"));
					
					serieslist.add(series);
				}
				map.put(category, serieslist);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return map;
	}
	
	
}

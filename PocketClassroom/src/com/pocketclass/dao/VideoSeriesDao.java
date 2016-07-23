package com.pocketclass.dao;

import java.util.List;
import java.util.Map;

import com.pocketclass.domain.Middle;
import com.pocketclass.domain.Super;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.utils.SplitPageUtils;

public interface VideoSeriesDao {

	/**
	 * 找到对应id的视频系列
	 * @param idVideoSeries 视频系列的ID号
	 * @return 对应的视频系列
	 */
	VideoSeries findByID(String idVideoSeries);
	
	List<VideoSeries> findAllById(String categoryId,SplitPageUtils sp);
	/**
	 * 得到给定一级分类下的所有的视频系列
	 * @param 一级分类编号
	 * @return 一级分类下的所有视频系列的map集合
	 */
  List<VideoSeries> findAllByPrimaryID(String idSuper,SplitPageUtils sp);
	/**
	 * 得到给定二级级分类下的所有的视频系列
	 * @param 二级分类编号
	 * @return 二级分类下的所有视频系列的map集合
	 */
   List<VideoSeries> findAllBySecondaryID(String idMiddle,SplitPageUtils sp);

	//找出最新上线的视频系列(读配置文件吧)

	/**
	 * 根据用户输入的关键字搜索视频 
	 * @param keyword 关键字
	 * @return 符合搜索条件的所有视频系列的map集合
	 */
List<VideoSeries> videoSeriesSearch(String keyword,SplitPageUtils sp);
	
	Super getSuperBySeriesID(String idSeries);

	List<Middle> getMiddleBySuper(String idSeries);
	
	List<VideoSeries> getRelativeSeries(String idSeries);
	
	Map<String,List<VideoSeries>> getAllGroupBySuper();
	
	int getRowCountById(String categoryId);
	
	int getRowCountByKeyword(String keyword);
	
	 List<VideoSeries> getNewestSeries();
	 
	 String getCategoryNameById(String categoryId);
	 
	 List<VideoSeries> getAll(SplitPageUtils sp);
	 
	 public int getCount();
}
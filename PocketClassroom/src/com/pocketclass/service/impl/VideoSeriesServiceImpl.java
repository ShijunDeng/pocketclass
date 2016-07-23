package com.pocketclass.service.impl;

import java.util.List;
import java.util.Map;

import com.pocketclass.dao.VideoSeriesDao;
import com.pocketclass.dao.impl.customer.VideoSeriesDaoImpl;
import com.pocketclass.domain.Middle;
import com.pocketclass.domain.Super;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.service.VideoSeriesService;
import com.pocketclass.utils.SplitPageUtils;
/**
 * 视频系列相关操作的业务逻辑类
* @author zhanghan 
 * @version 1.0 <br/>         
 */
public class VideoSeriesServiceImpl implements VideoSeriesService {

	private VideoSeriesDao dao = new VideoSeriesDaoImpl();
	
	@Override
	public VideoSeries findByID(String idVideoSeries)
	{
		return dao.findByID(idVideoSeries);
	}
	@Override
	public List<VideoSeries> findAllById(String categoryId,SplitPageUtils sp)
	{
		return dao.findAllById(categoryId, sp);
	}
	@Override
	public List<VideoSeries> findAllByPrimaryID(String idSuper,SplitPageUtils sp)
	{
		return dao.findAllByPrimaryID(idSuper,sp);
	}
	
	@Override
	public List<VideoSeries> findAllBySecondaryID(String idMiddle,SplitPageUtils sp)
	{
		return dao.findAllBySecondaryID(idMiddle,sp);
	}
	
	@Override
	public List<VideoSeries> videoSeriesSearch(String keyword,SplitPageUtils sp)
	{
		return dao.videoSeriesSearch(keyword,sp);
	}
	
	@Override
	public Super getSuperBySeriesID(String idSeries)
	{
		return dao.getSuperBySeriesID(idSeries);
	}
	
	@Override
	public List<Middle> getMiddleBySuper(String idSeries)
	{
		return dao.getMiddleBySuper(idSeries);
	}
	
	@Override
	public List<VideoSeries> getRelativeSeries(String idSeries)
	{
		return dao.getRelativeSeries(idSeries);
	}
	
	@Override
	public Map<String,List<VideoSeries>> getAllGroupBySuper()
	{
		return dao.getAllGroupBySuper();
	}
	
	@Override
	public int getRowCountById(String categoryId)
	{
		return dao.getRowCountById(categoryId);
	}
	
	@Override
	public int getRowCountByKeyword(String keyword)
	{
		return dao.getRowCountByKeyword(keyword);
	}
	
	@Override
	public List<VideoSeries> getNewestSeries()
	{
		return dao.getNewestSeries();
	}
	
	@Override
	public String getCategoryNameById(String categoryId)
	{
		return dao.getCategoryNameById(categoryId);
	}
	
	@Override
	public List<VideoSeries> getAll(SplitPageUtils sp)
	{
		return dao.getAll(sp);
	}
	
	@Override
	public int getCount()
	{
		return dao.getCount();
	}
}

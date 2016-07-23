package com.pocketclass.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.pocketclass.dao.VideoSeriesDao;
import com.pocketclass.dao.impl.customer.VideoSeriesDaoImpl;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.utils.SplitPageUtils;

public class VideoSeriesDaoImplTest {

	@Test
	public void testFindAllByPrimaryID()
	{
		String idSuper = "Super01";
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		List<VideoSeries> list = dao.findAllByPrimaryID(idSuper,new SplitPageUtils());
		
		for(VideoSeries series:list)
		{
			System.out.println(series.getId());
		}
	}
	@Test//通过
	public void testFindAllBySecondaryID()
	{
		String idMiddle = "Middle0101";
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		List<VideoSeries> list = dao.findAllBySecondaryID(idMiddle,new SplitPageUtils());
		
		for(VideoSeries series:list)
		{
			System.out.println(series.getId());
		}
	}
	
	@Test//通过
	public void testVideoSeriesSearch()
	{
		String keyword = "解";
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		List<VideoSeries> list = dao.videoSeriesSearch(keyword,new SplitPageUtils());
		
		for(VideoSeries series:list)
		{
			System.out.println(series.getId());
		}		
	}
	
	@Test
	public void testGetRelativeSeries()
	{
		String idSeries = "ViSer010101";
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		List<VideoSeries> list = dao.getRelativeSeries(idSeries);
		
		for(VideoSeries series:list)
		{
			System.out.println(series.getId());
		}		
	}
	
	@Test
	public void testGetAll()
	{
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		List<VideoSeries> list = dao.getAll(new SplitPageUtils());
		
		for(VideoSeries series:list)
		{
			System.out.println(series.getId());
		}	
	}
	@Test
	public void findAllById()
	{
		String idSuper = "Middle0101";
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		List<VideoSeries> list = dao.findAllById(idSuper,new SplitPageUtils());
		
		for(VideoSeries series:list)
		{
			System.out.println(series.getId());
		}	
	}
	
	@Test
	public void  getNewestSeries()
	{
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		
		List<VideoSeries> list = dao.getNewestSeries();
		
		for(VideoSeries series:list)
		{
			System.out.println(series.getId());
		}		
	}
	
	@Test
	public void testGetAllGroupBySuper()
	{
		VideoSeriesDao dao = new VideoSeriesDaoImpl();
		Map<String,List<VideoSeries>> map = dao.getAllGroupBySuper();
		for(Map.Entry<String, List<VideoSeries>> entry:map.entrySet())
		{
			System.out.println(entry.getKey());
			 List<VideoSeries> list = entry.getValue();
			{
				for(VideoSeries series:list)
				{
					System.out.println(series.getId());
				}
			}
			System.out.println("------------------------------------");
		}
	}
	
}


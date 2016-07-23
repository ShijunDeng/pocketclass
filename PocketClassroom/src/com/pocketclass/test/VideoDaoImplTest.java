package com.pocketclass.test;

import java.util.List;
import org.junit.Test;

import com.pocketclass.dao.VideoDao;
import com.pocketclass.dao.impl.VideoDaoImpl;
import com.pocketclass.domain.Video;

public class VideoDaoImplTest {

	@Test//Í¨¹ý
	public void testFindAllInASeries()
	{
		String idVideoSeries = "viser010101";
		VideoDao dao = new VideoDaoImpl();
		List<Video> list = dao.findAllInASeries(idVideoSeries);
		
		
		for(Video series:list)
		{
			System.out.println(series.getId());
		}		
	}
}

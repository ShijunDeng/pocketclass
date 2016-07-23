package com.pocketclass.service.impl;

import java.util.List;
import com.pocketclass.dao.VideoDao;
import com.pocketclass.dao.impl.VideoDaoImpl;
import com.pocketclass.domain.Video;
import com.pocketclass.service.VideoService;

public class VideoServiceImpl implements VideoService {

	private VideoDao dao = new VideoDaoImpl();
	
	@Override
	public List<Video> findAllInASeries(String idVideoSeries)
	{
		return dao.findAllInASeries(idVideoSeries);
	}
	
	@Override
	public Video findById(String idVideo)
	{
		return dao.findById(idVideo);
	}
}

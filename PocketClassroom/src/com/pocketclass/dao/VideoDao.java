package com.pocketclass.dao;

import java.util.List;

import com.pocketclass.domain.Video;

public interface VideoDao {

	/**
	 * 得到一个视频系列中的所有的视频
	 * @param idVideoSeries 视频系列的ID
	 * @return 视频系列下的所有视频的map集合
	 */
	List<Video> findAllInASeries(String idVideoSeries);

	Video findById(String idVideo);
}
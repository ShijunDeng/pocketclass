package com.pocketclass.service;

import java.util.List;
import java.util.Map;

import com.pocketclass.domain.Middle;
import com.pocketclass.domain.Super;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.utils.SplitPageUtils;

public interface VideoSeriesService {

	VideoSeries findByID(String idVideoSeries);

	List<VideoSeries> findAllById(String categoryId, SplitPageUtils sp);

	List<VideoSeries> findAllByPrimaryID(String idSuper,
			SplitPageUtils sp);

	List<VideoSeries> findAllBySecondaryID(String idMiddle,
			SplitPageUtils sp);

	List<VideoSeries> videoSeriesSearch(String keyword, SplitPageUtils sp);

	Super getSuperBySeriesID(String idSeries);

	List<Middle> getMiddleBySuper(String idSeries);

	List<VideoSeries> getRelativeSeries(String idSeries);

	Map<String, List<VideoSeries>> getAllGroupBySuper();

	int getRowCountById(String categoryId);

	int getRowCountByKeyword(String keyword);
	
	String getCategoryNameById(String categoryId);

	List<VideoSeries> getAll(SplitPageUtils sp);
	
	List<VideoSeries> getNewestSeries();
	
	int getCount();
}
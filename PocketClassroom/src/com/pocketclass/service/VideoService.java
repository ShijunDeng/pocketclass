package com.pocketclass.service;

import java.util.List;

import com.pocketclass.domain.Video;

public interface VideoService {

	List<Video> findAllInASeries(String idVideoSeries);

	Video findById(String idVideo);
}
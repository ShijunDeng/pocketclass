package com.pocketclass.dao;

import java.util.List;

import com.pocketclass.domain.Video;

public interface VideoDao {

	/**
	 * �õ�һ����Ƶϵ���е����е���Ƶ
	 * @param idVideoSeries ��Ƶϵ�е�ID
	 * @return ��Ƶϵ���µ�������Ƶ��map����
	 */
	List<Video> findAllInASeries(String idVideoSeries);

	Video findById(String idVideo);
}
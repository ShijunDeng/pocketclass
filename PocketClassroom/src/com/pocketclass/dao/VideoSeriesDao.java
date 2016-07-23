package com.pocketclass.dao;

import java.util.List;
import java.util.Map;

import com.pocketclass.domain.Middle;
import com.pocketclass.domain.Super;
import com.pocketclass.domain.VideoSeries;
import com.pocketclass.utils.SplitPageUtils;

public interface VideoSeriesDao {

	/**
	 * �ҵ���Ӧid����Ƶϵ��
	 * @param idVideoSeries ��Ƶϵ�е�ID��
	 * @return ��Ӧ����Ƶϵ��
	 */
	VideoSeries findByID(String idVideoSeries);
	
	List<VideoSeries> findAllById(String categoryId,SplitPageUtils sp);
	/**
	 * �õ�����һ�������µ����е���Ƶϵ��
	 * @param һ��������
	 * @return һ�������µ�������Ƶϵ�е�map����
	 */
  List<VideoSeries> findAllByPrimaryID(String idSuper,SplitPageUtils sp);
	/**
	 * �õ����������������µ����е���Ƶϵ��
	 * @param ����������
	 * @return ���������µ�������Ƶϵ�е�map����
	 */
   List<VideoSeries> findAllBySecondaryID(String idMiddle,SplitPageUtils sp);

	//�ҳ��������ߵ���Ƶϵ��(�������ļ���)

	/**
	 * �����û�����Ĺؼ���������Ƶ 
	 * @param keyword �ؼ���
	 * @return ��������������������Ƶϵ�е�map����
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
package com.pocketclass.dao;

import java.util.ArrayList;

/**
 * ��ҳ��¼�Ĳ�ѯ�ӿ�
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-27 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public interface PageDataDao {
	/**
	 * 
	 * @param currentPage
	 *            :��ѯ��¼����ʼλ��
	 * @param recordNum
	 *            ����¼����
	 * @param keyword
	 *            ���ؼ���
	 * @return:(currentPage,min(recordNum+currentPage,��¼����))��Ľ����
	 */
	public <T> ArrayList<T> getPageData(int currentPage,
			int recordNum, String keyword);

	/**
	 * 
	 * @param keyword
	 *            :�ؼ���
	 * @return����ǰҳ��ļ�¼
	 */
	public <T> ArrayList<T> CurrentPageData(String keyword);

	/**
	 * 
	 * @param keyword
	 *            :�ؼ���
	 * @return����һҳ��ļ�¼
	 */
	public <T> ArrayList<T> NextPageData(String keyword);

	/**
	 * 
	 * @param keyword
	 *            :�ؼ���
	 * @return����һҳ��ļ�¼
	 */
	public <T> ArrayList<T> PriorPageData(String keyword);

	/**
	 * 
	 * @param keyword
	 *            ���ؼ���
	 * @return�����м�¼��ҳ��ҳ��
	 */
	public int getTotalPagesNum(String keyword);

	/**
	 * 
	 * @param keyword
	 *            :�ؼ���
	 * @return����¼������
	 */
	public int getRecordsNum(String keyword);

}

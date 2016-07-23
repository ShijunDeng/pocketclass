package com.pocketclass.dao;

import java.util.ArrayList;

/**
 * 分页记录的查询接口
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-27 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public interface PageDataDao {
	/**
	 * 
	 * @param currentPage
	 *            :查询记录的起始位置
	 * @param recordNum
	 *            ：记录条数
	 * @param keyword
	 *            ：关键字
	 * @return:(currentPage,min(recordNum+currentPage,记录总数))间的结果集
	 */
	public <T> ArrayList<T> getPageData(int currentPage,
			int recordNum, String keyword);

	/**
	 * 
	 * @param keyword
	 *            :关键字
	 * @return：当前页面的记录
	 */
	public <T> ArrayList<T> CurrentPageData(String keyword);

	/**
	 * 
	 * @param keyword
	 *            :关键字
	 * @return：下一页面的记录
	 */
	public <T> ArrayList<T> NextPageData(String keyword);

	/**
	 * 
	 * @param keyword
	 *            :关键字
	 * @return：上一页面的记录
	 */
	public <T> ArrayList<T> PriorPageData(String keyword);

	/**
	 * 
	 * @param keyword
	 *            ：关键字
	 * @return：表中记录总页数页数
	 */
	public int getTotalPagesNum(String keyword);

	/**
	 * 
	 * @param keyword
	 *            :关键字
	 * @return：记录总条数
	 */
	public int getRecordsNum(String keyword);

}

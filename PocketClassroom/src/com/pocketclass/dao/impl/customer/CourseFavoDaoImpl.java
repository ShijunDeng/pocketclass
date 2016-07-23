package com.pocketclass.dao.impl.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pocketclass.dao.PageDataDao;
import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.web.beans.CourseFavorites;

/**
 * ��ѯ�û���ϵ���ղ�,�漰(seriesfavorite,videoseries,teacher, middle)���ű�Ĳ���
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-27 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
@SuppressWarnings("unchecked")
public class CourseFavoDaoImpl implements PageDataDao {
	private int pageRecordsNum = 10;// ҳ���¼����
	private int currentPageTag = 1;// ��ǰҳ����

	public int getPageRecordsNum() {
		return pageRecordsNum;
	}

	public void setPageRecordsNum(int pageRecordsNum) {
		this.pageRecordsNum = pageRecordsNum;
	}

	public int getCurrentPageTag() {
		return currentPageTag;
	}

	public void setCurrentPageTag(int currentPageTag) {
		this.currentPageTag = currentPageTag;
	}

	/**
	 * 
	 * @param currentPage
	 *            :��ѯ��¼����ʼλ��(������)
	 * @param recordNum
	 *            ����¼����
	 * @param keyword
	 *            ���ؼ���
	 * @return: 
	 *          ��recordNum>0,����(currentPage,min(recordNum+currentPage,��¼����))��Ľ����
	 *          ��recordNum< 0 ,����(max(recordNum+currentPage
	 *          ,0),currentPage)��Ľ����
	 */
	@Override
	public ArrayList<CourseFavorites> getPageData(int currentPage,
			int recordNum, String keyword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//SimpleDateFormat sdfToLocal = new SimpleDateFormat(
			//	"yyyy��MM��dd�� HHʱmm��ss��");
		ArrayList<CourseFavorites> courseFavorites = new ArrayList<CourseFavorites>();
		if (currentPage < 1)
			throw new IllegalArgumentException("�������Ϸ���" + currentPage);
		String sqlQuery;
		if (recordNum < 0) {
			sqlQuery = "SELECT videoseries.idVideoSeries,middle.idMiddle, middle.middleCateGory,videoseries.topic,teacher.name teacher,seriesfavorite.addTime,VideoSeries.thumbnail"
					+ " from seriesfavorite,videoseries,teacher, middle WHERE videoSeries.idMiddle=middle.idMiddle AND videoSeries.idVideoSeries=seriesfavorite.idVideoSeries AND videoSeries.idTeacher=teacher.idTeacher"
					+ " AND seriesfavorite.username='"
					+ keyword
					+ "' ORDER BY seriesfavorite.addTime DESC limit "
					+ Math.max(0, (currentPage - 1) * this.getPageRecordsNum()
							+ recordNum) + "," + Math.abs(recordNum) + "";

		} else {
			sqlQuery = "SELECT videoseries.idVideoSeries,middle.idMiddle, middle.middleCateGory,videoseries.topic,teacher.name teacher,seriesfavorite.addTime,VideoSeries.thumbnail"
					+ " from seriesfavorite,videoseries,teacher, middle WHERE videoSeries.idMiddle=middle.idMiddle AND videoSeries.idVideoSeries=seriesfavorite.idVideoSeries AND videoSeries.idTeacher=teacher.idTeacher"
					+ " AND seriesfavorite.username='"
					+ keyword
					+ "' ORDER BY seriesfavorite.addTime DESC limit "
					+ (currentPage - 1)
					* this.getPageRecordsNum()
					+ ","
					+ recordNum;
		}

		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			if (rs != null) {
				while (rs.next()) {
					CourseFavorites courFavo = new CourseFavorites();
					courFavo.setIdVideoSeries(rs.getString("idVideoSeries"));
					courFavo.setTopic(rs.getString("topic"));
					courFavo.setIdMiddle(rs.getString("idMiddle"));
					courFavo.setMiddleCateGory(rs.getString("middleCateGory"));
					courFavo.setAddTime(sdf.format(sdf.parse((rs
							.getString("addTime")))));
					courFavo.setTeacher(rs.getString("teacher"));
					courFavo.setThumbnail(rs.getString("thumbnail"));
					courseFavorites.add(courFavo);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return courseFavorites;
	}

	/**
	 * ���ص�ǰҳ����
	 */
	@Override
	public ArrayList<CourseFavorites> CurrentPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag(),
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * ������һҳ����
	 */
	@Override
	public ArrayList<CourseFavorites> NextPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() + 1,
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * ����ǰһҳ����
	 */
	@Override
	public ArrayList<CourseFavorites> PriorPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() - 1,
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * 
	 * @param keyword
	 * @return :ɾ���û���Ϊkeyword�������ղؼ�¼�������ɹ�����true�����򷵻�false
	 */
	public boolean delete(String keyword) {
		String sqlDelete = "DELETE FROM seriesfavorite WHERE username='"
				+ keyword + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param keyword
	 * @return :ɾ��ָ���ղر�ŵ��ղؼ�¼�������ɹ�����true�����򷵻�false
	 */
	public boolean delete(String keyword, String idVideoSeries) {
		String sqlDelete = "DELETE FROM seriesfavorite WHERE username='"
				+ keyword + "' AND idVideoSeries='" + idVideoSeries + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param keyword
	 * @return :���һ���ղؼ�¼�������ɹ�����true�����򷵻�false
	 */
	public boolean add(String keyword, String idVideoSeries) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String sqlAdd = "INSERT INTO SeriesFavorite VALUES('" + keyword + "','"
				+ idVideoSeries + "','" + sdf.format(now) + "')";
		try {
			DBConnectorImpl.getStatement().execute(sqlAdd);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * ����һ������ҳ
	 */
	@Override
	public int getTotalPagesNum(String keyword) {
		if (this.getRecordsNum(keyword) % this.getPageRecordsNum() == 0) {
			return this.getRecordsNum(keyword) / this.getPageRecordsNum();
		}
		return (this.getRecordsNum(keyword) / this.getPageRecordsNum()) + 1;
	}

	@Override
	public int getRecordsNum(String keyword) {
		String sqlQuery = "SELECT COUNT(*) from seriesfavorite,videoseries,teacher, middle WHERE videoSeries.idMiddle=middle.idMiddle AND videoSeries.idTeacher=teacher.idTeacher"
				+ " AND videoSeries.idVideoSeries=seriesfavorite.idVideoSeries AND seriesfavorite.username='"
				+ keyword + "' ";
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			if (rs != null) {
				while (rs.next()) {
					return Integer.parseInt(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return 0;
	}

	/**
	 * 
	 * @param keyword
	 * @return 
	 *         :�û���Ϊkeyword���û��Ƿ��ղص�ϵ�б��ΪidVideoSeries����Դ���Ƿ���true���쳣����û���ղ��򷵻�false
	 */
	public boolean exist(String keyword, String idVideoSeries) {
		String sqlQuery = "SELECT * FROM seriesfavorite WHERE username='"
				+ keyword + "' AND idVideoSeries='" + idVideoSeries + "'";
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param username
	 * @return :�û���Ϊusername�����е�ϵ���ղر��
	 */
	public Map<String, String> getAllFavoCourse(String username) {
		String sqlQuery = "SELECT idVideoSeries FROM seriesfavorite WHERE username='"
				+ username + "' ";
		ResultSet rs;
		Map<String, String> allFavaCour = new HashMap<String, String>();
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			while (rs.next()) {
				allFavaCour.put(rs.getString("idVideoSeries"), username);
			}
			return allFavaCour;
		} catch (SQLException e) {
			return allFavaCour;
		}
	}
}

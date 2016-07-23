package com.pocketclass.dao.impl.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.pocketclass.dao.PageDataDao;
import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.web.beans.LearnProgress;

/**
 * 
 * ѧϰ���ȵĲ���
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-29 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
@SuppressWarnings("unchecked")
public class LearnDaoImpl implements PageDataDao {
	private int pageRecordsNum = 10;// ҳ���¼����
	private int currentPageTag = 1;// ��ǰҳ����
	private String isEnd = "YES";// �Ƿ����

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

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
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
	public ArrayList<LearnProgress> getPageData(int currentPage, int recordNum,
			String keyword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<LearnProgress> learnProgresses = new ArrayList<LearnProgress>();
		if (currentPage < 1)
			throw new IllegalArgumentException("�������Ϸ���" + currentPage);
		String sqlQuery;
		if (recordNum < 0) {
			sqlQuery = "SELECT videoseries.idVideoSeries,videoseries.size,"
					+ "middle.idMiddle, middle.middleCateGory,"
					+ "videoseries.topic,teacher.name teacher,VideoSeries.thumbnail,"
					+ "progress.startTime,progress.endTime ,progress.isEnd "
					+ "from videoseries,teacher, middle,progress "
					+ " WHERE videoSeries.idMiddle=middle.idMiddle AND "
					+ "videoSeries.idVideoSeries=progress.idVideoSeries AND "
					+ "videoSeries.idTeacher=teacher.idTeacher AND "
					+ "videoseries.idMiddle=middle.idMiddle AND  "
					+ "progress.isEnd='"
					+ this.getIsEnd()
					+ "' AND "
					+ "progress.username='"
					+ keyword
					+ "' ORDER BY progress.startTime DESC  limit "
					+ Math.max(0, (currentPage - 1) * this.getPageRecordsNum()
							+ recordNum) + "," + Math.abs(recordNum);

		} else {
			sqlQuery = "SELECT videoseries.idVideoSeries,videoseries.size, "
					+ "middle.idMiddle, middle.middleCateGory, "
					+ "videoseries.topic,teacher.name teacher,VideoSeries.thumbnail, "
					+ "progress.startTime,progress.endTime ,progress.isEnd  "
					+ "from videoseries,teacher, middle,progress   "
					+ "WHERE videoSeries.idMiddle=middle.idMiddle AND  "
					+ "videoSeries.idVideoSeries=progress.idVideoSeries AND  "
					+ "videoSeries.idTeacher=teacher.idTeacher AND  "
					+ "videoseries.idMiddle=middle.idMiddle AND  "
					+ "progress.isEnd='" + this.getIsEnd() + "' AND "
					+ "progress.username='" + keyword
					+ "' ORDER BY progress.startTime DESC limit "
					+ (currentPage - 1) * this.getPageRecordsNum() + ","
					+ recordNum;

		}

		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			if (rs != null) {
				while (rs.next()) {
					LearnProgress learnProgress = new LearnProgress();
					learnProgress.setIdVideoSeries(rs
							.getString("idVideoSeries"));
					learnProgress.setTopic(rs.getString("topic"));
					learnProgress.setIdMiddle(rs.getString("idMiddle"));
					learnProgress.setMiddleCateGory(rs
							.getString("middleCateGory"));
					learnProgress.setStartTime(sdf.format(sdf.parse(rs
							.getString("startTime"))));
					learnProgress.setTeacher(rs.getString("teacher"));
					learnProgress.setThumbnail(rs.getString("thumbnail"));
					learnProgress.setIsEnd(rs.getString("isEnd"));
					if (learnProgress.getIsEnd().trim().equals("YES")) {
						learnProgress.setEndTime(sdf.format(sdf.parse(rs
								.getString("endTime"))));
					} else {
						learnProgress.setEndTime("");

					}
					learnProgress.setSize(rs.getInt("size"));
					learnProgresses.add(learnProgress);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return learnProgresses;
	}

	/**
	 * ���ص�ǰҳ����
	 */
	@Override
	public ArrayList<LearnProgress> CurrentPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag(),
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * ������һҳ����
	 */
	@Override
	public ArrayList<LearnProgress> NextPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() + 1,
				this.getPageRecordsNum(), keyword);
	}

	/**
	 * ����ǰһҳ����
	 */
	@Override
	public ArrayList<LearnProgress> PriorPageData(String keyword) {
		return this.getPageData(this.getCurrentPageTag() - 1,
				this.getPageRecordsNum(), keyword);
	}
	/**
	 * 
	 * @param keyword
	 * @param idVideoSeries
	 * @return ����һ���Ե�ǰʱ��Ϊ��ʼʱ�䡢��Ƶ���ΪidVideoSeries��ѧϰ��¼
	 */
	public boolean add(String keyword, String idVideoSeries) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String sqlDelete = "INSERT INTO  progress VALUES('" + keyword
				+ "','" + idVideoSeries + "','"+ sdf.format(now)+"',NULL,'"+"NO"+"')";
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
	 * @param idVideoSeries
	 * @return ɾ���û�����idVideoSeries��ѧϰ��¼
	 */
	public boolean delete(String keyword, String idVideoSeries) {
		String sqlDelete = "DELETE FROM progress WHERE username='" + keyword
				+ "' AND progress.idVideoSeries='" + idVideoSeries + "'";
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
	 * @param idVideoSeries
	 * @return ɾ���û�������Ƶϵ�б��ΪidVideoSeries��״̬ΪisEnd��ѧϰ��¼
	 */
	public boolean delete(String keyword, String idVideoSeries, String isEnd) {
		String sqlDelete = "DELETE FROM progress WHERE username='" + keyword
				+ "' AND progress.idVideoSeries='" + idVideoSeries
				+ "' AND progress.isEnd='" + isEnd + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * �ڵ��õ�ʱ���������isEnd���Ա���������
	 * @param keyword
	 * @return :ɾ���û���Ϊkeyword������״̬ΪisEnd�Ľ��ȼ�¼��Ĭ��ɾ���Ѿ�ѧ��ģ��������ɹ�����true�����򷵻�false
	 */
	public boolean delete(String keyword) {
		String sqlDelete = "DELETE FROM progress WHERE username='" + keyword
				+ "' AND progress.isEnd='" + this.getIsEnd() + "'";
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
	 * @return :���Ϊ�Ѿ�ѧ��
	 */
	public boolean changeLearnState(String keyword, String idVideoSeries,
			String isEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String sqlDelete = "UPDATE progress SET isEnd='" + isEnd
				+ "',endTime='" + sdf.format(now) + "' WHERE username='"
				+ keyword + "' AND idVideoSeries='" + idVideoSeries + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
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
		String sqlQuery = "SELECT COUNT(*) "
				+ "from videoseries,teacher, middle,progress "
				+ " WHERE videoSeries.idMiddle=middle.idMiddle AND "
				+ "videoSeries.idVideoSeries=progress.idVideoSeries AND "
				+ "videoSeries.idTeacher=teacher.idTeacher AND "
				+ "videoseries.idMiddle=middle.idMiddle AND  "
				+ "progress.isEnd='" + this.getIsEnd() + "' AND "
				+ "progress.username='" + keyword + "'";
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

}

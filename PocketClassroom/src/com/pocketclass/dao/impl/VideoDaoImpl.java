package com.pocketclass.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.pocketclass.dao.VideoDao;
import com.pocketclass.domain.Video;

/**
 * ��Ƶ�ģ�����
 * 
 * @author zhanghan
 * @version 1.0 <br/>
 * 
 */
public class VideoDaoImpl implements VideoDao {

	/**
	 * �õ�һ����Ƶϵ���е����е���Ƶ
	 * 
	 * @param idVideoSeries
	 *            ��Ƶϵ�е�ID
	 * @return ��Ƶϵ���µ�������Ƶ��map����
	 */
	@Override
	public List<Video> findAllInASeries(String idVideoSeries) {
		String sqlQuery = "select * from video where idVideoSeries = '"
				+ idVideoSeries + "'";
		List<Video> list = new ArrayList<Video>();
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs != null) {
				while (rs.next()) {
					Video video = new Video();
					String id = rs.getString("idVideo");
					video.setId(id);
					video.setDuration(rs.getTime("duration").toString());
					video.setLinkAddress(rs.getString("linkAddress"));
					video.setParentId(rs.getString("idVideoSeries"));
					video.setPosition(rs.getInt("position"));
					video.setSize(rs.getDouble("size"));
					video.setTitle(rs.getString("title"));
					video.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("uploadTime")));

					list.add(video);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * ������Ƶ�ı�ŵõ���Ӧ����Ƶ
	 * @param idVideo ��Ƶ���
	 * @return �ҵ��ˣ����� �����򣬷���null
	 */
	@Override
	public Video findById(String idVideo) {
		if (idVideo == null || idVideo.trim().equals("")) {
			return null;
		}
		
		String sqlQuery = "select * from video where idVideo = '" + idVideo	+ "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			if (rs != null) {
				if(rs.next())
				{
					Video video = new Video();
					
					String id = rs.getString("idVideo");
					video.setId(id);
					video.setDuration(rs.getTime("duration").toString());
					video.setLinkAddress(rs.getString("linkAddress"));
					video.setParentId(rs.getString("idVideoSeries"));
					video.setPosition(rs.getInt("position"));
					video.setSize(rs.getDouble("size"));
					video.setTitle(rs.getString("title"));
					video.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("uploadTime")));
					
					return video;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}

package com.pocketclass.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.pocketclass.dao.VideoDao;
import com.pocketclass.domain.Video;

/**
 * 视频ＤＡＯ类
 * 
 * @author zhanghan
 * @version 1.0 <br/>
 * 
 */
public class VideoDaoImpl implements VideoDao {

	/**
	 * 得到一个视频系列中的所有的视频
	 * 
	 * @param idVideoSeries
	 *            视频系列的ID
	 * @return 视频系列下的所有视频的map集合
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
	 * 根据视频的编号得到对应的视频
	 * @param idVideo 视频编号
	 * @return 找到了，返回 ；否则，返回null
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

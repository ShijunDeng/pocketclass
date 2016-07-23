package com.pocketclass.dao.impl.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.message.Message;

/**
 * 站内通信消息处理
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-06 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class MessageDaoImpl {
	private int pageRecordsNum = 10;// 页面记录条数
	private int currentPageTag = 1;// 当前页面标记

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
	 * @param username
	 * @param idLocationCategory
	 * @param isRead
	 * @return 返回用户idLocationCategory分类中阅读属性为isRead的信息,按发信时间降序排序
	 *         当recordNum>0,返回(currentPage,min(recordNum+currentPage,记录总数))间的结果集
	 *         当recordNum< 0 ,返回(max(recordNum+currentPage ,0),currentPage)间的结果集
	 */

	public ArrayList<Message> getPageData(String username,
			String idLocationCategory, String isRead, int currentPage,
			int recordNum) {
		ArrayList<Message> msgs = new ArrayList<Message>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfToLocal = new SimpleDateFormat(
				"yyyy年MM月dd日 HH时mm分ss秒");
		String sqlQuery;
		if (currentPage < 1)
			throw new IllegalArgumentException("参数不合法：" + currentPage);
		if (recordNum < 0) {
			sqlQuery = "SELECT Message.sendUserName,Message.addreUserName,Message.sendTime,Message.title,Message.isRead "
					+ "FROM Message,MessageLocation WHERE "
					+ "Message.sendUserName= MessageLocation.sendUserName AND "
					+ "Message.sendTime=MessageLocation.sendTime AND "
					+ "Message.isRead='"
					+ isRead
					+ "MessageLocation.idLocationCategory='"
					+ idLocationCategory
					+ "' AND"
					+ "MessageLocation.username='"
					+ username
					+ "'   ORDER BY Message.sendTime DESC limit "
					+ Math.max(0, (currentPage - 1) * this.getPageRecordsNum()
							+ recordNum) + "," + Math.abs(recordNum);
		} else {
			sqlQuery = "SELECT Message.sendUserName,Message.addreUserName,Message.sendTime,Message.title,Message.content,Message.isRead "
					+ "FROM Message,MessageLocation WHERE "
					+ "Message.sendUserName= MessageLocation.sendUserName AND "
					+ "Message.sendTime=MessageLocation.sendTime AND "
					+ "Message.isRead='"
					+ isRead
					+ "MessageLocation.idLocationCategory='"
					+ idLocationCategory
					+ "' AND"
					+ "MessageLocation.username='"
					+ username
					+ "'  ORDER BY Message.sendTime DESC limit "
					+ (currentPage - 1)
					* this.getPageRecordsNum()
					+ ","
					+ recordNum;

		}

		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Message msg = new Message();
				msg.setSendUserName(rs.getString("sendUserName"));
				msg.setAddreUserName(rs.getString("addreUserName"));
				msg.setSendTime(sdfToLocal.format(sdf.parse(rs
						.getString("sendTime"))));
				msg.setTitle(rs.getString("title"));
				// msg.setContent(rs.getString("content"));
				// 这里先不加载消息内容
				msg.setIsRead(rs.getString("isRead").trim());
				msgs.add(msg);
			}
		} catch (Exception e) {
			// 异常：Sql执行异常和日期转换异常，都会使查到的数据不完整或者查询不到相关的数据，故而直接返回null
			return null;
		}
		return msgs;

	}

	/**
	 * 
	 * @param username
	 * @param idLocationCategory
	 * @param isRead
	 * @return 返回用户idLocationCategory分类中全部信息,按发信时间降序排序
	 */
	public ArrayList<Message> getPageData(String username,
			String idLocationCategory, int currentPage, int recordNum) {
		ArrayList<Message> msgs = new ArrayList<Message>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery;
		if (currentPage < 1)
			throw new IllegalArgumentException("参数不合法：" + currentPage);
		if (recordNum < 0) {
			sqlQuery = "SELECT Message.sendUserName,Message.addreUserName,Message.sendTime,Message.title,Message.content,Message.isRead "
					+ "FROM Message,MessageLocation WHERE "
					+ "Message.sendUserName= MessageLocation.sendUserName AND "
					+ "Message.sendTime=MessageLocation.sendTime AND "
					+ "MessageLocation.idLocationCategory='"
					+ idLocationCategory
					+ "' AND"
					+ "MessageLocation.username='"
					+ username
					+ "'   ORDER BY Message.sendTime DESC limit "
					+ Math.max(0, (currentPage - 1) * this.getPageRecordsNum()
							+ recordNum) + "," + Math.abs(recordNum);
		} else {
			sqlQuery = "SELECT Message.sendUserName,Message.addreUserName,Message.sendTime,Message.title,Message.content,Message.isRead "
					+ "FROM Message,MessageLocation WHERE "
					+ "Message.sendUserName= MessageLocation.sendUserName AND "
					+ "Message.sendTime=MessageLocation.sendTime AND "
					+ "MessageLocation.idLocationCategory='"
					+ idLocationCategory
					+ "' AND "
					+ "MessageLocation.username='"
					+ username
					+ "'  ORDER BY Message.sendTime DESC limit "
					+ (currentPage - 1)
					* this.getPageRecordsNum()
					+ ","
					+ recordNum;

		}

		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			while (rs.next()) {
				Message msg = new Message();
				msg.setSendUserName(rs.getString("sendUserName"));
				msg.setAddreUserName(rs.getString("addreUserName"));
				msg.setSendTime(sdf.format(sdf.parse(rs.getString("sendTime"))));
				msg.setTitle(rs.getString("title"));
				// msg.setContent(rs.getString("content"));
				msg.setIsRead(rs.getString("isRead").trim());
				msgs.add(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常：Sql执行异常和日期转换异常，都会使查到的数据不完整或者查询不到相关的数据，故而直接返回null
			return null;
		}
		return msgs;

	}

	/**
	 * 
	 * @param msg
	 * @return 向数据库中的消息表中插入一条数据，发信人的发信箱插入一条记录,收信人的收信箱插入一条记录,成功返回true，否则返回false,
	 */
	public boolean sendMesage(Message msg) {
		if (msg == null) {
			return false;
		} else {
			// 插入一条消息记录
			String sqlInsert = "INSERT INTO Message(sendUserName,addreUserName,sendTime,title,content,isRead) VALUES('"
					+ msg.getSendUserName()
					+ "','"
					+ msg.getAddreUserName()
					+ "','"
					+ msg.getSendTime()
					+ "','"
					+ msg.getTitle()
					+ "','" + msg.getContent() + "','" + msg.getIsRead() + "')";
			// 发信人的发信箱插入一条记录
			String sqlInsertFrom = "INSERT INTO MessageLocation(idLocationCategory,sendUserName,username,sendTime) VALUES('002','"
					+ msg.getSendUserName() + "','" + msg.getSendUserName() + "','" + msg.getSendTime() + "')";
			// 收信人的收信箱插入一条记录
			String sqlInsertTo = "INSERT INTO MessageLocation(idLocationCategory,sendUserName,username,sendTime) VALUES('001','"
					+ msg.getSendUserName() + "','" + msg.getAddreUserName() + "','" + msg.getSendTime() + "')";

			try {
				DBConnectorImpl.getStatement().execute(sqlInsert);
				DBConnectorImpl.getStatement().execute(sqlInsertFrom);
				DBConnectorImpl.getStatement().execute(sqlInsertTo);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;

	}

	/**
	 * @param username
	 * @param msg
	 *            被操作的消息对象
	 * @param fromIdLocaCare
	 *            ：消息原位置
	 * @param toIdLocaCare
	 *            ：消息被移动的目的位置
	 * @return 
	 *         ：将用户username的一条消息从fromIdLocaCare分类移至toIdLocaCare中，操作成功返回true，否则返回false
	 */
	public boolean messageOperate(String username, Message msg,
			String fromIdLocaCare, String toIdLocaCare) {
		/******************** 数据的undo操作 ***********************************/
		String sqlUpdate = "UPDATE  MessageLocation SET idLocationCategory='"
				+ toIdLocaCare + "' WHERE sendUserName='"
				+ msg.getSendUserName() + "' AND username='" + username
				+ "' AND sendTime='" + msg.getSendTime()
				+ "' AND idLocationCategory='" + fromIdLocaCare + "'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			if (this.exist(username, toIdLocaCare, msg)) {
				// 自己自己发信息的时候，当用户从收信箱和发信箱同时收藏这条消息的时候，会产生冲突，但是这种极端情况极少发生，
				// 为了兼顾程序的执行效率，就只在异常中判断和解决，而不是每次都判断
				return this
						.deleteMsgByLocationId(username, msg, fromIdLocaCare);
			}
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * @ username:用户名
	 * 
	 * @param msg
	 *            :被操作的消息对象
	 * @param idLocationCategory
	 *            :添加消息的目的位置
	 * @return ：将消息添加到用户username的idLocationCategory的分类中
	 */
	public boolean addToMessageBox(String username, Message msg,
			String idLocationCategory) {
		String sqlInsert = "INSERT INTO  MessageLocation VALUES('"
				+ idLocationCategory + "','" + msg.getSendUserName() + "','"
				+ username + "','" + msg.getSendTime() + "')";
		try {
			DBConnectorImpl.getStatement().execute(sqlInsert);
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

	/**
	 * 
	 * @return 删除一条消息，操作成功返回true,否则返回false(不考虑依赖)
	 */
	public boolean deleteMsg(Message msg) {
		String sqlDelete = "DELETE FROM Message WHERE sendUserName='"
				+ msg.getSendUserName() + "' AND sendTime='"
				+ msg.getSendTime() + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

	/**
	 * 删除用户username的idLocationCategory分类的全部信息
	 * 
	 * @return 删除一条消息，操作成功返回true,否则返回false(不考虑依赖)
	 */
	public boolean deleteMsgByLocationId(String username,
			String idLocationCategory) {
		String sqlDelete = "DELETE FROM MessageLocation WHERE username='"
				+ username + "' AND idLocationCategory='" + idLocationCategory
				+ "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

	/**
	 * 删除用户username的idLocationCategory分类消息msg
	 * 
	 * @return 删除一条消息，操作成功返回true,否则返回false(不考虑依赖)
	 */
	public boolean deleteMsgByLocationId(String username, Message msg,
			String idLocationCategory) {
		String sqlDelete = "DELETE FROM MessageLocation WHERE username='"
				+ username + "' AND idLocationCategory='" + idLocationCategory
				+ "' AND sendUserName='" + msg.getSendUserName()
				+ "' AND sendTime='" + msg.getSendTime() + "'";
		try {
			DBConnectorImpl.getStatement().execute(sqlDelete);
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

	/**
	 * 返回当前页数据
	 */
	public ArrayList<Message> CurrentPageData(String keyword,
			String idLocationCategory) {
		return this.getPageData(keyword, idLocationCategory,
				this.getCurrentPageTag(), this.getPageRecordsNum());
	}

	/**
	 * 返回下一页数据
	 */
	public ArrayList<Message> NextPageData(String keyword,
			String idLocationCategory) {
		return this.getPageData(keyword, idLocationCategory,
				this.getCurrentPageTag() + 1, this.getPageRecordsNum());
	}

	/**
	 * 返回前一页数据
	 */
	public ArrayList<Message> PriorPageData(String keyword,
			String idLocationCategory) {
		return this.getPageData(keyword, idLocationCategory,
				this.getCurrentPageTag() - 1, this.getPageRecordsNum());
	}

	/**
	 * 返回一共多少页
	 */
	public int getTotalPagesNum(String username, String idLocationCategory) {
		if (this.getRecordsNum(username, idLocationCategory)
				% this.getPageRecordsNum() == 0) {
			return this.getRecordsNum(username, idLocationCategory)
					/ this.getPageRecordsNum();
		}
		return (this.getRecordsNum(username, idLocationCategory) / this
				.getPageRecordsNum()) + 1;
	}

	public int getRecordsNum(String username, String idLocationCategory) {
		String sqlQuery = " SELECT COUNT(*) FROM MessageLocation WHERE "
				+ "idLocationCategory='" + idLocationCategory + "' AND "
				+ "username='" + username + "' ";
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

	public boolean exist(String username, String idLocationCategory, Message msg) {
		String sqlQuery = " SELECT * FROM MessageLocation WHERE "
				+ "idLocationCategory='" + idLocationCategory + "' AND "
				+ "username='" + username + "' AND sendUserName='"
				+ msg.getSendUserName() + "' AND sendTime='"
				+ msg.getSendTime() + "'";
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			if (rs != null) {
				return rs.next();
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @param username
	 * @return username的返回未读消息数目
	 */
	public int getUnReadMessageNum(String username) {
		String sqlQuery = "SELECT COUNT(*) FROM Message WHERE addreUserName='"
				+ username + "' AND isRead='NO'";
		ResultSet rs;
		try {
			rs = DBConnectorImpl.getStatement().executeQuery(sqlQuery);
			if (rs.next())
				return rs.getInt(1);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public Message getMessage(Message msgPara) {
		String sqlQuery = " SELECT sendUserName,addreUserName,sendTime,content,title,isRead FROM Message WHERE  sendUserName='"
				+ msgPara.getSendUserName()
				+ "' AND sendTime='"
				+ msgPara.getSendTime() + "'";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				Message msg = new Message();
				msg.setSendUserName(rs.getString("sendUserName"));
				msg.setAddreUserName(rs.getString("addreUserName"));
				msg.setSendTime(sdf.format(sdf.parse(rs.getString("sendTime"))));
				msg.setTitle(rs.getString("title"));
				msg.setContent(rs.getString("content"));
				msg.setIsRead(rs.getString("isRead").trim());
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean changeMsgToRead(Message msgPara) {
		String upDate = "UPDATE  Message SET isRead='YES' WHERE  sendUserName='"
				+ msgPara.getSendUserName()
				+ "' AND sendTime='"
				+ msgPara.getSendTime() + "'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(upDate);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}

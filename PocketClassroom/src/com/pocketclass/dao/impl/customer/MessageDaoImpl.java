package com.pocketclass.dao.impl.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.pocketclass.dao.impl.DBConnectorImpl;
import com.pocketclass.domain.message.Message;

/**
 * վ��ͨ����Ϣ����
 * 
 * @author ���˾� wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-06 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class MessageDaoImpl {
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
	 * @param username
	 * @param idLocationCategory
	 * @param isRead
	 * @return �����û�idLocationCategory�������Ķ�����ΪisRead����Ϣ,������ʱ�併������
	 *         ��recordNum>0,����(currentPage,min(recordNum+currentPage,��¼����))��Ľ����
	 *         ��recordNum< 0 ,����(max(recordNum+currentPage ,0),currentPage)��Ľ����
	 */

	public ArrayList<Message> getPageData(String username,
			String idLocationCategory, String isRead, int currentPage,
			int recordNum) {
		ArrayList<Message> msgs = new ArrayList<Message>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfToLocal = new SimpleDateFormat(
				"yyyy��MM��dd�� HHʱmm��ss��");
		String sqlQuery;
		if (currentPage < 1)
			throw new IllegalArgumentException("�������Ϸ���" + currentPage);
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
				// �����Ȳ�������Ϣ����
				msg.setIsRead(rs.getString("isRead").trim());
				msgs.add(msg);
			}
		} catch (Exception e) {
			// �쳣��Sqlִ���쳣������ת���쳣������ʹ�鵽�����ݲ��������߲�ѯ������ص����ݣ��ʶ�ֱ�ӷ���null
			return null;
		}
		return msgs;

	}

	/**
	 * 
	 * @param username
	 * @param idLocationCategory
	 * @param isRead
	 * @return �����û�idLocationCategory������ȫ����Ϣ,������ʱ�併������
	 */
	public ArrayList<Message> getPageData(String username,
			String idLocationCategory, int currentPage, int recordNum) {
		ArrayList<Message> msgs = new ArrayList<Message>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlQuery;
		if (currentPage < 1)
			throw new IllegalArgumentException("�������Ϸ���" + currentPage);
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
			// �쳣��Sqlִ���쳣������ת���쳣������ʹ�鵽�����ݲ��������߲�ѯ������ص����ݣ��ʶ�ֱ�ӷ���null
			return null;
		}
		return msgs;

	}

	/**
	 * 
	 * @param msg
	 * @return �����ݿ��е���Ϣ���в���һ�����ݣ������˵ķ��������һ����¼,�����˵����������һ����¼,�ɹ�����true�����򷵻�false,
	 */
	public boolean sendMesage(Message msg) {
		if (msg == null) {
			return false;
		} else {
			// ����һ����Ϣ��¼
			String sqlInsert = "INSERT INTO Message(sendUserName,addreUserName,sendTime,title,content,isRead) VALUES('"
					+ msg.getSendUserName()
					+ "','"
					+ msg.getAddreUserName()
					+ "','"
					+ msg.getSendTime()
					+ "','"
					+ msg.getTitle()
					+ "','" + msg.getContent() + "','" + msg.getIsRead() + "')";
			// �����˵ķ��������һ����¼
			String sqlInsertFrom = "INSERT INTO MessageLocation(idLocationCategory,sendUserName,username,sendTime) VALUES('002','"
					+ msg.getSendUserName() + "','" + msg.getSendUserName() + "','" + msg.getSendTime() + "')";
			// �����˵����������һ����¼
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
	 *            ����������Ϣ����
	 * @param fromIdLocaCare
	 *            ����Ϣԭλ��
	 * @param toIdLocaCare
	 *            ����Ϣ���ƶ���Ŀ��λ��
	 * @return 
	 *         �����û�username��һ����Ϣ��fromIdLocaCare��������toIdLocaCare�У������ɹ�����true�����򷵻�false
	 */
	public boolean messageOperate(String username, Message msg,
			String fromIdLocaCare, String toIdLocaCare) {
		/******************** ���ݵ�undo���� ***********************************/
		String sqlUpdate = "UPDATE  MessageLocation SET idLocationCategory='"
				+ toIdLocaCare + "' WHERE sendUserName='"
				+ msg.getSendUserName() + "' AND username='" + username
				+ "' AND sendTime='" + msg.getSendTime()
				+ "' AND idLocationCategory='" + fromIdLocaCare + "'";
		try {
			DBConnectorImpl.getStatement().executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			if (this.exist(username, toIdLocaCare, msg)) {
				// �Լ��Լ�����Ϣ��ʱ�򣬵��û���������ͷ�����ͬʱ�ղ�������Ϣ��ʱ�򣬻������ͻ���������ּ���������ٷ�����
				// Ϊ�˼�˳����ִ��Ч�ʣ���ֻ���쳣���жϺͽ����������ÿ�ζ��ж�
				return this
						.deleteMsgByLocationId(username, msg, fromIdLocaCare);
			}
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * @ username:�û���
	 * 
	 * @param msg
	 *            :����������Ϣ����
	 * @param idLocationCategory
	 *            :�����Ϣ��Ŀ��λ��
	 * @return ������Ϣ��ӵ��û�username��idLocationCategory�ķ�����
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
	 * @return ɾ��һ����Ϣ�������ɹ�����true,���򷵻�false(����������)
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
	 * ɾ���û�username��idLocationCategory�����ȫ����Ϣ
	 * 
	 * @return ɾ��һ����Ϣ�������ɹ�����true,���򷵻�false(����������)
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
	 * ɾ���û�username��idLocationCategory������Ϣmsg
	 * 
	 * @return ɾ��һ����Ϣ�������ɹ�����true,���򷵻�false(����������)
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
	 * ���ص�ǰҳ����
	 */
	public ArrayList<Message> CurrentPageData(String keyword,
			String idLocationCategory) {
		return this.getPageData(keyword, idLocationCategory,
				this.getCurrentPageTag(), this.getPageRecordsNum());
	}

	/**
	 * ������һҳ����
	 */
	public ArrayList<Message> NextPageData(String keyword,
			String idLocationCategory) {
		return this.getPageData(keyword, idLocationCategory,
				this.getCurrentPageTag() + 1, this.getPageRecordsNum());
	}

	/**
	 * ����ǰһҳ����
	 */
	public ArrayList<Message> PriorPageData(String keyword,
			String idLocationCategory) {
		return this.getPageData(keyword, idLocationCategory,
				this.getCurrentPageTag() - 1, this.getPageRecordsNum());
	}

	/**
	 * ����һ������ҳ
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
	 * @return username�ķ���δ����Ϣ��Ŀ
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

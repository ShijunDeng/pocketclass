package com.pocketclass.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.pocketclass.dao.UserDao;
import com.pocketclass.domain.User;
import com.pocketclass.utils.EncryptionUtils;
import com.pocketclass.utils.StringConverter;

/**
 * 用户逻辑业务类
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-05 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class UserDaoImpl implements UserDao {

	// 增加用户
	@Override
	public void add(User user) {
		Date birthOdDate = user.getDateOfBirth();
		String sInsert;
		if (birthOdDate != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sInsert = "INSERT INTO Account(username,password,name,nickname,gender,dateOfBirth,"
					+ "phoneNum,email,QQ,college,academy,access,headPorAdd) VALUES('"
					+ user.getUsername().trim()
					+ "','"
					+ EncryptionUtils.md5(user.getPassword()).trim()
					+ "','"
					+ user.getName().trim()
					+ "','"
					+ user.getNickname().trim()
					+ "','"
					+ user.getGender().trim()
					+ "','"
					+ df.format(birthOdDate).trim()
					+ "','"
					+ user.getPhoneNum().trim()
					+ "','"
					+ user.getEmail().trim()
					+ "','"
					+ user.getQQ().trim()
					+ "','"
					+ user.getCollege().trim()
					+ "','"
					+ user.getAcademy().trim()
					+ "','"
					+ user.getAccess().trim()
					+ "','"
					+ user.getHeadPorAdd()
					+ "')";
		} else {
			sInsert = "INSERT INTO Account(username,password,name,nickname,gender,dateOfBirth,"
					+ "phoneNum,email,QQ,college,academy,access,headPorAdd) VALUES('"
					+ user.getUsername().trim()
					+ "','"
					+ EncryptionUtils.md5(user.getPassword()).trim()
					+ "','"
					+ user.getName().trim()
					+ "','"
					+ user.getNickname().trim()
					+ "','"
					+ user.getGender().trim()
					+ "',NULL,'"
					+ user.getPhoneNum().trim()
					+ "','"
					+ user.getEmail().trim()
					+ "','"
					+ user.getQQ().trim()
					+ "','"
					+ user.getCollege().trim()
					+ "','"
					+ user.getAcademy().trim()
					+ "','"
					+ user.getAccess().trim()
					+ "','"
					+ user.getHeadPorAdd().trim() + "')";
		}

		try {
			DBConnectorImpl.getStatement().execute(sInsert);
		} catch (SQLException e) {
			System.out.println(sInsert);
			throw new RuntimeException(e);
		}
	}

	// 更新除密码外的用户信息
	@Override
	public void update(User user) {
		Date birthOdDate = user.getDateOfBirth();
		String sUpdate="";
		if (birthOdDate != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sUpdate = "UPDATE Account set name='" + user.getName().trim()
					+ "',nickname='" + user.getNickname().trim() + "',gender='"
					+ user.getGender().trim() + "',dateOfBirth='"
					+ df.format(birthOdDate).trim() + "',phoneNum='"
					+ user.getPhoneNum().trim() + "',email='"
					+ user.getEmail().trim() + "',QQ='" + user.getQQ().trim()
					+ "',college='" + user.getCollege().trim() + "',academy='"
					+ user.getAcademy().trim() + "',access='"
					+ user.getAccess().trim() + "',headPorAdd='"
					+ user.getHeadPorAdd().trim() + "' WHERE username='"
					+ user.getUsername().trim() + "'";
		} else {
			sUpdate = "UPDATE Account set name='" + user.getName().trim()
					+ "',nickname='" + user.getNickname().trim() + "',gender='"
					+ user.getGender().trim() + "',dateOfBirth=NULL,phoneNum='"
					+ user.getPhoneNum().trim() + "',email='"
					+ user.getEmail().trim() + "',QQ='" + user.getQQ().trim()
					+ "',college='" + user.getCollege().trim() + "',academy='"
					+ user.getAcademy().trim() + "',access='"
					+ user.getAccess().trim() + "',headPorAdd='"
					+ user.getHeadPorAdd().trim() + "' WHERE username='"
					+ user.getUsername().trim() + "'";
		}

		try {
			DBConnectorImpl.getStatement().execute(sUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 查询指定用户名、密码的用户是否存在
	@Override
	public User query(String username, String password) {
		username = StringConverter.TransactSQLInjection(username);
//System.out.println("转过的用户名："+username);
//		password = StringConverter.TransactSQLInjection(password);
//System.out.println("转过的密码 ："+password);		
		String sqlQuery = "SELECT * FROM Account WHERE username='"
				+ username.trim() + "' AND password='"
				+ password.trim() + "'";
		User user = null;
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				user =  new User();
				user.setUsername(rs.getString("username") );
				user.setPassword(rs.getString("password") );
				user.setName(rs.getString("name") );
				user.setNickname(rs.getString("nickname") );
				user.setGender(rs.getString("gender") );
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				user.setPhoneNum(rs.getString("phoneNum") );
				user.setEmail(rs.getString("email") );
				user.setQQ(rs.getString("QQ") );
				user.setAcademy(rs.getString("academy") );
				user.setCollege(rs.getString("college") );
				user.setAccess(rs.getString("access") );
				user.setHeadPorAdd(rs.getString("headPorAdd") );
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	// 查询指定用户名用户
	public User query(String username) {
		String sqlQuery = "SELECT * FROM Account WHERE username='"
				+ username.trim() + "'";
		User user =null;
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			if (rs.next()) {
				user =  new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setGender(rs.getString("gender"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				user.setPhoneNum(rs.getString("phoneNum"));
				user.setEmail(rs.getString("email"));
				user.setQQ(rs.getString("QQ"));
				user.setAcademy(rs.getString("academy"));
				user.setCollege(rs.getString("college"));
				user.setAccess(rs.getString("access"));
				user.setHeadPorAdd(rs.getString("headPorAdd"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	@Override
	public boolean existUser(String username) {
		username = StringConverter.TransactSQLInjection(username);
//System.out.println("转过的用户名："+username);		
		String sqlQuery = "SELECT * FROM Account WHERE username='"
				+ username.trim() + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean existEmail(String email) {
		String sqlQuery = "SELECT * FROM Account WHERE email='" + email.trim()
				+ "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	// 邮箱或者用户名已经存在
	public boolean existData(String username, String email) {
		String sqlQuery = "SELECT * FROM Account WHERE username='" + username
				+ "' OR email='" + email + "'";
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * 查询用户username的properties值是否为value
	 * 
	 * @param properties
	 *            ：属性名称
	 * @param value
	 *            ：属性值
	 * @param username
	 *            ：用户名
	 * @return :是返回true，不是返回false
	 */
	public boolean rightInformation(Object properties, Object value,
			String username) {
		String sqlQuery;
		if (((String) properties).trim().equals("dateOfBirth")
				|| value instanceof Date) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sqlQuery = "SELECT * FROM Account WHERE username='" + username
					+ "' AND " + (String)properties + "='" + df.format((Date)value) + "'";
		} else {
			sqlQuery = "SELECT * FROM Account WHERE username='" + username
					+ "' AND " + (String)properties + "='" + (String) value + "'";
		}
		try {
			ResultSet rs = DBConnectorImpl.getStatement()
					.executeQuery(sqlQuery);
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	public void updatePassword(String username, String password)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET password='"
				+ EncryptionUtils.md5(password) + "' WHERE username='"
				+ username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateEmail(String username, String email) throws SQLException {
		String sqlUpdate = "UPDATE Account SET email='" + email
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateName(String username, String name) throws SQLException {
		String sqlUpdate = "UPDATE Account SET name='" + name
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateNickname(String username, String nickname)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET nickname='" + nickname
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateGender(String username, String gender)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET gender='" + gender
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateDateOfBirth(String username, String dateOfBirth)
			throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sqlUpdate = "UPDATE Account SET dateOfBirth='"
				+ df.format(dateOfBirth) + "' WHERE username='" + username
				+ "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updatePhoneNum(String username, String phoneNum)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET phoneNum='" + phoneNum
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateQQ(String username, String QQ) throws SQLException {
		String sqlUpdate = "UPDATE Account SET QQ='" + QQ
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateCollege(String username, String college)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET college='" + college
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateAcademy(String username, String academy)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET academy='" + academy
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateAccess(String username, String access)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET access='" + access
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	public void updateHeadPorAdd(String username, String headPorAdd)
			throws SQLException {
		String sqlUpdate = "UPDATE Account SET headPorAdd='" + headPorAdd
				+ "' WHERE username='" + username + "'";
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}

	/**
	 * 修改Account的属性
	 * 
	 * @param properties
	 *            ：属性名称
	 * @param value
	 *            ：属性值
	 * @param username
	 *            ：用户名
	 * @throws SQLException
	 *             ：修改不成功抛出异常
	 */
	public void updateProperties(Object properties, Object value,
			String username) throws SQLException {
		String sqlUpdate;
		if (((String) properties).trim().equals("dateOfBirth")
				|| value instanceof Date) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sqlUpdate = "UPDATE Account SET " + (String) properties + "='"
					+ df.format((Date) value) + "' WHERE username='" + username
					+ "'";
		} else {
			sqlUpdate = "UPDATE Account SET " + (String) properties + "='"
					+ (String) value + "' WHERE username='" + username + "'";
		}
		DBConnectorImpl.getStatement().execute(sqlUpdate);
	}
}

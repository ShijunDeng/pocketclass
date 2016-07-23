package com.pocketclass.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.pocketclass.dao.impl.MailPropertiesDao;
import com.pocketclass.web.beans.AuthenticatorBean;
/**
 * 
 * 发送邮件的工具类
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-10-18 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class EmailUtils {

	private AuthenticatorBean authenticator;
	private Session session;
	private Properties props;
	public EmailUtils() {
		props = new Properties();
		//从配置文件email.properties中读取
		props.setProperty("mail.smtp.host", MailPropertiesDao.getMailSmtpHost());
		props.setProperty("mail.smtp.auth", MailPropertiesDao.geEmailSmtpAuth());

		authenticator = new AuthenticatorBean();
		// 默认的地址
		// 获取系统邮箱地以及密码(用来发出验证信息的邮箱)
		String username = MailPropertiesDao.getEmailAddress();
		String password = MailPropertiesDao.getPassword();
		authenticator.setUsername(username);
		authenticator.setPassword(password);

		session = Session.getDefaultInstance(props, authenticator);
		session.setDebug(true);
	}

	
	public void setAuthenticator(AuthenticatorBean authenticator) {
		this.authenticator = authenticator;
	}


	public void setProps(Properties props) {
		this.props = props;
	}


	/**
	 * 
	 * @param authenticator：封装发信人的地址及密码的bean
	 * @param toAddress:收信人地址
	 * @param subject：主题
	 * @param content：内容
	 * @param props：协议等属性
	 * @throws MessagingException
	 */
	public void sendEmail(AuthenticatorBean authenticator, String toAddress, String subject,
			String content,Properties props) throws MessagingException {
		AuthenticatorBean tempAuthenticatorBean=this.authenticator;
		Properties tempProps=this.props;
		session =Session.getDefaultInstance(
				props, authenticator);
		session.setDebug(true);
		Address from = new InternetAddress(authenticator.getUsername());
		Address to = new InternetAddress(toAddress);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(content,"text/html;charset=utf-8");
		msg.setRecipient(javax.mail.Message.RecipientType.TO, to);
		/*
		 *  Transport transport = session.getTransport("smtp");
		 * transport.connect("smtp.163.com", userName, password); 
		 * transport.sendMessage(msg,msg.getAllRecipients()); 
		 * transport.close(); 
		 */
		Transport.send(msg);
		//还原,以免后来用户对调用默认发送方式时产生影响
		this.setAuthenticator(tempAuthenticatorBean);
		this.setProps(tempProps);
	}
	/**
	 * 默认发送地址信息为email.properties中的内容
	 * @param toAddress:收信人地址
	 * @param subject：主题
	 * @param content：内容
	 * @throws MessagingException
	 */
	public void sendEmail( String toAddress, String subject,
			String content) throws MessagingException {
		this.sendEmail(this.authenticator, toAddress, subject, content, this.props);
	}
}

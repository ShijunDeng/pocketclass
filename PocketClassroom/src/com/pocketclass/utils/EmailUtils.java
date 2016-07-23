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
 * �����ʼ��Ĺ�����
 * 
 * @author ���˾� wust_dengshijun@163.com
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
		//�������ļ�email.properties�ж�ȡ
		props.setProperty("mail.smtp.host", MailPropertiesDao.getMailSmtpHost());
		props.setProperty("mail.smtp.auth", MailPropertiesDao.geEmailSmtpAuth());

		authenticator = new AuthenticatorBean();
		// Ĭ�ϵĵ�ַ
		// ��ȡϵͳ������Լ�����(����������֤��Ϣ������)
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
	 * @param authenticator����װ�����˵ĵ�ַ�������bean
	 * @param toAddress:�����˵�ַ
	 * @param subject������
	 * @param content������
	 * @param props��Э�������
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
		//��ԭ,��������û��Ե���Ĭ�Ϸ��ͷ�ʽʱ����Ӱ��
		this.setAuthenticator(tempAuthenticatorBean);
		this.setProps(tempProps);
	}
	/**
	 * Ĭ�Ϸ��͵�ַ��ϢΪemail.properties�е�����
	 * @param toAddress:�����˵�ַ
	 * @param subject������
	 * @param content������
	 * @throws MessagingException
	 */
	public void sendEmail( String toAddress, String subject,
			String content) throws MessagingException {
		this.sendEmail(this.authenticator, toAddress, subject, content, this.props);
	}
}

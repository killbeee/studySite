package com.myProject.myPj.common.etc;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailService {

	final String ENCODING = "UTF-8";
	final String PORT = "465";
	final String SMTPHOST = "smtp.gmail.com";
	final String TO = "lsrong3531@naver.com";
	
	/**
	 * Session값 셋팅
	 * @param props
	 * @return
	 */
	public Session setting(Properties props, String user_name, String password) {
		Session session = null;
		try {
			
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTPHOST);
			props.put("mail.smtp.port", PORT);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", SMTPHOST);
			props.put("mail.smtp.starttls.required", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");

			props.put("mail.smtp.quit-wait", "false");
			props.put("mail.smtp.socketFactory.port", PORT);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user_name, password);
				}
			});
		} catch (Exception e) {		
			System.out.println("Session Setting 실패");
		}
		
		return session;
	}
	
	/**
	 * 메시지 세팅 후 메일 전송
	 * @param session
	 * @param title
	 * @param content
	 */
	public void goMail(Session session, String title, String content) {
		
		Message msg = new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress("보내는 메일", "관리자", ENCODING));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(title);
			msg.setContent(content, "text/html; charset=utf-8");
			
			Transport.send(msg);
			
			System.out.println("메일 보내기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메일 보내기 실패");
		}
	}
	
	public void sendMail(Map<String, Object> paramMap) throws UnsupportedEncodingException {
		Map<String, String> decodeMap = commonService.parseQueryString((String)paramMap.get("data"));
		String title = decodeMap.get("name")+"님이 보내신 메일:"+decodeMap.get("subject");
		String content = decodeMap.get("message")+"\r 회신이메일:"+decodeMap.get("email");
		String user_name = "beeeeee3531@gmail.com";
		String password = "yrl3613531";
		
		goMail(setting(new Properties(),user_name,password), title, content);
	}
}
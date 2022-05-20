package com.movie.mail.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MailSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MailSendAction_execute() 호출");
		
		// 한글 정보가 깨지지 않도록 함
		request.setCharacterEncoding("UTF-8");
		// 전달정보 저장
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String content = "<발신 : "+sender+">"+request.getParameter("content");
		System.out.println(sender);
		System.out.println(content);
			
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = response.getWriter();
		try {
			
			Properties properties = System.getProperties(); // 서버 정보를 Properties 객체에 저장
			properties.put("mail.smtp.starttls.enable", "true"); // Starttls Command를 사용할 수 있게 설정하는 부분. 
			properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 서버를 지정하는 부분.
			properties.put("mail.smtp.auth", "true"); // AUTH command를 사용하여 사용자 인증을 할 수 있게 설정하는 부분.
			properties.put("mail.smtp.port", "587"); // gmail 포트 - 서버 포트를 지정하는 부분
			Authenticator auth = new GoogleAuthentication(); // 인증 정보를 생성하는 부분
			Session s = Session.getDefaultInstance(properties, auth);  // 메일을 전송하는 역할을 하는 단위인 Session 객체를 생성하는 부분
			//Session s = Session.getdefultInstance(properties, auth); 
			Message message = new MimeMessage(s); // 생성한 Session 객체를 사용하여 전송할 Message 객체를 생성하는 부분
			Address sender_address = new InternetAddress(sender); // 메일을 송신할 주소를 생성하는 부분
			Address receiver_address = new InternetAddress(receiver); // 메일을 수신할 수신 주소를 생성하는 부분
            
			// 메일 전송에 필요한 값들을 설정하는 부분
			message.setHeader("content-type", "text/html;charset=UTF-8"); 
			message.setFrom(sender_address);
			message.addRecipient(Message.RecipientType.TO, receiver_address);
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=UTF-8");
			message.setSentDate(new java.util.Date());
            
			Transport.send(message); // 메시지를 메일로 전송하는 부분
			out.println("<h3>Mail sent Successfully :)</h3>");
		} catch (Exception e) {
			out.println("There is a problem with the service. . . X(");
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
		
		return null;
	}

}

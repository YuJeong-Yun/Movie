package com.movie.mail.action;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator {
	PasswordAuthentication passAuth;
	
	public GoogleAuthentication() {
		// 첫 번째 인자가 구글 아이디, 두 번째 인자가 비밀번호
		passAuth = new PasswordAuthentication("yyj1999@gmail.com", "okusjclgktddwmom");
	}
	
	// Authenticator 구현시 필수로 구현해야하는 메소드
	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
}

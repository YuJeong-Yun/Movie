package com.movie.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : LogoutAction_execute() 호출 ");
		
		// 로그아웃 -> 세션정보 초기화
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 메인 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.do");
		forward.setRedirect(true);
		
		return forward;
	}

}

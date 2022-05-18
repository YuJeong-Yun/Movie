package com.movie.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : LogoutAction_execute() 호출 ");
		
		// 로그아웃 -> 세션정보 초기화
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		
		// 페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그아웃이 완료되었습니다.'); location.href='./Main.do';</script>");
		out.flush();
		out.close();
			
		return null;
	}

}

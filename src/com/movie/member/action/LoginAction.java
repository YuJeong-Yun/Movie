package com.movie.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.action.db.MemberDAO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(" M : LoginAction_execute() 호출 ");
		
		// 전달 정보 저장 - id, pw
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
			
		// 전달받은 정보를 비교하기 위해서 DB로 이동
		// DAO 객체를 사용
		MemberDAO dao = new MemberDAO();

		// 로그인여부를 체크하는 동작
		int result = dao.loginCheck(id, pw);
		
		
		String alert="";
		ActionForward forward = new ActionForward();
		// result 결과 (-1, 0, 1)
		// 1=회원, 0=비밀번호 오류, -1=비회원
		if (result == -1) {
			alert="비회원 입니다.";
			request.setAttribute("alert", alert);
			
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);
			
			return forward;
		} else if (result == 0) {
			alert="비밀번호 오류 입니다.";
			request.setAttribute("alert", alert);
			
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);
			
			return forward;
		} else { // result == 1
			// 세션에 아이디 정보를 저장
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			forward.setPath("./Main.do");
			forward.setRedirect(true);
			
			return forward;
		}
	}

}

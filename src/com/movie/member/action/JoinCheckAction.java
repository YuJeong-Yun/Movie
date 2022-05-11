package com.movie.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.action.db.MemberDAO;

public class JoinCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : JoinCheckAction_execute() 실행 ");
		
		// 전달 정보 저장
		String id = request.getParameter("id");
		
		// DB 처리
		MemberDAO dao = new MemberDAO();
		// 아이디 존재하면 1, 없으면 -1
		int result = dao.idDbCheck(id);
		System.out.println(" 아이디 중복 결과 : "+result);
		
		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		
		// 페이지 이동 객체
		ActionForward forward = new ActionForward();
		forward.setPath("./member/idDbCheck.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

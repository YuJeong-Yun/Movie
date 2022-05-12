package com.movie.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.action.db.MemberDAO;
import com.movie.member.action.db.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberUpdateAction_execute() 실행");
		
		// 전달 정보 저장
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// DAO 객체 생성
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id); 
		
		// 정보 저장
		request.setAttribute("dto", dto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./member/myPage.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

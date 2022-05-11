package com.movie.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.member.action.db.MemberDAO;
import com.movie.member.action.db.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : JoinAction_execute() 실행 ");
		
		// 전달정보 저장
		MemberDTO dto = new MemberDTO();
		
		dto.setAddr(request.getParameter("addr"));
		dto.setEmail(request.getParameter("email"));
		dto.setGender(request.getParameter("gender"));
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPw(request.getParameter("pw"));
		dto.setTel(request.getParameter("tel"));
		
		// DB처리 => DAO 객체 생성
		MemberDAO dao = new MemberDAO();
		// 회원가입을 처리하는 동작 구현 => 메서드 생성
		dao.insertMember(dto);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Login.me");
		forward.setRedirect(true);
		
		return forward;
	}

}

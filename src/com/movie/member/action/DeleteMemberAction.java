package com.movie.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.db.MemberDAO;

public class DeleteMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : DeleteMemberAction_execute() 실행");
		
		// 전달 정보 저장
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		// DAO 객체 
		MemberDAO dao = new MemberDAO();
		int result = dao.deleteMember(id, pw); //-1:회원 아님, 0:비밀번호 오류, 1:탈퇴 완료
		
		// 삭제 결과에 따른 페이지 이동
		if(result == -1) {  // 아이디 없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원이 아닙니다.'); location.href='./Main.do';</script>");
			out.flush();
			out.close();
			
		}else if(result == 0) {  // 비밀번호 없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 오류입니다.'); location.href='./DeleteMember.me';</script>");
			out.flush();
			out.close();
			
		}else {  // result == 1  // 삭제완료
			session.invalidate();  // 삭제한 회원정보 초기화
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 탈퇴가 완료되었습니다.'); location.href='./Main.do';</script>");
			out.flush();
			out.close();
		
		}
		// 페이지 이동
		return null;
	}

}

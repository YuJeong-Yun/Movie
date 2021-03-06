package com.movie.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.db.MemberDAO;

public class MemberPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberPwAction_exeucte() 실행 ");
		
		// 전달 정보 저장
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String pwNow = request.getParameter("pwNow");
		String pwNew = request.getParameter("pwNew");
		
		// DAO 객체
		MemberDAO dao = new MemberDAO();
		int result = dao.updatePw(id, pwNow, pwNew);
		
		if(result==-1) { // 회원 없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원이 아닙니다.');");
			out.println("location.href='./MyPage.me';");
			out.println("</script>");
			out.flush();
			out.close();
			
		}else if(result==0) { // 비밀번호 오류
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류입니다.');");
			out.println("location.href='./MemberPw.me';");
			out.println("</script>");
			out.flush();
			out.close();
			
		}else { // 정보 수정 완료
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경이 완료되었습니다.');");
			out.println("location.href='./MyPage.me';");
			out.println("</script>");
			out.flush();
			out.close();
			
		}
		
		// 페이지 이동
		return null;
	}

}

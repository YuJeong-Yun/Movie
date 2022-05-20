package com.movie.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.db.MemberDAO;
import com.movie.member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateAction_execute() 실행");
		
		// 전달정보 저장
		HttpSession session = request.getSession();
		
		String addr = request.getParameter("postcode")+ "/"+request.getParameter("address")+"/"
				+request.getParameter("detailAddress");

		MemberDTO dto = new MemberDTO();
		dto.setAddr(addr);
		dto.setEmail(request.getParameter("email"));
		dto.setGender(request.getParameter("gender"));
		dto.setTel(request.getParameter("tel"));
		dto.setPw(request.getParameter("pw"));
		dto.setId((String) session.getAttribute("id"));
		
		
		// DAO 객체
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto); // -1:회원 없음, 0:비밀번호 오류, 1:정보 수정

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result==-1) { // 아이디 없음
			out.println("<script>");
			out.println("alert('회원이 아닙니다.');");
			out.println("location.href='./MyPage.me';");
			out.println("</script>");
			
			out.flush();
			out.close();
			
		}else if(result==0) { // 비밀번호 오류
			out.println("<script>");
			out.println("alert('비밀번호 오류입니다.');");
			out.println("location.href='./MyPage.me';");
			out.println("</script>");
			out.flush();
			out.close();
			
		}else { // 정보 수정 완료
			out.println("<script>");
			out.println("alert('수정이 완료되었습니다.');");
			out.println("location.href='./MyPage.me';");
			out.println("</script>");
			
			out.flush();
			out.close();
			
		}
		
		return null;
	}
}

		
		
	
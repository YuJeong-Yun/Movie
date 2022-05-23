package com.movie.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.member.db.MemberDAO;
import com.movie.member.db.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : JoinAction_execute() 실행 ");
		
		// 전달정보 저장
		MemberDTO dto = new MemberDTO();
		
		String addr = request.getParameter("postcode")+ "/"+request.getParameter("address")+"/"
						+request.getParameter("detailAddress")+"/" + request.getParameter("extraAddress");
		
		dto.setAddr(addr);
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
		
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('회원가입이 완료되었습니다.');");
		out.println("location.href='./Login.me';");
		out.println("</script>");
		
		out.flush();
		out.close();
		
		return null;
		
		}

}

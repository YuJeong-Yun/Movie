package com.movie.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.db.MemberDAO;
import com.movie.member.db.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateAction_execute() 실행");
		
		// 파일 업로드 - 가상의 업로드 폴더 설정 
		String path = request.getRealPath("/profile");
		System.out.println(" M path : " +path);
		//		- 업로드 파일의 크기 설정(제한) - 10MB
		int maxSize = 10 * 1024 * 1024; 
		//		- MultipartRequest 객체 생성(업로드)
		MultipartRequest  multi 
				= new MultipartRequest(
						request,	// request 내장객체 (파라메터, 파일정보)
						path,		// 업로드 위치(가상경로)
						maxSize,		// 업로드 파일의 크기 
						"UTF-8",		// 업로드시 인코딩처리
						new DefaultFileRenamePolicy()		// 중복된 파일이름 업로드시 처리 객체 -> 중복된 이름 넣으면 이름 뒤에 (1) 이런식으로 붙임
						);
		
		System.out.println(" M : 파일 업로드 완료! ");
		
		// 전달정보 저장
		HttpSession session = request.getSession();
		String addr = multi.getParameter("postcode")+ "/"+multi.getParameter("address")+"/"
				+multi.getParameter("detailAddress") +"/"+ multi.getParameter("extraAddress");
		System.out.println(addr);
		System.out.println("pro : "+multi.getParameter("profileDel"));
		
		int profileDel = 0;
		// profileDel != null이면 profileDel = 1 => 프로필 삭제
		if(multi.getParameter("profileDel") == null ) {
		}else {
			profileDel = 1;
		}
		System.out.println("pro "+profileDel);
		
		MemberDTO dto = new MemberDTO();
		dto.setAddr(addr);
		dto.setEmail(multi.getParameter("email"));
		dto.setGender(multi.getParameter("gender"));
		dto.setTel(multi.getParameter("tel"));
		dto.setPw(multi.getParameter("pw"));
		dto.setId((String) session.getAttribute("id"));
		dto.setProfile(multi.getFilesystemName("profile"));
		
		// DAO 객체
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto, profileDel); // -1:회원 없음, 0:비밀번호 오류, 1:정보 수정

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

		
		
	
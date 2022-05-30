package com.movie.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.review.db.MovieBoardDAO;
import com.movie.review.db.MovieBoardDTO;

public class MovieReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달 정보 저장
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		MovieBoardDTO dto = new MovieBoardDTO();
		dto.setContent(content);
		dto.setId(id);
		dto.setIp(ip);
		dto.setSubject(subject);
		
//		System.out.println(" M : "+dto);
			
		// insertBoard() 글쓰기 동작 실행
		MovieBoardDAO dao = new MovieBoardDAO();
		int num = dao.insertBoard(dto);
		
		// 페이지 이동 - 현재 글 번호 함께 전달
		ActionForward forward = new ActionForward();
		forward.setPath("");
		forward.setRedirect(true);
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('글쓰기가 완료되었습니다.');");
		out.print("location.href='./MovieReviewContent.bo?num="+num+"&pageNum=1';");
		out.print("</script>");
		out.flush();
		
		// 응답처리하던 연결통로를 제거 (자원해제)
		out.close();
		
		return null;
	}

}
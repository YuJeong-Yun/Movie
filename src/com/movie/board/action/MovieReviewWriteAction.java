package com.movie.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.board.db.MovieBoardDAO;
import com.movie.board.db.MovieBoardDTO;

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
		
		System.out.println(" M : "+dto);
			
		// insertBoard() 글쓰기 동작 실행
		MovieBoardDAO dao = new MovieBoardDAO();
		int num = dao.insertBoard(dto);
		int pageNum = 1;
		
		// 페이지 이동 - 현재 글 번호 함께 전달
		ActionForward forward = new ActionForward();
		forward.setPath("./MovieReviewContent.bo?num="+num+"&pageNum="+pageNum);
		forward.setRedirect(true);
		
		return forward;
	}

}
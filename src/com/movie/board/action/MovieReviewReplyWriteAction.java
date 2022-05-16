package com.movie.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.board.db.MovieBoardDAO;
import com.movie.board.db.MovieBoardDTO;
import com.movie.board.db.MovieBoardReplyDTO;
import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

public class MovieReviewReplyWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println( " M : MovieReviewReplyWriteAction_execute() 호출");
		
		// 전달 정보 저장
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("reply");
		String ip = request.getRemoteAddr();
		String pageNum = request.getParameter("pageNum");
		
		// dto에 정보 저장
		MovieBoardReplyDTO dto = new MovieBoardReplyDTO();
		dto.setId(id);
		dto.setRe_ref(num);
		dto.setContent(content);
		dto.setIp(ip);
		
		// DAO 객체 생성
		MovieBoardDAO dao = new MovieBoardDAO();
		dao.reInsertBoard(dto);
		
		
		// 페이지 이동 객체
		ActionForward forward = new ActionForward();
		forward.setPath("./MovieReviewContent.bo?num="+num+"&pageNum="+pageNum);
		forward.setRedirect(true);
		return forward;
	}

}

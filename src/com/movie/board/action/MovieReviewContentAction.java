package com.movie.board.action;

import java.rmi.Remote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.board.action.db.MovieBoardDAO;
import com.movie.board.action.db.MovieBoardDTO;

public class MovieReviewContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M :MovieReviewContentAction_execute() 호출 ");
		
		// 전달정보 저장
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num")); // 
		String id = (String) session.getAttribute("id");
		System.out.println("id : "+id);
		// BoardDAO 객체 생성
		MovieBoardDAO dao = new MovieBoardDAO();
		
		// 조회수 증가 동작 실행
		int result = dao.updateReadCount(num, id);
		System.out.println(" M : 조회수 "+result+" 증가 완료"); // 조회수 증가 체크
		
		// 글번호에 해당하는 글 전체의 정보를 가져오기
		MovieBoardDTO dto = dao.getBoard(num);
		System.out.println(" M :글정보 1개 조회 완료");
		
		// request 영역에 글정보를 저장
		request.setAttribute("dto", dto);
		
		// 페이지 이동 객체 
		ActionForward forward = new ActionForward();
		forward.setPath("./board/reviewContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

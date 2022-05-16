package com.movie.board.action;

import java.rmi.Remote;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.board.action.db.MovieBoardDAO;
import com.movie.board.action.db.MovieBoardDTO;
import com.movie.board.action.db.MovieBoardReplyDTO;

public class MovieReviewContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M :MovieReviewContentAction_execute() 호출 ");
		
		// 전달정보 저장
		HttpSession session = request.getSession();
		
		int num = Integer.parseInt(request.getParameter("num")); // 
		String pageNum = request.getParameter("pageNum");
		String id = (String) session.getAttribute("id");
		
		
		// BoardDAO 객체 생성
		MovieBoardDAO dao = new MovieBoardDAO();
		
		// 조회수 증가 동작 실행
		int result = dao.updateReadCount(num, id);
		System.out.println(" M : 조회수 "+result+" 증가 완료"); // 조회수 증가 체크
		// 이전, 다음 글 번호 받아오기
		int prevNum = dao.getBoardSideNum(0, num);
		int nextNum = dao.getBoardSideNum(1, num);
		System.out.println("pN : "+prevNum+", nN : "+nextNum);
		
		// 글번호에 해당하는 글 전체의 정보를 가져오기
		MovieBoardDTO dto = dao.getBoard(num);
		System.out.println(" M :글정보 1개 조회 완료");
		List<MovieBoardReplyDTO> boardReplyList = dao.getBoardReplyList(num);
		
		// request 영역에 글정보를 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("prevNum", prevNum);
		request.setAttribute("nextNum", nextNum);
		request.setAttribute("boardReplyList", boardReplyList);
		
		// 페이지 이동 객체 
		ActionForward forward = new ActionForward();
		forward.setPath("./board/reviewContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

package com.movie.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.review.db.MovieBoardDAO;
import com.movie.review.db.MovieBoardDTO;

public class MovieReviewUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MovieReviewUpdateAction_execute() 호출 ");
		
		// 전달된 정보를 저장(num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// DAO 객체
		MovieBoardDAO dao = new MovieBoardDAO();
		// 글 정보 가져오는 메서드 (getBoard())
		MovieBoardDTO dto = dao.getBoard(num);
		// 전달된 글 정보를 저장 - request 영역
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);

		// 페이지 이동 (./center/updateForm.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/reviewUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

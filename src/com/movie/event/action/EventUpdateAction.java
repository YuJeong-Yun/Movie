package com.movie.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.event.db.EventBoardDAO;
import com.movie.event.db.EventBoardDTO;

public class EventUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달된 정보를 저장(num, pageNum, category)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// DAO 객체
		EventBoardDAO dao = new EventBoardDAO();
		// 글 정보 가져오는 메서드 (getBoard())
		EventBoardDTO dto = dao.getBoard(num);
		// 전달된 글 정보를 저장 - request 영역
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);

		// 페이지 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./event/eventUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

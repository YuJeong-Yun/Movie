package com.movie.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.event.db.EventBoardDAO;
import com.movie.event.db.EventBoardDTO;

public class EventContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : EventContentAction_execute() 호출 ");

		// 전달정보 저장
		int num = Integer.parseInt(request.getParameter("num")); //
		String pageNum = request.getParameter("pageNum");
		String category = request.getParameter("category");

		// BoardDAO 객체 생성
		EventBoardDAO dao = new EventBoardDAO();

		// 이전, 다음 글 번호 받아오기
		int prevNum = dao.getBoardSideNum(0, num, category);
		int nextNum = dao.getBoardSideNum(1, num, category);
		// System.out.println("pN : "+prevNum+", nN : "+nextNum);

		// 글번호에 해당하는 글 전체의 정보를 가져오기
		EventBoardDTO dto = dao.getBoard(num);
		System.out.println(" M :글정보 1개 조회 완료");

		// request 영역에 글정보를 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("prevNum", prevNum);
		request.setAttribute("nextNum", nextNum);

		// 페이지 이동 객체
		ActionForward forward = new ActionForward();
		forward.setPath("./event/eventContent.jsp");
		forward.setRedirect(false);

		return forward;
	}

}

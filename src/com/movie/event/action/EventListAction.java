package com.movie.event.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.event.db.EventBoardDAO;
import com.movie.event.db.EventBoardDTO;


public class EventListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : EventListAction_execute() 호출");
		
		// 전달정보 저장
		String category = request.getParameter("category");
		// 카테고리 정보 없으면 special 카테고리 저장
		if(category == null) {
			category = "special";
		}
		
		// DAO 객체 생성
		EventBoardDAO dao = new EventBoardDAO();
		// 글 개수 확인 동작 실행
		int result = dao.getBoardCount(category);
		System.out.println(" M : 글 개수 " + result + "개");
		
		
		///////////////////////////////////////////////////////////////////////////////////
		// 페이징 처리 1
		// 한 페이지에 보여줄 글의 개수
		int pageSize = 9;

		// 현 페이지 정보 계산하기
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // pageNum정보가 없을경우 항상 1페이지
		}

		// 페이지 시작행 계산 1, 10, 19, 28, 37,......
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		// 페이지 끝행 계산 9, 18, 27, 36,....
		int endRow = currentPage * pageSize;

		///////////////////////////////////////////////////////////////////////////////////

		// 글이 있을때, 글정보 전부를 가져오기
		// getBoardList();
		List<EventBoardDTO> boardList = null;
		if (result > 0) {
			boardList = dao.getBoardList(startRow, pageSize, category);
		}
		
		///////////////////////////////////////////////////////////////////////////////////
		// 페이징 2
		// 전체 필요한 페이지수 계산
		int pageCount = result / pageSize + (result % pageSize == 0 ? 0 : 1);

		// 한 화면에 보여줄 페이지 블럭의 수
		int pageBlock = 5;

		// 페이지 블럭의 시작번호 1~10=>1 11~20=>11 21~30=>21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 페이지 블럭의 끝번호
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		///////////////////////////////////////////////////////////////////////////////////


		// request 영역에 글정보(list) 저장
		request.setAttribute("boardList", boardList);
		System.out.println("boardList : "+boardList);

		// request 영역에 페이징처리 정보를 저장
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		// 페이지 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./event/eventList.jsp");
		forward.setRedirect(false);

		return forward;
	}


}

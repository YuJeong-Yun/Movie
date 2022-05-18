package com.movie.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.notice.db.NoticeBoardDAO;
import com.movie.notice.db.NoticeBoardDTO;


public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : NoticeListAction_execute() 호출");
		
		// DAO 객체 생성
		NoticeBoardDAO dao = new NoticeBoardDAO();
		// 글 개수 확인 동작 실행
		int result = dao.getBoardCount();
		System.out.println(" M : 글 개수 " + result + "개");
		
		
		///////////////////////////////////////////////////////////////////////////////////
		// 페이징 처리 1
		// 한 페이지에 보여줄 글의 개수
		int pageSize = 10;

		// 현 페이지 정보 계산하기
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // pageNum정보가 없을경우 항상 1페이지
		}

		// 페이지 시작행 계산 1, 11, 21, 31, 41,......
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		// 페이지 끝행 계산 10,20,30,40,....
		int endRow = currentPage * pageSize;

		///////////////////////////////////////////////////////////////////////////////////

		// 글이 있을때, 글정보 전부를 가져오기
		// getBoardList();
		List<NoticeBoardDTO> boardList = null;
		if (result > 0) {
			boardList = dao.getBoardList(startRow, pageSize);
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
		forward.setPath("./notice/noticeList.jsp");
		forward.setRedirect(false);

		return forward;
	}

}

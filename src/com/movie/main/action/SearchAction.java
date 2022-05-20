package com.movie.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.main.db.MainDAO;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : SearchAction_execute() 호출 ");
		
		
		// 전달된 정보 저장 (search)
		String search = request.getParameter("search");
		System.out.println(" M 검색어 : " + search);
		
		// DAO 객체 생성
		MainDAO dao = new MainDAO();
		// 검색어가 포함된 글이 있는지 체크(개수)
		List<Integer> boardCnt = dao.getBoardCount(search);
		
		
		// 인덱스 0: movie_board, 1 : movie_event_board, 2 : movie_notice_board
		System.out.println(" M : 검색된 글 개수  " + boardCnt);
		
		
		///////////////////////////////////////////////////////////////////////////////////


		
		List<List> searchList = null;
		// 검색어가 포함된 제목 하나라도 있으면 => 해당 내용 디비에서 저장해서 가져오기
		if(boardCnt.stream().filter(cnt -> cnt != 0).count() > 0) {
			searchList = dao.getBoardList(boardCnt, search);
		}
		
		
		// DB에서 전달받은 데이터를 request 영역에 저장
		request.setAttribute("boardCnt", boardCnt);
		request.setAttribute("searchList", searchList);
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./main/search.jsp");
		forward.setRedirect(false);
		return forward;
	}

}

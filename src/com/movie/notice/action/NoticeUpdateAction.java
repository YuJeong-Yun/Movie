package com.movie.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.notice.db.NoticeBoardDAO;
import com.movie.notice.db.NoticeBoardDTO;


public class NoticeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : NoticeUpdateAction() 호출 ");
		
		// 전달된 정보를 저장(num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// DAO 객체
		NoticeBoardDAO dao = new NoticeBoardDAO();
		// 글 정보 가져오는 메서드 (getBoard())
		NoticeBoardDTO dto = dao.getBoard(num);
		// 전달된 글 정보를 저장 - request 영역
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);

		// 페이지 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./notice/noticeUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

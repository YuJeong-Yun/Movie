package com.movie.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.notice.db.NoticeBoardDAO;
import com.movie.notice.db.NoticeBoardDTO;


public class NoticeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : NoticeContentAction_execute() 실행");
		
		// 전달정보 저장
		HttpSession session = request.getSession();
		
		int num = Integer.parseInt(request.getParameter("num")); // 
		String pageNum = request.getParameter("pageNum");
		String id = (String) session.getAttribute("id");
		
		
		// BoardDAO 객체 생성
		NoticeBoardDAO dao = new NoticeBoardDAO();
		
		// 관리자 계정 제외 조회수 증가 동작 실행
		int result = 0;
		System.out.println("id : "+id);
		if(id==null || !id.equals("admin")) {
			result = dao.updateReadCount(num);
		}
		System.out.println(" M : 조회수 "+result+" 증가 완료"); // 조회수 증가 체크
		// 이전, 다음 글 번호 받아오기
		int prevNum = dao.getBoardSideNum(0, num);
		int nextNum = dao.getBoardSideNum(1, num);
		System.out.println("pN : "+prevNum+", nN : "+nextNum);
		
		// 글번호에 해당하는 글 전체의 정보를 가져오기
		NoticeBoardDTO dto = dao.getBoard(num);
		System.out.println(" M :글정보 1개 조회 완료");
		
		// request 영역에 글정보를 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("prevNum", prevNum);
		request.setAttribute("nextNum", nextNum);
		
		// 페이지 이동 객체 
		ActionForward forward = new ActionForward();
		forward.setPath("./notice/noticeContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

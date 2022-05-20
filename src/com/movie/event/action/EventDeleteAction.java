package com.movie.event.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.event.db.EventBoardDAO;
import com.movie.notice.db.NoticeBoardDAO;

public class EventDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : EventDeleteAction_execute() 호출");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 관리자 계정 아니면 메인으로 이동
		if(id == null || !id.equals("admin")) {
			response.sendRedirect("./Main.do");
		}
		
		// 전달정보 저장
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String category = request.getParameter("category");

		
		// DAO 객체 -> 삭제 메서드
		EventBoardDAO dao = new EventBoardDAO();
		int result = dao.deleteBoard(num);
		
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		
		if(result==-1) {
			out.print("<script>");
			out.print("alert('글이 존재하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
			
			out.flush();
			out.close();
			
		}else { // result == 1
			out.print("<script>");
			out.print("alert('글 삭제가 완료되었습니다.');");
			out.print("location.href='./Event.ev?pageNum="+pageNum+"&category="+category+"';");
			out.print("</script>");
			
			out.flush();
			out.close();
		}
		return null;
	}

}

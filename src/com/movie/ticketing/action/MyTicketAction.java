package com.movie.ticketing.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.ticketing.db.TicketingDAO;



public class MyTicketAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MyTicketAction_execute 호출 ");
		
		// 전달정보 저장
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// DAO 객체 - 해당 아이디 예매내역 조회
		TicketingDAO dao = new TicketingDAO();
		// 정보 전달
		request.setAttribute("payList", dao.getPayInfo(id));
		
		
		// 페이지 이동 객체
		ActionForward forward = new ActionForward();
		
		forward.setPath("./member/myTicket.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}

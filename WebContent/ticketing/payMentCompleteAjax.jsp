<%@page import="com.movie.ticketing.db.TicketingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	// 전달정보 저장
	String payment_num = request.getParameter("imp_uid");
	String order_num = request.getParameter("order_num");
	String member_id = (String) session.getAttribute("id");
	String movie_title = request.getParameter("movieTitle");
	String movie_theater = request.getParameter("movieTheater");
	String movie_date = request.getParameter("movieDate");
	String movie_time = request.getParameter("movieTime");
	String movie_seat = request.getParameter("movieSeat");
	int price = Integer.parseInt(request.getParameter("totalPrice"));
	
	System.out.println(payment_num);
	System.out.println(order_num);
	System.out.println(request.getParameter("movieSeat"));
	
	// DAO 객체 생성
	TicketingDAO dao = new TicketingDAO();
	
	
%>

 
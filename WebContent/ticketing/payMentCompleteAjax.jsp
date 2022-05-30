<%@page import="java.time.LocalDate"%>
<%@page import="com.movie.ticketing.db.TicketingDTO"%>
<%@page import="com.movie.ticketing.db.TicketingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	// 전달정보 저장
	String payment_num = request.getParameter("imp_uid");
	String order_num = request.getParameter("merchant_uid");
	String member_id = (String) session.getAttribute("id");
	String movie_title = request.getParameter("movieTitle");
	String movie_theater = request.getParameter("movieTheater");
	String movie_dateTime = request.getParameter("movieDateTime");
	String movie_seat = request.getParameter("movieSeat");
	int price = Integer.parseInt(request.getParameter("totalPrice"));


	// movie_seat 마지막 구분자(,) 제거
	movie_seat = movie_seat.substring(0, movie_seat.length()-1);
	
	// DTO 객체 생성 -> 정보 저장	
	TicketingDTO dto = new TicketingDTO();
	dto.setMember_id(member_id);
	dto.setMovie_dateTime(movie_dateTime);
	dto.setMovie_seat(movie_seat);
	dto.setMovie_theater(movie_theater);
	dto.setMovie_title(movie_title);
	dto.setOrder_num(order_num);
	dto.setPayment_num(payment_num);
	dto.setPrice(price);
	
	// DAO 객체 생성
	TicketingDAO dao = new TicketingDAO();
	// 결제 정보 DB에 저장
	dao.insertPayInfo(dto); 
%>

 
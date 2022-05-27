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
	String movie_date = request.getParameter("movieDate");
	String movie_time = request.getParameter("movieTime");
	String movie_seat = request.getParameter("movieSeat");
	int price = Integer.parseInt(request.getParameter("totalPrice"));


	// 영화 상영 날짜 계산
	String movie_dateTime ="";
	
	LocalDate today = LocalDate.now();
	int date = today.getDayOfMonth(); // 오늘 일 계산
	
	int movieDateOnly = Integer.parseInt(movie_date.substring(1)); // 날짜에서 일만 추출
	
	
	if(movieDateOnly >= date) { // 상영날이 오늘 날짜보다 크거나 같으면 이번 달 날짜임
		movie_dateTime = today.getYear() +"년 "+today.getMonthValue() +"월 "+movieDateOnly + "일 " +movie_time;
	}else { // 상영날이 오늘 날짜보다 작으면 다음 달 날짜임
		if(today.getMonthValue() == 12) { // 이번달이 12월이면 연도+1
			movie_dateTime =  (today.getYear()+1) +"년 "+1 +"월 "+movieDateOnly + "일 " +movie_time;
		}
		movie_dateTime =  today.getYear() +"년 "+(today.getMonthValue()+1) +"월 "+movieDateOnly + "일 " +movie_time;
	} //if
	
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
	
	System.out.println("Ajax : 정보 저장 "+dto);
	
	// DAO 객체 생성
	TicketingDAO dao = new TicketingDAO();
	dao.insertPayInfo(dto); 
%>

 
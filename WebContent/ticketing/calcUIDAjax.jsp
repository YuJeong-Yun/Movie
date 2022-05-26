<%@page import="com.movie.ticketing.db.TicketingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	// 주문번호 계산하는 파일
 	// DAO 객체 생성
 	TicketingDAO dao = new TicketingDAO();
 	// 주문번호 생성
 	String order_num = dao.makeOrderNum() ;
 	System.out.println("Ajax : 주문번호 "+order_num);
 	
 %>
<%= order_num %>
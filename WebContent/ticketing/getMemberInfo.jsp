<%@page import="com.movie.member.db.MemberDTO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.movie.ticketing.db.TicketingDAO"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//     String id = (String) session.getAttribute("id");
	
	// DAO 객체 생성
	TicketingDAO dao = new TicketingDAO();
	MemberDTO dto = dao.getMemberInfo("admin");
	
	// 주소 - 우편번호랑 나누기
	String[] addrAll = dto.getAddr().split("/");
	String postcode = addrAll[0];
	String addr = addrAll[1] +" "+ addrAll[2];
	
	JSONObject obj = new JSONObject();
	
	obj.put("email", dto.getEmail());
	obj.put("name", dto.getName());
	obj.put("tel", dto.getTel());
	obj.put("addr", addr);
	obj.put("postcode", postcode);
	
	System.out.println("Ajax 회원 정보 조회 :"+obj);

%>

<%= obj%>
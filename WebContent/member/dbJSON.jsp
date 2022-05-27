<%@page import="com.movie.member.db.MemberDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// dbJSON.jsp
	
	// DAO 객체 생성
	MemberDAO dao = new MemberDAO();
	
	JSONArray memberList = dao.dbCheck();
	
%>

<%=memberList %>
    
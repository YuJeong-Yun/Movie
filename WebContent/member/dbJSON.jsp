<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// dbJSON.jsp
	// DB정보를 가져와서 -> JSON형태로 변경
	
	
	// 1. 드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver");

	// 2. 디비 연결
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jspdb", "root", "1234");
	
	// 3. sql 생성 & pstmt 객체
	String sql = "select id, tel, email from movie_member";
	PreparedStatement pstmt = con.prepareStatement(sql);
			
	// 4. sql 실행
	ResultSet rs = pstmt.executeQuery();
	
	// 5. 데이터 처리 => DB정보를 JSON 형태로 변경
	JSONArray memberList = new JSONArray();
	while(rs.next()) {
		JSONObject obj = new JSONObject();
		
		obj.put("id", rs.getString("id"));
		obj.put("tel", rs.getString("tel"));
		obj.put("email", rs.getString("email"));
		
		memberList.add(obj);
	}
	System.out.println(memberList);
%>

<%=memberList %>
    
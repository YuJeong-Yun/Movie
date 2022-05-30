<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.movie.review.db.MovieBoardReplyDTO"%>
<%@page import="com.movie.review.db.MovieBoardDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = (String) session.getAttribute("id");

	// 전달 정보 dto에 저장
	MovieBoardReplyDTO dto = new MovieBoardReplyDTO();
	dto.setId(id);
	dto.setBno(Integer.parseInt(request.getParameter("bno")));
	dto.setContent(request.getParameter("content"));
	dto.setIp(request.getRemoteAddr());
	
	// DAO 객체 생성 - 댓글 쓰기 메서드
	MovieBoardDAO dao = new MovieBoardDAO();
	// 댓글 쓰기
	int cno = dao.insertReply(dto); 
	// 해당 댓글 정보 가져오기
	dto = dao.getReply(cno);
	
	// 해당하는 글의 댓글 가져와서 JSONObject로 생성
	JSONObject reply = new JSONObject();
	
    // 날짜 형식 지정
	SimpleDateFormat sDate = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    
	reply.put("cno", dto.getCno()+"");
	reply.put("id", dto.getId());
	reply.put("name", dto.getName());
	reply.put("content", dto.getContent());
	reply.put("bno", dto.getBno()+"");
	reply.put("date", sDate.format(dto.getDate()));
	reply.put("ip", dto.getIp());

%>

<%=reply %>


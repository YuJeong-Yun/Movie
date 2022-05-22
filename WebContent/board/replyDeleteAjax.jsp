<%@page import="com.movie.review.db.MovieBoardDAO"%>
<%@page import="com.movie.review.db.MovieBoardReplyDTO"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    	// 전달 정보 저장
		String id = (String) session.getAttribute("id");
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		// DTO에 저장
		MovieBoardReplyDTO dto = new MovieBoardReplyDTO();
		dto.setId(id);
		dto.setCno(cno);
		
		// DAO 객체 생성
		MovieBoardDAO dao = new MovieBoardDAO();
		int result = dao.deleteReply(dto);
%>

<%=result %>
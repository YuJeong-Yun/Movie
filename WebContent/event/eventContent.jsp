<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 브라우저 스타일 초기화 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" />
  <!-- 구글 폰트 -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
  <!-- 구글 아이콘 -->
  <link
    href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
    rel="stylesheet">

  <link rel="stylesheet" href="./css/event/eventContent.css">
</head>
<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>

    

  <!--이벤트 본문-->
  <section class="event-content">
    <div class="inner">
      <!-- 이전 글 / 다음 글 / 목록 이동 가능 버튼 -->
      <div class="btn-group title">
        <!-- num 1인 글은 다음글 버튼 없음 
        	가장 최신 글은 이전글 버튼 없음 -->
        <c:if test="${prevNum ne 0 }"><a href="./EventContent.ev?num=${prevNum }&pageNum=${pageNum }&category=${dto.category}" class="btn">△ 이전글</a></c:if>
        <c:if test="${nextNum ne 0 }"><a href="./EventContent.ev?num=${nextNum }&pageNum=${pageNum }&category=${dto.category}" class="btn">▽ 다음글</a></c:if>
        <a href="./Event.ev?pageNum=${pageNum }&category=${dto.category }" class="btn">목록</a>
      </div>
      
      <!-- 이벤트 글 타이틀 -->
      <div class="title-wrapper">
        <div class="category">
          <c:if test="${dto.category eq 'special' }">SPECIAL</c:if>
          <c:if test="${dto.category eq 'movie' }">영화/예매</c:if>
          <c:if test="${dto.category eq 'membership' }">멤버십/CLUB</c:if>
          <c:if test="${dto.category eq 'cgv' }">CGV 극장별</c:if>
          <c:if test="${dto.category eq 'discount' }">제휴/할인</c:if>
        </div>
        <div class="title">${dto.subject }</div>
        <div class="date">
          <!-- 기간에 값 있으면 표시 -->
          <c:if test="${dto.eventDateStart ne '' or dto.eventDateEnd ne '' }">
          기간: ${dto.eventDateStart } ~ ${dto.eventDateEnd }
          </c:if>
        </div>
      </div>
      <!-- 이벤트 글 내용 -->
      <div class="event-img">
        <img src="./upload/${dto.image }" />
      </div>
      
      <!-- 관리자 계정일경우 수정, 삭제 버튼 표시 -->
      <c:if test="${id eq 'admin' }">
        <div class="btn-group content">
          <a href="./EventUpdate.ev?num=${dto.num }&pageNum=${pageNum }&category=${dto.category }" class="btn">수정하기</a>
          <a href="./EventDelete.ev?num=${dto.num }&pageNum=${pageNum }&category=${dto.category }" class="btn">삭제하기</a>
        </div>
      </c:if>
      
      <!-- 최상단 이동 버튼 -->
      <a href="#" class="btn btn--top">▲ TOP</a>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
 
</body>
</html>
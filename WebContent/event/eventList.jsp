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
  
    <link rel="stylesheet" href="./css/event/eventList.css">
</head>
<body>
    <!-- HEADER -->
 	<jsp:include page="../inc/header.jsp"></jsp:include>



  <!-- 이벤트 목록 출력 -->
  <section class="board">
    <div class="inner">
      <div class="board-title">EVENT</div> <!--이벤트 타이틀 -->
      <ul class="category"> <!--이벤트 카테고리-->
        <li><a href="./Event.ev?category=special">SPECIAL</a></li>
        <li><a href="./Event.ev?category=movie">영화/예매</a></li>
        <li><a href="./Event.ev?category=membership">멤버십/CLUB</a></li>
        <li><a href="./Event.ev?category=cgv">CGV 극장별</a></li>
        <li><a href="./Event.ev?category=discount">제휴/할인</a></li>
      </ul>
      <ul class="event-list">
        <c:forEach var="event" items="${boardList }">
	        <li>
	          <a href="./EventContent.ev?num=${event.num }&pageNum=${pageNum }">
	            <img src="./upload/${event.image_thumb }" />
	              <div class="txt1">${event.subject }</div>
	              <div class="txt2">
	              	<f:formatDate value="${event.eventDateStart }" pattern="yyyy.MM.dd"/> ~
	              	<f:formatDate value="${event.eventDateEnd }" pattern="yyyy.MM.dd"/>
	              </div>
	          </a>
	        </li>
        </c:forEach>
      </ul>
      

      <!-- 페이징 처리 -->
      <div class="paging">
      	<c:if test="${result != 0 }">
      		<!-- 이전 -->
      		<c:if test="${startPage > pageBlock }">
      			<a href="./Event.ev?pageNum=${startPage-pageBlock }" class="prev">이전</a>
      		</c:if>
      		
      		<!-- 1 2 3 4 .... 10     11 12 ... 20 -->
      		<c:forEach var="i" begin="${startPage }" end="${endPage }">
      			<c:if test="${startPage <= endPage }">
      				<!-- 현재 페이지블럭에 css 적용 -->
      				<c:if test="${pageNum eq i }">
      					<a href="./Event.ev?pageNum=${i }" class="pageNum nowPageNum">${i }</a>
      				</c:if>
      				<c:if test="${pageNum ne i }">
      					<a href="./Event.ev?pageNum=${i }" class="pageNum">${i }</a>
      				</c:if>
      			</c:if>
      		</c:forEach>
      		
      		<!-- 다음 -->
      		<c:if test="${endPage < pageCount }">
      			<a href="./Event.ev?pageNum=${startPage + pageBlock }" class="next">다음</a>
      		</c:if>
      	</c:if>
      	
      	<!-- 관리자 계정만 이벤트 글쓰기 가능 -->
      	<c:if test="${(sessionScope.id eq 'admin') }">
      		<a href="./EventWrite.ev" class="writeBtn">글쓰기</a>
      	</c:if>
      </div>
    </div>
  </section>



  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>
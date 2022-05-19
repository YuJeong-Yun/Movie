<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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

<!--     <script defer src="./js/event/eventList.js"></script> -->
</head>
<body>
    <!-- HEADER -->
 	<jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 이벤트 목록 출력 -->
  <section class="board">
    <div class="inner">
      <!--이벤트 타이틀 -->
      <div class="board-title">EVENT</div> 
      <!--이벤트 카테고리-->
      <ul class="category">
        <li><a href="./Event.ev?category=special">SPECIAL</a></li>
        <li><a href="./Event.ev?category=movie">영화/예매</a></li>
        <li><a href="./Event.ev?category=membership">멤버십/CLUB</a></li>
        <li><a href="./Event.ev?category=cgv">CGV 극장별</a></li>
        <li><a href="./Event.ev?category=discount">제휴/할인</a></li>
      </ul>
      <!-- 이벤트 글 리스트 -->
      <ul class="event-list">
        <c:forEach var="event" items="${boardList }">
	        <li>
	          <a href="./EventContent.ev?num=${event.num }&pageNum=${pageNum }&category=${event.category }">
	            <img src="./upload/${event.image_thumb }" />
	            <div class="txt1">${event.subject }</div>
	            <div class="txt2">
	              ${event.eventDateStart }
	              <c:if test="${event.eventDateEnd ne '' or event.eventDateStart ne '' }">~</c:if>
	               ${event.eventDateEnd }
	            </div>
	          </a>
	        </li>
        </c:forEach>
      </ul>
      <!-- 이벤트 배너 광고 -->
      <div class="event-ad">
      	<img src="./images/event_ad1.jpg" alt="그대가 조국" />
      	<img src="./images/event_ad2.jpg" alt="CGV GIFTCARD" />
      	<img src="./images/event_ad3.png" alt="피는 물보다 진하다" />
      </div>
      

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
  
	<script type="text/javascript">
		// 카테고리 목록 가져오기
		const categoryArr = document.querySelectorAll('.category li a');
		console.log(categoryArr);
	
		// 현재 카테고리와 일치하는 요소에 폰트 색상 적용
		 const categoryList = ['special', 'movie', 'membership', 'cgv', 'discount'];
		 const category = '${param.category}';
		 for(let i=0; i<categoryList.length ; i++) {
			if(category==categoryList[i]) {
		 		categoryArr[i].style.color = "#fb4357";
			}
		}
	</script>
</body>
</html>
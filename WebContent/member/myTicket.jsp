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

  <link rel="stylesheet" href="./css/member/myTicket.css">
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp" />



  <!--예매 내역-->
  <section class="my-ticket">
    <div class="inner">
      <!-- LEFT MENU -->
      <jsp:include page="../inc/left.jsp" />

      <div class="ticket-wrapper">
        <h2 class="ticket-title">MY 예매 내역</h2>
        <c:forEach var="pay" items="${payList }">
	        <table><col width="120"><col width="280"><col width="120"><col width="250">
	          <tr>
	            <td colspan="2" class="movie-title">${pay.movie_title }</td>
	            <td>예매번호</td>
	            <td class="order_num">${pay.order_num }</td>
	          </tr>
	          <tr>
	            <td>관람 일시</td>
	            <td>${pay.movie_dateTime }</td>
	            <td>관람 극장</td>
	            <td class="movie-theater">CGV ${pay.movie_theater }</td>
	          </tr>
	          <tr>
	            <td>결제 날짜</td>
	            <td><f:formatDate value="${pay.order_date }" pattern="yyyy년 MM월 dd일" /></td>
	            <td>결제 금액</td>
	            <td><f:formatNumber value="${pay.price }" pattern="#,##0 원" /></td>
	          </tr>
	        </table>
        </c:forEach>
        <c:if test="${payList.size() eq 0 }">
        	<table><col width="120"><col width="280"><col width="120"><col width="250">
	          <tr>
	            <td colspan="2" class="movie-title">예매 내역 없음</td>
	            <td>예매번호</td>
	            <td class="order_num">CGV에서 지금 예매해보세요!</td>
	          </tr>
	          <tr>
	            <td>관람 일시</td>
	            <td></td>
	            <td>관람 극장</td>
	            <td class="movie-theater"></td>
	          </tr>
	          <tr>
	            <td>결제 날짜</td>
	            <td></td>
	            <td>결제 금액</td>
	            <td></td>
	          </tr>
	        </table>
        </c:if>
        
      </div>

    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp" />
</body>

</html>
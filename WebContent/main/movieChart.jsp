<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <!-- 브라우저 스타일 초기화 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" />
  <!-- 구글 폰트 -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
  <!-- 구글 아이콘 -->
  <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">

  <link rel="stylesheet" href="./css/movieChart.css">
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>





  <!-- 무비 차트 -->
  <section class="movie-chart">
    <div class="inner">
    
      <!-- 광고 이미지 -->
      <div class="ad-wrapper">
	    <img src="./images/movieChart_ad.jpg" alt="CGV GIFTCARD" class="ad-img"/>  
	    <div class="description">
          <div>CGV 기프트 카드</div>
  	      <div>감사의 마음을 선물하세요!</div>
	    </div>
      </div>
    
      <h2 class="title">무비차트</h2>
      <ol class="movie-wrapper">
      	<c:forEach var="mc" items="${sessionScope.movieChart }" begin="0" end="2" step="1">
	      <li>
	        <div class="movie-rank">${mc.rank }</div>
	        <img src="${mc.img }" alt="영화" class="movie-img" />
	        <div class="movie-contents">
	          <div class="title">${mc.movieTitle }</div>
	          <div class="rate">${mc.movieRate }</div>
	          <div class="opening-date">${mc.movieOpenDate }</div>
	        </div>
	        <a href="javascript:void(0)" class="movie-ticketing">예매하기</a>
	      </li>
        </c:forEach>
      </ol>
      <ol class="movie-wrapper second">
      	<c:forEach var="mc" items="${sessionScope.movieChart }" begin="3" end="18" step="1">
	      <li>
	        <div class="movie-rank">${mc.rank }</div>
	        <img src="${mc.img }" alt="영화" class="movie-img" />
	        <div class="movie-contents">
	          <div class="title">${mc.movieTitle }</div>
	          <div class="rate">${mc.movieRate }</div>
	          <div class="opening-date">${mc.movieOpenDate }</div>
	        </div>
	        <a href="javascript:void(0)" class="movie-ticketing">예매하기</a>
	      </li>
        </c:forEach>
      </ol>
      
    </div>
  </section>



  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>

</body>
</html>
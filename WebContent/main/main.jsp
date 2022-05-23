<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>영화 그 이상의 감동, CGV</title>
  <!-- 브라우저 스타일 초기화 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" />
  <!-- 구글 폰트 -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
  <!-- 구글 아이콘 -->
  <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
  <!-- SWIPER 라이브러리 -->
  <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
  <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

  <link rel="stylesheet" href="./css/main/main.css" />

  <script defer src="./js/main/youtube.js"></script>
  <script defer src="./js/main/main.js"></script>
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 유튜브 iFrame API -->
  <section class="youtube">
    <div class="youtube-video">
      <div class="youtube__area">
        <div id="player"></div>
      </div>
    </div>
    
    <div class="youtube-text">
      <div class="title">피는 물보다 진하다</div>
      <div class="description">하드보일드 액션의<br>기준이 바뀐다!</div>
    </div>
  </section> 


  <!-- 영화 차트 슬라이드 -->
  <section class="movie-chart">
    <div class="inner">
      <div class="swiper">
        <h1 class="movie-chart-title">무비차트</h1>
        <div class="swiper-wrapper">
       	  <c:forEach var="mc" items="${sessionScope.movieChart }" begin="0" end="9" step="1">
         	<div class="swiper-slide">
         	  <div class="poster-bg">
                <img src="${mc.img }" alt="movie" class="poster" />
              </div>
              <div class="movieContent">
                <div class="rank">${mc.rank.substring(3) }</div>
                <div class="title">${mc.movieTitle }</div>
                <div class="rate">${mc.movieRate }</div>
              </div>
            </div>
       	  </c:forEach>	
        </div>
      </div>

      <div class="swiper-prev">
        <div class="material-icons">chevron_left</div>
      </div>
      <div class="swiper-next">
        <div class="material-icons">navigate_next</div>
      </div>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>

  </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

  <link rel="stylesheet" href="../css/movieChart.css">
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>



  <!-- 무비 차트 -->
  <section class="movie-chart">
    <div class="inner">
      <ol class="movie-wrapper">
        <li>
          <div class="movie-rank">No.1</div>
          <img src="../images/movieImg1.jpg" alt="영화" class="movie-img" />
          <div class="movie-contents">
            <div class="title">닥터 스트레인지</div>
            <div class="rate">예매율 30%</div>
            <div class="opening-date">2022.05.01 개봉</div>
          </div>
          <a href="javascript:void(0)" class="movie-ticketing">예매하기</a>
        </li>
        <li>
          <div class="movie-rank">No.1</div>
          <img src="../images/movieImg1.jpg" alt="영화" class="movie-img" />
          <div class="movie-contents">
            <div class="title">닥터 스트레인지</div>
            <div class="rate">예매율 30%</div>
            <div class="opening-date">2022.05.01 개봉</div>
          </div>
          <a href="javascript:void(0)" class="movie-ticketing">예매하기</a>
        </li>
        <li>
          <div class="movie-rank">No.1</div>
          <img src="../images/movieImg1.jpg" alt="영화" class="movie-img" />
          <div class="movie-contents">
            <div class="title">닥터 스트레인지</div>
            <div class="rate">예매율 30%</div>
            <div class="opening-date">2022.05.01 개봉</div>
          </div>
          <a href="javascript:void(0)" class="movie-ticketing">예매하기</a>
        </li>
        <li>
          <div class="movie-rank">No.1</div>
          <img src="../images/movieImg1.jpg" alt="영화" class="movie-img" />
          <div class="movie-contents">
            <div class="title">닥터 스트레인지</div>
            <div class="rate">예매율 30%</div>
            <div class="opening-date">2022.05.01 개봉</div>
          </div>
          <a href="javascript:void(0)" class="movie-ticketing">예매하기</a>
        </li>
      </ol>
    </div>
  </section>



  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>

</body>
</html>
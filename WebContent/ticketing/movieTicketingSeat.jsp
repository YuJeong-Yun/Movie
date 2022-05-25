<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

  <link rel="stylesheet" href="./css/ticketing/movieTicketingSeat.css">
  <script defer src="./js/ticketing/movieTicketingSeat.js"></script>
</head>

<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 좌석 -->
  <section class="seat">
    <div class="inner">
      <!-- 인원 선택 -->
      <div class="select-personnel">
        <div class="title">
          <span>인원/좌석</span>
        </div>
        <div class="personnel-wrapper">
          <div class="personnel__item">
            <div class="adult__text">일반</div>
            <ul class="adult__count">
              <li>0</li>
              <li>1</li>
              <li>2</li>
              <li>3</li>
              <li>4</li>
              <li>5</li>
            </ul>
          </div>
          <div class="personnel__item">
            <div class="teenager__text">청소년</div>
            <ul class="teenager__count">
              <li>0</li>
              <li>1</li>
              <li>2</li>
              <li>3</li>
              <li>4</li>
              <li>5</li>
            </ul>
          </div>
        </div>
        <div class="movie-wrapper">
          <span class="movie-theater">대전</span>
          <span class="movie-date">2022.05.26 목</span>
          <div class="movie-title">닥터 스트레인지</div>
        </div>
      </div>
      <!-- 좌석 선택 -->
      <div class="select-seat">
        <div class="screen">SCREEN</div>
        <div class="seat-container"></div>
      </div>

      <a href="javascript:void(0)" class="back">이전화면</a>
      <a href="javascript:void(0)" class="payment">결제하기</a>
    </div>
  </section>




  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
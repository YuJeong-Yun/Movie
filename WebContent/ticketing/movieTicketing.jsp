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

  <link rel="stylesheet" href="./css/ticketing/movieTicketing.css">
  <script defer src="./js/ticketing/movieTicketing.js"></script>
</head>

<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 영화 예매 선택 -->
  <section class="movie-reserve">
    <div class="inner">

      <ul class="reserve-wrapper">
        <!-- 영화 -->
        <li class="movie-container">
          <div class="title">영화</div>
          <div class="item-wrapper">
            <ul class="movie__item">
              <c:forEach var="mc" items="${movieChart }">
                <li>${mc.movieTitle }</li>
              </c:forEach>
            </ul>
          </div>
        </li>
        <!-- 극장 -->
        <li class="theater-container">
          <div class="title">극장</div>
          <div class="item-wrapper">
            <div class="theater__item">
              <div>서울</div>
              <ul class="hide">
                <li>강변</li>
                <li>명동</li>
                <li>목동</li>
                <li>불광</li>
                <li>송파</li>
              </ul>
            </div>
            <div class="theater__item">
              <div>경기</div>
              <ul class="hide">
                <li>동백</li>
                <li>동수원</li>
                <li>소풍</li>
                <li>오리</li>
                <li>야탑</li>
              </ul>
            </div>
            <div class="theater__item">
              <div>인천</div>
              <ul class="hide">
                <li>계양</li>
                <li>부평</li>
                <li>연수역</li>
              </ul>
            </div>
            <div class="theater__item">
              <div>대전/충청</div>
              <ul class="hide">
                <li>대전</li>
                <li>세종</li>
                <li>천안</li>
                <li>청주</li>
                <li>홍성</li>
                <li>논산</li>
                <li>당진</li>
              </ul>
            </div>
            <div class="theater__item">
              <div>부산/울산</div>
              <ul class="hide">
                <li>대연</li>
                <li>동래</li>
                <li>서면</li>
                <li>센텀시티</li>
                <li>아시아드</li>
                <li>울산동구</li>
                <li>울산삼산</li>
              </ul>
            </div>
          </div>
        </li>
        <!-- 날짜 -->
        <li class="date-container">
          <div class="title">날짜</div>
          <div class="item-wrapper">
            <div class="date__month"></div>
            <ul class="date__list"></ul>
            <div class="date__nextMonth"></div>
            <ul class="date__nextList"></ul>
          </div>
        </li>
        <!-- 시간 -->
        <li class="time-container">
          <div class="title">시간</div>
          <div class="item-wrapper hide">

            <div class="time__item">
              <span class="theater-num">9관[imax] 8층</span>
              <span class="theater-seats">(총 502석)</span>
              <ul>
                <li>
                  <div class="time">12:15</div>
                  <div class="seats">502</div>
                </li>
                <li>
                  <div class="time">14:30</div>
                  <div class="seats">502</div>
                </li>
                <li>
                  <div class="time">16:45</div>
                  <div class="seats">502</div>
                </li>
                <li>
                  <div class="time">19:00</div>
                  <div class="seats">502</div>
                </li>
                <li>
                  <div class="time">21:15</div>
                  <div class="seats">502</div>
                </li>
              </ul>
            </div>
            <div class="time__item">
              <span class="theater-num">GOLD CLASS 7층</span>
              <span class="theater-seats">(총 31석)</span>
              <ul>
                <li>
                  <div class="time">11:10</div>
                  <div class="seats">31</div>
                </li>
                <li>
                  <div class="time">13:45</div>
                  <div class="seats">31</div>
                </li>
                <li>
                  <div class="time">16:05</div>
                  <div class="seats">31</div>
                </li>
                <li>
                  <div class="time">18:20</div>
                  <div class="seats">31</div>
                </li>
              </ul>
            </div>
          </div>
        </li>
      </ul>
      
      <form action="./MovieTicketingSeat.ti" method="get" name="fr">
        <input type="submit" value="좌석선택" class="selectSeat">
      </form>
    </div>
  </section>



  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
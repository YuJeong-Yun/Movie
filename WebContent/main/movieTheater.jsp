<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- 브라우저 스타일 초기화 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" />
  <!-- 구글 폰트 -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
  <!-- 구글 아이콘 -->
  <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">

  <link rel="stylesheet" href="./css/main/movieTheater.css" />
  
  <script src="https://d3js.org/d3.v7.min.js"></script>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=99fbd6629cf7202febfa5d915b1ff3c3"></script>
  <script defer src="./js/main/movieTheater.js"></script>

</head>
<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 지도 출력 -->
  <section class="map">
    <div class="inner">
      <div class="title">주변 CGV 찾기</div>
      <div id="map" style="width: 1100px; height: 550px;"></div>
    </div>
  </section>
	
	
	
  <!-- 하단 메뉴에서 광고 배너 빼고 출력 -->
  <!-- FOOTER -->
  <footer>
  <div class="inner">
    <ul class="menu">
      <li>회사소개</li>
      <li>IR</li>
      <li>채용정보</li>
      <li>광고/제휴/출점문의</li>
      <li>이용약관</li>
      <li>편성기준</li>
      <li>개인정보처리방침</li>
      <li>법적고지</li>
      <li>이메일주소무단수집거부</li>
      <li>윤리경영</li>
      <li>사이버감사실</li>
    </ul>

    <ul class="info">
      <li>(04377)서울특별시 용산구 한강대로 23길 55, 아이파크몰 6층(한강로동)</li>
      <li>
        <span>대표이사: 허민회</span>
        <span>사업자등록번호: 104-81-45690</span>
        <span>통신판매업신고번호: 2017-서울용산-0662 사업자정보확인</span>
      </li>
      <li>
        <span>호스팅사업자: CJ올리브네트웍스</span>
        <span>개인정보보호 책임자: 심준범</span>
        <span>대표이메일: jcgvmaster@cj.net</span>
      </li>
      <li>© CJ CGV. All Rights Reserved</li>
      <li class="mail"
      	onclick="window.open('./Mail.ml', 'mail', 'top=100, left=100, width=550, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> * 메일 보내기</li>
    </ul>
  </div>
</footer>
</body>
</html>
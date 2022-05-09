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
  <link
    href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
    rel="stylesheet">

  <link rel="stylesheet" href="../css/movieReview.css">
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>

  <!-- 광고 이미지 -->
  <div class="ad-wrapper">
    <img src="../images/movieChart_ad.jpg" alt="CGV GIFTCARD" class="ad-img"/>  
    <div class="description">
      <div>CGV 기프트 카드</div>
      <div>감사의 마음을 선물하세요!</div>
    </div>
  </div>
  <!-- 게시판 글 목록 -->
  <section class="board">
    <div class="inner">
      <div class="board-title">영화 REVIEW</div>
      <table class="board-table">
        <tr>
          <th width="80">No.</th>
          <th width="450">제목</th>
          <th width="130">작성자</th>
          <th width="160">작성일</th>
          <th width="70">조회</th>
        </tr>
        <tr>
          <td>1</td>
          <td>후기1</td>
          <td>유정</td>
          <td>2022.05.02</td>
          <td>10</td>
        </tr>
        <tr>
          <td>2</td>
          <td>후기2</td>
          <td>유정</td>
          <td>2022.05.15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>2</td>
          <td>후기2</td>
          <td>유정</td>
          <td>2022.05.15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>2</td>
          <td>후기2</td>
          <td>유정</td>
          <td>2022.05.15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>2</td>
          <td>후기2</td>
          <td>유정</td>
          <td>2022.05.15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>2</td>
          <td>후기2</td>
          <td>유정</td>
          <td>2022.05.15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>2</td>
          <td>후기2</td>
          <td>유정</td>
          <td>2022.05.15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>2</td>
          <td>후기2</td>
          <td>유정</td>
          <td>2022.05.15</td>
          <td>15</td>
        </tr>
      </table>
      <button class="writeBtn"><a href="javascript:void(0)">글쓰기</a></button>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
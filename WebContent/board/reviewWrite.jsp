<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title></title>
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
  
    <link rel="stylesheet" href="./css/board/reviewWrite.css" />
    <script defer src="./js/board/reviewWrite.js"></script>
</head>

<body>
  <!-- 로그인 안되어있으면 메인으로 이동 -->
  <c:if test="${id eq null }">
  	<%response.sendRedirect("./Main.do"); %>
  </c:if>
  
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <section class="review-write">
    <div class="inner">
      <form action="./MovieReviewWriteAction.bo" method="post" name="fr" onsubmit="return check();" >
        <table>
          <tr>
            <td><input type="text" disabled="disabled" value="영화 리뷰 글쓰기"></td>
          </tr>
          <tr>
            <!-- 글 제목 입력. 최대 300자 -->
            <td><input type="text" name="subject" placeholder="제목을 입력해 주세요." maxlength="300"></td>
          </tr>
          <tr>
            <td>
              <!-- 영화 차트 / 예매 이동 링크 -->
              <div>지금 영화 차트를 확인하고 싶다면 → <a href="./MovieChart.do"><span class="material-icons-outlined">equalizer</span></a></div>
              <div>지금 바로 예매하고 싶다면 → <a href="./MovieTicketing.ti"><span class="material-icons-outlined">date_range</span></a></div>
            </td>
          </tr>
          <tr>
            <!-- 글 본문 입력. 최대 5000자 -->
            <td><textarea name="content" maxlength="5000">※ 영화 리뷰 게시판 입니다.
영화 감상을 자유롭게 등록해주세요!</textarea></td>
          </tr>
        </table>
        <input type="submit" value="등록하기" class="btn">
      </form>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>

</body>

</html>
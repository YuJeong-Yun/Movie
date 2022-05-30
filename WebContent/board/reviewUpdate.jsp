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
  
    <link rel="stylesheet" href="./css/board/reviewUpdate.css" />
    <script defer src="./js/board/reviewUpdate.js"></script>
</head>

<body>
  <!-- 로그인한 id와 글쓴 id 다르면 메인으로 이동 -->
  <c:if test="${id ne dto.id }">
  	<%response.sendRedirect("./Main.do"); %>
  </c:if>
  
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <section class="review-write">
    <div class="inner">
      <form action="./MovieReviewUpdateAction.bo?pageNum=${pageNum }" method="post" name="fr" onsubmit="return check();" >
        <input type="hidden" name="num" value="${dto.num }">
        <table>
          <tr>
            <td><input type="text" disabled="disabled" value="영화 리뷰 글쓰기"></td>
          </tr>
          <tr>
            <td><input type="text" name="subject" maxlength="200" value="${dto.subject }"></td>
          </tr>
          <tr>
            <td>
              <div>지금 영화 차트를 확인하고 싶다면 → <a href="./MovieChart.do"><span class="material-icons-outlined">equalizer</span></a></div>
              <div>상영 시간표를 확인하고 싶다면 → <a href="javascript:void(0)"><span class="material-icons-outlined">date_range</span></a></div>
            </td>
          </tr>
          <tr>
            <td><textarea name="content" maxlength="5000">${dto.content }</textarea></td>
          </tr>
        </table>
        <input type="submit" value="수정하기" class="btn">
      </form>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>

</body>

</html>
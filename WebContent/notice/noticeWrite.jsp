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
  
    <link rel="stylesheet" href="./css/notice/noticeWrite.css" />
    <script defer src="./js/notice/noticeWrite.js"></script>
</head>

<body>
  <!-- 관리자 계정 아니면 메인으로 이동 -->
  <c:if test="${id ne 'admin' }">
  	<%response.sendRedirect("./Main.do"); %>
  </c:if>
  
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <section class="notice-write">
    <div class="inner">
      <form action="./NoticeWriteAction.no" method="post"  enctype="multipart/form-data" name="fr" onsubmit="return check();" >
        <table>
          <tr>
            <td><input type="text" disabled="disabled" value="공지사항 글쓰기"></td>
          </tr>
          <tr>
            <!-- 글 제목 입력. 최대 300자 -->
            <td><input type="text" name="subject" placeholder="제목을 입력해 주세요." maxlength="300"></td>
          </tr>
          <tr>
            <!-- 글 본문 입력. 최대 5000자 -->
            <td><textarea name="content" maxlength="5000"></textarea></td>
          </tr>
          <tr>
          	<td><input type="file" name="file"></td>
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
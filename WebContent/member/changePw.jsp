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

  <link rel="stylesheet" href="./css/member/changePw.css" />
  <script defer src="./js/member/changePw.js"></script>
</head>

<body>
  <c:if test="${sessionScope.id == null }">
    <% response.sendRedirect("./Login.me"); %>
  </c:if>


  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- MY CGV -->
  <section class="my-cgv">
    <div class="inner">
      <!-- LEFT MENU -->
      <jsp:include page="../inc/left.jsp"></jsp:include>


      <!-- 비밀번호 변경 -->
      <div class="changePw">
        <h2 class="changePw-title">MY CGV</h2>
        <form action="./MemberPwAction.me" method="post" name="fr" onsubmit="return check();">
          <div class="form-title">비밀번호 변경하기</div>
          <table>
            <tr>
              <td>현재 비밀번호</td>
              <td><input type="password" name="pwNow"></td>
            </tr>
            <tr>
              <td>변경할 비밀번호</td>
              <td><input type="password" name="pwNew" placeholder="8~12자 사이로 입력해주세요." minlength="8" maxlength="12"></td>
            </tr>
            <tr>
              <td>비밀번호 확인</td>
              <td><input type="password" name="pwNew2"></td>
            </tr>
            
            <input type="submit" value="변경하기" class="submitBtn">
          </table>
        </form>
      </div>
    </div>
  </section>



  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
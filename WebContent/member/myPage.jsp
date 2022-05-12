<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Page</title>
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

  <link rel="stylesheet" href="./css/myPage.css" />
  <script defer src="./js/myPage.js"></script>
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


      <!-- 회원 정보 수정 -->
      <div class="modify-info">
        <h2 class="info-title">MY CGV</h2>
        <form action="./MemberUpdateAction.me" method="post" name="fr" onsubmit="return check();">
          <div class="form-title">필수 회원 정보</div>
          <table>
            <tr>
              <td>이름</td>
              <td>${dto.name }</td>
            </tr>
            <tr>
              <td>아이디</td>
              <td>${dto.id }</td>
            </tr>
            <tr>
              <td>비밀번호</td>
              <td><button class="pwBtn">비밀번호 변경하기</button></td>
            </tr>
            <tr>
              <td>비밀번호 확인</td>
              <td><input type="password" name="pw"></td>
            </tr>
            <tr>
              <td>성별</td>
              <td>
                <label><input type="radio" name="gender" value="남" 
                	<c:if test="${dto.gender eq '남' }">
                		checked="checked"
                	</c:if>
                > 남</label>
                <label><input type="radio" name="gender" value="여"
                    <c:if test="${dto.gender eq '여' }">
                		checked="checked"
                	</c:if>
                > 여</label>
              </td>
            </tr>
            <tr>
              <td>주소</td>
              <td><input type="text" name="addr" value="${dto.addr }"></td>
            </tr>
            <tr>
              <td>휴대전화</td>
              <td><input type="text" name="tel" value="${dto.tel }" ></td>
            </tr>
            <tr>
              <td>e-mail</td>
              <td><input type="email" name="email" value="${dto.email }" ></td>
            </tr>
            <input type="submit" value="수정하기" class="submitBtn">
          </table>
        </form>
      </div>
    </div>
  </section>



  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
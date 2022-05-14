<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
  <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">

  <link rel="stylesheet" href="./css/member/login.css">
  <script defer src="./js/member/login.js"></script>
</head>
<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>  


  <!-- 로그인 -->
  <section class="logIn">
    <div class="inner">
      <div class="btn-wrapper">
        <button class="btn"><a href="javascript:void(0)">로그인</a></button>
        <button class="btn"><a href="javascript:void(0)">비회원 예매</a></button>
        <button class="btn"><a href="javascript:void(0)">비회원 예매확인</a></button>
      </div>
      
      <h2 class="alert">${requestScope.alert }</h2>
      <form action="./LoginAction.me" method="post" class="logInForm" name="loginForm" onsubmit="return loginCheck();">
        <input type="text" name="id"><br>
        <input type="password" name="pw"><br>
        <div class="logInBtn-wrapper"> 
          <input type="submit" value="로그인" class="logInSubmit">
        </div>
        <label class="save-id"><input type="checkbox" name="saveId">아이디 저장</label>
      </form>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>
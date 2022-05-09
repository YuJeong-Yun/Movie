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


  <link rel="stylesheet" href="../css/join.css">
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 회원가입 -->
  <section class="join">
    <div class="inner">
      <img src="../images/join.jpg" alt="회원가입" class="join-img">

      <div class="form-wrapper">
        <form action="javascript:void(0)" method="post">
          <div class="form-title">필수 회원 정보</div>
          <table>
            <tr>
              <td>아이디</td>
              <td><input type="text" name="id"></td>
            </tr>
            <tr>
              <td>비밀번호</td>
              <td><input type="password" name="pw"></td>
            </tr>
            <tr>
              <td>비밀번호 확인</td>
              <td><input type="password" name="pw2"></td>
            </tr>
            <tr>
              <td>이름</td>
              <td><input type="text" name="name"></td>
            </tr>
            <tr>
              <td>성별</td>
              <td>
                <label><input type="radio" name="gender" vlaue="남"> 남</label>
                <label><input type="radio" name="gender" value="여"> 여</label>
              </td>
            </tr>
            <tr>
              <td>주소</td>
              <td><input type="text" name="addr"></td>
            </tr>
            <tr>
              <td>휴대전화</td>
              <td><input type="text" name="tel"></td>
            </tr>
            <tr>
              <td>e-mail</td>
              <td><input type="email" name="email"></td>
            </tr>

            <input type="submit" class="joinBtn" value="CGV+ 통합 회원가입">
          </table>
        </form>

      </div>
    </div>

  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
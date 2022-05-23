<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

  <link rel="stylesheet" href="./css/member/myPage.css" />
  <script defer src="./js/jquery-3.6.0.min.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script defer src="./js/member/myPage.js"></script>
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
        <form action="./MemberUpdateAction.me" method="post" enctype="multipart/form-data" name="fr" onsubmit="return formCheck();">
          <div class="form-title">필수 회원 정보</div>
          <table>
            <tr>
              <td>프로필
                <c:if test="${dto.profile ne null }">
                  <img src="./profile/${dto.profile }" class="profile-img">
                </c:if>
              </td>
              <td colspan="2">
                <input type="file" name="profile" value="프로필 등록하기"><br>
                <div class="profile-size">* 10MB 이하로 등록해주세요.</div>
                <label class="profile-del"><input type="checkBox" name="profileDel" value="1">프로필 삭제</label>
              </td>
            </tr>
            <tr>
              <td>이름</td>
              <td colspan="2">${dto.name }</td>
            </tr>
            <tr>
              <td>아이디</td>
              <td colspan="2">${dto.id }</td>
            </tr>
            <tr>
              <td>비밀번호</td>
              <td colspan="2"><a href="./MemberPw.me"><button type="button" class="pwBtn">비밀번호 변경하기</button></a></td>
            </tr>
            <tr>
              <td>비밀번호 확인</td>
              <td colspan="2"><input type="password" name="pw"></td>
            </tr>
            <tr class="gender">
              <td>성별</td>
              <td colspan="2">
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
              <td colspan="2" class="addr">
                <!-- DB에 '우편번호/주소/상세주소' 형태로 들어있음. / 로 구분해서 우편번호, 주소, 상세주소에 값을 출력  -->
              	<c:set var="addr" value="${fn:split(dto.addr, '/') }" />
                <input type="text" name="postcode" id="postcode" placeholder="우편번호" value="${addr[0] }"><input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
                <input type="text" name="address" id="address" placeholder="주소" value="${addr[1] }"><br>
                <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소" value="${addr[2] }" }>
                <input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목" value="${addr[3] }">
              </td>
            </tr>
            <tr>
              <td>휴대전화</td>
              <td><input type="text" name="tel" value="${dto.tel }" onkeyup="telDbCheck();" maxlength="13"></td>
              <td class="telDbResult"></tr>
            </tr>
            <tr>
              <td>e-mail</td>
              <td><input type="email" name="email" value="${dto.email }" onkeyup="emailDbCheck();"></td>
              <td class="emailDbResult"></tr>
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
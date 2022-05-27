<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
  
    <link rel="stylesheet" href="./css/event/eventUpdate.css" />
    <script defer src="./js/event/eventUpdate.js" ></script>
</head>

<body>
  <c:if test="${id eq null or id ne 'admin' }">
    <% response.sendRedirect("./Event.ev"); %>
  </c:if>

  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp" />


  <!-- 이벤트 글수정 -->
  <section class="event-update">
    <div class="inner">
      <form action="./EventUpdateProAction.ev?pageNum=${pageNum }" method="post" enctype="multipart/form-data" name="fr" onsubmit="return check();">
        <input type="hidden" name="num" value="${dto.num }">
        <table>
          <tr>
            <td>
              <!-- 카테고리 선택 필수 -->
              <select name="category">
                <option value="">카테고리 선택</option>
                <option value="special" 
                  <c:if test="${dto.category == 'special' }">selected</c:if>
                >SPECIAL</option>
                <option value="movie" 
                  <c:if test="${dto.category == 'movie' }">selected</c:if>
                >영화/예매</option>
                <option value="membership" 
                  <c:if test="${dto.category == 'membership' }">selected</c:if>
                >멤버십/CLUB</option>
                <option value="cgv" 
                  <c:if test="${dto.category == 'cgv' }">selected</c:if>
                >CGV 극장별</option>
                <option value="discount" 
                <c:if test="${dto.category == 'discount' }">selected</c:if>
                >제휴/할인</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><input type="text" name="subject" maxlength="30" value="${dto.subject }"></td>
          </tr>
          <tr>
            <td>이벤트 기간 
              <input type="date" name="eventDateStart" value=${dto.eventDateStart }>
              ~
              <input type="date" name="eventDateEnd" value="${dto.eventDateEnd }"></td>
          </tr>
          <tr>
            <td class="image-comment"><input type="file" name="image" accept="image/*"><br>
            	* 이미지 파일을 올려주세요!
            </td>
          </tr>
        </table>
        <input type="submit" value="수정하기" class="btn" />
      </form>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp" />
</body>

</html>
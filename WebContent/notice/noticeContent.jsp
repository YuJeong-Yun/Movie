<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

  <link rel="stylesheet" href="./css/notice/noticeContent.css" />
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 공지사항 -->
  <section class=notice>
    <div class="inner">
      <!-- 이전 글 / 다음 글 / 목록 이동 가능 버튼 -->
      <div class="btn-group title">
        <!-- num 1인 글은 다음글 버튼 없음 
        	가장 최신 글은 이전글 버튼 없음 -->
        <c:if test="${prevNum ne 0}"><a href="./NoticeContent.no?num=${prevNum }&pageNum=${pageNum }" class="btn">△ 이전글</a></c:if>
        <c:if test="${nextNum ne 0 }"><a href="./NoticeContent.no?num=${nextNum }&pageNum=${pageNum }" class="btn">▽ 다음글</a></c:if>
        <a href="./Notice.no?pageNum=${pageNum }" class="btn">목록</a>
      </div>
      
      <!-- 글의 타이틀 출력 부분 -->
      <ul class="notice__title">
        <ul class="inner">
          <li class="title__subject">${dto.subject }</li> <!-- 타이틀 -->
          <li class="title__name">CGV</li> <!-- 글 작성자 -->
          <li class="title__info">
            <span><f:formatDate value="${dto.date }" pattern="yyyy.MM.dd  HH:mm" /></span> <!-- 작성 시간 -->
            <span>조회수 ${dto.readcount }</span> <!-- 조회수 -->
          </li>
        </ul>
      </ul>
      
      <!-- 글의 본문 출력 부분 -->
      <div class="notice__content">
        <div class="inner">
          <div class="content__text" style="white-space:pre-wrap">${dto.content }</div> <!-- 글 내용 -->
            <!-- 첨부파일 있으면 -->
            <c:if test="${dto.file ne null }">
         	  <div class="content__file">
	          	<a href="./notice/fileDown.jsp?fileName=${dto.file }">
	          		<span class="material-icons-outlined">save</span>${dto.file }
	          	</a>	
          	  </div>
          	</c:if>
          <!-- 관리자 계정이면 수정/삭제 버튼 출력 -->
          <c:if test="${sessionScope.id eq 'admin' }">
            <div class="btn-group content">
              <a href="./NoticeUpdate.no?num=${dto.num }&pageNum=${pageNum }" class="btn">수정하기</a>
              <a href="./NoticeDelete.no?num=${dto.num }&pageNum=${pageNum }" class="btn" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제하기</a>
            </div>
          </c:if>
        </div>
      </div>
      
      <!-- 화면 상단으로 이동하는 버튼 -->
      <a href="#" class="btn btn--top">▲ TOP</a>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
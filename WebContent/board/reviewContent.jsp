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

  <link rel="stylesheet" href="./css/board/reviewContent.css" />
  <script defer src="./js/board/reviewContent.js"></script>
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- Review Content  -->
  <section class="movie-review">
    <div class="inner">
      <!-- 이전 글 / 다음 글 / 목록 이동 가능 버튼 -->
      <div class="btn-group title">
        <!-- num 1인 글은 다음글 버튼 없음 
        	가장 최신 글은 이전글 버튼 없음 -->
        <c:if test="${prevNum ne 0}"><a href="./MovieReviewContent.bo?num=${prevNum }&pageNum=${pageNum }" class="btn">△ 이전글</a></c:if>
        <c:if test="${nextNum ne 0 }"><a href="./MovieReviewContent.bo?num=${nextNum }&pageNum=${pageNum }" class="btn">▽ 다음글</a></c:if>
        <a href="./MovieReview.bo?pageNum=${pageNum }" class="btn">목록</a>
      </div>
      <!-- 글의 타이틀 출력 부분 -->
      <ul class="review__title">
        <ul class="inner">
          <li class="title__subject">${dto.subject }</li> <!-- 타이틀 -->
          <li class="title__name">${dto.name }</li> <!-- 글 작성자 -->
          <li class="title__info">
            <span><f:formatDate value="${dto.date }" pattern="yyyy.MM.dd  HH:mm" /></span> <!-- 작성 시간 -->
            <span>조회수 ${dto.readcount }</span> <!-- 조회수 -->
          </li>
        </ul>
      </ul>
      <!-- 글의 본문 출력 부분 -->
      <div class="review__content">
        <div class="inner">
          <div class="content__text" style="white-space:pre-wrap">${dto.content }</div> <!-- 글 내용 -->
          <!-- 로그인한 아이디와 글 쓴 아이디 비교해서 같은 사람이면 수정/삭제 버튼 출력 -->
          <c:if test="${sessionScope.id eq requestScope.dto.id }">
            <div class="btn-group content">
              <a href="./MovieReviewUpdate.bo?num=${dto.num }&pageNum=${pageNum }" class="btn">수정하기</a>
              <a href="./MovieReviewDelete.bo?num=${dto.num }&pageNum=${pageNum }" class="btn" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제하기</a>
            </div>
          </c:if>
        </div>
      </div>
      <!-- 해당 글의 리뷰 수 출력 부분 -->
      <ul class="review__comment">
        <div class="inner">
          <div class="comment__cnt">
            <span class="material-icons-outlined">textsms</span> <!-- 댓글 아이콘 -->
            <div>댓글 ${dto.re_cnt }</div> <!-- 댓글 수 -->
          </div>
          <!-- 글의 리뷰 모두 출력 -->
          <ul class="comment__content">
            <c:forEach var="boardReply" items="${boardReplyList }">
	            <li>
	              <span class="material-icons">person</span> <!-- 프로필 -->
	              <div class="content__wrapper">
	                <div  class="content__name">${boardReply.name }</div> <!-- 댓글 작성자 -->
	                <div class="content__text" style="white-space:pre-wrap">${boardReply.content }</div> <!-- 댓글 내용 -->
	                <div class="content__date">
	                	<f:formatDate value="${boardReply.date }" pattern="yyyy.MM.dd HH:mm" ></f:formatDate> <!-- 댓글 작성 시간 -->
	                </div>
	                <div class="content__btn">
	                  <!-- 댓글 작성 아이디와 로그인한 아이디 같으면 수정/삭제 버튼 출력 -->
	                  <c:if test="${boardReply.id eq id }">
	                    <a href="javascript:void(0)">수정</a>
	                    <a href="./MovieReviewReplyDelete.bo?num=${dto.num }&pageNum=${pageNum }&re_num=${boardReply.re_num }" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
	                  </c:if>
	                </div>
	              </div>
	            </li>
            </c:forEach>
            <!-- 로그인한 사람만 댓글 작성 가능 -->
            <c:if test="${id ne null }">
	            <li class="content__write">
	              <form action="./MovieReviewReply.bo?pageNum=${pageNum }" method="post" name="fr" onsubmit="return check();">
	                <!-- 댓글은 1000자 까지만 작성 가능. 남은 입력 가능 수 출력 -->
	                <div class="lengthCalc">1000</div> <!-- 남은 입력 가능 수 (최대 1000자) -->
	                <textarea name="reply" maxlength="1000" onkeyup="calcInputLength();"></textarea>
	                <input type="submit" value="등록">
	                <input type="hidden" name="num" value="${dto.num }">
	              </form>
	            </li>
	        </c:if>
          </ul>
          <!-- 화면 상단으로 이동하는 버튼 -->
          <a href="#" class="btn btn--top">▲ TOP</a>
        </div>
      </ul>
    
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
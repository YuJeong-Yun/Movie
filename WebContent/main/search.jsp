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

  <link rel="stylesheet" href="./css/main/search.css" />
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 영화 리뷰 게시판 검색 결과 있으면 출력 -->
  <c:if test="${boardCnt.get(0) ne 0 }">
  <!-- 게시판 글 목록 -->
  <section class="board">
    <div class="inner">
    
      <!-- 영화 리뷰 목록 출력 -->
      <div class="board-title">영화 REVIEW</div>
      <table class="board-table">
        <tr>
          <th width="80">No.</th>
          <th width="450">제목</th>
          <th width="130">작성자</th>
          <th width="160">작성일</th>
          <th width="70">조회</th>
        </tr>
		<c:forEach var="dto" items="${searchList.get(0) }">
			<tr>
				<td>${dto.rownum }</td><!-- 행번호 -->
				<td>
					<a href="./MovieReviewContent.bo?num=${dto.num }&pageNum=1">${dto.subject }</a> <!-- 제목 -->
					<span><c:if test="${dto.re_cnt ne 0 }">[${dto.re_cnt }]</c:if></span> <!-- 댓글수 있으면 표시 -->
				</td>
				<td>${dto.name }</td> <!-- 작성자 이름 -->
				<td><f:formatDate value="${dto.date }" pattern="yyyy.MM.dd"/></td> <!-- 작성 날짜  -->
				<td>${dto.readcount }</td><!-- 조회수 -->
			</tr>
		</c:forEach>
      </table>
    </div>
  </section>
  </c:if>
  
  <!-- 이벤트 게시판 결과 있으면 출력 -->
  <c:if test="${boardCnt.get(1) ne 0 }">
  <!-- 이벤트 목록 출력 -->
  <section class="event">
    <div class="inner">
      <!--이벤트 타이틀 -->
      <div class="board-title">EVENT</div> 
      <!-- 이벤트 글 리스트 -->
      <ul class="event-list">
        <c:forEach var="event" items="${searchList.get(1) }">
            <!-- 글 클릭하면 해당 이벤트 페이지로 이동 -->
	        <li>
	          <a href="./EventContent.ev?num=${event.num }&pageNum=1&category=${event.category }">
	            <img src="./upload/${event.image_thumb }" /> <!-- 이벤트 글 썸네일 -->
	            <div class="txt1">${event.subject }</div> <!-- 제목 -->
	            <div class="txt2"> <!-- 이벤트 기간 -->
	              ${event.eventDateStart }
	              <c:if test="${event.eventDateEnd ne '' or event.eventDateStart ne '' }">~</c:if>
	               ${event.eventDateEnd }
	            </div>
	          </a>
	        </li>
        </c:forEach>
      </ul>
    </div>
  </section>
  </c:if>
  
  <!-- 공지사항 검색 결과 있으면 출력 -->
  <c:if test="${boardCnt.get(2) ne 0 }">
  <!-- 공지사항 목록 출력 -->
  <section class="notice">
    <div class="inner">
      <div class="board-title">Notice</div>
      <table class="board-table">
        <tr>
          <th width="80">No.</th>
          <th width="450">제목</th>
          <th width="130">첨부파일</th>
          <th width="160">작성일</th>
          <th width="70">조회</th>
        </tr>
		<c:forEach var="dto" items="${searchList.get(2)}">
			<tr>
				<td>${dto.rownum }</td> <!-- 행 번호 -->
				<td>
					<a href="./NoticeContent.no?num=${dto.num }&pageNum=1">${dto.subject }</a> <!-- 글 제목 -->
				</td>
				<td class="file">
					<!-- 첨부파일 있으면 아이콘 표시 -->
					<c:if test="${dto.file ne null }">
						<span class="material-icons-outlined">save</span>
					</c:if>
				</td>
				<td><f:formatDate value="${dto.date }" pattern="yyyy.MM.dd"/></td> <!-- 작성일 -->
				<td>${dto.readcount }</td> <!-- 조회수 -->
			</tr>
		</c:forEach>
      </table>
    </div>
  </section>
  </c:if>
  
  <!-- 검색결과 없으면 출력 -->
  <c:if test="${boardCnt.stream().sum() eq 0 }">
  <section class="searchNone">
    <div class="inner">
      <h2>검색결과 없음</h2>
    </div>
  </section>
  </c:if>
  

  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
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

  <link rel="stylesheet" href="./css/notice/noticeList.css" />
</head>

<body>
  <!-- HEADER -->
  <jsp:include page="../inc/header.jsp"></jsp:include>


  <!-- 공지사항 글 목록 -->
  <section class="notice">
    <div class="inner">
    
      <!-- 공지사항 목록 출력 -->
      <div class="board-title">Notice</div>
      <table class="board-table">
        <tr>
          <th width="80">No.</th>
          <th width="450">제목</th>
          <th width="130">첨부파일</th>
          <th width="160">작성일</th>
          <th width="70">조회</th>
        </tr>
		<c:forEach var="dto" items="${requestScope.boardList }">
			<tr>
				<td>${dto.rownum }</td>
				<td>
					<a href="./NoticeContent.no?num=${dto.num }&pageNum=${pageNum }">${dto.subject }</a>
				</td>
				<td class="file">
					<!-- 첨부파일 있으면 아이콘 표시 -->
					<c:if test="${dto.file ne null }">
						<span class="material-icons-outlined">save</span>
					</c:if>
				</td>
				<td><f:formatDate value="${dto.date }" pattern="yyyy.MM.dd"/></td>
				<td>${dto.readcount }</td>
			</tr>
		</c:forEach>
      </table>
      
      <!-- 페이징 처리 -->
      <div class="paging">
      	<c:if test="${result != 0 }">
      		<!-- 이전 -->
      		<c:if test="${startPage > pageBlock }">
      			<a href="./Notice.no?pageNum=${startPage-pageBlock }" class="prev">이전</a>
      		</c:if>
      		
      		<!-- 1 2 3 4 .... 10     11 12 ... 20 -->
      		<c:forEach var="i" begin="${startPage }" end="${endPage }">
      			<c:if test="${startPage <= endPage }">
      				<!-- 현재 페이지블럭에 css 적용 -->
      				<c:if test="${pageNum eq i }">
      					<a href="./Notice.no?pageNum=${i }" class="pageNum nowPageNum">${i }</a>
      				</c:if>
      				<c:if test="${pageNum ne i }">
      					<a href="./Notice.no?pageNum=${i }" class="pageNum">${i }</a>
      				</c:if>
      			</c:if>
      		</c:forEach>
      		
      		<!-- 다음 -->
      		<c:if test="${endPage < pageCount }">
      			<a href="./Notice.no?pageNum=${startPage + pageBlock }" class="next">다음</a>
      		</c:if>
      	</c:if>
      	
      	<!-- 관리자 계정만 공지사항 글쓰기 가능 -->
      	<c:if test="${(sessionScope.id eq 'admin') }">
      		<a href="./NoticeWrite.no" class="writeBtn">글쓰기</a>
      	</c:if>
      </div>
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
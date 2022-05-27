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
  <script src="./js/jquery-3.6.0.min.js"></script>
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
          <li class="title__name"><!-- 글 작성자 -->
          	<img src="./profile/th_${dto.id}.jpg" onerror="this.style.display='none';" /> <!-- 프로필 -->
          	${dto.name }
          </li> 
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
          
          <!-- 댓글 아이콘 / 댓글 수 -->
          <div class="comment__cnt">
            <span class="material-icons-outlined">textsms</span> <!-- 댓글 아이콘 -->
            <div class="re_cnt">${dto.re_cnt }</div> <!-- 댓글 수 -->
          </div>
          
          <!-- 글의 리뷰 모두 출력 -->
          <ul class="comment__content">
            <c:forEach var="boardReply" items="${boardReplyList }" varStatus="status">
              <c:if test="${boardReply.id ne null }"> <!-- 존재하는 회원일 경우 -->
	            <li>
	              <!-- 프로필 이미지 없으면 익명 아이콘 출력 -->
				  <img src="./profile/th_${boardReply.id }.jpg" onerror="this.style.display='none'; this.nextElementSibling.style.display='block';" />	              
	              <span class="material-icons">person</span> 
	              <div class="content__wrapper">
	                <div  class="content__name"> <!-- 댓글 작성자 -->
	                  ${boardReply.name } 
	                  <c:if test="${boardReply.id eq dto.id }"> <!-- 글 작성자와 댓글 작성자 같으면 작성자 표시 -->
	                    <div class="writer">작성자</div>
	                  </c:if>
	                </div> 
	                <div class="content__text" style="white-space:pre-wrap">${boardReply.content }</div> <!-- 댓글 내용 -->
	                <div class="content__date">
	                	<f:formatDate value="${boardReply.date }" pattern="yyyy.MM.dd HH:mm" ></f:formatDate> <!-- 댓글 작성 시간 -->
	                </div>
	                <div class="content__btn">
	                  <!-- 댓글 작성 아이디와 로그인한 아이디 같으면 수정/삭제 버튼 출력 -->
	                  <c:if test="${boardReply.id eq id }">
	                    <input type="button" value="수정" onclick='replyUpdate(event)'>
	                    <input type="button" value="삭제" onclick='replyDelete(event, ${boardReply.cno});'>
	                  </c:if>
	                </div>
	              </div>
	            </li>
	          </c:if>
	          <c:if test="${boardReply.id eq null }"> <!-- 탈퇴한 회원일 경우 -->
	            <li>
	              <span class="material-icons">person</span> 
	              <div class="content__wrapper">
	                <div  class="content__name"> <!-- 댓글 작성자 -->
	                </div> 
	                <div class="content__text" style="white-space:pre-wrap">탈퇴한 회원입니다.</div> <!-- 댓글 내용 -->
	                <div class="content__date">
	                	<f:formatDate value="${boardReply.date }" pattern="yyyy.MM.dd HH:mm" ></f:formatDate> <!-- 댓글 작성 시간 -->
	                </div>
	              </div>
	            </li>
	          </c:if>
	              
	            <!-- 수정할 폼 -->
	            <li class="content__write" style="display: none;">
	              <form name="fr">
	                <!-- 댓글은 1000자 까지만 작성 가능. 남은 입력 가능 수 출력 -->
	                <div class="lengthCalc">1000</div> 
	                <textarea name="reply" maxlength="1000" onkeyup="calcInputLength(event);">${boardReply.content }</textarea>
	                <input type="button" value="취소" onclick="replyUpdateCancle(event)">
	                <input type="button" value="수정" onclick="replyUpdatePro(event, ${boardReply.cno}, ${status.index })">
	              </form>
	            </li>
	            
	            
            </c:forEach>
          </ul>
          
           <!-- 로그인한 사람만 댓글 작성 가능 -->
           <c:if test="${id ne null }">
            <li class="content__write">
              <form name="fr">
                <!-- 댓글은 1000자 까지만 작성 가능. 남은 입력 가능 수 출력 -->
                <div class="lengthCalc">1000</div>
                <textarea name="reply" maxlength="1000" onkeyup="calcInputLength(event);" placeholder="댓글을 입력해보세요"></textarea>
                <input type="button" value="등록" onclick="replyWrite(event);">
              </form>
            </li>
          </c:if>
          
          <!-- 화면 상단으로 이동하는 버튼 -->
          <a href="#" class="btn btn--top">▲ TOP</a>
        </div>
      </ul>
    
    </div>
  </section>

  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
  
  
  
  
  
    <script type="text/javascript">
		  
	  // 현재 글번호
	  let bno = ${dto.num };
	  // 본문 글쓴이 아이디
	  let boardWriter ='${dto.id }';
	  
	  // 댓글 쓰기
	  function replyWrite(event) {
		const e = event.target;
		// 공백이면 댓글 작성 안 함
	  	if(e.previousElementSibling.value.trim() == "") {
	  		return;
	  	}
		// 작성한 내용
		let content = e.previousElementSibling.value;
		// 댓글 마지막 인덱스 찾기
		let replyIndex = document.querySelector('.comment__content').childElementCount/2;
		  
		
	  	$.getJSON({
	  		url: "./board/replyWriteAjax.jsp",
	  		type: "post",
	  		data: {
	  			content: content,
	  			bno : bno,
	  		},
	  		success: function(item) {
				// 글쓴이 댓글쓴이 아이디 비교 -> 댓글 리스트에 함께 추가
				let writer = (boardWriter == item.id) ? "<div class='writer'>글쓴이</div>" : "";
	  			
	  			// 댓글 리스트에 추가
				let newReply = 
					"<li>" +
					   `<img src='./profile/th_`+item.id+`.jpg' onerror='this.style.display="none"; this.nextElementSibling.style.display="block";' />` +
			           "<span class='material-icons'>person</span>" + 
		              "<div class='content__wrapper'>" +
		                "<div  class='content__name'>"+item.name + 
		                writer +
		                "</div>" +
		                "<div class='content__text' style='white-space:pre-wrap'>"+item.content+"</div>" +
		                "<div class='content__date'>"+item.date+"</div>" +
		                "<div class='content__btn'>" +
	                      "<input type='button' value='수정' onclick='replyUpdate(event)'>" +
	                      "<input type='button' value='삭제' onclick='replyDelete(event,"+item.cno+")'>" +
		                "</div>" +
		              "</div>" +
		            "</li>" +
		            "<li class='content__write' style='display: none;'>" +
		              "<form name='fr'>" +
		                "<div class='lengthCalc'>1000</div>" +
		                "<textarea name='reply' maxlength='1000' onkeyup='calcInputLength(event);'>"+item.content+"</textarea>" +
		                "<input type='button' value='취소' onclick='replyUpdateCancle(event)'>" +
		                "<input type='button' value='수정' onclick='replyUpdatePro(event,"+item.cno+","+replyIndex+")'>" +
		              "</form>" +
		            "</li>";
		            
		       
	           $('.comment__content').append(newReply);
	           
	   	  	   // 댓글 입력창 내용 비우기
	   	  	   e.previousElementSibling.value = "";
	   	  	   // 댓글 입력창 남은 숫자 다시 1000으로 설정
	   	  	   e.previousElementSibling.previousElementSibling.textContent= 1000;
	   	       // 댓글 수 + 1
	           $('.re_cnt').text(Number($('.re_cnt').text())+1); 
	  		},
	  	    error:function(){
	  	      alert("댓글 작성 오류");
	  	    }
	  	}); // $.getJSON
	  }; // replyWrite()
  
	  
	  
	  // 댓글 삭제
	  function replyDelete(event, cno) {
		  
		  if(confirm('삭제하시겠습니까?')==false) {
			  return;
		  }
		  // 댓글 삭제
		  $.getJSON({
			url: "./board/replyDeleteAjax.jsp",
	  		type: "post",
	  		data: {
	  			cno : cno,
	  		},
	  		success: function(item) {
	  		},
	  	    error:function(){
	  	    	alert('댓글 삭제 오류');
		  	}
		  }); // $.getJSON
		  
		  // 삭제한 댓글 숨기기
		  event.target.parentElement.parentElement.parentElement.style.display="none";
		  // 본문 댓글 수 - 1
		  $('.re_cnt').text(Number($('.re_cnt').text())-1); 
	  }; // replyDelete()
	  
	  
	  
	  // 댓글 수정 클릭
	  function replyUpdate(event) {
		  const e = event.target;
		  
		  // 수정할 댓글 숨기기
		  e.parentElement.parentElement.parentElement.style.display="none";
		  // 수정할 폼 보이기
		  e.parentElement.parentElement.parentElement.nextElementSibling.style.display="block";
	  }; //replyUpdate()
	  
	  // 댓글 수정 취소 클릭
	  function replyUpdateCancle(event) {
		  const e = event.target;
		  
		  // 댓글 보이기
		  e.parentElement.parentElement.previousElementSibling.style.display="flex";
		  // 수정할 폼 숨기기
		  e.parentElement.parentElement.style.display="none";
	  }; //replyUpdateCancle()
	  
	  // 댓글 수정 동작하기
	  function replyUpdatePro(event, cno, index) {
		  const e = event.target;
		  // 수정한 댓글 내용
		  let content = event.target.previousElementSibling.previousElementSibling.value;
		  // 댓글 내용 없으면 수정 X
		  if(content.trim() == "") {
		  	return;
		  }
		  
		  // 댓글 수정
		  $.getJSON({
			url: "./board/replyUpdateAjax.jsp",
	  		type: "post",
	  		data: {
	  			cno : cno,
	  			content: content,
	  		},
	  		success: function(item) {
	  		  // 댓글 보이기
	  		  e.parentElement.parentElement.previousElementSibling.style.display="flex";
	  		  // 수정할 폼 숨기기
	  		  e.parentElement.parentElement.style.display="none";
	  		  // 댓글 내용 -> 수정한 내용으로 변경
	  		  let replyContentArr = document.querySelectorAll('.comment__content li .content__text');
	  		  replyContentArr[index].textContent = content;
	  		},
	  		error: function() {
	  			alert('댓글 수정 오류');
	  		}
		  }); // $.getJSON
		  

	  }; //replyUpdatePro()
	  
  </script>
</body>

</html>
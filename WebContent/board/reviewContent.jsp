<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Movie Review</title>
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
      <div class="btn-group title">
        <a href="javascript:void(0)" class="btn">△ 이전글</a>
        <a href="javascript:void(0)" class="btn">▽ 다음글</a>
        <a href="javascript:void(0)" class="btn">목록</a>
      </div>
      <ul class="review__title">
        <ul class="inner">
          <li class="title__subject">제목입니다.</li>
          <li class="title__name">작성자</li>
          <li class="title__info">
            <span>2022/05/13 18:30</span>
            <span>조회 300</span>
          </li>
        </ul>
      </ul>
      <div class="review__content">
        <div class="inner">
          <div class="content__text">내용입니다... ... ... .. . ..<br>sdfsdf</div>
          <div class="btn-group content">
            <a href="javascript:void(0)" class="btn">수정하기</a>
            <a href="javascript:void(0)" class="btn">삭제하기</a>
          </div>
        </div>
      </div>
      <ul class="review__comment">
        <div class="inner">
          <div class="comment__cnt">
            <span class="material-icons-outlined">textsms</span>
            <div>댓글 6</div>
          </div>
          <ul class="comment__content">
            <li class="content__write">
              <form action="javascript:void(0)" method="post" name="fr">
                <div class="lengthCalc">1000</div>
                <textarea name="reply" maxlength="1000" onkeyup="calcInputLength();"></textarea>
                <input type="submit" value="등록">
              </form>
            </li>
            <li>
              <span class="material-icons">person</span>
              <div class="content__wrapper">
                <div  class="content__name">댓글작성자</div>
                <div class="content__text">댓글 내용입니다.</div>
                <div class="content__date">2022/05/13 22:10</div>
                <div class="content__btn">
                  <a href="javascript:void(0)">답글</a>
                  <a href="javascript:void(0)">수정</a>
                  <a href="javascript:void(0)">삭제</a>
                </div>
              </div>
            </li>
            <li>
              <span class="material-icons">person</span>
              <div class="content__wrapper">
                <div  class="content__name">댓글작성자</div>
                <div class="content__text">댓글 내용입니다.</div>
                <div class="content__date">2022/05/13 22:10</div>
                <div class="content__btn">
                  <a href="javascript:void(0)">답글</a>
                  <a href="javascript:void(0)">수정</a>
                  <a href="javascript:void(0)">삭제</a>
                </div>
              </div>
            </li>
            <li>
              <span class="material-icons">person</span>
              <div class="content__wrapper">
                <div  class="content__name">댓글작성자</div>
                <div class="content__text">댓글 내용입니다.</div>
                <div class="content__date">2022/05/13 22:10</div>
                <div class="content__btn">
                  <a href="javascript:void(0)">답글</a>
                  <a href="javascript:void(0)">수정</a>
                  <a href="javascript:void(0)">삭제</a>
                </div>
              </div>
            </li>
          </ul>
          <a href="#" class="btn btn--top">▲ TOP</a>
        </div>
      </ul>
    
    </div>
  </section>


  <!-- FOOTER -->
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>
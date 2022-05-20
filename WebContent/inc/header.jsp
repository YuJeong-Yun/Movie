<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- HEADER -->
<header>
  <div class="menu1">
    <div class="inner">
      <!-- 로고 -->
      <div class="logo">
        <a href="./Main.do" class="logo__link">
          <img src="./images/cgv_logo.png" alt="cgv" class="logo__img" />
        </a>
        <span>C U L T U R E P L E X</span>
      </div>
	
      <!-- 로그인 / 회원가입 / 로그아웃 / 마이페이지 -->
      <ul class="memberInfo">
        <img src="./images/main_add_img.png" alt="현대카드" class="main_ad" />
        <!-- 로그인 안 되어 있으면 로그인/회원가입 아이콘 출력 -->
        <c:if test="${sessionScope.id == null }">
          <li>
            <a href="./Login.me" class="member-Icon">
              <div class="material-icons-outlined">lock</div>
              <div class="member-Text">로그인</div>
            </a>
          </li>
          <li>
            <a href="./Join.me" class="member-Icon">
              <div class="material-icons-outlined">group_add</div>
              <div class="member-Text">회원가입</div>
            </a>
          </li>
        </c:if>
        <!-- 로그인 되어있으면 로그아웃 아이콘 출력 -->
        <c:if test="${sessionScope.id != null }">
          <li>
            <a href="./Logout.me" class="member-Icon">
              <div class="material-icons-outlined">lock</div>
              <div class="member-Text">로그아웃</div>
            </a>
          </li>
        </c:if>
        <li>
          <a href="./MyPage.me" class="member-Icon">
            <div class="material-icons-outlined">person_outline</div>
            <div class="member-Text">마이페이지</div>
          </a>
        </li>
      </ul>
    </div>
  </div>

  <!-- 메뉴 리스트 -->
  <div class="menu2">
    <div class="main-menu">
      <div class="inner">
	    <div class="menu__name">
	      <h2><a href="javascript:void(0)">영화</a></h2>
	      <h2><a href="javascript:void(0)">극장</a></h2>
	      <h2><a href="javascript:void(0)" class="red">예매</a></h2>
	      <h2><a href="javascript:void(0)">이벤트</a></h2>
	      <h2><a href="javascript:void(0)">혜택</a></h2>
	    </div>
	    
	    <!-- 검색 -->
	    <div class="search">
	      <form action="./Search.do" method="post">
	        <input type="text" class="searchInput" name="search">
	        <span class="material-icons-outlined icon">search</span>
	      </form>
	    </div>
	    
      </div>

      <ul class="menu__contents">
        <div class="inner">
          <li>
            <h2>영화</h2>
            <ul>
              <li><a href="./MovieChart.do" class="do">무비차트</a></li>
              <li><a href="javascript:void(0)">아트하우스</a></li>
              <li><a href="javascript:void(0)">ICECON</a></li>
              <li><a href="./MovieReview.bo" class="do">관람 후기</a></li>
            </ul>
          </li>
          <li>
            <h2>극장</h2>
            <ul>
              <li><a href="javascript:void(0)">CGV극장</a></li>
              <li><a href="javascript:void(0)">특별관</a></li>
            </ul>
          </li>
          <li>
            <h2>예매</h2>
            <ul>
              <li><a href="javascript:void(0)">빠른 예매</a></li>
              <li><a href="javascript:void(0)">상영스케줄</a></li>
            </ul>
          </li>
          <li>
            <h2>이벤트</h2>
            <ul>
              <li><a href="./Event.ev?category=special" class="do">SPECIAL</a></li>
              <li><a href="./Event.ev?category=movie" class="do">영화/예매</a></li>
              <li><a href="./Event.ev?category=membership" class="do">멤버십/CLUB</a></li>
              <li><a href="./Event.ev?category=cgv" class="do">CGV 극장별</a></li>
              <li><a href="./Event.ev?category=discount" class="do">제휴할인</a></li>
              <li><a href="./Notice.no" class="do">공지사항</a></li>
              <li><a href="javascript:void(0)">당첨자 발표</a></li>
            </ul>
          </li>
          <li>
            <h2>혜택</h2>
            <ul>
              <li><a href="javascript:void(0)">CGV 할인정보</a></li>
              <li><a href="javascript:void(0)">CLUB 서비스</a></li>
              <li><a href="javascript:void(0)">VIP라운지</a></li>
            </ul>
          </li>
        </div>
      </ul>
    </div>
      
  </div>
</header>

<script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
<script type="text/javascript">
  const menuArr = document.querySelectorAll('.menu2 .main-menu .menu__name h2');
  const content = document.querySelector('.menu2 .main-menu .menu__contents');
  
  function show() {
	  content.style.height="300px";
  }
  function hide() {
	  content.style.height="0";
  }
  
  // 헤더 메뉴에 마우스 올리면 메뉴 리스트 보여줌
  for(let i=0; i<menuArr.length; i++) {
	  menuArr[i].addEventListener('mouseover', show);
	  menuArr[i].addEventListener('mouseout', hide);
  }
  content.addEventListener('mouseover', show);
  content.addEventListener('mouseout', hide);
  
  
  
  const mainMenu = document.querySelector('.menu2');
  const menu = document.querySelectorAll('.menu2 .main-menu .menu__name h2 a');
  const redMenu = document.querySelector('.menu2 .main-menu .menu__name .red');
  // 스크롤 내리면 상단 메뉴 고정
  // _.throttle 함수 이용해 0.2초마다 실행
  window.addEventListener('scroll', _.throttle(function() {
	  if(window.scrollY >= 100) {
		mainMenu.style.backgroundImage = "linear-gradient(to right, rgb(215, 67, 87), rgb(241,79,58) 59%, rgb(239, 100, 47))";
		mainMenu.style.position = "fixed";
		mainMenu.style.top = "0";
		mainMenu.style.left = "0";
		mainMenu.style.right = "0";
		mainMenu.style.margin =  "auto";
		for(let i=0; i<menu.length; i++) {
			menu[i].style.color = "#fff";
		}
	  }
	  if(window.scrollY < 100) {
		mainMenu.style.position = "static";
		mainMenu.style.backgroundImage = "none";
		for(let i=0; i<menu.length; i++) {
			menu[i].style.color = "#333";
		}
		redMenu.style.color = "#ff0000";
	  }
  }, 200));
  
</script>
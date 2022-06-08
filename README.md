# Movie
CGV 클론 코딩<br/><br/><br/>

1.개발환경
=============
>#### OS : Windows 10
>#### Toos/DB : Eclipse, VS Code, Apache Tomcat 8.5, MySQL, JDK1.8
>#### 언어 : JAVA, JSP/Servlet(Model2), HTML, CSS, JavaScript
>#### API : Kakao Map API / Daum Postcode API / Java Mail API /  IMPORT Payment API / Youtube Iframe API   
<br/><br/><br/>


2.ER DIAGRAM
=============
![image](https://user-images.githubusercontent.com/97975367/171113519-46360537-4e58-4da0-925c-04535698cbde.png)<br/><br/>
* #### 1. movie_board (id) -> movie_member(id) 참조 - Delete on cascade 속성 (탈퇴하면 게시글도 지워짐)

* #### 2. movie_board_reply (bno) -> movie_board(num) 참조 - Delete on cascade 속성 (게시글 지우면 댓글도 지워짐)<br/>  movie_board_reply (id) -> movie_member(id) 참조 - Delete on set null 속성 (탈퇴해도 댓글 정보 놔둠. 탈퇴한 회원입니다. 표시)

* #### 3. movie_ticketing (member_id) -> movie_member(id) 참조 - Delete on set null 속성 (탈퇴해도 결제정보 남아잇음)
<br/><br/><br/>



3.기능 구현
=============
3-1. 메인 화면
--------------
* Youtube Iframe API 이용 예고편 영상 자동재생/반복재생 출력   
![image](https://user-images.githubusercontent.com/97975367/171112924-5443ff77-0a88-4d0a-88c5-de60fc6f2962.png)   
<br/>

* 스크롤 위치 계산해서 메뉴를 위에 고정시킴 -> Lodash 라이브러리 throttle 함수 이용해 스크롤 위치 계산할 때 0.1초 텀을 줬음<br/>
![image](https://user-images.githubusercontent.com/97975367/171115587-de7acbd7-20ee-4d32-983a-c1d690d5070a.png)   
<br/>

* Main.do 주소 호출 시 마다 Jsoup 라이브러리 이용해 CGV에서 영화 차트 정보 크롤링해와서 JSONArray 형태로 Session 영역에 저장<br/>1~10위의 내용을 swiper 라이브러리 이용해 슬라이드 형식으로 출력시켜줌   
![image](https://user-images.githubusercontent.com/97975367/171115979-ab655fd5-b265-4b42-8d5f-c6ef7a74ec6b.png)   
<br/>

* 상단 메뉴에서 전체 게시판 검색 가능. 검색 결과에 해당하는 최신 10개 글 출력됨(이벤트 게시판은 12개).   
![image](https://user-images.githubusercontent.com/97975367/171123864-43d2b9e3-ce30-45ef-82ec-aeb39ca5d1ae.png)   
<br/><br/>

3-2. 회원 관련
--------------
>### 3-2-1. 회원 가입
* 아이디, 휴대전화, email은 AJAX 이용해 Keyup 동작마다 중복 체크 -> 중복된 값이면 회원 가입 동작 X<br/>정규식 이용해 아이디는 영어, 숫자 / 이름은 한글, 영어만 입력되도록 설정   
우편번호 찾기 클릭하면 다음 post API 이용해 주소 찾을 수 있도록 설정   
![image](https://user-images.githubusercontent.com/97975367/171116257-95565a4c-f391-4df0-b8e1-f6dc6cd9a4b0.png)   
<br/><br/>

>### 3-2-2.로그인
* 아이디 저장클릭하고 로그인 시 쿠키 생성해서 아이디 입력칸에 불러옴<br/>체크 해제 후 로그인 시 아이디값 쿠키 삭제   
![image](https://user-images.githubusercontent.com/97975367/171116471-515ddb55-d404-4343-92db-c1a2eba863a1.png)   
   
>### 3-2-3.회원 정보 수정
* 프로필은 JAI 라이브러리 이용해 100*100 크기로 저장<br/>게시글에서 프로필 사진 출력 시 프로필 주소를 가지고 있어야 하는 불편함을 줄이기 위해 th_’회원 Id’ 의 이름으로 저장<br/>프로필 삭제 체크 후 수정하면 프로필 삭제함(DB에서 파일 경로와 실제 업로드 파일 모두 삭제)   
![image](https://user-images.githubusercontent.com/97975367/171116676-4da037cb-773c-4326-ab3e-6867ae3a8a36.png)   
<br/><br/><br/>


3-3. 사용 가능한 메뉴
-----------------------
사용 가능한 메뉴는 진하게 표시했음.
![image](https://user-images.githubusercontent.com/97975367/171117200-c4836198-7c21-4479-bb3d-8127f7547993.png)
<br/><br/>

>### 3-3-1. 무비 차트
* 메인 화면 호출 시마다 JSOUP 라이브러리 이용해 CGV에서 크롤링 해 온 영화 차트를 1~19위 까지 출력   
![image](https://user-images.githubusercontent.com/97975367/171118156-e2ace5ca-49df-49b3-b476-f071f3eca7b9.png)   
<br/><br/>

>### 3-3-2. 영화 리뷰 게시판
* 목록 / 이전글 / 다음글 이동 가능   
* 본인 글 수정 / 삭제 가능   
![image](https://user-images.githubusercontent.com/97975367/171122807-ed15d438-4d65-4bff-8f91-040961d2ce34.png)   
<br/>

* 댓글 작성하면 댓글 수 +1   
* 본인이 댓글 쓰면 글쓴이 표시 / 수정 삭제 가능   
![image](https://user-images.githubusercontent.com/97975367/171123050-bc191e53-83e7-46cb-ba3a-4176f403a4c9.png)   
<br/>

* 프로필 등록한 경우(=profile 폴더에 th_'회원 id' 파일 존재할 경우 프로필 사진만 출력   
* 프로필 없는 경우 (=profile 폴더에 th_'회원 id' 파일 존재하지 않는 경우) Onerror 이벤트 이용해 익명 아이콘(기본 none으로 설정해놨음) display를 block으로 설정해서 화면에 표시함 
* 댓글은 AJAX 이용해 댓글 달기/수정/삭제 가능함   
![image](https://user-images.githubusercontent.com/97975367/171123199-098c4d2c-6826-4e7f-84e6-999dc833813b.png)   
</br></br>
   
>### 3-3-3. 이벤트 게시판
* 카테고리 선택해서 이동 가능. 해당 카테고리 글만 출력되며 현재 위치 카테고리에 빨간 글씨로 표시됨<br/>
* JAI 라이브러리 이용해 업로드한 사진의 윗부분부터 가로*세로 1*0.664의 비율로 사진을 자른 후, 282*190 크기로 썸네일 출력시킴   
![image](https://user-images.githubusercontent.com/97975367/171123630-948dc54e-d966-4e7f-9086-e76032e80ff4.png)
<br/><br/>
   
>### 3-3-4. 영화 예매
* 영화, 극장, 날짜 모두 선택하면 시간 출력됨   
* 각 항목 클릭하면 active 클래스 추가됨. 선택한 항목의 값을 hidden 태그의 value에 넣어서 전달시킴   
![image](https://user-images.githubusercontent.com/97975367/171124144-0bb24ac9-be4c-48e4-88f1-5df90b3508ed.png)   
   
>### 3-3-5. 좌석 선택
* 가격은 일반은 100원 청소년은 70원으로 설정   
* 여기서 선택한 인원만큼 좌석 선택해야 결제 가능   
![image](https://user-images.githubusercontent.com/97975367/171124321-d9d2d3aa-3f1f-4df6-8852-de20b5393c3b.png)   
<br/><br/>

>### 3-3-6. 결제
* 아임포트 결제 API 이용. 카드 결제 후 구매자 정보(회원 정보)출력- AJAX로 회원 정보/주문번호 받아옴<br/>(출력되는 구매자/이메일 값: 회원 정보에서 받아온 값)<br/>(주문번호는 '날짜-예매 정보 테이블 num 6자리' 로 계산   ex. 20220530-000005)   
![image](https://user-images.githubusercontent.com/97975367/171124507-e052d85c-ecac-4d38-903d-3887f318499e.png)   
<br/><br/><br/>

>### 3-3-7. 주변 극장 찾기
* getCurrentPosition 이용해서 유저의 현재 위치 받아옴   
* 처음 지도의 중심은 위치 차단했을 경우엔 서면 롯데백화점이, 허용했을 경우엔 현재 유저의 위치가 뜬다.   
위치 허용했을 경우 지도 사진 ㄱ   
![image](https://user-images.githubusercontent.com/97975367/171124863-a13e4333-d84f-4c68-a232-3deb249acfa1.png)   
<br/><br/>
* (2021.10.13 기준)국내 영화관 시설 빅데이터 csv 파일에서 D3 라이브러리 이용해 CGV만 파싱<br/>-> 주소, 도로명 주소, 경도, 위도 추출해서 모두 지도에 마커로 표시 (183개)   
![image](https://user-images.githubusercontent.com/97975367/171124922-a5c92ca7-916b-49a5-9378-154e01c17330.png)   
<br/>

* 마우스를 올리면 해당 지점의 지점명, 주소, 도로명 주소를 오버레이로 띄움  

![image](https://user-images.githubusercontent.com/97975367/171125044-953d97b4-617e-45e9-8852-0c3f7aeb2a18.png)   

<br/>






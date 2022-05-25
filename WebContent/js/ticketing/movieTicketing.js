// 영화 클릭시
const movieList = document.querySelectorAll('.movie-container .movie__item > li');

movieList.forEach((movie) => {
    movie.addEventListener('click', function() {
        const movieActive = document.querySelectorAll('.movie-active');
        movieActive.forEach((list) => {
            list.classList.remove("movie-active");
        });
        movie.classList.add("movie-active");
        timePrint();
    });
});





// 도시 클릭시 해당 도시의 극장 리스트 출력
const theaterCityList = document.querySelectorAll('.theater-container .theater__item > div'); // 모든 도시 리스트

theaterCityList.forEach((city) => {
    city.addEventListener('click', function() {
        const theaterCityActive = document.querySelectorAll(".movie-city-active");
        theaterCityActive.forEach((list) => {
            list.classList.remove("movie-city-active");
            list.nextElementSibling.classList.add("hide");
        });
        city.classList.add("movie-city-active");
        city.nextElementSibling.classList.remove("hide");
    });
});
// 극장 클릭시
const theaterList = document.querySelectorAll('.theater-container .theater__item ul li');

theaterList.forEach((theater) => {
    theater.addEventListener('click', function() {
        const theaterActive = document.querySelectorAll('.movie-theater-active');
        theaterActive.forEach((list) => {
            list.classList.remove("movie-theater-active");
        });
        theater.classList.add("movie-theater-active");
        timePrint();
    });
});






// 날짜 출력하기 - 14일
const dateMonth = document.querySelector('.date-container .date__month');
const dateList = document.querySelector(".date__list");
const dateNextMonth = document.querySelector('.date-container .date__nextMonth');
const dateNextList = document.querySelector(".date__nextList");


const date = new Date();
const weekOfDay = ["일", "월", "화", "수", "목", "금", "토"]
const year = date.getFullYear(); // 올해 년도
const month = date.getMonth()+1; // 이번 달

dateMonth.innerHTML = year+"/"+month; // 연도/이번 달 출력
const lastDay = new Date(year, month, 0); // 이번 달 마지막 날짜 계산


for (i = date.getDate(); i < date.getDate() + 14; i++) { // 오늘부터 14일간
    if(i > lastDay.getDate()) { // 이번달 마지막날 넘으면 다음달 날짜 계산
        calcNextMonth();
        break;
    };
    const listDay = document.createElement("li"); // 요일 + 날짜 담을 요소
    const spanWeekOfDay = document.createElement("span"); // 요일 담을 요소
    const spanDay = document.createElement("span"); // 날짜 담을 요소

    //class넣기
    spanWeekOfDay.classList = "week-of-day";
    spanDay.classList = "date";

    const dayOfWeek = weekOfDay[new Date(year + "-" + month + "-" + i).getDay()]; // 해당 요일 계산

    //요일 넣기 - 토 / 일은 색상 표시
    if (dayOfWeek == "토") {
        spanWeekOfDay.classList.add("saturday");
        spanDay.classList.add("saturday");
    } else if (dayOfWeek == "일") {
        spanWeekOfDay.classList.add("sunday");
        spanDay.classList.add("sunday");
    }
    spanWeekOfDay.innerHTML = dayOfWeek; // 요일 넣기
    listDay.append(spanWeekOfDay);
    
    spanDay.innerHTML = i; //날짜 넣기
    listDay.append(spanDay);

    dateList.append(listDay);

    dayClickEvent(listDay);
} // for-이번달

// 다음달 날짜 계산
function calcNextMonth() {
    if(month == 12) { // 이번달 12월이면 년도 + 1
        dateNextMonth.innerHTML = (year+1) + " / " + 1; // 년도/월 출력
    } else {
        dateNextMonth.innerHTML = year + "/" + (month+1); // 년도/월 출력
    }

    for(let i=1; i<=14-(lastDay.getDate() - date.getDate());i++) {
        const listDay = document.createElement("li"); // 요일 + 날짜
        const spanWeekOfDay = document.createElement("span"); // 요일 담을 요소
        const spanDay = document.createElement("span"); // 날짜 담을 요소

        //class넣기
        spanWeekOfDay.classList = "week-of-day";
        spanDay.classList = "date";
        
        const dayOfWeek = weekOfDay[new Date(year + "-" + (month+1) + "-" + i).getDay()]; // 해당 요일 계산

        //요일 넣기 - 토 / 일은 색상 표시
        if (dayOfWeek == "토") {
            spanWeekOfDay.classList.add("saturday");
            spanDay.classList.add("saturday");
        } else if (dayOfWeek == "일") {
            spanWeekOfDay.classList.add("sunday");
            spanDay.classList.add("sunday");
        }
        spanWeekOfDay.innerHTML = dayOfWeek; // 요일 넣기
        listDay.append(spanWeekOfDay);
        
        spanDay.innerHTML = i; //날짜 넣기
        listDay.append(spanDay);

        dateNextList.append(listDay);

        dayClickEvent(listDay);
    } // for-다음달
}


function dayClickEvent(listDay) {
    listDay.addEventListener("click", function() {
        const movieDateActive = document.querySelectorAll(".movie-date-active");
        movieDateActive.forEach((list) => {
            list.classList.remove("movie-date-active");
        });
        listDay.classList.add("movie-date-active");
        timePrint();
    });
};





// 시간
const timeTable = document.querySelector('.time-container .item-wrapper');
// 영화/극장/날짜 선택해야 시간 출력
function timePrint() {
    const movieActive = document.querySelector('.movie-active'); // 선택한 영화
    const theaterActive = document.querySelector('.movie-theater-active'); // 선택한 극장
    const movieDateActive = document.querySelector(".movie-date-active"); // 선택한 날짜
    if(movieActive == null || theaterActive == null || movieDateActive == null) { 
        timeTable.classList.add("hide");
    } else {
        timeTable.classList.remove("hide");
    }
}

const timeList = document.querySelectorAll('.time-container .time__item .time');

timeList.forEach((time) => {
    time.addEventListener('click', function() {
        const timeActive = document.querySelectorAll('.movie-time-active');
        timeActive.forEach((list) => {
            list.classList.remove("movie-time-active");
        });
        time.classList.add("movie-time-active");
    });
});





// 좌석선택 클릭

const fr = document.fr;

fr.addEventListener('submit', function(event){
	event.preventDefault();
	
	const movieActive = document.querySelector('.movie-active');
	const theaterActive = document.querySelector('.movie-theater-active');
	const dateActive = document.querySelector('.movie-date-active');
	const timeActive = document.querySelector('.movie-time-active');
	
	if(movieActive == null || theaterActive == null || dateActive == null || timeActive == null) {
		alert('항목을 선택하세요.');
		return;
	}
	
	const movieSelected = document.createElement("input"); 
	const theaterSelected = document.createElement("input"); 
	const dateSelected = document.createElement("input"); 
	const timeSelected = document.createElement("input"); 
	
	movieSelected.type ="hidden";
	movieSelected.name ="movie";
	movieSelected.value = movieActive.innerText;
	
	theaterSelected.type = "hidden";
	theaterSelected.name = "theater";
	theaterSelected.value = theaterActive.innerText;
	
	dateSelected.type = "hidden";
	dateSelected.name = "date";
	dateSelected.value = dateActive.innerText;
	
	timeSelected.type = "hidden";
	timeSelected.name = "time";
	timeSelected.value = timeActive.innerText;
	
	fr.append(movieSelected);
	fr.append(theaterSelected);
	fr.append(timeSelected);
	fr.append(dateSelected);
	
	fr.submit();
	
});
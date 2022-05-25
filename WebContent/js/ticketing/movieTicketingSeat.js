// 선택한 인원 수 계산
const adultCount = document.querySelectorAll('.adult__count li');
const teenagerCount = document.querySelectorAll('.teenager__count li');

// 일반에서 인원 선택했을 때
adultCount.forEach((adult) => {
    adult.addEventListener('click', function() {
        const adultCountActive = document.querySelectorAll('.adult-count-active');
        adultCountActive.forEach((active) => {
            active.classList.remove('adult-count-active');
        });
        adult.classList.add('adult-count-active');
        calcSelectSeat(); // 선택 가능한 좌석 수 계산
    });
});
// 청소년에서 인원 선택했을 때
teenagerCount.forEach((teenager) => {
    teenager.addEventListener('click', function() {
        const teenagerCountActive = document.querySelectorAll('.teenager-count-active');
        teenagerCountActive.forEach((active) => {
            active.classList.remove('teenager-count-active');
        });
        teenager.classList.add('teenager-count-active');
        calcSelectSeat(); // 선택 가능한 좌석 수 계산
    });
});

let selectSeat = 0;
// 선택 가능한 좌석 수 계산하기
function calcSelectSeat() {
    let adultCount = 0;
    let teenagerCount = 0;

    let adult = document.querySelector('.adult-count-active');
    let teenager = document.querySelector('.teenager-count-active');
    if(adult != null) {
        adultCount = Number(adult.innerText);
    }
    if(teenager != null) {
        teenagerCount = Number(teenager.innerText);
    }
    selectSeat = adultCount + teenagerCount;
}






// 좌석 뿌리기
const seatContainer = document.querySelector('.select-seat .seat-container');

let test = [];
let selectedSeats =[]; // 선택되어있는 '좌석번호' 담음
let clicked = []; // 선택되어있는 좌석 '태그' 담음
let div = "";

for (let i = 0; i < 7; i++) {
    div = document.createElement("div");
    seatContainer.append(div);
    for (let j = 0; j < 10; j++) {
        const input = document.createElement('input'); // 좌석이 될 요소
        input.type = "button";
        input.name = "seats"
        input.classList = "seat";
        //3중포문을 사용하지 않기위해 
        mapping(input, i, j); // 좌석 요소에 A0, A1. .. B0, B1 ... 값 넣어준
        div.append(input);
        input.addEventListener('click', function(e) {
            console.log(e.target.value);
            //중복방지 함수 - selectedSeats 비움 -> 클릭 일어날 때마다 clicked 클래스 가진 요소들 모두 담을 것
            selectedSeats = selectedSeats.filter((element, index) => selectedSeats.indexOf(element) != index);

            //click class가 존재할때(제거해주는 toggle)
            if (input.classList.contains("clicked")) {
                input.classList.remove("clicked");
                clicked = document.querySelectorAll(".clicked"); // clicked에 선택되어있는 '요소' 담음
                clicked.forEach((data) => {
                    selectedSeats.push(data.value); // selectedSeats에 선택되어있는 요소 '좌석번호' 담음
                });
            } else { //click class가 존재하지 않을때 (추가해주는 toggle)
                console.log(clicked.length);
                if(clicked.length >= selectSeat) { // 선택가능한 좌석 수 초과하면 선택 불가능
                    alert('선택 가능한 좌석수를 초과했습니다.');
                    return;
                }
                input.classList.add("clicked");
                clicked = document.querySelectorAll(".clicked"); // clicked에 선택되어있는 '요소' 담음
                clicked.forEach((data) => {
                    selectedSeats.push(data.value); // selectedSeats에 선택되어있는 요소 '좌석번호' 담음
                })
            } // if
            console.log(selectedSeats);

        }); // addEventListener
    } // for
} // for

function mapping(input, i, j) {
    if (i === 0) {
        input.value = "A" + j;
    } else if (i === 1) {
        input.value = "B" + j;
    } else if (i === 2) {
        input.value = "C" + j;
    } else if (i === 3) {
        input.value = "D" + j;
    } else if (i === 4) {
        input.value = "E" + j;
    } else if (i === 5) {
        input.value = "F" + j;
    } else if (i === 6) {
        input.value = "G" + j;
    }
}
// 좌석 뿌리기
const seatContainer = document.querySelector('.select-seat .seat-container');

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

            //click class가 존재할때(제거해주는 toggle)
            if (input.classList.contains("clicked")) {
                //중복방지 함수 - selectedSeats 비움 -> 클릭 일어날 때마다 clicked 클래스 가진 요소들 모두 담을 것
                selectedSeats = selectedSeats.filter((element, index) => selectedSeats.indexOf(element) != index);

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
                //중복방지 함수 - selectedSeats 비움 -> 클릭 일어날 때마다 clicked 클래스 가진 요소들 모두 담을 것
                selectedSeats = selectedSeats.filter((element, index) => selectedSeats.indexOf(element) != index);

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



// 인원 수 만큼 좌석 선택해야 결제창으로 이동
const payment = document.querySelector('.payment');
payment.addEventListener('click', function() {
	if(clicked.length != selectSeat) {
		alert('인원 수 만큼 좌석을 선택해 주세요.');
		return false;
	}
	requestPay();
});





// 결제하기 클릭 - 아임포트 API
var IMP = window.IMP; 
IMP.init('imp72265744'); // 가맹점 식별코드

// 주문번호 계산 함수
function calcUID() {
	let order_num = "";
	$.ajax({
		url: "./ticketing/calcUIDAjax.jsp",
		async: false, // 동기식으로 사용 -> 변수에 값 저장하기 위해
		success: function(data) {
			order_num = data.trim();
		},
	}); // ajax
	return order_num;
}; // calcUID

// 결제금액 계산 함수
function calcPrice() {
	const adultCntActive = document.querySelector('.adult-count-active');
	const teenagerCntActive = document.querySelector('.teenager-count-active');
	
	let adultCnt = 0;
	let teenagerCnt = 0;
	let totalPrice = 0;
	
	if(adultCntActive != null) {
		adultCnt = adultCntActive.innerText;
	}
	if(teenagerCntActive != null) {
		teenagerCnt = teenagerCntActive.innerText;
	}
	
	totalPrice = 100*adultCnt + 70*teenagerCnt;
	return totalPrice;
}; // calcPrice()


function requestPay() { // 결제요청
	const order_num = calcUID();   	
	const totalPrice = calcPrice();
	let seat =""; // 좌석 전달하기 위해 배열->문자로 변환
	for(let i=0; i<selectedSeats.length; i++) {
		seat+= selectedSeats[i]+",";
	};
	
	let buyer_email = "" ;
	let buyer_name = "";
	let buyer_tel = "";
	let buyer_addr = "";
	let buyer_postcode = "";
	
	
	$.getJSON({ // 회원정보 조회
		url: "./ticketing/getMemberInfo.jsp",
		async: false, // ajax 동기식으로 사용 -> 전역변수에 값 저장하기 위해
		success: function(data) {
			buyer_email = data.email;
			buyer_name = data.name;
			buyer_tel = data.tel;
			buyer_addr = data.addr;
			buyer_postcode = data.postcode;
		},
	}); // ajax
	
	
    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
        pg: "html5_inicis",
        pay_method: "card",
        merchant_uid: order_num,
        name: "영화 예매",
        amount: totalPrice,
        buyer_email: buyer_email,
        buyer_name: buyer_name,
        buyer_tel: buyer_tel,
        buyer_addr: buyer_addr,
        buyer_postcode: buyer_postcode,
    }, function (rsp) { // callback
   	   if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
   	        $.ajax({
   	            url: "./ticketing/payMentCompleteAjax.jsp", // 예: https://www.myservice.com/payments/complete
   	            method: "POST",
   	            data: {
   	                imp_uid: rsp.imp_uid,
   	                merchant_uid: rsp.merchant_uid,
   	                totalPrice: totalPrice,
   	                movieTitle: movieTitle,
   	                movieTheater: movieTheater,
   	                movieDateTime: movieDateTime,
   	                movieSeat: seat
   	            }
   	        }); // ajax
   	        alert("결제를 완료했습니다.");
   	        location.href='./MyTicket.ti';
       } else {
         alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
       } // if
    });
} // requestPay

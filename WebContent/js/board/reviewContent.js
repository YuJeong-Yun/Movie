const reply = document.fr.reply; // 댓글 입력하는 textarea
const lengthCalc = document.querySelector('.lengthCalc'); // 입력 가능한 남은 수 출력하는 태그

// 댓글 작성 가능 남은수 출력하는 함수
function calcInputLength() {
	lengthCalc.textContent = 1000-reply.value.length; // 댓글 남은 수 = 1000-입력수

	if(reply.value.length>1000) { // 1000자 넘어서면 알림 띄우고 남은 수는 0으로 출력
		alert("1000자 까지만 작성 가능합니다.");
		lengthCalc.textContent = 0;
	}
}


// 댓글 내용 유무 확인하는 함수
function check() {
	if(reply.value=="") {
		alert("내용을 작성하세요.")
		return false;
	}
}
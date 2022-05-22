
// 댓글 작성 가능 남은수 출력하는 함수
function calcInputLength(event) {
	const e = event.target;
	e.previousElementSibling.textContent = 1000-e.value.length; // 댓글 남은 수 = 1000-입력수

	if(e.value.length>1000) { // 1000자 넘어서면 알림 띄우고 남은 수는 0으로 출력
		alert("1000자 까지만 작성 가능합니다.");
		e.previousElementSibling.textContent = 0;
	}
}


const fr = document.fr;
const telDbResult= fr.querySelector('.telDbResult');
const emailDbResult = fr.querySelector('.emailDbResult');
const regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/; // 전화번호 유효성 체크용

const nowTel = fr.tel.value;
const nowEmail = fr.email.value;

let telArr = [];
let emailArr = [];

// tel, email DB에서 가져옴
$(function(){
	$.getJSON({
		url: "./member/dbJSON.jsp",
		success: function(data){
			$.each(data, function(index, item){
				telArr.push(item.tel);
				emailArr.push(item.email);
			});
		}
	});
}); // JQuery



// 폼 submit될 때 체크
function formCheck() {
	if(fr.pw.value == "") {
		alert("수정하려면 비밀번호를 입력하세요.");
		fr.pw.focus();
		
		return false;
		
	}else if(!fr.gender[0].checked && !fr.gender[1].checked) {
		alert("성별을 선택하세요.");
		
		return false;
		
	}else if(fr.addr.value == "") {
		alert("주소를 입력하세요.");
		fr.addr.focus();
		
		return false;
		
	}else if(fr.tel.value == "") {
		alert("전화번호를 입력하세요.");
		fr.tel.focus();
		
		return false;
		
	}else if(fr.email.value == "") {
		alert("이메일을 입력하세요.");
		fr.email.focus();
		
		return false;
		
	}else if(telDbResult.textContent!="") {
		alert("전화번호 중복을 확인해주세요.")
		fr.tel.focus();
		
		return false;
	}else if(emailDbResult.textContent!="") {
		alert("이메일 중복을 확인해주세요.")
		fr.email.focus();
		
		return false;
		
	}else if(!regExp.test(fr.tel.value)) {
		alert("전화번호를 확인해주세요.");
		fr.tel.focus();
		
		return false;
	}else {
		alert("수정이 완료되었습니다.");
		
		return true;
	}
}



// tel, email 중복 검사
function telDbCheck() {
	
	if(telArr.indexOf(fr.tel.value) != -1 && fr.tel.value!=nowTel) {
		telDbResult.textContent = "중복된 전화번호 입니다.";
	}else {
		telDbResult.textContent = "";
	}
	
	if(fr.tel.value.length == 3) {
		fr.tel.value = fr.tel.value+"-";
	}
	if(fr.tel.value.length == 8) {
		fr.tel.value = fr.tel.value+"-";
	}
}
function emailDbCheck() {
	if(emailArr.indexOf(fr.email.value) != -1 && fr.email.value!=nowEmail) {
		emailDbResult.textContent = "중복된 이메일 입니다.";
	}else {
		emailDbResult.textContent = "";
	}
	
}


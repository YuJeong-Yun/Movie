/**
 * 
 */
const fr = document.fr;
const idDbResult = fr.querySelector('.idDbResult');
const telDbResult= fr.querySelector('.telDbResult');
const emailDbResult = fr.querySelector('.emailDbResult');
const regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/; // 전화번호 유효성 체크용

let idArr = [];
let telArr = [];
let emailArr = [];

// id, tel, email DB에서 가져옴
$(function(){
	$.getJSON({
		url: "./member/dbJSON.jsp",
		success: function(data){
			$.each(data, function(index, item){
				idArr.push(item.id);
				telArr.push(item.tel);
				emailArr.push(item.email);
			});
		}
	});
}); // JQuery



// 폼 submit될 때 체크
function formCheck() {
	if(fr.id.value == "") {
		alert("아이디를 입력하세요.");
		fr.id.focus();
		
		return false;
	}else if(fr.pw.value == "") {
		alert("비밀번호를 입력하세요.");
		fr.pw.focus();
		
		return false;
		
	}else if(fr.name.value == "") {
		alert("이름을 입력하세요.");
		fr.name.focus();
		
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
	}else if(fr.pw.value != fr.pw2.value) {
		alert("비밀번호 확인을 제대로 입력해주세요.");
		fr.pw2.focus();
		
		return false;
	}else if(idDbResult.textContent!="") {
		alert("아이디 중복을 확인해주세요.")
		fr.id.focus();
		
		return false;
	}else if(telDbResult.textContent!="") {
		alert("전화번호 중복을 확인해주세요.")
		fr.tel.focus();
		
		return false;
	}else if(emailDbResult.textContent!="") {
		alert("이메일 중복을 확인해주세요.")
		fr.email.focus();
		
		return false;
	}else if(fr.id.value.indexOf(" ")!=-1) {
		alert("아이디에 공백을 제거해주세요.")
		fr.id.focus();
		
		return false;
	}else if(!regExp.test(fr.tel.value)) {
		alert("전화번호를 확인해주세요.");
		fr.tel.focus();
		
		return false;
	}
}




// id, tel, email 중복 검사
function idDbCheck() {
	if(idArr.indexOf(fr.id.value) != -1) {
		idDbResult.textContent = "중복된 아이디 입니다.";
	}else {
		idDbResult.textContent = "";
	}
}
function telDbCheck() {
	if(telArr.indexOf(fr.tel.value) != -1) {
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
	if(emailArr.indexOf(fr.email.value) != -1) {
		emailDbResult.textContent = "중복된 이메일 입니다.";
	}else {
		emailDbResult.textContent = "";
	}
	
}


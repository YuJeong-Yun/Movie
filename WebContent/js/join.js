/**
 * 
 */
 const fr = document.fr;
 
 // 아이디 중복검사 버튼 클릭시 이벤트
function idDbCheck() {
 	const id = fr.id.value;
 	
	if(id=="") {
		alert("아이디를 입력하세요.");
	}else {
		window.open("./JoinCheckAction.me?id="+id, "", "width=400,height=150");
	}
}


// 아이디 수정하면 중복검사 다시 해야함
function showIdDbCheck() {
	fr.idCheck.disabled = false;
	fr.idDuplication.value = "idUncheck";
}


function formCheck() {
	if(fr.id.value == "") {
		alert("아이디를 입력하세요.");
		fr.id.focus();
		
		return false;
		
	}else if(fr.idDuplication.value != "idCheck") {
		alert("아이디 중복체크를 해주세요.");
		
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
	}
	else {
		alert("회원가입이 완료되었습니다.");
		
		return true;
	}
}



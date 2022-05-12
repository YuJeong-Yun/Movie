const fr = document.fr;

function check() {
	if(fr.pwNow.value=="") {
		alert("변경하려면 비밀번호를 입력하세요.")
		fr.pwNow.focus();
		
		return false;
	}else if(fr.pwNew.value=="") {
		alert("변경할 비밀번호를 입력하세요.")
		fr.pwNew.focus();
		
		return false;
	}else if(fr.pwNew.value=="") {
		alert("변경할 비밀번호를 입력하세요.")
		fr.pwNew2.focus();
		
		return false;
	}else if(fr.pwNew.value != fr.pwNew2.value) {
		alert("비밀번호 확인의 값이 다릅니다.");
		fr.pwNew2.focus();
		
		return false;
	}
}
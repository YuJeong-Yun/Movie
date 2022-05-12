const fr = document.fr;

function check() {
	if(fr.pw.value=="") {
		alert("수정하려면 비밀번호를 입력하세요.")
		fr.pw.focus();
		
		return false;
	}
}

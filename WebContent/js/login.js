function loginCheck() {
	if(document.loginForm.id.value=="") {
		alert("아이디를 입력해주세요.");
		document.loginForm.id.focus();
		
		return false;
	}else if(document.loginForm.pw.value=="") {
		alert("비밀번호를 입력해주세요.");
		document.loginForm.pw.focus();
		
		return false;
	}
}
  

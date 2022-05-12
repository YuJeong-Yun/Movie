const fr = document.loginForm;

function getCookie(Name) { 
    let search = Name + "=";
    if (document.cookie.length > 0) { // 쿠키 존재 여부
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 찾는 쿠키 존재하면
            offset += search.length; //  set index of beginning of value
            end = document.cookie.indexOf(";", offset); // 쿠키값 마지막 위치 인덱스 값 설정
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
}

// 쿠키에 저장된 id 정보 가져오기
window.onload = function() {
    if (getCookie("id")) { // id라는 이름의 쿠키를 불러와서 있을경우
        fr.id.value = getCookie("id"); // id칸에 쿠키에 저장된 id값 가져옴
        fr.saveId.checked = true; // 체크는 체크됨으로
    }
}


// 아이디 정보 쿠키로 저장
function setIdCookie(name, value, days) {
	  var expDate = new Date(); // 현 시스템 시간정보
	  expDate.setDate(expDate.getDate()+days); // 유효기간
	  
	  document.cookie = name + "=" + escape(value) + "; path=/; expires="
      + expDate.toGMTString() + ";"
}


function loginCheck() {
	if(fr.saveId.checked==true){
		setIdCookie("id", fr.id.value, 7);
	}else {
		setIdCookie("id", fr.id.value, 0);
	}
	
	if(fr.id.value=="") {
		alert("아이디를 입력해주세요.");
		fr.id.focus();
		
		return false;
	}else if(fr.pw.value=="") {
		alert("비밀번호를 입력해주세요.");
		fr.pw.focus();
		
		return false;
	}
	
}

 const fr = document.fr;
 
 function check() {
 	if(fr.subject.value == "") {
 		alert('제목을 입력해주세요.');
 		fr.subject.focus();
 		
 		return false;
 		
 	}else if(fr.content.value == "" ) {
 		alert('내용을 입력해주세요.');
 		fr.content.focus();
 		
 		return false;
 	}
 }
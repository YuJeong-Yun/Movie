 const fr = document.fr;
 const imageVal = document.fr.image.value.right(4);
 
 function check() {
	if(fr.category.value=="") {
		alert('카테고리를 선택해 주세요.');
		fr.category.focus();
		
		return false;
	}else if(fr.subject.value == "") {
 		alert('제목을 입력해 주세요.');
 		fr.subject.focus();
 		
 		return false;
	}else if(fr.image.value == "") {
		alert('이미지를 업로드해 주세요.');
		
		return false;
	}
 }
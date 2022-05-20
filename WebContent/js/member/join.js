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

// id, tel, email DB에서 가져옴 - 중복 검사용
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


//id, tel, email 중복 검사
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
		
	}else if(fr.postcode.value == "") {
		alert("주소를 입력하세요.");
		fr.postcode.focus();
		
		return false;
		
	}else if(fr.address.value == "") {
		alert("주소를 입력하세요.");
		fr.address.focus();
		
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


// 카카오 주소 API 이용
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}




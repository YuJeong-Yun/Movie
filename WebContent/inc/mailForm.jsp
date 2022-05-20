<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
	<title>메일 보내기</title>
	<style type="text/css">
		body {
			background-color: #f4f4f4;
		}
		form {
			width: 450px;
			margin: 20px auto;
		}
		h2 {
			color: #4B89DC;
		}
		input,
		textarea {
			width: 100%;
			height: 35px;
			margin-bottom: 15px;
			border: 1px solid #ddd;
			border-radius: 5px;
			outline: none;
		}
		input.submitBtn {
			background-color: #4B89DC;
			width: 70px;
			height: 30px;
			border: none;
			color: #fff;
			cursor: pointer;
		}
		input.submitBtn:hover {
			background-color: #2967BA;
		}
		textarea {
			height: 200px;
			resize: none;
		}
		div {
			margin-bottom: 3px;
			font-size: 14px;
			color: #999;
			font-weight: 700;
			
		}
	</style>
</head>

<body>
	<form action="./MailSend.ml" method="post" name="fr" onsubmit="return check();">
		<h2>CONTACT!</h2>
		
			<div>FROM</div>
			<input type="email" name="sender" placeholder="보내는 메일 주소"><br>
			
			<div>TO EMAIL</div>
			<input type="email" name="receiver" placeholder="받는 메일 주소"><br>
			
			<div>SUBJECT</div>
			<input type="text" name="subject" maxlength="100"><br>
			
			<div>CONTENT</div>
			<textarea name="content" maxlength="1000"></textarea><br>
			
			<input type="submit" value="전송" class="submitBtn">
	</form>
	
	<script type="text/javascript">
		// 필수 입력 항목 체크
		function check() {
			if(document.fr.sender.value=="") {
				alert('보내는 메일 주소를 입력해주세요.');
				document.fr.sender.focus();
				
				return false;
			}else if(document.fr.receiver.value=="") {
				alert('받는 메일 주소를 입력해주세요.');
				document.fr.sender.focus();
				
				return false;
			}else if(document.fr.subject.value=="") {
				alert('제목을 입력해주세요.');
				document.fr.subject.focus();
				
				return false;
			}else if(document.fr.content.value=="") {
				alert('내용을 입력해주세요.');
				document.fr.content.focus();
				
				return false;
			}
		}
	</script>
</body>
</html>
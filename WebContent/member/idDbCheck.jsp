<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID Check</title>
<script type="text/javascript">
	function onCheck() {
		if(document.idDbCheckFr.chResult.value=="N") { // 중복된 아이디
			window.close();
		
		}else { // 가능한 아이디
			opener.document.fr.idDuplication.value="idCheck";
			opener.document.fr.idCheck.disabled = true;
			window.close();			
		}
	}
</script>
</head>
<body style="background-color:#ddd;">

	<form name="idDbCheckFr">
		<c:choose>
		<c:when test="${sessionScope.result==1 }">
			<h3 style="color:#ff0000;">이미 존재하는 아이디 입니다.</h3>
			<input type="hidden" name="chResult" value="N">
		</c:when>
		
		<c:when test="${sessionScope.result==-1 }">
			<h3>사용 가능한 아이디 입니다.</h3>
			<input type="hidden" name="chResult" value="Y">
		</c:when>
		
		<c:otherwise>
			<h3>오류 발생</h3>
		</c:otherwise>
		</c:choose>
	</form>
	
	<input type="button" name="chResult" value="확인" onclick="onCheck();">
</body>
</html>


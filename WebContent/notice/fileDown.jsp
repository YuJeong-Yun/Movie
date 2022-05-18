<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<%
		// ------ 다운로드할 파일 경로 계산
		// 다운로드할 파일명
		String fileName = request.getParameter("fileName");
	
		// 파일을 업로드한 곳의 주소
		String savePath = "file";
	
		// 업로드된 폴더 접근
		ServletContext ctx = getServletContext();
		String downLoadPath = ctx.getRealPath(savePath);
	
		System.out.println(downLoadPath);
	
		// 내가 다운로드할 파일의 전체 경로
		String downFilepath = downLoadPath + "\\" + fileName;
	
		System.out.println("다운로드 파일 전체 경로 : "+downFilepath);
	
	
		//////////////////////////////////////////////////////////////////////
		
		// 파일의 데이터를 담아서 저장하는 배열
		byte[] b = new byte[4096]; // 4KB
		
		// 파일 입력스트림 객체
		FileInputStream fis = new FileInputStream(downFilepath);
		
	    // 다운로드할 파일의 MIME타입을 확인
		String MimeType = ctx.getMimeType(downFilepath);
		System.out.println("MimeType : "+MimeType);
		
		// MIME 타입이 없을 경우
		if(MimeType == null) {
			// 이진 파일의 기본값 (알려지지 않은 파일의 형태)
			MimeType = "application/octect-stream";
		}
		
		// 응답할 데이터의 MIME 타입으로 JSP페이지 내용의 형태를 변경
		response.setContentType(MimeType);
		
		/////////////////////////////////////////////////////////
		// 브라우저별 인코딩 /ie/

		String agent = request.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf("MISE") > -1 || agent.indexOf("Trident") > -1);

		if(ieBrowser) {
			// ie : 다운로드시 한글 깨짐, 공백문자 표시 +
			
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			
		}else {
			// 그 외 나머지 브라우저 : 인코딩처리 (한글처리)
			
			fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		}
	
		///////////////////////////////////////////////////////////////////
		
		// 모든 파일이 다운로드 형태로 실행되도록 설정 (파싱이되는 파일이라도 다운로드됨)
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		
		out.clear();
		out = pageContext.pushBody();
		
		// 출력 스트림 객체
		ServletOutputStream out2 = response.getOutputStream();
		
		int data = 0;
		
		// 파일의 배열을 크기만큼씩 읽어보기 / 파일이 끝날때까지
		while( (data = fis.read(b,0,b.length)) != -1 ) {
			out2.write(b,0,data);
			
		}
		// 배열에 공백을 채우기	
		out2.flush();
		out2.close();
		fis.close();
	%>

</body>
</html>
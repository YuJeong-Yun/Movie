package com.movie.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.notice.db.NoticeBoardDAO;
import com.movie.notice.db.NoticeBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : NoticeUpdateProAction_execute() 호출");

		// 전달정보 저장
		String pageNum = request.getParameter("pageNum");
		
		// 1) 파일 업로드
		// 		- 가상의 업로드 폴더 설정 : file 폴더
		String path = request.getRealPath("/file");
		System.out.println(" M path : " +path);
		//		- 업로드 파일의 크기 설정(제한) - 10MB
		int maxSize = 10 * 1024 * 1024; 
		//		- MultipartRequest 객체 생성(업로드)
		MultipartRequest  multi 
				= new MultipartRequest(
						request,	// request 내장객체 (파라메터, 파일정보)
						path,		// 업로드 위치(가상경로)
						maxSize,		// 업로드 파일의 크기 
						"UTF-8",		// 업로드시 인코딩처리
						new DefaultFileRenamePolicy()		// 중복된 파일이름 업로드시 처리 객체 -> 중복된 이름 넣으면 이름 뒤에 (1) 이런식으로 붙임
						);
		
		System.out.println(" M : 파일 업로드 완료! ");
		
		
		// 2) DB저장
		// 전달정보를 저장 (DTO) - subject, content, file, num
		int num = Integer.parseInt(multi.getParameter("num"));
		
		NoticeBoardDTO dto = new NoticeBoardDTO();
		dto.setNum(num);
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setFile(multi.getFilesystemName("file")); // 서버에 업로드된 파일명
//		dto.setFile(multi.getOriginalFileName("file")); // 실제 파일명
		

		
		System.out.println(" M  dto : " + dto);
		
		// DAO 객체 생성 - 글 수정 메서드 호출 updateBoard()
		NoticeBoardDAO dao = new NoticeBoardDAO();
		// 처리결과 저장 (-1, 0, 1)
		int result = dao.updateBoard(dto);
		
		
		
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		
		if(result==-1) { // 글정보 없음
			out.print("<script>");
			out.print("alert('글정보가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
			
			out.flush();
			out.close();
			
		}else { // result == 1
			out.print("<script>");
			out.print("alert('글 수정을 완료했습니다.');");
			out.print("location.href='./NoticeContent.no?num="+num+"&pageNum="+pageNum+"';");
			out.print("</script>");
			
			out.flush();
			out.close();
			
		}
		return null;
	}

}

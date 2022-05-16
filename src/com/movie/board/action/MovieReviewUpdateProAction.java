package com.movie.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.board.db.MovieBoardDAO;
import com.movie.board.db.MovieBoardDTO;

public class MovieReviewUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MovieReivewUpdateProAction_execute() 호출");
		
		// 전달된 정보 저장 (수정할 데이터 subject, content)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		
		MovieBoardDTO dto = new MovieBoardDTO();
		dto.setId(id);
		dto.setNum(num);
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		System.out.println(" M : " + dto);
		System.out.println(" M : pageNum : "+pageNum);
		
		
		// DAO 객체 생성 - 글 수정 메서드 호출 updateBoard()
		MovieBoardDAO dao = new MovieBoardDAO();
		// 처리결과 저장 (-1, 0, 1)
		int result = dao.updateBoard(dto);
		
		// 처리 결과에 따른 페이지 이동(js)
		// 처리 응답 결과는 html 형태로 보여주겠다. (MIME 타입)
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		
		if(result==0) { // 아이디 다름
			out.print("<script>alert('본인만 수정할 수 있습니다.'); history.back(); </script>");
			out.flush();
			// 응답처리하던 연결통로를 제거 (자원해제)
			out.close();
			
			return null;
			
		}else if(result==-1) { // 글정보 없음
			out.print("<script>alert('글정보가 없습니다.'); history.back(); </script>");
			out.flush();
			out.close();
			
			return null;
		}else { // result == 1
			out.print("<script>alert('글 수정을 완료했습니다.'); location.href='./MovieReviewContent.bo?num="+num+"&pageNum="+pageNum+"'; </script>");
			out.flush();
			out.close();
			
			return null;
		}
	}

}

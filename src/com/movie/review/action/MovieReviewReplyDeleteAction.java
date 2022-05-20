package com.movie.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.review.db.MovieBoardDAO;
import com.movie.review.db.MovieBoardReplyDTO;

public class MovieReviewReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MovieReviewReplyDeleteAction_exeucte() 호출 ");
		
		// 전달 정보 저장
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		int re_num = Integer.parseInt(request.getParameter("re_num"));
		
		// DTO에 저장
		MovieBoardReplyDTO dto = new MovieBoardReplyDTO();
		dto.setId(id);
		dto.setRe_ref(num);
		dto.setRe_num(re_num);
		
		// DAO 객체 생성
		MovieBoardDAO dao = new MovieBoardDAO();
		int result = dao.reDeleteBoard(dto);
		
		// 삭제 처리 결과에 따른 페이지 이동(js)
		// => Action 페이지에서 js 이동시에는 컨트롤러를 통한 페이지 이동 X
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		System.out.println("re : "+result);
		
		if(result==0) { // 아이디 다름
			out.print("<script>");
			out.print("alert('본인만 삭제할 수 있습니다.');");
			out.print("history.back();");
			out.print("</script>");
			
			out.flush();
			// 응답처리하던 연결통로를 제거 (자원해제)
			out.close();
			
		}else if(result==-1) {
			out.print("<script>");
			out.print("alert('댓글이 존재하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
			
			out.flush();
			out.close();
			
		}else { // result == 1
			out.print("<script>");
			out.print("alert('댓글 삭제가 완료되었습니다.');");
			out.print("location.href='./MovieReviewContent.bo?num="+num+"&pageNum="+pageNum+"';");
			out.print("</script>");
			
			out.flush();
			out.close();
		}
		return null;
	}

}

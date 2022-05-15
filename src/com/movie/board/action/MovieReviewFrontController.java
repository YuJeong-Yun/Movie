package com.movie.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MovieReviewFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doPROCESS() 호출");
		
		//////////////////////////////1. 가상 주소 계산 /////////////////////////////////
		System.out.println(" C :1. 가상 주소 계산 시작");
		
		// 가상주소 가져오기
		String requestURI =	request.getRequestURI();
		// 프로젝트명  가져오기
		String ctxPath = request.getContextPath();
		// 가상주소 계산 (가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - " + command);
		
		
		System.out.println(" C : 1. 가상 주소 계산 끝\n");
		//////////////////////////////1. 가상 주소 계산 /////////////////////////////////
		//////////////////////////////2. 가상 주소 매핑 /////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/MovieReview.bo")) { // 영화 리뷰 게시판 이동
			System.out.println(" C : /MovieReview.bo 호출 ");
			// DB 사용 O, 페이지 출력
			
			action = new MovieReviewListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MovieReviewContent.bo")) { // 영화 리뷰 글 페이지 이동
			System.out.println(" C : /MovieReviewContent.bo 호출 ");
			// DB 사용 O, 페이지 출력
			
			action = new MovieReviewContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MovieReviewWrite.bo")) { // 리뷰 글쓰기 페이지 이동
			System.out.println(" C : /MovieReviewWrite.bo 호출 ");
			// DB 사용 X, 페이지 이동
			
			forward = new ActionForward();
			forward.setPath("./board/reviewWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MovieReviewWriteAction.bo")) { // 리뷰 글쓰기 동작
			System.out.println(" C : /MovieReviewWriteAction.bo 호출 ");
			// DB 사용, 페이지 이동
			
			action = new MovieReviewWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MovieReviewDelete.bo")) { // 글 삭제
			System.out.println(" C : /MovieReviewDelete.bo 호출 ");
			// DB 사용 O ,페이지 이동
			
			action = new MovieReviewDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MovieReviewUpdate.bo")) { // 글 수정 페이지 이동
			System.out.println(" C : /MovieReviewUpdate.bo 호출");
			// DB 사용 o, 페이지 출력
			
			action = new MovieReviewUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MovieReviewUpdateAction.bo")) { // 글 수정 동작
			System.out.println(" C : /MovieReviewUpdateAction.bo 호출");
			// DB 사용 ㅇ, 페이지 이동
			
			action = new MovieReviewUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MovieReviewReply.bo")) { // 글 댓글 쓰는 동작
			System.out.println(" C : /MovieReviewReply.bo 호출");
			// DB 사용 ㅇ, 페이지 이동
			
			action = new MovieReviewReplyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
		System.out.println(" C : 2. 가상 주소 매핑 끝\n ");
		//////////////////////////////2. 가상 주소 매핑 /////////////////////////////////
		//////////////////////////////3. 페이지 이동 /////////////////////////////////
		System.out.println(" C : 3. 페이지 이동 시작");
		
		if(forward != null) { // 페이지 이동정보가 있을 때
			if(forward.isRedirect()) { // true
				System.out.println(" C : redirect 방식, "+forward.getPath()+"로 이동");
				response.sendRedirect(forward.getPath());
			} else { //false
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				System.out.println(" C : forward방식, "+forward.getPath()+"로 이동");
				dis.forward(request, response);
			} 
		}
		
		System.out.println(" C : 3. 페이지 이동 끝\n");
		//////////////////////////////3. 페이지 이동 /////////////////////////////////
		
	}

	@Override
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doGET() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doPOST() 호출");
		doProcess(request, response);
	}
 
	
}

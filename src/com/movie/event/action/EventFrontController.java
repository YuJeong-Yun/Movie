package com.movie.event.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.ev")
public class EventFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" EventFrontController - doPROCESS() 호출");
		
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
		
		if(command.equals("/Event.ev")) { // 이벤트 목록 페이지로 이동
			System.out.println(" C : /Event.ev 호출");
			// DB 사용 ㅇ, 페이지 출력
			
			action = new EventListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/EventContent.ev")) { // 이벤트 내용 페이지로 이동
			System.out.println(" C : /EventContent.ev 호출");
			// DB 사용 ㅇ, 페이지 출력
			
			action = new EventContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/EventWrite.ev")) { // 이벤트 글쓰기 페이지 이동
			System.out.println(" C : /EventWrite.ev 호출");
			// DB 사용 x, 페이지 이동
			
			forward = new ActionForward();
			forward.setPath("./event/eventWrite.jsp");
			forward.setRedirect(false);
			
			
		}else if(command.equals("/EventWriteAction.ev")) { // 이벤트 글쓰기 동작
			System.out.println(" C : /EventWrite.ev 호출");
			// DB 사용 ㅇ, 페이지 이동
			
			action = new EventWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/EventUpdate.ev")) { // 이벤트 수정 페이지 이동
			System.out.println(" C : /EventUpdate.ev 호출");
			// DB 사용 ㅇ, 페이지 출력
			
			action = new EventUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/EventUpdateProAction.ev")) { // 이벤트 수정 동작
			System.out.println(" C : /EventUpdateProAction.ev 호출");
			// DB 사용ㅇ, 페이지 이동
			
			action = new EventUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/EventDelete.ev")) { // 이벤트 글 삭제 동작
			System.out.println(" C : /EventDelete.ev 호출");
			// DB 사용ㅇ, 페이지 이동
			
			action = new EventDeleteAction();
			
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

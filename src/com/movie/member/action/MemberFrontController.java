package com.movie.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MemberFrontController extends HttpServlet {
	
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
		
		if(command.equals("/Login.me")) { // 로그인 페이지로 이동
			System.out.println(" C : /Login.me 호출 " );
			// DB사용 X, view 이동
			
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);
				
		}else if(command.equals("/LoginAction.me")) { // 로그인 처리 페이지
			System.out.println(" C : /LoginAction.me 호출 ");
			// DB 사용 ㅇ, 페이지 이동
			
			action = new LoginAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Logout.me")) { // 로그아웃 처리
			System.out.println(" C : /Logout.me 호출 ");
			// DB 사용 X, view 이동
			
			action = new LogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Join.me")) { // 회원가입 페이지로 이동
			System.out.println(" C : /Join.me 호출");
			// DB 사용 X, view 이동
			
			// 회원가입 페이지 이동
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/JoinAction.me")) { // 회원가입 실행 페이지
			System.out.println(" C : /JoinAction.me 호출 ");
			// DB 사용 O, 로그인 페이지 이동
			
			action = new JoinAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MyPage.me")) { // 마이 페이지(정보 수정 페이지)로 이동
			System.out.println(" C : /MyPage.me 호출");
			// DB 사용 O, 페이지 출력
			
			action = new MemberInfoAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberUpdateAction.me")) { // 정보 수정 실행
			System.out.println(" C : /MemberUpdateAction.me 호출");
			// DB 사용 O, 페이지 이동
			
			action = new MemberUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/DeleteMember.me")) { // 회원 탈퇴 비밀번호 입력 페이지
			System.out.println(" C : /DeleteMember.me 호출");
			// DB 사용 X , view 이동
			
			forward = new ActionForward();
			forward.setPath("./member/deleteMember.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/DeleteMemberAction.me")) { // 회원 탈퇴 실행
			System.out.println(" C : /DeleteMemberAction.me ");
			// DB 사용 O, 페이지 이동
			
			action = new DeleteMemberAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberPw.me")) { // 비밀번호 변경 페이지로 이동
			System.out.println(" C : /MemberPw.me 호출");
			// DB 사용 X, view 이동
			
			forward = new ActionForward();
			forward.setPath("./member/changePw.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberPwAction.me")) { // 비밀번호 확인 후 비밀번호 수정
			System.out.println(" C : /MemberPwAction.me 호출 ");
			// DB 사용 O, 페이지 이동
			
			action = new MemberPwAction();
			
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

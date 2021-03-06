package com.movie.main.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.mail.action.MailSendAction;


@WebServlet("*.do")
public class MainFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" MainFrontController - doPROCESS() 호출");
		
		//////////////////////////////1. 가상 주소 계산 /////////////////////////////////
		System.out.println(" C :1. 가상 주소 계산 시작");
		
		// 가상주소 가져오기
		String requestURI =	request.getRequestURI();
		System.out.println(" C : requestURI -  " + requestURI);
		// 프로젝트명  가져오기
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		// 가상주소 계산 (가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - " + command);
		
		
		System.out.println(" C : 1. 가상 주소 계산 끝\n");
		//////////////////////////////1. 가상 주소 계산 /////////////////////////////////
		//////////////////////////////2. 가상 주소 매핑 /////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Main.do")) { // 메인 페이지로 이동
			System.out.println(" C : /Main.do 호출 " );
			// DB사용 X, Model 이동
			
			action = new MainMovieChartAction(); // CGV 영화 차트 크롤링해서 세션에 저장
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MovieChart.do")) { // 영화 차트 페이지로 이동
			System.out.println(" C : /MovieChart.do 호출 ");
			// DB 사용 X, view 이동
			
			forward = new ActionForward();
			forward.setPath("./main/movieChart.jsp");
			forward.setRedirect(false);
					
		}else if(command.equals("/Search.do")) { // 검색하기
			System.out.println(" C : /Search.do 호출");
			// DB 사용 ㅇ, 페이지 출력
			
			action = new SearchAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MovieTheater.do")) { // 영화관 지도 페이지 이동
			System.out.println(" C : /MovieTheater.do 호출");
			// DB 사용 X, 페이지 이동
			
			forward = new ActionForward();
			forward.setPath("./main/movieTheater.jsp");
			forward.setRedirect(false);
			
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

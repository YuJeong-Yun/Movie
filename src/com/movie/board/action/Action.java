package com.movie.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 호출할때 request,response 정보를 필요로하고,
	// 처리동작 후 ActionForward(페이지 이동객체) 리턴
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response);
	

}

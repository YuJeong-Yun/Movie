package com.movie.main.action;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainMovieChartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(" M : MainMovieChart_execute() 호출 ");

		// Jsoup를 이용해서 크롤링
		String url = "http://www.cgv.co.kr/movies/?lt=1&ot=1"; // 크롤링할 url지정
		Document doc = null; // Document에 페이지의 전체 소스가 저장됨

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// select를 이용하여 원하는 태그를 선택
		Elements ranks = doc.select(".rank");
		Elements imgs = doc.select(".thumb-image > img");
		Elements movieTitles = doc.select("div.box-contents strong.title");
		Elements movieRates = doc.select(".percent > span");
		Elements movieOpenDates = doc.select(".txt-info > strong");

		// JSON 형태로 movieChart에 영화 정보 저장
		JSONArray movieChart = new JSONArray();

		for (int i = 0; i < ranks.size(); i++) {
			JSONObject obj = new JSONObject();

			obj.put("rank", ranks.get(i).text());
			obj.put("img", imgs.get(i).attr("src"));
			obj.put("movieTitle", movieTitles.get(i).text());
			obj.put("movieRate", movieRates.get(i).text());
			obj.put("movieOpenDate", movieOpenDates.get(i).text());

			movieChart.add(obj);
		}

		System.out.println("movieChart : " + movieChart);
		
		// session에 movieList 저장
		HttpSession session = request.getSession();
		session.setAttribute("movieChart", movieChart);

		// 페이지 이동 객체 생성
		ActionForward forward = new ActionForward();
		forward = new ActionForward();
		forward.setPath("./main/main.jsp");
		forward.setRedirect(false);

		return forward;
	}
}

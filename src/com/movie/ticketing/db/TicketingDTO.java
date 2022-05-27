package com.movie.ticketing.db;

import java.sql.Timestamp;


public class TicketingDTO {
	private int num; // 번호(pk)
	private String order_num; // 주문번호
	private String member_id; // 주문자 아이디

	private String movie_title; // 영화 제목
	private String movie_theater; // 영화관
	private String movie_dateTime; // 상영시간
	private String movie_seat; // 좌석

	private Timestamp order_date; // 주문 시간
	private int price; // 금액
	private String payment_num; // 결제번호

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public String getMovie_theater() {
		return movie_theater;
	}

	public void setMovie_theater(String movie_theater) {
		this.movie_theater = movie_theater;
	}

	public String getMovie_dateTime() {
		return movie_dateTime;
	}

	public void setMovie_dateTime(String movie_dateTime) {
		this.movie_dateTime = movie_dateTime;
	}

	public String getMovie_seat() {
		return movie_seat;
	}

	public void setMovie_seat(String movie_seat) {
		this.movie_seat = movie_seat;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPayment_num() {
		return payment_num;
	}

	public void setPayment_num(String payment_num) {
		this.payment_num = payment_num;
	}

	@Override
	public String toString() {
		return "TicketingDTO [num=" + num + ", order_num=" + order_num + ", member_id=" + member_id + ", movie_title="
				+ movie_title + ", movie_theater=" + movie_theater + ", movie_dateTime=" + movie_dateTime
				+ ", movie_seat=" + movie_seat + ", order_date=" + order_date + ", price=" + price + ", payment_num="
				+ payment_num + "]";
	}

}

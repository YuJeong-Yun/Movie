package com.movie.board.action.db;

import java.sql.Timestamp;

public class MovieBoardReplyDTO {
	private int re_num;
	private String id;
	private String name;
	private String content;
	private int re_ref;
	private Timestamp date;
	private String ip;

	public int getRe_num() {
		return re_num;
	}

	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRe_ref() {
		return re_ref;
	}

	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "MovieBoardReplyDTO [re_num=" + re_num + ", id=" + id + ", name=" + name + ", content=" + content
				+ ", re_ref=" + re_ref + ", date=" + date + ", ip=" + ip + "]";
	}

}
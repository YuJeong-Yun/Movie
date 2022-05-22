package com.movie.review.db;

import java.sql.Timestamp;

public class MovieBoardReplyDTO {
	private int cno;
	private String id;
	private String name;
	private String content;
	private int bno;
	private Timestamp date;
	private String ip;

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
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

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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
		return "MovieBoardReplyDTO [cno=" + cno + ", id=" + id + ", name=" + name + ", content=" + content + ", bno="
				+ bno + ", date=" + date + ", ip=" + ip + "]";
	}

}

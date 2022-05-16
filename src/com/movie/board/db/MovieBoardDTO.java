package com.movie.board.db;

import java.sql.Timestamp;

public class MovieBoardDTO {
	private int num;
	private int rownum;
	private String id;
	private String name;
	private String subject;
	private String content;
	private int readcount;
	private int re_cnt;
	private Timestamp date;
	private String ip;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRe_cnt() {
		return re_cnt;
	}

	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
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
		return "MovieBoardDTO [num=" + num + ", rownum=" + rownum + ", id=" + id + ", name=" + name + ", subject="
				+ subject + ", content=" + content + ", readcount=" + readcount + ", re_cnt=" + re_cnt + ", date="
				+ date + ", ip=" + ip + "]";
	}

}

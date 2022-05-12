package com.movie.board.action.db;

import java.sql.Date;

public class MovieBoardDTO {
	int num;
	String id;
	String name;
	String pass;
	String subject;
	String content;
	int readcount;
	int re_cnt;
	Date date;
	String ip;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		return "MovieBoardDTO [num=" + num + ", id=" + id + ", name=" + name + ", pass=" + pass + ", subject=" + subject
				+ ", content=" + content + ", readcount=" + readcount + ", re_cnt=" + re_cnt + ", date=" + date
				+ ", ip=" + ip + "]";
	}

}

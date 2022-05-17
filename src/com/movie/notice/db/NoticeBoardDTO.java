package com.movie.notice.db;

import java.sql.Timestamp;

public class NoticeBoardDTO {
	private int num;
	private int rownum;
	private String subject;
	private String content;
	private int readcount;
	private Timestamp date;
	private String file;

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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "NoticeBoardDTO [num=" + num + ", rownum=" + rownum + ", subject=" + subject + ", content=" + content
				+ ", readcount=" + readcount + ", date=" + date + ", file=" + file + "]";
	}

}

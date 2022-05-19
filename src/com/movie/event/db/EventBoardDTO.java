package com.movie.event.db;

import java.sql.Timestamp;

public class EventBoardDTO {
	private int num;
	private int rownum;
	private String category;
	private String subject;
	private Timestamp eventDateStart;
	private Timestamp eventDateEnd;
	private String image;
	private String image_thumb;
	private Timestamp date;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Timestamp getEventDateStart() {
		return eventDateStart;
	}

	public void setEventDateStart(Timestamp eventDateStart) {
		this.eventDateStart = eventDateStart;
	}

	public Timestamp getEventDateEnd() {
		return eventDateEnd;
	}

	public void setEventDateEnd(Timestamp eventDateEnd) {
		this.eventDateEnd = eventDateEnd;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage_thumb() {
		return image_thumb;
	}

	public void setImage_thumb(String image_thumb) {
		this.image_thumb = image_thumb;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "EventBoardDTO [num=" + num + ", rownum=" + rownum + ", category=" + category + ", subject=" + subject
				+ ", eventDateStart=" + eventDateStart + ", eventDateEnd=" + eventDateEnd + ", image=" + image
				+ ", image_thumb=" + image_thumb + ", date=" + date + "]";
	}

}

package com.movie.member.db;

import java.util.Date;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String addr;
	private String tel;
	private String email;
	private Date date;
	private String profile;
	private String th_profile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getTh_profile() {
		return th_profile;
	}

	public void setTh_profile(String th_profile) {
		this.th_profile = th_profile;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", addr=" + addr
				+ ", tel=" + tel + ", email=" + email + ", date=" + date + ", profile=" + profile + ", th_profile="
				+ th_profile + "]";
	}

}

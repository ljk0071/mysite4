package com.javaex.vo;

public class GuestVo {

	private String name, password, content, regDate;
	private int no;

	public GuestVo(String name, String password, String content) {
		this.name = name;
		this.password = password;
		this.content = content;
	}

	public GuestVo(int no, String name, String password, String content, String regDate) {
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}

	public GuestVo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "GuestVo [name=" + name + ", password=" + password + ", content=" + content + ", regDate=" + regDate
				+ ", No=" + no + "]";
	}

}

package com.javaex.vo;

public class RboardVo {

	
	private int no, userNo, hit, groupNo, orderNo, depth;
	private String name, title, content, regDate; 
	
	public RboardVo() {
		
	}
	public RboardVo(int no, int userNo, String name, String title, String content, int hit, String regDate, int groupNo, int orderNo, int depth) {
		this.no = no;
		this.userNo = userNo;
		this.name = name;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
	}
	public RboardVo(int userNo, String title, String content, int groupNo, int orderNo, int depth) {
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
	}
	public RboardVo(int userNo, String title, String content) {
		this.userNo = userNo;
		this.title = title;
		this.content = content;
	}
	public RboardVo(int groupNo, int orderNo, int depth) {
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", userNo=" + userNo + ", hit=" + hit + ", groupNo=" + groupNo + ", orderNo="
				+ orderNo + ", depth=" + depth + ", name=" + name + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
}

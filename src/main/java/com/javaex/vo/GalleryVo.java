package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class GalleryVo {
	
	private MultipartFile img;
	private int no;
	private int userNo;
	private String name;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	
	public GalleryVo() {
	}
	
	public GalleryVo(String content, MultipartFile img) {
		this.content = content;
		this.img = img;
	}
	public GalleryVo(int no, int userNo, String content, String saveName, String name) {
		this.no = no;
		this.userNo = userNo;
		this.content = content;
		this.saveName = saveName;
		this.name = name;
	}
	
	public GalleryVo(String orgName, String saveName, String filePath, long fileSize) {
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
	
	public GalleryVo(String content, String filePath, String orgName, String saveName, long fileSize) {
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	
	public GalleryVo(int userNo, String content, String filePath, String orgName, String saveName, long fileSize) {
		this.userNo = userNo;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	
	public GalleryVo(int no, int userNo, String name, String content, String filePath, String orgName, String saveName, long fileSize) {
		this.no = no;
		this.userNo = userNo;
		this.name = name;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	

	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "GalleryVo [img=" + img + ", no=" + no + ", userNo=" + userNo + ", name=" + name + ", content=" + content
				+ ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize="
				+ fileSize + "]";
	}
}

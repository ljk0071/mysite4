package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao gDao;
	
	public List<GalleryVo> getList() {
		return gDao.SelectAll();
	}
	public int delete(int no) {
		return gDao.Delete(no);
	}
	
	public void save(GalleryVo gVo) {
		
		String saveDir = "C:\\JavaStudy\\upload";
		
		String orgName = gVo.getImg().getOriginalFilename();
		
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		
		String filePath = saveDir + "\\" + saveName;
		
		long fileSize = gVo.getImg().getSize();
		
		GalleryVo gVo2 = new GalleryVo(gVo.getNo(), gVo.getContent(), filePath, orgName, saveName, fileSize);
		
		gDao.Insert(gVo2);
		
		
		try {
			byte[] fileData = gVo.getImg().getBytes();
			
			OutputStream os =  new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

@Service
public class FileService {
	
//	@Autowired
//	private FileDao fDao;
	
	public String save(MultipartFile file) {
		System.out.println("FileService>save()");
		
		String saveDir = "C:\\JavaStudy\\upload";
		
		String orgName = file.getOriginalFilename();
		
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		
		String filePath = saveDir + "\\" + saveName;
		
		long fileSize = file.getSize();
		
		FileVo fVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fVo);
		
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os =  new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
		
		
	}

}

package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {
	
	@Autowired
	private GuestDao gDao;
	
	public List<GuestVo> getGuestList() {
		return gDao.SelectAll();
	}
	public GuestVo getGuset(int gNo) {
		return gDao.Select(gNo);
	}
	public GuestVo doAddGuest(GuestVo gVo) {
		
		gDao.Insert(gVo);
		
		int no = gVo.getNo();
		
		GuestVo gVo2 = gDao.Select(no);
		
		return gVo2;
	}
	public int doDeleteGuest(int gNo) {
		return gDao.Delete(gNo);
	}
}

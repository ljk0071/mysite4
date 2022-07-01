package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService2 {
	
	@Autowired
	private UserDao uDao;

	public String doChkId(String id) {
		return uDao.Select(id);
	}
	
	public int doAddUser(UserVo uVo) {
		return uDao.Insert(uVo);
	}
	
	public UserVo getUser(String id, String pw) {
		return uDao.Select(id, pw);
	}
	
}

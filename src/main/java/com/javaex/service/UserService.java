package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao uDao;

	public List<UserVo> getUserList() {
		return uDao.SelectAll();
	}

	public UserVo getUser(int uNo) {
		return uDao.Select(uNo);
	}
	
	public UserVo getUser(String id, String pw) {
		return uDao.Select(id, pw);
	}

	public int doAddPerson(UserVo uVo) {
		return uDao.Insert(uVo);
	}

	public int doUpdatePerson(UserVo uVo) {
		return uDao.Update(uVo);
	}

	public int doDeletePerson(int uNo) {
		return uDao.Delete(uNo);
	}
}

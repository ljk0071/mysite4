package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	private int count = -1;
	
	@Autowired
	private SqlSession sqlSession;

	public List<UserVo> SelectAll() {
		List<UserVo> uList = sqlSession.selectList("userbook.SelectAll");
		System.out.println(uList);
		return uList;
	}

	public UserVo Select(int uNo) {
		UserVo uVo = sqlSession.selectOne("userbook.Select", uNo);
		return uVo;
	}
	
	public UserVo Select(String id, String pw) {
		Map<String, Object> uMap = new HashMap<String, Object>();
		uMap.put("id", id);
		uMap.put("pw", pw);
		UserVo uVo = sqlSession.selectOne("userbook.SelectLogin", uMap);
		return uVo;
	}

	public int Insert(UserVo uVo) {
		count = sqlSession.insert("userbook.Insert", uVo);
		return count;
	}

	public int Update(UserVo uVo) {
		count = sqlSession.update("userbook.Update", uVo);
		return count;
	}

	public int Delete(int uNo) {
		count = sqlSession.delete("userbook.Delete", uNo);
		return count;
	}
	
}

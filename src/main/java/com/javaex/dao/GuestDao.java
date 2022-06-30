package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	private int count = 0;

	@Autowired
	private SqlSession sqlSession;

	public int Insert(GuestVo gVo) {
		count = sqlSession.insert("guestbook.InsertSelectKey", gVo);
		return count;
	}

	public int Delete(GuestVo gVo) {
		count = sqlSession.delete("guestbook.Delete", gVo);
		return count;
	}

	public List<GuestVo> SelectAll() {
		List<GuestVo> gList = sqlSession.selectList("guestbook.SelectAll");
		return gList;
	}

	public GuestVo Select(int gNo) {
		GuestVo gVo = sqlSession.selectOne("guestbook.Select", gNo);
		return gVo;
	}
}

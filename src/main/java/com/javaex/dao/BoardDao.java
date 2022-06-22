package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	private int count = 0;

	@Autowired
	private SqlSession sqlSession;

	public int Insert(BoardVo bVo){
		count = sqlSession.insert("boardbook.Insert", bVo);
		return count;
	}

	public int Delete(int bNo) {
		count = sqlSession.delete("boardbook.Delete", bNo);
		return count;
	}

	public List<BoardVo> SelectAll() {
		List<BoardVo> bList = sqlSession.selectList("boardbook.SelectAll");
		return bList;
	}

	public List<BoardVo> SelectAll(String title) {
		List<BoardVo> sList = sqlSession.selectList("boardbook.SearchAll", title);
		return sList;
	}

	public BoardVo Select(int bNo) {
		BoardVo bVo = sqlSession.selectOne("boardbook.Select", bNo);
		return bVo;
	}

	public int Update(BoardVo bVo) {
		count = sqlSession.update("boardbook.Update", bVo);
		return count;

	}

	public int UpdateHit(int bNo) {
		count = sqlSession.update("boardbook.UpdateHit", bNo);
		return count;
	}
}

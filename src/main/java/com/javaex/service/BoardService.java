package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao bDao;

	public List<BoardVo> getBoardList() {
		return bDao.SelectAll();
	}
	
	public List<BoardVo> getSearchList(String title) {
		return bDao.SelectAll(title);
	}

	public BoardVo getBoard(int uNo) {
		return bDao.Select(uNo);
	}

	public int doAddBoard(BoardVo bVo) {
		return bDao.Insert(bVo);
	}

	public int doDeleteBoard(int bNo) {
		return bDao.Delete(bNo);
	}

	public int doUpdateBoard(BoardVo bVo) {
		return bDao.Update(bVo);
	}

	public int doUpdateBoardHit(int bNo) {
		return bDao.UpdateHit(bNo);
	}

}

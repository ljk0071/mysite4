package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	private int count = 0;
	
	@Autowired
	private SqlSession sS;
	
//	public List<RboardVo> SelectAll(String title) {
//		List<RboardVo> rList = sS.selectList("rboardbook.SelectAll", title);
//		return rList;
//	}
//	
	public List<RboardVo> SelectAll(int startRnum, int endRnum) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("sNum", startRnum);
		pageMap.put("eNum", endRnum);
		List<RboardVo> rList = sS.selectList("rboardbook.SelectAll", pageMap);
		return rList;
	}
	
	public List<RboardVo> SelectAll(int startRnum, int endRnum, String keyword) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("sNum", startRnum);
		pageMap.put("eNum", endRnum);
		pageMap.put("keyword", keyword);
		List<RboardVo> rList = sS.selectList("rboardbook.SelectAll", pageMap);
		return rList;
	}
	
	public RboardVo Select(int rNo) {
		RboardVo rVo = sS.selectOne("rboardbook.Select", rNo);
		return rVo;
	}
	
	public int SelectTotalCnt() {
		int totalCnt = sS.selectOne("rboardbook.SelectTotalCnt");
		return totalCnt;
	}
	
	public int SelectSearchCnt(String title) {
		int searchCnt = sS.selectOne("rboardbook.SelectSearchCnt", title);
		return searchCnt;
	}
	
	public int UpdateHit(int rNo) {
		count = sS.update("rboardbook.UpdateHit", rNo);
		return count;
	}
	
	public int Insert(RboardVo rVo){
		count = sS.insert("rboardbook.Insert", rVo);
		return count;
	}
	
	public int InsertRepl(RboardVo rVo){
		count = sS.insert("rboardbook.InsertRepl", rVo);
		return count;
	}
	
	public int Update(RboardVo rVo) {
		count = sS.update("rboardbook.Update", rVo);
		return count;

	}
	
	public int UpdateReplOrderNo(int groupNo, int orderNo) {
		Map<String, Object> replMap = new HashMap<String, Object>();
		replMap.put("groupNo", groupNo);
		replMap.put("orderNo", orderNo);
		count = sS.update("rboardbook.UpdateReplOrderNo", replMap);
		return count;
	}
	
	public int Delete(int rNo) {
		count = sS.delete("rboardbook.Delete", rNo);
		return count;
	}
	

}

package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	private int count = 0;
	
	@Autowired
	private SqlSession sS;
	
	public List<RboardVo> SelectAll() {
		List<RboardVo> rList = sS.selectList("rboardbook.SelectAll");
		return rList;
	}
	
	public RboardVo Select(int rNo) {
		RboardVo rVo = sS.selectOne("rboardbook.Select", rNo);
		return rVo;
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
	
	public int Delete(int rNo) {
		count = sS.delete("rboardbook.Delete", rNo);
		return count;
	}
	

}

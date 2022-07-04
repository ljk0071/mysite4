package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	
	@Autowired
	private RboardDao rDao;
	private String prefix = "";
	private int prefixcnt = 0;
	
	public List<RboardVo> getRboardList() {
		
		return rDao.SelectAll();
	}
	
	public RboardVo getRboard(int rNo,int cancel) {
		if (cancel == 0) {
			return rDao.Select(rNo);
		}
		rDao.UpdateHit(rNo);
		return rDao.Select(rNo);
	}
	
	public RboardVo getRboard(int rNo) {
		return rDao.Select(rNo);
	}
	
	public int doAddBoard(RboardVo rVo) {
		rVo.setContent(rVo.getContent().replace("\r\n", "<br>"));
		if (rVo.getGroupNo() == 0) {
			return rDao.Insert(rVo);
		}
		prefixcnt = rVo.getDepth();
		do {
			prefix += "▶";
			if (prefixcnt == 1) {
				prefix += "▶";
				break;
			}
			prefixcnt -= 1;
		}while(prefixcnt > 0);
		rVo.setTitle(prefix + rVo.getTitle());
		prefix = "";
		return rDao.InsertRepl(rVo);
	}
	
	public int doDeleteRboard(int rNo) {
		return rDao.Delete(rNo);
	}

	public int doUpdateRboard(RboardVo rVo) {
		rVo.setContent(rVo.getContent().replace("\r\n", "<br>"));
		if (rVo.getOrderNo() == 1) {
			return rDao.Update(rVo);
		}
		prefixcnt = rVo.getDepth();
		do {
			prefix += "▶";
			if (prefixcnt == 1) {
				prefix += "▶";
				break;
			}
			prefixcnt -= 1;
		}while(prefixcnt > 0);
		rVo.setTitle(prefix + rVo.getTitle());
		return rDao.Update(rVo);
	}


}
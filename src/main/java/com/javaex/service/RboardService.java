package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private int cnt = 0;
	
	public Map<String, Object> getRboardList(int crtPage,String keyword) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		crtPage = (crtPage>0) ? crtPage : (crtPage=1);
		
		final int listCnt = 10;
		final int pageBtnCount = 5;
		
		int startRnum = (crtPage-1)*listCnt+1;
		int endRnum = startRnum+listCnt-1;
		
		int endPageNum = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;
		int startPageNum = (endPageNum-pageBtnCount)+1;
		boolean prev = (startPageNum!=1) ? true : false;
		
		boolean next = false;
		List<RboardVo> rList;
		
		
		if( keyword == null || keyword == "" ) {
			int totalCnt = rDao.SelectTotalCnt();
			if ((listCnt*endPageNum) < totalCnt) {
				next = true;
			}else {
				endPageNum = (int)Math.ceil(totalCnt/(double)listCnt);
			}
			rList = rDao.SelectAll(startRnum, endRnum);
		} else {
			int searchCnt = rDao.SelectSearchCnt(keyword);
			if ((listCnt*endPageNum) < searchCnt) {
				next = true;
			}else {
				endPageNum = (int)Math.ceil(searchCnt/(double)listCnt);
			}
			rList = rDao.SelectAll(startRnum, endRnum, keyword);
			pMap.put("keyword", keyword);
		}
		pMap.put("rList", rList);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageNum", startPageNum);
		pMap.put("endPageNum", endPageNum);
		pMap.put("crtPage", crtPage);
		pMap.put("pageBtnCount", pageBtnCount);
		return pMap;
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
		cnt += rDao.UpdateReplOrderNo(rVo.getGroupNo(), rVo.getOrderNo());
		cnt += rDao.InsertRepl(rVo);
		return cnt;
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

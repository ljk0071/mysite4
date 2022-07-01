package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	private int count = 0;

	@Autowired
	private SqlSession sqlSession;

	public int Insert(GalleryVo gVo) {
		count = sqlSession.insert("gallerybook.Insert", gVo);
		return count;
	}

	public int Delete(int gNo) {
		count = sqlSession.delete("gallerybook.Delete", gNo);
		return count;
	}

	public List<GalleryVo> SelectAll() {
		List<GalleryVo> gList = sqlSession.selectList("gallerybook.SelectAll");
		return gList;
	}

	public GalleryVo Select(int gNo) {
		GalleryVo gVo = sqlSession.selectOne("gallerybook.Select", gNo);
		return gVo;
	}

}

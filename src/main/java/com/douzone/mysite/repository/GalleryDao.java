package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> getGalleryList(){
		List<GalleryVo> list = sqlSession.selectList("gallery.getGalleryList");
		return list;
	}
	
	public int insertGallery(GalleryVo galleryVo) {
		return sqlSession.insert("gallery.insertGallery", galleryVo);
	}
	
	public int deleteGallery(long no) {
		int resulst = sqlSession.delete("gallery.deleteGallery", no);
		return resulst; 
	}
}

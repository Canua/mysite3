package com.douzone.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryDao;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryDao galleryDao;
	
	public List<GalleryVo> getGalleryList(){
		List<GalleryVo> list = new ArrayList<GalleryVo>();
		list = galleryDao.getGalleryList();
		return list;
	}
	
	public boolean insertGallery(GalleryVo galleryVo) {
		int count = galleryDao.insertGallery(galleryVo);
		return count == 1;
	}
	
	public int deleteGallery(long no) {
		int result = galleryDao.deleteGallery(no);
		System.out.println("삭제됐나요? " + result);
		return result;
	}
}

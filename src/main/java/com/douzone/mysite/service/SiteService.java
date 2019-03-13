package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteDao;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	SiteDao siteDao;

	public SiteVo getMain() {
		SiteVo siteVo = siteDao.getMain();
		return siteVo;
	}

	public boolean updateMain(SiteVo vo) {	
		System.out.println("관리자 vo : " + vo);
		int count = siteDao.updateMain(vo);
		return count == 1;
	}
}

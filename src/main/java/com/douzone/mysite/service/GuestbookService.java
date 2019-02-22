package com.douzone.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> list() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		list = guestbookDao.getList();
		return list;
	}
	public void insert(GuestbookVo guestbookVo) {
		long no = guestbookDao.insert(guestbookVo);
		System.out.println("no : last_primary key " + no);
	}
	public void delete(GuestbookVo guestbookVo) {
		guestbookDao.delete(guestbookVo);
	}
}

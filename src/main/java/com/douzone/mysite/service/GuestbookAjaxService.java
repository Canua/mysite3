package com.douzone.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookAjaxService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> getList(int page) {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		list = guestbookDao.getList(page);
		System.out.println("ajax Guestbook : " + list);
		return list;
	}

	public long insert(GuestbookVo guestbookVo) {
		long no = guestbookDao.insert(guestbookVo);
		System.out.println("no : last_primary key " + no);
		return no;
	}

	public GuestbookVo getList(long no) {
		GuestbookVo guestbookVo = guestbookDao.get(no);
		System.out.println("getlist : " + guestbookVo);
		return guestbookVo;
	}

	public int delete(GuestbookVo guestbookVo) {
		int result = guestbookDao.delete(guestbookVo);
		System.out.println("result : 삭제성공 - " + result);
		return result;
	}
}

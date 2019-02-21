package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public int size() {
		int size = 0;
		List<BoardVo> list = boardDao.getList();
		size = list.size();
		return size;
	}

	public List<BoardVo> list() {
		List<BoardVo> list = boardDao.getList();
//		int totalCount = boardDao.count();

		// pager 알고리즘
		//
		//
		//

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
//		map.put("totalPageCount", toTalPageCount);

//		return map;
		return (List<BoardVo>) map;
	}
	
	public List<BoardVo> getPageList(int pageNum) {
		List<BoardVo> list = boardDao.getPageList(pageNum);
	
		return list;
	}
}

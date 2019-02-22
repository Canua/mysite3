package com.douzone.mysite.service;

import java.util.List;

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

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("list", list);
//		map.put("totalPageCount", toTalPageCount);
//		System.out.println	("맵 사이즈 : " + map.size());
//		System.out.println("key : " + map.get("list") );

//		return (List<BoardVo>) map;
		return list;
	}
	
	public List<BoardVo> getPageList(int pageNum) {
		List<BoardVo> list = boardDao.getPageList(pageNum);
		return list;
	}
	public void write(BoardVo boardVo) {
		boardDao.insert(boardVo);
	}
	public void delete(long no) {
		boardDao.delete(no);
	}
	public long getUser(long no) {
		BoardVo boardVo = null;
		boardVo = boardDao.getInfo(no);
		long board_user = boardVo.getUser_no();
		return board_user;
	}
	public void hitUpdate(long no) {
		boardDao.hitUpdate(no);
	}
	public BoardVo getView(long no) {
		BoardVo vo = boardDao.getView(no);
		return vo;
	}
	public boolean check(long no, long user_no) {
		boolean check = boardDao.check(no, user_no);
		return check;
	}
	public void update(BoardVo boardVo) {
		boardDao.update(boardVo);
	}
	public BoardVo getInfo(long no) {
		BoardVo vo = boardDao.getInfo(no);
		return vo;
	}
}

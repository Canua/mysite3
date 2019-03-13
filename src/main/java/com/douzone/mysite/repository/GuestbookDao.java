package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	public GuestbookVo get(long searchNo) {
		return sqlSession.selectOne("guestbook.getListSearchNo", searchNo);
		
	}
	
	public int delete(GuestbookVo guestbooktVo) {
		int result = sqlSession.delete("guestbook.delete", guestbooktVo);
		result = (int) ((result == 1) ? guestbooktVo.getNo() : -1);
		
		return result;
	}
	

	public long insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
		// inert 쿼리 실행 후 no 값을 얻을 수 있다.
		long no = vo.getNo();
		return no;
	}

	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}
	public List<GuestbookVo> getList(int page_num){
		int page = (page_num - 1) * 5;
		System.out.println("변화하는 page" + page);
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getListAndPage", page);
		return list;
	}

}
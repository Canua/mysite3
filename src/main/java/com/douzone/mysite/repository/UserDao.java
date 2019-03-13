package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}

	public UserVo get(long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}

	public int update(UserVo userVo) {
		return sqlSession.update("user.update", userVo);
	}

	public UserVo get(String email, String password) {
		// 원래 Uservo를 던져야 하지만
		// map을 사용해서 던진다
		// 내장타입 사용
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);

		return sqlSession.selectOne("user.getByEmailAndPassword", map);
	}

	public int insert(UserVo vo) {
		// namespace의 id
		return sqlSession.insert("user.insert", vo);

	}
}

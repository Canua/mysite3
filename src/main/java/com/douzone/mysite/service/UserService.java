package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void join(UserVo userVo) {
		// 1. DB에 가입 회원 정보 inert 하기
		userDao.insert(userVo);
		// 2. 화면전환
	}
	public UserVo get(long no) {
		UserVo userVo = null;
		userVo = userDao.get(no);
		return userVo;
	}
	public UserVo login(UserVo userVo) {
		UserVo authUser = null;
		authUser = userDao.get(userVo.getEmail(), userVo.getPassword());
		System.out.println(authUser);
		return authUser;
	}
	public UserVo modifyform(UserVo authUser ) {
		UserVo userVo = null;
		userVo = userDao.get(authUser.getNo());
		return userVo;
	}
	public void modify(UserVo userVo) {
		UserVo authUser = null;
		System.out.println("업데이트 실행");
		userDao.update(userVo);
	}
}

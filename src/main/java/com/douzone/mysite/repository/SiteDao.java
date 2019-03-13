package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteDao {
	
	@Autowired
	private SqlSession sqlSession;
	// Update , Select
	public SiteVo getMain() {
		return sqlSession.selectOne("site.selectMain");
	}
	public int updateMain(SiteVo siteVo) {
		System.out.println("사이트 업데이트");
		return sqlSession.update("site.updateMain", siteVo);
	}
 	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드러이버 로딩 실패:" + e);
		}

		return conn;
	}
}

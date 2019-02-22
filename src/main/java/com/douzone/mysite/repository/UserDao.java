package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(String email){
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	
	public boolean update(UserVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = dataSource.getConnection();
			 
			 String sql = "update user set name = ?, password = ?, gender = ? where no = ?";
			 String sql2 = "update user set name = ?, gender = ? where no = ?";
			 

			 if (vo.getPassword().equals(""))
			 {
				 pstmt = conn.prepareCall(sql2);
				 pstmt.setString(1, vo.getName());
				 pstmt.setString(2, vo.getGender());
				 pstmt.setLong(3, vo.getNo());

			 }
			 else
			 {
				 pstmt = conn.prepareCall(sql);
				 pstmt.setString(1, vo.getName());
				 pstmt.setString(2, vo.getPassword());
				 pstmt.setString(3, vo.getGender());
				 pstmt.setLong(4, vo.getNo());

			 }
			 			 
			 int count = pstmt.executeUpdate();
			 result = count == 1;
		} 
		catch (SQLException e) 
		{
			System.out.println("error : " + e);
		}
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public UserVo get(long no)
	{
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			 conn = dataSource.getConnection();
			 
			 String sql = "select no, name, email, gender from user where no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 
			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 long nos = rs.getLong(1);
				 String name = rs.getString(2);
				 String email = rs.getString(3);
				 String gender = rs.getString(4);
				 
				 
				 result = new UserVo();
				 result.setNos(nos);
				 result.setName(name);
				 result.setEmail(email);
				 result.setGender(gender);
			 }
		} 
		catch (SQLException e) 
		{
			System.out.println("error : " + e);
		}
		finally 
		{
			try 
			{
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public UserVo get(String email, String password) {
		// 원래 Uservo를 던져야 하지만
		// map을 사용해서 던진다
		// 내장타입 사용
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo;
	}

	public int insert(UserVo vo){
		// namespace의 id
		return sqlSession.insert("user.insert", vo);
		
	}
}

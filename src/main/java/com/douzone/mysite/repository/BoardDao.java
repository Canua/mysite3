package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int delete(long no) {
		BoardVo vo = null;
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public long hitUpdate(long viewNo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set hit = hit + 1 where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, viewNo);

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set title = ?, contents =? where no = ?";

			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int insert(BoardVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into board values (null, ?, ?, now(), 0, (SELECT ifnull(max(g_no) + 1, 1) from board a), 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUser_no());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public int reply(BoardVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into board values(null, ?, ?, now(), 0, ?, ?+1, ?+1, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getG_no());
			pstmt.setLong(4, vo.getO_no());
			pstmt.setLong(5, vo.getDepth());
			pstmt.setLong(6, vo.getUser_no());
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public boolean replyUpdate(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set o_no = o_no + 1 where g_no = ? and o_no > ?";

			pstmt = conn.prepareCall(sql);
			pstmt.setLong(1, vo.getG_no());
			pstmt.setLong(2, vo.getO_no());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public BoardVo getInfo(long no) {
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select o_no, g_no, depth, user_no from board where no = ?";

			pstmt = conn.prepareCall(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				long o_no = rs.getLong(1);
				long g_no = rs.getLong(2);
				long depth = rs.getLong(3);
				long user_no = rs.getLong(4);
				result = new BoardVo();
				result.setO_no(o_no);
				result.setG_no(g_no);
				result.setDepth(depth);
				result.setUser_no(user_no);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public BoardVo getView(long viewNo) {
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.contents, a.g_no, a.o_no, a.depth, a.user_no from board a, user b where a.user_no = b.no and a.no = ?";

			pstmt = conn.prepareCall(sql);

			pstmt.setLong(1, viewNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				long g_no = rs.getLong(4);
				long o_no = rs.getLong(5);
				long depth = rs.getLong(6);
				long user_no = rs.getLong(7);
				result = new BoardVo();
				result.setNo(no);
				result.setTitle(title);
				result.setContents(contents);
				result.setG_no(g_no);
				result.setO_no(o_no);
				result.setDepth(depth);
				result.setUser_no(user_no);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean check(long no, long user_no) {
		boolean check = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long num = 0;

		try {
			conn = getConnection();

			String sql = "select a.no\r\n" + "	from board a, user b\r\n" + "    where a.user_no = b.no\r\n"
					+ "	and a.no = ?\r\n" + "    and a.user_no = ?";

			pstmt = conn.prepareCall(sql);

			pstmt.setLong(1, no);
			pstmt.setLong(2, user_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getLong(1);
			}
			if (num != 0) {
				check = true;
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return check;
	}

	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stmt = conn.createStatement();

			String sql = "select a.no, title, b.name, hit, date_format(write_date, '%Y-%m-%d %h:%i:%s'), a.user_no, a.depth"
					+ "	from board a, user b\r\n" + "    where a.user_no = b.no order by g_no desc, o_no asc";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				long hit = rs.getLong(4);
				String writeDate = rs.getString(5);
				long user_no = rs.getLong(6);
				long depth = rs.getLong(7);
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setWriteDate(writeDate);
				vo.setUser_no(user_no);
				vo.setDepth(depth);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	public List<BoardVo> getPageList(int pageNum) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "select a.no, title, b.name, hit, date_format(write_date, '%Y-%m-%d %h:%i:%s'), a.user_no, a.depth"
					+ "	from board a, user b\r\n" + "    where a.user_no = b.no order by g_no desc, o_no asc limit ?, 5";
			
			pstmt = conn.prepareCall(sql);

			pstmt.setInt(1, pageNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				long hit = rs.getLong(4);
				String writeDate = rs.getString(5);
				long user_no = rs.getLong(6);
				long depth = rs.getLong(7);
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setWriteDate(writeDate);
				vo.setUser_no(user_no);
				vo.setDepth(depth);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	public 	List<BoardVo> select(String kwd, int selectCount){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			conn = getConnection();
			stmt = conn.createStatement();


			if(selectCount == 1) {
			String sql = "select a.no, title, b.name, hit, date_format(write_date, '%Y-%m-%d %h:%i:%s'), a.user_no, a.depth \r\n" + 
					"from board a, user b \r\n" + 
					"where a.user_no = b.no \r\n" + 
					" AND (a.title LIKE '%" + kwd + "%'\r\n" + 
					"	OR b.name LIKE '%"+ kwd + "%')\r\n" + 
					"order by g_no desc, o_no asc";
			
			pstmt = conn.prepareCall(sql);

			}else if(selectCount == 2) {
			
				String sql = "select a.no, title, b.name, hit, date_format(write_date, '%Y-%m-%d %h:%i:%s'), a.user_no, a.depth \r\n" + 
						"from board a, user b \r\n" + 
						"where a.user_no = b.no \r\n" + 
						" AND (b.name LIKE '%" + kwd + "%')\r\n" + 
						"order by g_no desc, o_no asc";
				
				pstmt = conn.prepareCall(sql);
			}else if(selectCount == 3) {
				
				String sql = "select a.no, title, b.name, hit, date_format(write_date, '%Y-%m-%d %h:%i:%s'), a.user_no, a.depth \r\n" + 
						"from board a, user b \r\n" + 
						"where a.user_no = b.no \r\n" + 
						" AND (a.title LIKE '%" + kwd + "%')\r\n" + 
						"order by g_no desc, o_no asc";
				
				pstmt = conn.prepareCall(sql);
			}
	

			rs = pstmt.executeQuery();

			while (rs.next()) {
					long no = rs.getLong(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					long hit = rs.getLong(4);
					String writeDate = rs.getString(5);
					long user_no = rs.getLong(6);
					long depth = rs.getLong(7);
					BoardVo vo = new BoardVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setName(name);
					vo.setHit(hit);
					vo.setWriteDate(writeDate);
					vo.setUser_no(user_no);
					vo.setDepth(depth);
					list.add(vo);
				}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (stmt != null) 
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
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

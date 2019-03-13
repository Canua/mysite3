package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.CommentVo;

@Repository
public class CommentDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<CommentVo> getComments(long no) {
		List<CommentVo> list = sqlSession.selectList("comment.getComment", no);
		return list;
	}
	
	public int addComment(CommentVo commentVo) {
		return sqlSession.insert("comment.addComment", commentVo);
	}
	
	public int deleteComment(CommentVo commentVo) {
		int result = sqlSession.delete("comment.deleteComment", commentVo);
		result = (int) ((result == 1) ? commentVo.getNo() : -1);
		
		return result;
	}
}

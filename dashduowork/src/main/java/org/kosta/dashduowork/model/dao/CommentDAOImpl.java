package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.CommentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

	@Repository
	public class CommentDAOImpl implements CommentDAO {
		@Resource(name="sqlSessionTemplate")
		private SqlSessionTemplate sqlSessionTemplate;	

		@Override
		public void replyWrite(CommentVO covo) {
			System.out.println("CommentDAO"+covo);
			sqlSessionTemplate.insert("comment.replyWrite",covo);
			
		}
	/*
		@Override
		public List<CommentVO> selectByCommemtInnNo(CommentListVO cvo) {
			System.out.println("commentDAO select : "+cvo);
			return sqlSessionTemplate.selectList("comment.selectByCommemtInnNo",cvo);
		}
	*/
		@Override
		public void deleteReply(int commentNo) {
			System.out.println("댓글삭제 dao"+commentNo);
			sqlSessionTemplate.delete("comment.deleteReply",commentNo);
		}

		@Override
		public List<CommentVO> getCommentList(HashMap<String, String> param) {
			System.out.println("dao"+param);
			return sqlSessionTemplate.selectList("comment.getCommentList", param);
		}

		@Override
		public int totalContent(String innNo) {
			System.out.println("dao 토탈");
			return sqlSessionTemplate.selectOne("comment.totalContent", innNo);
		}

		
}

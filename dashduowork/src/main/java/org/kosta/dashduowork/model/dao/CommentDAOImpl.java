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
		
		/**
		 * @param covo : 페이지에서 댓글 쓴 정보를 담기 위한 그릇이다.
		 * @작성자 : 은수, 정은
		 * @Method설명 : 상세보기에 댓글추가하는 메서드
		 */
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
		/**
		 * @param commentNo ; 댓글번호를 받아온다
		 * @작성자 : 은수, 정은
		 * @Method설명 : 상세보기에서 댓글을 삭제하는 메서드이며  자신이 쓴 댓글만 삭제할 수 있다.
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

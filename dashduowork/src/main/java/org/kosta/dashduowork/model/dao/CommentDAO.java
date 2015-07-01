package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.CommentVO;

public interface CommentDAO {
	/**
	 * @param covo : 페이지에서 댓글 쓴 정보를 담기 위한 그릇이다.
	 * @작성자 : 은수, 정은
	 * @Method설명 : 상세보기에 댓글추가하는 메서드
	 */
	public void replyWrite(CommentVO covo);

/*	public List<CommentVO> selectByCommemtInnNo(CommentListVO cvo);*/
	/**
	 * @param commentNo ; 댓글번호를 받아온다
	 * @작성자 : 은수, 정은
	 * @Method설명 : 상세보기에서 댓글을 삭제하는 메서드이며  자신이 쓴 댓글만 삭제할 수 있다.
	 */
	public void deleteReply(int commentNo);

	public List<CommentVO> getCommentList(HashMap<String, String> param);

	public int totalContent(String innNo);

}
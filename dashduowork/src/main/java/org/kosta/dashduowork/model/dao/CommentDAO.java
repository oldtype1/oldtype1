package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.CommentVO;

public interface CommentDAO {

	public void replyWrite(CommentVO covo);

/*	public List<CommentVO> selectByCommemtInnNo(CommentListVO cvo);*/

	public void deleteReply(int commentNo);

	public List<CommentVO> getCommentList(HashMap<String, String> param);

	public int totalContent(String innNo);

}
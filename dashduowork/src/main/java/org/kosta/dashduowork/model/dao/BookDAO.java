package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.BookVO;
import org.kosta.dashduowork.model.vo.MemberVO;

public interface BookDAO {
	
	public abstract int getTotalPostingCount(MemberVO vo);
	public abstract List<BookVO> getmybooklist(HashMap<String, String> param);

}
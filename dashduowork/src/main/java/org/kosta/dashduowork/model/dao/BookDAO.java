package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.BookVO;
import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.TradeVO;

public interface BookDAO {
	
	public abstract int getTotalPostingCount(MemberVO vo);
	public abstract List<BookVO> getmybooklist(HashMap<String, String> param);
	public abstract void bookDelete(DeleteVO bdvo);
	public abstract int checkChildBookTable(int innNo);
	public abstract void bookDeleteOutDate();
	public abstract List<TradeVO> selectBookOutDate();

}
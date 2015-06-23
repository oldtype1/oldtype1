package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.TradeVO;

public interface TradeDAO {

	public abstract List<TradeVO> getmytradelist(HashMap<String, String> param);
	public abstract int getTotalPostingCount(MemberVO vo);
	public abstract void tradeDelete(DeleteVO tdvo);
	public abstract void insertTradeBookOutDate(TradeVO tvo);
	//6/23 별점 체크시 N-->Y 로 변경(insert시 딱 한번 실행) 
	public abstract void updateRatingCheck(int tradeNo);
}

package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.TradeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TradeDAOImpl implements TradeDAO{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	
	
	@Override
	public List<TradeVO> getmytradelist(HashMap<String, String> param) {
		return sqlSessionTemplate.selectList("inn.getmytradelist",param);
	}

	@Override
	public int getTotalPostingCount(MemberVO vo) {
		return sqlSessionTemplate.selectOne("inn.getTotalTradeCount",vo.getMemberId());
	}

	@Override
	public void tradeDelete(DeleteVO tdvo) {
			sqlSessionTemplate.delete("delete.tradeDelete",tdvo);
			//예약 테이블에서 예약번호로 지난 예약 지운다.
			sqlSessionTemplate.delete("delete.bookDelete", tdvo.getTrade_bookno());
	}
}

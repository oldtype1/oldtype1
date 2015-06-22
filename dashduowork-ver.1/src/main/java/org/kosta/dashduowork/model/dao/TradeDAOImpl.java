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
	
	//거래내역 목록 불러오기
	@Override
	public List<TradeVO> getmytradelist(HashMap<String, String> param) {
		return sqlSessionTemplate.selectList("inn.getmytradelist",param);
	}
	//거래내역 페이징빈 토탈게시물 수
	@Override
	public int getTotalPostingCount(MemberVO vo) {
		return sqlSessionTemplate.selectOne("inn.getTotalTradeCount",vo.getMemberId());
	}

	//book에서 아웃데이트 된것을 거래내역에 추가시켜준다!!!
	@Override
	public void insertTradeBookOutDate(TradeVO tlist) {
		sqlSessionTemplate.insert("scheduler.insertTradeBookOutDate",tlist);
	}

	//거래내역에서 삭제 6/18
	@Override
	public void tradeDelete(DeleteVO tdvo) {
			sqlSessionTemplate.delete("delete.tradeDelete",tdvo);
	}
}

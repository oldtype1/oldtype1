package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.BookVO;
import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.TradeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	
	
	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.BookDAO#getTotalPostingCount(org.kosta.dashduowork.model.vo.MemberVO)
	 */
	@Override
	public int getTotalPostingCount(MemberVO vo) {
		return sqlSessionTemplate.selectOne("inn.getTotalBookCount",vo.getMemberId());
	}
	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.BookDAO#getmybooklist(java.util.HashMap)
	 */
	@Override
	public List<BookVO> getmybooklist(HashMap<String, String> param) {
		return sqlSessionTemplate.selectList("inn.getmybooklist",param);
	}
	@Override
	public void bookDelete(DeleteVO bdvo) {
		System.out.println("예약 취소"+bdvo);
	sqlSessionTemplate.delete("delete.bookDelete", bdvo);
	}
	@Override
	public int checkChildBookTable(int innNo) {
		//6/19 주형- 숙소 삭제시 예약한 테이블이 있나 확인하는 메서드임 
		return sqlSessionTemplate.selectOne("inn.checkChildBookTable",innNo);
	}
	/***** 스케쥴러 ******/
	
	//6.19 주형-체크아웃 시간 지난 예약 목록을 가져온다.(거래목록에 넣기위해)
		@Override
		public List<TradeVO> selectBookOutDate() {
			List<TradeVO> tlist= sqlSessionTemplate.selectList("scheduler.selectBookOutDate");
			System.out.println("기한지난 예약목록: "+tlist);		
			return sqlSessionTemplate.selectList("scheduler.selectBookOutDate");
		}
	
	//6.19 주형- 기한 지난 예약테이블 삭제
	@Override
	public void bookDeleteOutDate() {
		sqlSessionTemplate.delete("scheduler.bookDeleteOutDate");
	}
	
	
	
}

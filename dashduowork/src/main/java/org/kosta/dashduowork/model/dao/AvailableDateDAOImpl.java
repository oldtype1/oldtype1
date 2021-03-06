package org.kosta.dashduowork.model.dao;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AvailableDateDAOImpl implements AvailableDateDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	
	@Override
	public void register(AvailableDateVO vo) {
		System.out.println("AvailableDateVO : "+vo);
		sqlSessionTemplate.insert("availabledate.register",vo);
	}
	
	@Override
	public void update(AvailableDateVO vo) {
		sqlSessionTemplate.update("availabledate.updatedate", vo);
	}
	@Override
	public AvailableDateVO selectAvailableDate(int innNo) {
		return sqlSessionTemplate.selectOne("availabledate.selectAvailableDate", innNo);
	}
	public AvailableDateVO selectByAvailableDateInnNo(String innNo){
		return sqlSessionTemplate.selectOne("availabledate.selectByInnNo", innNo);
	}
}

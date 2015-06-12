package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InnDAOImpl implements InnDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	

	@Override
	public int getTotalPostingCount(MemberVO vo) {
		return sqlSessionTemplate.selectOne("inn.getTotalPostingCount",vo.getMemberId());
	}

	@Override
	public List<InnVO> getmyinnlist(HashMap<String, String> param) {
		return sqlSessionTemplate.selectList("inn.getmyinnlist",param);
	}
	
	@Override //은식,동원
	public List<InnVO> selectInnByCityAndAcceptableNo(InnVO vo) {
		return sqlSessionTemplate.selectList("inn.selectInnByCityAndAcceptableNo", vo);
	}

	@Override //은식,동원
	public List<InnVO> selectInnByCheckedAmenity(AmenityVO vo) {
		return sqlSessionTemplate.selectList("inn.selectInnByCheckedAmenity", vo);
	}
}

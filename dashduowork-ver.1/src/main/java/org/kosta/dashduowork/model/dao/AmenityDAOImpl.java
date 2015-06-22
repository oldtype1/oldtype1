package org.kosta.dashduowork.model.dao;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AmenityDAOImpl implements AmenityDAO {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void register(AmenityVO vo) {
		System.out.println("AmenityVO : " + vo);
		sqlSessionTemplate.insert("amenity.register", vo);
	}
	@Override
	public void update(AmenityVO avo) {
		sqlSessionTemplate.update("amenity.updateAmenity", avo);
	}

	@Override
	public AmenityVO selectAmenity(String innNo) {
		return sqlSessionTemplate.selectOne("amenity.selectAmenity", innNo);
	}
}

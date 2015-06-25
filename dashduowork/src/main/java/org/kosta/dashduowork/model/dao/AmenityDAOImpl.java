package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AmenityDAOImpl implements AmenityDAO {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public void update(AmenityVO avo) {
		sqlSessionTemplate.update("amenity.updateAmenity", avo);
	}

	@Override
	public AmenityVO selectAmenity(String innNo) {
		return sqlSessionTemplate.selectOne("amenity.selectAmenity", innNo);
	}

	@Override
	public void register(AmenityVO vo) {
		sqlSessionTemplate.insert("amenity.register", vo);
		
	}
}

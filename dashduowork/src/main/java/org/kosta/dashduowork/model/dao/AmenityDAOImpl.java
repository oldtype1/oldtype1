package org.kosta.dashduowork.model.dao;


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
	public List<AmenityVO> selectAmenity(String innNo) {
		return sqlSessionTemplate.selectList("amenity.selectAmenity", innNo);
	}

	public void delete(int innNo){
		sqlSessionTemplate.delete("amenity.delete", innNo);
	}
	
	@Override
	public void register(AmenityVO vo) {
		sqlSessionTemplate.insert("amenity.register", vo);
		
	}

}

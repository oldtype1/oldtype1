package org.kosta.dashduowork.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.InnPicCompVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InnPicCompDAOImpl implements InnPicCompDAO {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void register(InnPicCompVO vo) {
		System.out.println("InnPicCompVO : " + vo);
			sqlSessionTemplate.insert("innPic.register", vo);
		}
	
	@Override
	public InnPicCompVO getMyPicList(int innNo) {
		return sqlSessionTemplate.selectOne("innPic.getMyPicList",innNo);
	}
	
	@Override
	public List<InnPicCompVO> selectByInnNo(String innNo) {
		System.out.println("InnPicCompVO : "+ innNo);
		return sqlSessionTemplate.selectList("innPic.selectByInnNo",innNo);
	}
	}

package org.kosta.dashduowork.model.dao;

import javax.annotation.Resource;
import org.kosta.dashduowork.model.vo.InnRatingVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InnRatingDAOImpl implements InnRatingDAO {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int checkRatingTable(int innNo) {
		int count = sqlSessionTemplate.selectOne("rating.checkRatingTable",innNo);
		System.out.println("이 숙소에 이미 별점등록?"+count);
		return count;
	}

	@Override
	public void updateInnRating(InnRatingVO irv) {
		System.out.println("별점 업데이트: "+irv);
		sqlSessionTemplate.update("rating.updateInnRating",irv);
	}

	@Override
	public void insertNewInnRating(InnRatingVO irv) {
		System.out.println("뉴숙소 별점 추가 :"+irv);
		sqlSessionTemplate.insert("rating.insertNewInnRating",irv);
	}

}

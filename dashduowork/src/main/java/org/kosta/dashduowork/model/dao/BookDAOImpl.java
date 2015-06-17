package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.BookDeleteVO;
import org.kosta.dashduowork.model.vo.BookVO;
import org.kosta.dashduowork.model.vo.MemberVO;
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
	public void bookDelete(BookDeleteVO bdvo) {
	sqlSessionTemplate.delete("inn.bookDelete", bdvo);
	}
}

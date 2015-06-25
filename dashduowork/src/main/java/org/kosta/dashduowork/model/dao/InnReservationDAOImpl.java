package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.InnReservationVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class InnReservationDAOImpl implements InnReservationDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	


	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.InnReservationDAO#getTotalPostingCount(org.kosta.dashduowork.model.vo.MemberVO)
	 */
	@Override
	public int getTotalPostingCount(MemberVO vo) {
		return sqlSessionTemplate.selectOne("inn.getTotalInnReservationCount",vo.getMemberId());
	}

	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.InnReservationDAO#getMyInnReservationList(java.util.HashMap)
	 */
	@Override
	public List<InnReservationVO> getMyInnReservationList(HashMap<String, String> param) {
		System.out.println("getMyInnReservationList DAO    "+param);
		return sqlSessionTemplate.selectList("inn.getMyInnReservationList",param);
	}
}

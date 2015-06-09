package org.kosta.dashduowork.model.dao;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	
	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.dao.MemberDAO#login(org.kosta.dashduowork.model.vo.MemberVO)
	 */
	@Override
	public MemberVO login(MemberVO vo){
		return (MemberVO) sqlSessionTemplate.selectOne("member.login",vo);			
	}	
	
}

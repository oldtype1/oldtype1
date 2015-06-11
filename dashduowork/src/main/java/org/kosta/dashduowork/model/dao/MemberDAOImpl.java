package org.kosta.dashduowork.model.dao;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kosta.dashduowork.model.dao.MemberDAO#login(org.kosta.dashduowork
	 * .model.vo.MemberVO)
	 */
	@Override
	public MemberVO login(MemberVO vo) {
		return (MemberVO) sqlSessionTemplate.selectOne("member.login", vo);
	}

	public MemberVO findMemberById(String memberId) {
		return (MemberVO) sqlSessionTemplate.selectOne("member.findMemberById",
				memberId);
	}

	@Override
	public void updateMemberInfo(MemberVO vo) {
		sqlSessionTemplate.update("member.updateMemberInfo", vo);
	}

	@Override
	public void memberSecession(String memberId) {
		sqlSessionTemplate.delete("member.memberSecession", memberId);
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSessionTemplate.insert("member.register", vo);
	}

	@Override
	public void insertProfilePic(ProfilePicVO vo) {
		sqlSessionTemplate.insert("profilePic.insertProfilePic", vo);
	}
}

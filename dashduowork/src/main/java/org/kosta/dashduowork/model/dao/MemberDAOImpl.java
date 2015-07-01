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
	
	/**
	 * 로그인
	   * @param vo : 회원정보를 가져오는 객체이다
	   * @return
	   * @작성자 : 은수, 정은
	   * @Method설명 : 회원 로그인 하는 메서드
	   */
	@Override
	public MemberVO login(MemberVO vo) {
		//DB의 member정보를 select 하여 입력한 정보와 일치하는 지 확인
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
	public void updateProfilePic(ProfilePicVO pvo) {
		sqlSessionTemplate.update("profilePic.updateProfilePic", pvo);
	}

	@Override
	public void memberSecession(String memberId) {
		sqlSessionTemplate.delete("member.memberSecession", memberId);
	}

	@Override
	public String memberPasswordcheck(String memberId) {
		return sqlSessionTemplate.selectOne("member.memberPasswordcheck",
				memberId);
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSessionTemplate.insert("member.register", vo);
	}

	@Override
	public void insertProfilePic(ProfilePicVO vo) {
		sqlSessionTemplate.insert("profilePic.insertProfilePic", vo);
	}

	@Override
	public ProfilePicVO selectProfilePic(String memberId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("profilePic.selectProfilePic",
				memberId);
	}

	@Override
	public String memberIdcheck(String memberId) {
		System.out.println("dao에서 idcheck" + memberId);
		return sqlSessionTemplate.selectOne("member.memberIdCheck", memberId);
	}
	
	public MemberVO selectPasswordByAnswer(MemberVO vo){
		System.out.println("dao에서 memberVO check" + vo);
		return sqlSessionTemplate.selectOne("member.selectPasswordByAnswer", vo);
	}
	@Override
	public MemberVO getMemberAccountByInnNo(String innNo) {
		return sqlSessionTemplate.selectOne("member.getMemberAccountByInnNo", innNo);
	}
}

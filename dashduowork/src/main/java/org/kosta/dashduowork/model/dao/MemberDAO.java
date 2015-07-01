
package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;

public interface MemberDAO {
	  /**
	   * @param vo : 회원정보를 가져오는 객체이다
	   * @return
	   * @작성자 : 은수, 정은
	   * @Method설명 : 회원 로그인 하는 메서드
	   */
	  public abstract MemberVO login(MemberVO vo);
   	  //아이디 찾기
      public abstract MemberVO findMemberById(String memberId);     
      //회원수정
      public abstract void updateMemberInfo(MemberVO vo);
      public abstract void updateProfilePic(ProfilePicVO pvo);
      //회원탈퇴
      public abstract void memberSecession(String memberId);
      //비밀번호 확인
      public abstract String memberPasswordcheck(String memberId);
      //회원가입
      public abstract void insertMember(MemberVO vo);
      public abstract void insertProfilePic(ProfilePicVO vo);
      //회원 사진 찾기
      public abstract ProfilePicVO selectProfilePic(String memberId);
      //아이디 중복 확인
      public abstract String memberIdcheck(String memberId);
      // 패스워드 분실시 찾는 메서드
      public MemberVO selectPasswordByAnswer(MemberVO vo);
      //무통장입금시 master 계좌번호 찾는 메서드
    public abstract MemberVO getMemberAccountByInnNo(String innNo);
}
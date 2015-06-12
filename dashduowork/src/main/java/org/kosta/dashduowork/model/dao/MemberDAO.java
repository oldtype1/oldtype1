
package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;

public interface MemberDAO {

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
}

package org.kosta.dashduowork.model.service;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;

import Exception.ChildBookTableException;

public interface MemberService {
	  
	/**
	   * @param vo : 회원정보를 가져오는 객체이다
	   * @return
	   * @작성자 : 은수, 정은
	   * @Method설명 : 회원 로그인 하는 메서드
	   */
	  public abstract MemberVO login(MemberVO vo);
      public abstract MemberVO findMemberById(String memberId);
      public abstract void updateMemberInfo(MemberVO mvo,ProfilePicVO pvo, 
    		  String uploadPath, String viewPath) throws Exception;
      public abstract void memberSecession(String memberId) throws ChildBookTableException;
      public abstract String memberPasswordcheck(String memberId);
      public void memberRegister(MemberVO mvo, ProfilePicVO pvo, 
    		  String viewPath, String uploadPath) throws Exception;
      public abstract ProfilePicVO selectProfilePic(String memberId);
      public abstract String memberIdcheck(String memberId);
      public MemberVO searchPass(MemberVO vo);

}

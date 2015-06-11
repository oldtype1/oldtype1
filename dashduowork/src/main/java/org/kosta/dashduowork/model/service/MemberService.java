package org.kosta.dashduowork.model.service;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;

public interface MemberService {

	public abstract MemberVO login(MemberVO vo);
	   public abstract MemberVO findMemberById(String memberId);
	   public abstract void updateMemberInfo(MemberVO vo);
	   public void memberSecession(String memberId);
	   public void memberRegister(MemberVO mvo, ProfilePicVO pvo);


}
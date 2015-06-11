package org.kosta.dashduowork.model.service;

import org.kosta.dashduowork.model.vo.MemberVO;

public interface MemberService {

	public abstract MemberVO login(MemberVO vo);
	   public abstract MemberVO findMemberById(String memberId);
	   public abstract void updateMemberInfo(MemberVO vo);
	   public void memberSecession(String memberId);
	
}
package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.MemberVO;

public interface MemberDAO {

	public abstract MemberVO login(MemberVO vo);
	   public abstract MemberVO findMemberById(String memberId);
	   public abstract void updateMemberInfo(MemberVO vo);
	   public void memberSecession(String memberId);
}
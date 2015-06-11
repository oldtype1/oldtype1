package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;

public interface MemberDAO {

	public abstract MemberVO login(MemberVO vo);
	   public abstract MemberVO findMemberById(String memberId);
	   public abstract void updateMemberInfo(MemberVO vo);
	   public void memberSecession(String memberId);
		//6 .10 
		
		public abstract void insertMember(MemberVO vo);
		public abstract void insertProfilePic(ProfilePicVO vo);
}
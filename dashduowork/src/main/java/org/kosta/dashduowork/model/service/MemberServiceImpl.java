package org.kosta.dashduowork.model.service;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.dao.MemberDAO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Resource(name="memberDAOImpl")
	private MemberDAO memberDAO;
	
	/* (non-Javadoc)
	 * @see org.kosta.dashduowork.model.service.MemberService#login(org.kosta.dashduowork.model.vo.MemberVO)
	 */
	@Override
	public MemberVO login(MemberVO vo){
		return memberDAO.login(vo);
	}
}

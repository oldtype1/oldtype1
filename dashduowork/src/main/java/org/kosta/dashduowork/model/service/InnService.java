package org.kosta.dashduowork.model.service;

import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.MemberVO;

public interface InnService {
	public InnListVO getmyinnlist(MemberVO vo, String pageNo);
}

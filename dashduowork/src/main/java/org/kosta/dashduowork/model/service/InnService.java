package org.kosta.dashduowork.model.service;

import java.util.List;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;

public interface InnService {
	public InnListVO getmyinnlist(MemberVO vo, String pageNo);
	public List<InnVO> findInnByCheckedAmenity(AmenityVO vo); //은식, 동원
}

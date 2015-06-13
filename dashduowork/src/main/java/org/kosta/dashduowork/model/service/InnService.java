package org.kosta.dashduowork.model.service;

import java.util.List;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.SearchVO;

public interface InnService {
	public InnListVO getmyinnlist(MemberVO vo, String pageNo);
	public List<InnVO> findInnByCheckedAmenity(AmenityVO vo); //은식, 동원
	public List<InnVO> findInnByCityAndAcceptableNo(SearchVO vo); //은식, 동원
	public List<InnVO> findInnByCityAndDateAndAcceptableNo(SearchVO vo); //은식, 동원
	public BookListVO getmybooklist(MemberVO vo, String pageNo);
}

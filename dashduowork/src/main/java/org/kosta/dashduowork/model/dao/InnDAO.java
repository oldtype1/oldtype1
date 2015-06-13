package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.SearchVO;


public interface InnDAO {
	
	public abstract List<InnVO> getmyinnlist(HashMap<String,String> param);

	public abstract int getTotalPostingCount(MemberVO vo);
	
	public abstract List<InnVO> selectInnByCityAndAcceptableNo(InnVO vo); //은식,동원
	public abstract List<InnVO> selectInnByCheckedAmenity(AmenityVO vo); //은식,동원
	public abstract List<InnVO> selectInnByCityAndAcceptableNo(SearchVO vo); //은식,동원
	public abstract List<InnVO> selectInnByCityAndDateAndAcceptableNo(SearchVO vo); //은식,동원

}
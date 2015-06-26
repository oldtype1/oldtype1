package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.FilterVO;
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

	public abstract void register(InnVO vo);
	// 6/15일 추가
	public abstract List<InnVO> selectInnByCityAndDateAndAcceptableNoWithFilter(FilterVO vo);
	public abstract List<InnVO> selectInnByCityAndAcceptableNoWithFilter(FilterVO vo);
	public InnVO selectInn(String innNo); // 은수
	//6/17일 추가
	public abstract List<InnVO> selectInnCityListByInnCityCharacter(SearchVO vo);

	public abstract void innDelete(DeleteVO idvo);
	
	//6/18일 추가
	public abstract List<InnVO> selectInnByCityAndAcceptableNoWithPrice(FilterVO vo);
	public abstract List<InnVO> selectInnByCityAndDateAndAcceptableNoWithPrice(FilterVO vo);
	//6/19일 추가 -윤정-
	public AmenityVO selectAmenity(String innNo);
	public void updateInnInfo(InnVO ivo);
	// 6/19 은수
	public void updateInnAvailabilityOff(String innNo);
	public void updateInnAvailabilityOn(String innNo);
	//6/19 주형 --> 숙소가능여부 변경
	public abstract void updateinnAvailabilitytoNo(int innNo);
	public abstract void updateinnAvailabilitytoYes(int innNo);
	
	// 6/25일 추가 검색메서드
	public abstract List<InnVO> selectInnByWordAndAcceptNo(FilterVO fvo);
	public abstract List<InnVO> selectInnByWordAndAcceptNoAndDate(FilterVO fvo);
	public abstract List<InnVO> selectInnByWordAndAcceptNoWithPrice(FilterVO fvo);
	public abstract List<InnVO> selectInnByWordAndAcceptNoAndDateWithPrice(FilterVO fvo);
	//윤정
	public abstract InnVO getInnByInnNo(int innNo);

}



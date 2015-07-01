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
	public abstract void register(InnVO vo);
	public InnVO selectInn(String innNo); // 은수
	//6/17일 추가(지역자동완성)
	public abstract List<InnVO> selectInnCityListByInnCityCharacter(FilterVO fvo);

	public abstract void innDelete(DeleteVO idvo);
	
	//6/19일 추가 -윤정-
	public AmenityVO selectAmenity(String innNo);
	public void updateInnInfo(InnVO ivo);
	// 6/19 은수
	public void updateInnAvailabilityOff(String innNo);
	public void updateInnAvailabilityOn(String innNo);
	//6/19 주형 --> 숙소가능여부 변경
	public abstract void updateinnAvailabilitytoNo(int innNo);
	public abstract void updateinnAvailabilitytoYes(int innNo);
	
//	// 6/25일 추가 검색메서드
//	public abstract List<InnVO> selectInnByWordAndAcceptNo(FilterVO fvo);
//	public abstract List<InnVO> selectInnByWordAndAcceptNoAndDate(FilterVO fvo);
	public abstract List<InnVO> selectInnByWordAndAcceptNoWithPrice(FilterVO fvo);
	public abstract List<InnVO> selectInnByWordAndAcceptNoAndDateWithPrice(FilterVO fvo);
	
	//이거 초기조건 이름 / 인원수
	public abstract List<InnVO> selectInnByWordAndAcceptNo(HashMap<String, Object> param);
	public abstract int getTotalSearchingCountWithNoDate(	HashMap<String, Object> param);
	//이거 초기조건 이름 / 날짜 / 인원수
	public abstract List<InnVO> selectInnByWordAndAcceptNoAndDate(HashMap<String, Object> param);
	public abstract int getTotalSearchingCountWithDate(	HashMap<String, Object> param);
	
	//윤정
	public abstract InnVO getInnByInnNo(int innNo);
}



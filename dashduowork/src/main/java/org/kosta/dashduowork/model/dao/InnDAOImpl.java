package org.kosta.dashduowork.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnPicCompVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InnDAOImpl implements InnDAO {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;	

	@Override
	public int getTotalPostingCount(MemberVO vo) {
		return sqlSessionTemplate.selectOne("inn.getTotalInnCount",vo.getMemberId());
	}

	@Override
	public void innDelete(DeleteVO idvo) {
		sqlSessionTemplate.delete("delete.innDelete", idvo);
	}
	
	@Override
	public List<InnVO> getmyinnlist(HashMap<String, String> param) {
		return sqlSessionTemplate.selectList("inn.getmyinnlist",param);
	}

	@Override
	public void register(InnVO vo){
		System.out.println("inndao : "+vo);
		sqlSessionTemplate.insert("inn.register", vo);
	}
	
	// 은수
	public InnVO selectInn(String innNo){
		return sqlSessionTemplate.selectOne("inn.select", innNo);
		}
	
	//FIXME
	public void updateInnAvailabilityOff(String innNo){
		 sqlSessionTemplate.update("inn.updateInnAvailabilityOff", innNo);
	}
	//FIXME
	public void updateInnAvailabilityOn(String innNo){
		 sqlSessionTemplate.update("inn.updateInnAvailabilityOn", innNo);
	}
	
	// 6/16일 추가내용------------------------ 숙소번호로 사진경로 받아오기
	public InnPicCompVO selectOneInnPicPath(int innNo){
		return sqlSessionTemplate.selectOne("innPic.selectOneInnPicPath", innNo);
	}
	
	//6/19일 추가 -윤정-
	@Override
	public AmenityVO selectAmenity(String innNo) {
		return sqlSessionTemplate.selectOne("inn.selectAmenity", innNo);
	}

	@Override
	public void updateInnInfo(InnVO ivo) {
		sqlSessionTemplate.update("inn.updateInnInfo", ivo);
	}

	//6/19 사용 가능여부 변경
	@Override
	public void updateinnAvailabilitytoNo(int innNo) {
		System.out.println("노로 바꾼다");
		sqlSessionTemplate.update("inn.updateinnAvailabilitytoNo",innNo);
	}
	@Override
	public void updateinnAvailabilitytoYes(int innNo) {
		System.out.println("예스로 바꾼다");
		sqlSessionTemplate.update("inn.updateinnAvailabilitytoYes",innNo);
	}
	
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	// 
	// 									검색 관련 메서드
	//
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////	
	//6/17일 추가(지역 자동완성)
	@Override
	public List<InnVO> selectInnCityListByInnCityCharacter(FilterVO fvo) {
		return sqlSessionTemplate.selectList("inn.selectInnCityListByInnCityCharacter", fvo);
	}
	/**
	 * @param fvo : 검색요구조건에 해당하는 fvo를 함께 받기 위해 사용.
	 * @return : 검색 요구조건에 맞는 숙소들을 받기 위해 list 사용.
	 * @작성자 : 은식,동원
	 * @Method 설명 : 1) 숙소 검색 조건에 해당하는 숙소들을 mybatis와 직접적으로 연동해서 받아오는 메서드
	 * 						 	  2) 필터검색 포함+숙소 사용 희망일 포함안함
	 */
	@Override
	public List<InnVO> selectInnByWordAndAcceptNoWithPrice(FilterVO fvo){
		return sqlSessionTemplate.selectList("inn.selectInnByWordAndAcceptNoWithPriceFilter", fvo);
	}
	
	/**
	 * @param fvo : 검색요구조건에 해당하는 fvo를 함께 받기 위해 사용.
	 * @return : 검색 요구조건에 맞는 숙소들을 받기 위해 list 사용.
	 * @작성자 : 은식,동원
	 * @Method 설명 : 1) 숙소 검색 조건에 해당하는 숙소들을 mybatis와 직접적으로 연동해서 받아오는 메서드
	 * 						 	  2) 필터검색 포함+숙소 사용 희망일 포함
	 */
	@Override
	public List<InnVO> selectInnByWordAndAcceptNoAndDateWithPrice(FilterVO fvo){
		return sqlSessionTemplate.selectList("inn.selectInnByWordAndAcceptNoAndDateWithPriceFilter", fvo);
	}
	@Override
	public InnVO getInnByInnNo(int innNo) {
		return sqlSessionTemplate.selectOne("inn.getInnByInnNo", innNo);
	}
	// 6/25일 추가 검색메서드
	// 기본 이름 + 인원수
	
	/**
	 * @param param : 페이징 처리를 하기위한 pageNo와 검색요구조건에 해당하는 fvo를 함께 받기 위해서 HashMap사용. 
	 * @return : 검색 요구조건에 맞는 숙소들을 받기 위해 list 사용.
	 * @작성자 : 은식,동원
	 * @Method 설명 : 1) 숙소 검색 조건에 해당하는 숙소들을 mybatis와 직접적으로 연동해서 받아오는 메서드.
	 * 						 2) 숙소지역명 + 사용 인원 만으로 숙소 검색(숙소 사용 희망일 포함안함)
	 */
	@Override
	public List<InnVO> selectInnByWordAndAcceptNo(HashMap<String, Object> param){
		System.out.println("=============InnDAIImpl영역입장===========");
		System.out.println("selectInnByWordAndAcceptNo에 들어가는데 과연 나올까요???");
		List<InnVO> list = null;
		list =  sqlSessionTemplate.selectList("inn.selectInnByWordAndAcceptNo", param);
		System.out.println("InnDAOImpl에서 받은 list입니다."+ list);
		return list;
	}
	
	
	/**
	 * @param param : 페이지 넘버를 받아오기 위해 사용.
	 * @return : 검색 요구조건에 해당하는 숙소들의 총 개수를 보내기 위해 사용.
	 * @작성자 : 은식, 동원
	 * @Method 설명 : 1) 숙소 검색 조건에 해당하는 숙소들을 mybatis와 직접적으로 연동해서 받아오는 메서드. 
	 * 						 2) 숙소지역명 + 사용 인원 만으로 검색(숙소 사용 희망일 포함안함)한 숙소 목록 개수를 받아오기 위한 메서드
	 */
	@Override
	public int getTotalSearchingCountWithNoDate(HashMap<String, Object> param){
		return sqlSessionTemplate.selectOne("inn.getTotalSearchingCountWithNoDate",param);
	}
	
	//기본 이름 +인원수 ++ 날짜
	/**
	 * @param param : 페이징 처리를 하기위한 pageNo와 검색요구조건에 해당하는 fvo를 함께 받기 위해서 HashMap사용. 
	 * @return : 검색 요구조건에 맞는 숙소들을 받기 위해 list 사용.(필터검색아님)
	 * @작성자 : 은식,동원
	 * @Method 설명 : 1) 숙소 검색 조건에 해당하는 숙소들을 mybatis와 직접적으로 연동해서 받아오는 메서드.(필터검색아님)
	 * 						 2) 숙소지역명 + 숙소 사용 희망일(시작 && 끝) + 사용 인원으로 숙소 검색
	 */
	@Override
	public List<InnVO> selectInnByWordAndAcceptNoAndDate(HashMap<String, Object> param){
		System.out.println("=============InnDAIImpl영역입장===========");
		System.out.println("selectInnByWordAndAcceptNoAndDate에 들어가는데 과연 나올까요???");
		List<InnVO> list = null;
		list = sqlSessionTemplate.selectList("inn.selectInnByWordAndAcceptNoAndDate", param);
		System.out.println("InnDAOImpl에서 받은 list입니다."+ list);
		return list;
	}
	
	
	/**
	 * @param param : 페이지 넘버를 받아오기 위해 사용.
	 * @return : 검색 요구조건에 해당하는 숙소들의 총 개수를 보내기 위해 사용.
	 * @작성자 : 은식, 동원
	 * @Method 설명 : 숙소지역명 + 숙소 사용 희망일(시작 && 끝) + 사용 인원으로 검색한 숙소 목록 개수를 받아오기 위한 메서드
	 */
	@Override
	public int getTotalSearchingCountWithDate(HashMap<String, Object> param){
		return sqlSessionTemplate.selectOne("inn.getTotalSearchingCountWithDate",param);
	}
}



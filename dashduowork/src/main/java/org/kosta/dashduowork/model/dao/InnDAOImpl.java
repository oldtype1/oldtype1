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
	
	@Override //은식,동원
	public List<InnVO> selectInnByCityAndAcceptableNo(InnVO vo) {
		return sqlSessionTemplate.selectList("inn.selectInnByCityAndAcceptableNo", vo);
	}

	@Override //은식,동원
	public List<InnVO> selectInnByCheckedAmenity(AmenityVO vo) {
		return sqlSessionTemplate.selectList("inn.selectInnByCheckedAmenity", vo);
	}
	//plus+++++++++++++++++++++++++++++++++++++++++++
	public List<InnVO> selectInnByCityAndAcceptableNo(SearchVO vo){
		return sqlSessionTemplate.selectList("inn.selectInnByCityAndAcceptableNo", vo);
	}
	public List<InnVO> selectInnByCityAndDateAndAcceptableNo(SearchVO vo){ //은식,동원
		return sqlSessionTemplate.selectList("inn.selectInnByCityAndDateAndAcceptableNo", vo);
	}

	@Override
	public void register(InnVO vo){
		System.out.println("inndao : "+vo);
		sqlSessionTemplate.insert("inn.register", vo);
	}
	// 6/15일 추가내용******************
	public List<InnVO> selectInnByCityAndDateAndAcceptableNoWithFilter(FilterVO vo){ //지역&날짜&인원+필터
		return sqlSessionTemplate.selectList("inn.selectInnByCityAndDateAndAcceptableNoWithFilter", vo);
	}
	public List<InnVO> selectInnByCityAndAcceptableNoWithFilter(FilterVO vo){ //지역&인원+필터
		return sqlSessionTemplate.selectList("inn.selectInnByCityAndAcceptableNoWithFilter", vo);
	}
	
	// 은수
	public InnVO selectInn(String innNo){
		return sqlSessionTemplate.selectOne("inn.select", innNo);
		}
	
	// 6/16일 추가내용------------------------ 숙소번호로 사진경로 받아오기
	public InnPicCompVO selectOneInnPicPath(int innNo){
		return sqlSessionTemplate.selectOne("innPic.selectOneInnPicPath", innNo);
	}
	//6/17일 추가(지역 자동완성)
	@Override
	public List<InnVO> selectInnCityListByInnCityCharacter(SearchVO vo) {
		return sqlSessionTemplate.selectList("inn.selectInnCityListByInnCityCharacter",vo);
	}
}

package org.kosta.dashduowork.model.service;

import java.util.List;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.kosta.dashduowork.model.vo.BookDeleteVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnPicCompVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.kosta.dashduowork.model.vo.WishListDeleteVO;
import org.kosta.dashduowork.model.vo.WishListListVO;

public interface InnService {
	public InnListVO getmyinnlist(MemberVO vo, String pageNo);
	public List<InnVO> findInnByCheckedAmenity(AmenityVO vo); //은식, 동원
	public List<InnVO> findInnByCityAndAcceptableNo(SearchVO vo); //은식, 동원
	public List<InnVO> findInnByCityAndDateAndAcceptableNo(SearchVO vo); //은식, 동원
	public BookListVO getmybooklist(MemberVO vo, String pageNo);
	public WishListListVO getmywishlist(MemberVO vo, String pageNo);
	public void wishListDelete(WishListDeleteVO wdvo);
	public void bookDelete(BookDeleteVO bdvo);
	
	public void registerInn(InnVO ivo);
	public void registerInnPic(InnPicCompVO vo);
	public void registerInnEtc(AmenityVO avo, AvailableDateVO avvo);
	
	//6/15일 추가
	public List<InnVO> findInnByCityAndDateAndAcceptableNoWithFilter(FilterVO vo);
	public List<InnVO> findInnByCityAndAcceptableNoWithFilter(FilterVO vo);
}

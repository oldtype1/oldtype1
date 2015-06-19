package org.kosta.dashduowork.model.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.BookVO;
import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnPicCompVO;
import org.kosta.dashduowork.model.vo.InnReservationListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.kosta.dashduowork.model.vo.TradeListVO;
import org.kosta.dashduowork.model.vo.WishListListVO;

public interface InnService {
   public InnListVO getmyinnlist(MemberVO vo, String pageNo);
//   public List<InnVO> findInnByCheckedAmenity(AmenityVO vo); //은식, 동원
   public InnListVO findInnByCityAndAcceptableNo(SearchVO vo); //은식, 동원
   public InnListVO findInnByCityAndDateAndAcceptableNo(SearchVO vo); //은식, 동원
   public BookListVO getmybooklist(MemberVO vo, String pageNo);
   public WishListListVO getmywishlist(MemberVO vo, String pageNo);
   
   public void registerInn(InnVO ivo);
   public void registerInnPic(InnPicCompVO vo);
   public void registerInnEtc(AmenityVO avo, AvailableDateVO avvo);
	public TradeListVO getmytradelist(MemberVO vo, String pageNo);//거래내역 
   
   //6/15일 추가
	public InnListVO findInnByCityAndDateAndAcceptableNoWithFilter(FilterVO vo);
	public InnListVO findInnByCityAndAcceptableNoWithFilter(FilterVO vo);

	public InnReservationListVO getMyInnReservationList(MemberVO vo, String pageNo);
	public Map<String,Object> selectInn(String innNo); // 은수
	//6/16추가
	public List<InnVO> findInnCityListByInnCityCharacter(SearchVO vo);
   
	public List<InnPicCompVO> selectByInnNo(String innNo);
	public ProfilePicVO selectByProfilePic(String memberId);
	   /**주형 윤정 마이페이지 내역 삭제 메서드**/
	public void wishListDelete(DeleteVO wdvo);
	public void bookDelete(DeleteVO bdvo);
	public void innDelete(DeleteVO idvo);
	public void tradeDelete(DeleteVO idvo);
	public boolean checkChildBookTable(int innNo);
	
	//6/18일 추가
	public InnListVO findInnByCityAndAcceptableNoWithPrice(FilterVO vo);
	public InnListVO findInnByCityAndDateAndAcceptableNoWithPrice(FilterVO vo);
	//6/19일 추가-윤정- 등록숙소 수정 관련
	public AmenityVO selectAmenity(String innNo);
	public void updateInnInfo(InnVO ivo);
	public void updateInnEtc(AmenityVO avo, AvailableDateVO avvo);
	public AvailableDateVO selectAvailableDate(int innNo);
	public abstract List<InnPicCompVO> selectFilePathByInnNo(int innNo);
	public abstract void deleteInnPic(int innPicNo);
	public AvailableDateVO selectByAvailableDateInnNo(String innNo);
<<<<<<< HEAD
	public HashMap<String, Object> bookInsert(BookVO bvo, String innNo, String memberId) throws ParseException;
=======
	public Boolean bookInsert(BookVO bvo, String innNo, String memberId) throws ParseException;
	public void updateinnAvailability(int innNo, String innAvailability);
>>>>>>> branch 'master' of https://github.com/oldtype1/oldtype1.git
}

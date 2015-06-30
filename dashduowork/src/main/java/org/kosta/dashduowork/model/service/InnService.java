package org.kosta.dashduowork.model.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.BookVO;
import org.kosta.dashduowork.model.vo.CommentListVO;
import org.kosta.dashduowork.model.vo.CommentVO;
import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnPicCompVO;
import org.kosta.dashduowork.model.vo.InnRatingVO;
import org.kosta.dashduowork.model.vo.InnReservationListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.kosta.dashduowork.model.vo.TradeListVO;
import org.kosta.dashduowork.model.vo.WishListListVO;
import org.kosta.dashduowork.model.vo.WishListVO;

import Exception.ChildBookTableException;
import Exception.NoInnException;

public interface InnService {
   /**마이페이지 -->세션 exception 처리 -->안하고 컨트롤러에서 처리..!**/
   public InnListVO getmyinnlist(MemberVO vo, String pageNo);
   public BookListVO getmybooklist(MemberVO vo, String pageNo);
   public WishListListVO getmywishlist(MemberVO vo, String pageNo);
   public InnReservationListVO getMyInnReservationList(MemberVO vo, String pageNo);
   public TradeListVO getmytradelist(MemberVO vo, String pageNo);//거래내역 
   
   public void registerInn(InnVO ivo);
   public void registerInnPic(InnPicCompVO vo);
   public void registerInnEtc(AmenityVO avo, AvailableDateVO avvo);

	public Map<String,Object> selectInn(String innNo) throws NoInnException; // 은수
	//6/16추가
	public List<InnVO> findInnCityListByInnCityCharacter(FilterVO fvo);
   
	public List<InnPicCompVO> selectByInnNo(String innNo);
	public ProfilePicVO selectByProfilePic(String memberId);
	   /**주형 윤정 마이페이지 내역 삭제 메서드**/
	public void wishListDelete(DeleteVO wdvo);
	public void bookDelete(DeleteVO bdvo);
	public void innDelete(DeleteVO idvo) throws ChildBookTableException;
	public void tradeDelete(DeleteVO idvo);
	
	//6/19일 추가-윤정- 등록숙소 수정 관련
	public List<AmenityVO> selectAmenity(String innNo);
	public void updateInnInfo(InnVO ivo);
	public void updateInnEtc(AmenityVO avo, AvailableDateVO avvo);
	public AvailableDateVO selectAvailableDate(int innNo);
	public abstract List<InnPicCompVO> selectFilePathByInnNo(int innNo);
	public abstract void deleteInnPic(int innPicNo);
	public AvailableDateVO selectByAvailableDateInnNo(String innNo);

	public HashMap<String, Object> bookInsert(BookVO bvo, String innNo, String memberId) throws ParseException;
	public void updateinnAvailability(int innNo, String innAvailability);
	
	//위시리스트
	public abstract void wishlistreg(WishListVO wvo);
	public int wishCheck(WishListVO wvo);
	//위시리스트취소
	public abstract int getWishListNoByInnNo(InnVO ivo);
	//별점주기
	public void ratingInn(InnRatingVO irv,int tradeNo) throws NoInnException;
	
<<<<<<< HEAD
	//6/25 검색메서드추가
//	public abstract InnListVO findInnByWordAndAcceptNoAndDate(FilterVO fvo);
	public abstract InnListVO findInnByWordAndAcceptNoAndDate(String pageNo, FilterVO fvo);
	public abstract InnListVO findInnByWordAndAcceptNoAndDateWithPrice(FilterVO fvo);
=======
>>>>>>> branch 'master' of https://github.com/oldtype1/oldtype1.git
	//윤정 추가
	public abstract InnVO getInnByInnNo(int innNo);
	public abstract MemberVO getMemberAccountByInnNo(String innNo);
	public abstract void bookingInn(BookVO bvo);
	//상세보기--별 점수를 불러오기 6/29 주형
	public int selectInnRating(int innNo2);
	//별점 테이블을 가져옴 
	public int selectPeopleNum(int innNo2);
	
	//댓글
		public void replyWrite(CommentVO covo);
		public CommentListVO selectByCommemtInnNo(String innNo, String pageNo);
		//댓글 삭제
		public void deleteReply(int commentNo);
		
		//6/25 검색메서드추가
		public abstract InnListVO findInnByWordAndAcceptNoAndDate(String pageNo, FilterVO fvo);
		public abstract InnListVO findInnByWordAndAcceptNoAndDateWithPrice(String pageNo, FilterVO fvo); //이새끼가 문제네
		//윤정 추가
}

package org.kosta.dashduowork.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.dao.AmenityDAO;
import org.kosta.dashduowork.model.dao.AvailableDateDAO;
import org.kosta.dashduowork.model.dao.BookDAO;
import org.kosta.dashduowork.model.dao.InnDAO;
import org.kosta.dashduowork.model.dao.InnPicCompDAO;
import org.kosta.dashduowork.model.dao.InnReservationDAO;
import org.kosta.dashduowork.model.dao.MemberDAO;
import org.kosta.dashduowork.model.dao.TradeDAO;
import org.kosta.dashduowork.model.dao.WishListDAO;
import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.BookVO;
import org.kosta.dashduowork.model.vo.DeleteVO;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnPicCompVO;
import org.kosta.dashduowork.model.vo.InnReservationListVO;
import org.kosta.dashduowork.model.vo.InnReservationVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.PagingBean;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.kosta.dashduowork.model.vo.TradeListVO;
import org.kosta.dashduowork.model.vo.TradeVO;
import org.kosta.dashduowork.model.vo.WishListListVO;
import org.kosta.dashduowork.model.vo.WishListVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class InnServiceImpl implements InnService {
   @Resource(name="innDAOImpl")
   private InnDAO innDAO;
   @Resource(name="bookDAOImpl")
   private BookDAO bookDAO;
   @Resource(name="wishListDAOImpl")
   private WishListDAO wishListDAO; 
   @Resource(name="innPicCompDAOImpl")
   private InnPicCompDAO innPicCompDAO;
   @Resource(name="amenityDAOImpl")
   private AmenityDAO amenityDAO;
   @Resource(name="availableDateDAOImpl")
   private AvailableDateDAO availableDateDAO;  
   @Resource(name="innReservationDAOImpl")
   private InnReservationDAO innReservationDAO;
   @Resource(name="memberDAOImpl")
   private MemberDAO memberDAO;
   @Resource(name="tradeDAOImpl")
	private TradeDAO tradeDAO;
   
   @Override
   public void registerInn(InnVO ivo){
      System.out.println("serivce : "+ivo);
      innDAO.register(ivo);
   }
   public void registerInnPic(InnPicCompVO vo){
      System.out.println("serivce : "+vo);
      innPicCompDAO.register(vo);      
   }
   @Override
   public void registerInnEtc(AmenityVO avo, AvailableDateVO avvo){
      System.out.println("service 들어옴?");
   
      amenityDAO.register(avo);
      System.out.println("serivce : "+avo);
      availableDateDAO.register(avvo);
      System.out.println("serivce : "+avvo);
      
   }
   /**6/16 -- 숙소 메인사진을 리스트에 병합했습니다- 주형**/
   @Override
	public InnListVO getmyinnlist(MemberVO vo, String pageNo) {
		int pn=1;
		if(pageNo!=null){
			pn=Integer.parseInt(pageNo);
		}
		System.out.println("pageNo?"+ pn);		
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("pageNo",Integer.toString(pn));
		param.put("member_id",vo.getMemberId());		
		List<InnVO> list = innDAO.getmyinnlist(param);
		//List<InnPicCompVO> piclist = new ArrayList<InnPicCompVO>();
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++){		
			list.get(i).setInnMainPic((innPicCompDAO.getMyPicList(list.get(i).getInnNo())));
			//list.get(i).setInnmainpic(innPicCompDAO.getMyPicList(list.get(i).getInnNo()));
			//piclist.add(innPicCompDAO.getMyPicList(list.get(i).getInnNo()));			
			System.out.println("사진 piclist: "+list.get(i).getInnMainPic());
		}		
		int total=innDAO.getTotalPostingCount(vo);
		PagingBean pagingBean=new PagingBean(total,pn);
		return new InnListVO(list,pagingBean);
	}
   
	@Override
	public TradeListVO getmytradelist(MemberVO vo, String pageNo) {
		int pn=1;
		if(pageNo!=null){
			pn=Integer.parseInt(pageNo);
		}
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("pageNo",Integer.toString(pn));
		param.put("member_id",vo.getMemberId());
		List<TradeVO> list = tradeDAO.getmytradelist(param);
		int total = tradeDAO.getTotalPostingCount(vo);
		PagingBean pagingBean=new PagingBean(total,pn);	
		return new TradeListVO(list, pagingBean);
	}
   
   
/*   @Override //은식, 동원
   public List<InnVO> findInnByCheckedAmenity(AmenityVO vo) {
      System.out.println("Service의 findInnByCheckedAmenity");
      List<InnVO> list =  innDAO.selectInnByCheckedAmenity(vo);
      System.out.println(list);
      return list;
   }*/
   //plus+++++++++++++++++++++++++++++++++++++++++++
	public InnListVO findInnByCityAndAcceptableNo(SearchVO vo) {
		InnListVO innListVO=new InnListVO();
		List<InnVO> innList =  innDAO.selectInnByCityAndAcceptableNo(vo);
//		System.out.println("서비스에서 메서드 확인");
		for (int i=0;i<innList.size();i++) {
			int innNo=innList.get(i).getInnNo();
//			System.out.println("list.getInnNo 확인 : "+innNo);
			innList.get(i).setInnMainPic(innPicCompDAO.getMyPicList(innNo));
		}
		innListVO.setInnList(innList);
//		System.out.println("숙소리스트 : "+innList);
		return innListVO;
	}
	public InnListVO findInnByCityAndDateAndAcceptableNo(SearchVO vo){
		InnListVO innListVO=new InnListVO();
		List<InnVO> innList =  innDAO.selectInnByCityAndDateAndAcceptableNo(vo);
		for (int i=0;i<innList.size();i++) {
			int innNo=innList.get(i).getInnNo();
			innList.get(i).setInnMainPic(innPicCompDAO.getMyPicList(innNo));
		}
		innListVO.setInnList(innList);
		return innListVO;
	}
	
/**주형 -- 예약/위시리스트 사진목록 추가 6/17**/
	@Override
	public BookListVO getmybooklist(MemberVO vo, String pageNo) {
		int pn=1;
		if(pageNo!=null){
			pn=Integer.parseInt(pageNo);
		}
		System.out.println("pageNo?"+ pn);
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("pageNo",Integer.toString(pn));
		param.put("member_id",vo.getMemberId());
		List<BookVO> list = bookDAO.getmybooklist(param);
		for(int i=0; i<list.size(); i++){		
			list.get(i).setBookMainPic((innPicCompDAO.getMyPicList(list.get(i).getInnNo())));
			System.out.println("book 사진list: "+list.get(i).getBookMainPic());
		}		
		int total=bookDAO.getTotalPostingCount(vo);
		PagingBean pagingBean=new PagingBean(total,pn);
		return new BookListVO(list, pagingBean);
	}
	
	@Override
	public WishListListVO getmywishlist(MemberVO vo, String pageNo) {

		int pn=1;
		if(pageNo!=null){
			pn=Integer.parseInt(pageNo);
		}
		System.out.println("pageNo?"+ pn);
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("pageNo",Integer.toString(pn));
		param.put("member_id",vo.getMemberId());
		List<WishListVO> list=wishListDAO.getmywishlist(param);
		for(int i=0; i<list.size(); i++){		
			list.get(i).setWishlistMainPic((innPicCompDAO.getMyPicList(list.get(i).getInnNo())));
			System.out.println("wishlist 사진list: "+list.get(i).getWishlistMainPic());
		}
		int total=wishListDAO.getTotalPostingCount(vo);
		PagingBean pagingBean=new PagingBean(total,pn);
		return new WishListListVO(list, pagingBean);
	}
	/**주형 윤정 삭제 서비스**/
	@Override
	   public void wishListDelete(DeleteVO wdvo) {
	   wishListDAO.wishListDelete(wdvo);
	}//1.위시리스트삭제
	   @Override
	   public void bookDelete(DeleteVO bdvo) {
	      bookDAO.bookDelete(bdvo);
	   }//2.예약취소-->예약삭제
		@Override
		public boolean checkChildBookTable(int innNo){
			//1. 참조하는 BOOK 자식이 있는지 확인해야 한다.
			int count = bookDAO.checkChildBookTable(innNo);
			System.out.println("book 자식테이블"+count);
			if(count>0){//참조하는 자식테이블이 있으므로 에러난다.
				return false;
			}
			else{//참조하는 자식테이블이 없다.
				return true;
			}			
		}//등록숙소 삭제시 자식 예약테이블 있나 확인
		public void innDelete(DeleteVO idvo) {
			//삭제되는것 ---> on DELETE CASCADE 때문에 다 지워진다.
			//1. 위시리스트 2. 숙소 사진 3.가능 날짜 4.편의시설 3. 숙소 자체
			innDAO.innDelete(idvo);
		}//3. 등록숙소 삭제
		@Override
		public void tradeDelete(DeleteVO tdvo) {
			tradeDAO.tradeDelete(tdvo);
		}//4. 거래내역 삭제
		/**삭제 끝 6/18**/ 
/**6/19 스케쥴러 이용한 거래내역 인서트,예약완료 삭제 -->스케쥴러 service 에서 수행한다.**/
		
   //6/15일 추가내용
	public InnListVO findInnByCityAndDateAndAcceptableNoWithFilter(FilterVO vo){ //지역&날짜&인원+필터
		InnListVO innListVO=new InnListVO();
		List<InnVO> innList =  innDAO.selectInnByCityAndDateAndAcceptableNoWithFilter(vo);
		for (int i=0;i<innList.size();i++) {
			int innNo=innList.get(i).getInnNo();
			innList.get(i).setInnMainPic(innPicCompDAO.getMyPicList(innNo));
		}
		innListVO.setInnList(innList);
		return innListVO;
	}
	public InnListVO findInnByCityAndAcceptableNoWithFilter(FilterVO vo){ //지역&날짜&인원+필터
		InnListVO innListVO=new InnListVO();
		List<InnVO> innList =  innDAO.selectInnByCityAndAcceptableNoWithFilter(vo);
		for (int i=0;i<innList.size();i++) {
			int innNo=innList.get(i).getInnNo();
			innList.get(i).setInnMainPic(innPicCompDAO.getMyPicList(innNo));
		}
		innListVO.setInnList(innList);
		return innListVO;
	}

	  public InnReservationListVO getMyInnReservationList(MemberVO vo, String pageNo){
	      System.out.println("getMyInnReservationList 서비스    "+vo);
	      int pn=1;
	      if(pageNo!=null){
	         pn=Integer.parseInt(pageNo);
	      }
	      System.out.println("pageNo?"+ pn);
	      HashMap<String,String> param = new HashMap<String,String>();
	      param.put("pageNo",Integer.toString(pn));
	      param.put("master",vo.getMemberId());
	      List<InnReservationVO> list = innReservationDAO.getMyInnReservationList(param);
	      for(int i=0; i<list.size(); i++){		
				list.get(i).setInnReservationMainPic((innPicCompDAO.getMyPicList(list.get(i).getInnNo())));
				System.out.println("innReservation 사진list: "+list.get(i).getInnReservationMainPic());
			}
	      int total=innReservationDAO.getTotalPostingCount(vo);
	      PagingBean pagingBean=new PagingBean(total,pn);
	      return new InnReservationListVO(list, pagingBean);
	   }
   
   // 은수
   public Map<String,Object> selectInn(String innNo) {
      Map<String,Object> map= new HashMap<String,Object>();
      map.put("innVO", innDAO.selectInn(innNo));
      InnVO ivo = (InnVO)map.get("innVO");
      map.put("memberVO", memberDAO.findMemberById(ivo.getMemberId()));
      return map;
   }
   //6/17일 추가(지역명 자동완성처리)
   @Override
	public List<InnVO> findInnCityListByInnCityCharacter(SearchVO vo) {
		return innDAO.selectInnCityListByInnCityCharacter(vo);
	}
   
   @Override
	public List<InnPicCompVO> selectByInnNo(String innNo) {
		List<InnPicCompVO> ipvo=innPicCompDAO.selectByInnNo(innNo);
		System.out.println("dao에서"+ipvo);
		return ipvo;
	}
	@Override
	 public ProfilePicVO selectByProfilePic(String memberId) {
	  		ProfilePicVO pvo =  memberDAO.selectProfilePic(memberId);
	  		System.out.println("service에서 pvo2:"+pvo);
	  		return pvo;
	  	}
	
	//6/18일 추가
	public InnListVO findInnByCityAndAcceptableNoWithPrice(FilterVO vo){ //지역&인원+가격
		System.out.println("서비스의 지역,인원,가격검색");
		InnListVO innListVO=new InnListVO();
		List<InnVO> innList = innDAO.selectInnByCityAndAcceptableNoWithPrice(vo);
		for (int i=0;i<innList.size();i++) {
			int innNo=innList.get(i).getInnNo();
			innList.get(i).setInnMainPic(innPicCompDAO.getMyPicList(innNo));
		}
		System.out.println("서비스에서 innList 확인 : "+innList);
		innListVO.setInnList(innList);
		return innListVO;
	}
	
	public InnListVO findInnByCityAndDateAndAcceptableNoWithPrice(FilterVO vo){
		InnListVO innListVO=new InnListVO();
		List<InnVO> innList =  innDAO.selectInnByCityAndDateAndAcceptableNoWithPrice(vo);
		for (int i=0;i<innList.size();i++) {
			int innNo=innList.get(i).getInnNo();
			innList.get(i).setInnMainPic(innPicCompDAO.getMyPicList(innNo));
		}
		innListVO.setInnList(innList);
		return innListVO;
	}
	//6/19윤정 추가
	@Override
	public AmenityVO selectAmenity(String innNo) {
		return amenityDAO.selectAmenity(innNo);
	}
	@Override
	public void updateInnInfo(InnVO ivo) {
		System.out.println("InnServiceImple ---InnVO"+ivo);
		innDAO.updateInnInfo(ivo);
	}
	@Override
	public void updateInnEtc(AmenityVO avo, AvailableDateVO avvo) {
		System.out.println("InnServiceImple ---Amenity와 AvailableDate"+avo+avvo);
		availableDateDAO.update(avvo);
		amenityDAO.update(avo);
	}
	@Override
	public AvailableDateVO selectAvailableDate(int innNo) {
		return availableDateDAO.selectAvailableDate(innNo);
	}
	@Override
	public List<InnPicCompVO> selectFilePathByInnNo(int innNo) {
		return innPicCompDAO.selectFilePathByInnNo(innNo);
	}
	@Override
	public void deleteInnPic(int innPicNo) {
		System.out.println("innServiceImol-------"+innPicNo);
		innPicCompDAO.deleteInnPic(innPicNo);
	}
	
	 public AvailableDateVO selectByAvailableDateInnNo(String innNo){
		 return availableDateDAO.selectByAvailableDateInnNo(innNo);
	 }
	 // 예약
	 @Transactional
	 public HashMap<String, Object> bookInsert(BookVO bvo, String innNo, String memberId) throws ParseException{
		 
		 HashMap<String, Object> result = new HashMap();
		 System.out.println("service : "+innNo);
		 List<BookVO> bookList = new ArrayList<BookVO>();
		 bookList = bookDAO.selectBookList(innNo);
		 InnVO ivo = innDAO.selectInn(innNo);
		 System.out.println("bookList : "+bookList);
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 Boolean flag = false;
		 System.out.println("book Checking....");
		 Date checkIn = formatter.parse(bvo.getBookCheckIn());
		 Date checkOut = formatter.parse(bvo.getBookCheckOut());
		 if(!bookList.isEmpty()){
			 	System.out.println(checkIn);
			 	System.out.println(checkOut);
			 for (int i = 0; i < bookList.size(); i++) {
				 System.out.println("date...");
				Date bookListCheckIn = formatter.parse(bookList.get(i).getBookCheckIn());
				Date bookListCheckOut = formatter.parse(bookList.get(i).getBookCheckOut());
				System.out.println("bookListCheckIn"+i+" : "+bookListCheckIn);
				System.out.println("bookListCheckOut"+i+" : "+bookListCheckOut);
				System.out.println("date checking....");
				
				System.out.println("test...");
				System.out.println(checkIn.before(bookListCheckIn));
				System.out.println(checkOut.after(bookListCheckIn));
				System.out.println(checkIn.before(bookListCheckOut));
				System.out.println(checkOut.after(bookListCheckOut));
				System.out.println(checkIn.equals(bookListCheckIn));
				System.out.println(checkIn.equals(bookListCheckOut));
				System.out.println(checkOut.equals(bookListCheckIn));
				System.out.println(checkOut.equals(bookListCheckOut));
					//일정이 겹치는 부분이 존재하는가
				if ((checkIn.before(bookListCheckIn) && checkOut.after(bookListCheckIn))	||
						(checkIn.before(bookListCheckOut) && checkOut.after(bookListCheckOut))||
						// 일정이 예약된 일정과 완전히 일치하는가
						checkIn.equals(bookListCheckIn)||checkIn.equals(bookListCheckOut)||
						checkOut.equals(bookListCheckIn)||checkOut.equals(bookListCheckOut)
						// 일정이 예약된 일정 내에 존재하는가
						||(checkOut.after(bookListCheckIn)&&checkIn.before(bookListCheckOut))){
					
					result.put("checkIn", bookListCheckIn); result.put("checkOut",bookListCheckOut);
					System.out.println("예약일자가 중복되었습니다!");
					flag = true;
					result.put("message", "예약일자가 중복되었습니다!\n");
					String DuplicateCheckIn = formatter.format(bookListCheckIn);
					String DuplicateCheckOut = formatter.format(bookListCheckOut);
 					formatter.format(bookListCheckOut);
					System.out.println(bookListCheckIn);
					System.out.println(bookListCheckOut);
					result.put("flag", flag);
					result.put("checkIn", "중복된 체크인 날짜 : "+DuplicateCheckIn+"\n");
					result.put("checkOut", "중복된 체크아웃 날짜 : "+DuplicateCheckOut+"\n");
					return result;
			     	} // if in for
			 } // for
		}// if
/*		 if(ivo.getInnAvailability()=="N"){
			 System.out.println("예약완료된 숙소입니다!");
			 return flag=true;
		 }*/
		 if(checkIn.after(checkOut)||checkOut.before(checkIn)){
			 System.out.println("예약일자가 엉터리야! 장난하냐??");
			 flag=true;
			 result.put("message", "체크인 날짜보다 반드시 체크아웃 날짜보다 뒤에 있어야 합니다!\n");
			 result.put("flag", flag);
			 return result;
		 }
		 if(memberId.equals(ivo.getMemberId())){
			 System.out.println("자기자신의 숙소는 예약할 수 없습니다.\n");
			 flag=true;
			 result.put("message", "자기 자신의 숙소를 자신이 예약할 수는 없습니다!\n");
			 result.put("flag", flag);
			 return result;
		 }
	 
		 System.out.println("book Check Over...");
			if(flag==false){
			System.out.println("ok : "+innNo);
			System.out.println("정상적으로 예약되었습니다.");
/*			innDAO.updateInnAvailabilityOff(innNo);*/
			bookDAO.bookInsert(bvo);
			result.put("flag", flag);
		}
		return result;
	 }
	 //6/19
	@Override
	public void updateinnAvailability(int innNo, String innAvailability) {
		System.out.println("서비스에서 가능여부 "+innAvailability);
		if(innAvailability.equals("Y")){
			innDAO.updateinnAvailabilitytoNo(innNo);
		}
		else{
			innDAO.updateinnAvailabilitytoYes(innNo);

		}		
	}
	@Override
	public void wishlistreg(WishListVO wvo) {
		System.out.println("service에서 실행될??"+wvo);
		wishListDAO.wishlistreg(wvo);
	}
 @Override
	public int wishCheck(WishListVO wvo) {
		int count=wishListDAO.wishCheck(wvo);
		System.out.println("장바구니 중복개수:"+count);
		return count;
	}
 @Override
 public int getWishListNoByInnNo(InnVO ivo) {
 	System.out.println("getWishListNoByInnNo  Service"+ivo);
 	return wishListDAO.getWishListNoByInnNo(ivo);
 }
}

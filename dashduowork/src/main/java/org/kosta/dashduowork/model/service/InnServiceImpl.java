package org.kosta.dashduowork.model.service;

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
	   System.out.println("wishListDelete 서비스 들어옴!!!!!!  "+wdvo);
	   wishListDAO.wishListDelete(wdvo);
	   }
	   @Override
	   public void bookDelete(DeleteVO bdvo) {
	      bookDAO.bookDelete(bdvo);
	   }
		@Override
		public void innDelete(DeleteVO idvo) {
			innDAO.innDelete(idvo);
		}
		@Override
		public void tradeDelete(DeleteVO tdvo) {
			tradeDAO.tradeDelete(tdvo);
		}
		
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
}
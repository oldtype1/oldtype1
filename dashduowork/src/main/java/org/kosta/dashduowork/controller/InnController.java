package org.kosta.dashduowork.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.dao.InnDAO;
import org.kosta.dashduowork.model.service.InnService;
import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.BookVO;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Exception.ChildBookTableException;
import Exception.NoInnException;

@Controller
public class InnController {
	
	@Resource(name="innServiceImpl")
	private InnService innService;
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	
	@RequestMapping("inn_register_from.do")
	public String innRegister() {
		System.out.println("registerfrom으로");
		return "inn_register_from";
	}
	
	@Resource(name = "uploadPath")
	private String path;
	
	@Resource(name="viewPath")
	private String viewPath;
	
	@Transactional
	@RequestMapping(value = "inn_register.do", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, InnVO ivo,
			InnPicCompVO ipvo, AmenityVO avo, AvailableDateVO avvo) {
		System.out.println("Inn register start....");
		List<MultipartFile> file = ipvo.getFile();
		System.out.println("notice");
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		ivo.setMemberId(mvo.getMemberId());
		innService.registerInn(ivo);

		ipvo.setInnNo(ivo.getInnNo());
		avo.setInnNo(ivo.getInnNo());
		avvo.setInnNo(ivo.getInnNo());

		ivo.setMemberId(mvo.getMemberId());
		System.out.println(ivo.getMemberId());
		System.out.println("ivo:" + ivo);
		
		
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < file.size(); i++) {
			// System.out.println(list.get(i).getOriginalFilename().equals(""));
			// TODO 파일 이름이 겹치는 상황에 대한 대처를 생각해본다.
			int a = (int) (Math.random() * 10);
			int b = (int) (Math.random() * 10);
			int c = (int) (Math.random() * 10);
			String fileName = path+a + "" + b + "" + c + "_"
					+ file.get(i).getOriginalFilename();
			if (!fileName.equals("")) {
				try {
					nameList.add(viewPath+a + "" + b + "" + c + "_"
							+file.get(i).getOriginalFilename());
					file.get(i).transferTo(new File(fileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ipvo.setFilePathes(nameList);
		for(int i=0;i<nameList.size();i++){
			ipvo.setFilePath(ipvo.getFilePathes().get(i));
			innService.registerInnPic(ipvo);
		}
		innService.registerInnEtc(avo, avvo);
		return new ModelAndView("redirect:get_myinnlist.do");
	}
	
	@RequestMapping(value="get_myinnlist.do")
	public String getMyInnList(String pageNo,HttpServletRequest request, Model model){		
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");		
		InnListVO lvo = innService.getmyinnlist(vo,pageNo);
		ProfilePicVO pvo = memberService.selectProfilePic(vo.getMemberId());
		 if(pvo!=null){
			 vo.setProfilePicVO(pvo);
		     }
		     else{
		    	 vo.setProfilePicVO(new ProfilePicVO(vo.getMemberId(),  "http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
		     }
		 model.addAttribute("member", vo);
		  model.addAttribute("lvo", lvo);
		return "member_inn_list";	
	}
	@RequestMapping(value="get_mytradelist.do")
	public ModelAndView getMyTradeList(String pageNo,HttpServletRequest request,Model model){		
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");		
		TradeListVO tvo = innService.getmytradelist(vo,pageNo);
		ProfilePicVO pvo = memberService.selectProfilePic(vo.getMemberId());
		 if(pvo!=null){
			 vo.setProfilePicVO(pvo);
		     }
		     else{
		    	 vo.setProfilePicVO(new ProfilePicVO(vo.getMemberId(),"http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
		     }
		 model.addAttribute("member", vo);
		System.out.println("tvo는? "+ tvo);
		return new ModelAndView("member_trade_list","tvo",tvo);
	
	}
/*	@RequestMapping(value ="selectInnByCheckedAmenity.do") //은식,동원
	public String selectInnByCheckedAmenity(AmenityVO vo, Model model) {
//		System.out.println(vo);
		List<InnVO> list =innService.findInnByCheckedAmenity(vo);
//		System.out.println(list);
		model.addAttribute("list", list);
		return "inn_search_result";
	}*/
	@RequestMapping(value ="selectInnByCheckedAmenity.do", method=RequestMethod.POST )
	@ResponseBody
	public InnListVO selectInnByCheckedAmenity(FilterVO vo, Model model) {
		InnListVO innListVO=new InnListVO();
		System.out.println("필터VO 확인 : "+vo);
		if(vo.getAmenityBBQ().equals("N")&vo.getAmenityBed().equals("N")&vo.getAmenityKitchen().equals("N")&vo.getAmenityTV().equals("N")&vo.getAmenityWiFi().equals("N")){
			SearchVO svo=new SearchVO();
			svo.setAcceptableNo(vo.getFirstSearchPeopleNo());
			svo.setEndDate(vo.getFirstSearchEndDate());
			svo.setInnCity(vo.getFirstSearchCity());
			svo.setStartDate(vo.getFirstSearchStartDate());
			
			if(svo.getStartDate()==""){
				//날짜 안들어간경우		
				System.out.println("컨트롤러의 지역,인원,가격으로 검색");
				innListVO=innService.findInnByCityAndAcceptableNoWithPrice(vo);
			}else{//날짜 들어간경우
				innListVO=innService.findInnByCityAndDateAndAcceptableNoWithPrice(vo);
			}
			model.addAttribute("searchVO", svo);
		}else{
			if(vo.getFirstSearchStartDate()==""){//날짜 없는경우
				System.out.println("날짜 없는곳으로 입장.");
				//서브쿼리 이용해서 도시+인원검색 후 결과를 filter처리 parameterType으로 새로운 VO를 만들거나 기존 VO에 변수 추가해야할듯
				innListVO=innService.findInnByCityAndAcceptableNoWithFilter(vo);
				List<InnVO> list=innListVO.getInnList();
				for (InnVO innVO : list) {
					System.out.println(innVO);
				}
			}else{//날짜 들어간경우
				System.out.println("컨트롤러의 지역,인원,가격+필터로 검색");
				//서브쿼리 이용해서 도시+날짜+인원검색 후 결과를 filter처리
				innListVO=innService.findInnByCityAndDateAndAcceptableNoWithFilter(vo);
			}
		}
		//model.addAttribute("list", list);
		//System.out.println("결과 list 확인 : "+list);
		return innListVO;
	}
	@RequestMapping(value="searchByCityDateNo.do")
	public String searchByCityDateNo(SearchVO vo, Model model, HttpServletRequest request){
		InnListVO innListVO=new InnListVO();
		if(vo.getStartDate()==""){
			//날짜 안들어간경우		
//			System.out.println("날짜 안들어간 컨트롤러의 값 확인");
			innListVO=innService.findInnByCityAndAcceptableNo(vo);
			System.out.println(innListVO.getInnList());
		}else{//날짜 들어간경우
			innListVO=innService.findInnByCityAndDateAndAcceptableNo(vo);
		}
		model.addAttribute("innListVO", innListVO);
		model.addAttribute("searchVO", vo);
		HttpSession session = request.getSession(false);
        MemberVO memberVO = (MemberVO)session.getAttribute("mvo");
        System.out.println("searchMemberVO : "+memberVO);
		return "inn_search_result";
	}
	
	@RequestMapping(value="get_mybooklist.do")
	public String getMyBookList(String pageNo,HttpServletRequest request,Model model){
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		BookListVO blvo=innService.getmybooklist(vo, pageNo);
		ProfilePicVO pvo = memberService.selectProfilePic(vo.getMemberId());
		 if(pvo!=null){
			 vo.setProfilePicVO(pvo);
		     }
		     else{
		    	 vo.setProfilePicVO(new ProfilePicVO(vo.getMemberId(),  "http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
		     }
		 model.addAttribute("member", vo);
		model.addAttribute("blvo", blvo);
		return"member_book_list";
	}

	
	@RequestMapping(value="get_mywishlist.do")
	public String getMyWishList(String pageNo,HttpServletRequest request,Model model){
		
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		WishListListVO wlvo=innService.getmywishlist(vo, pageNo);
		ProfilePicVO pvo = memberService.selectProfilePic(vo.getMemberId());
		 if(pvo!=null){
			 vo.setProfilePicVO(pvo);
		     }
		     else{
		    	 vo.setProfilePicVO(new ProfilePicVO(vo.getMemberId(),  "http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
		     }
		 model.addAttribute("member", vo);
		model.addAttribute("wlvo", wlvo);
		return"member_wish_list";
	}
	
	/**목록 삭제 컨트롤러--주형윤정**/
	/*위시리스트*/
	@RequestMapping(value="wishlistdelete.do")
	public String wishListDelete(int wishListNo, HttpServletRequest request){
		HttpSession session=request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO wdvo=new DeleteVO(wishListNo,vo.getMemberId());
		innService.wishListDelete(wdvo);
		return "redirect:get_mywishlist.do";
	}	
	/*예약숙소취소*/
	@RequestMapping(value="bookdelete.do")
	public String bookDelete(int bookNo, HttpServletRequest request){
		HttpSession session=request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO bdvo=new DeleteVO(bookNo, vo.getMemberId());
		innService.bookDelete(bdvo);
		//예약 취소시 거래내역에서도 삭제되게 해야한다. 
		//-->6/19 수정: 예약취소 -> 예약만 삭제 // 거래내역은 예약완료시 insert 예정
		/*innService.tradeDeleteByBookNo(bdvo);*/
		return "redirect:get_mybooklist.do";
	}
	/*등록숙소  6/25 exception으로 수정함 */ 
	@RequestMapping(value="inndelete.do")
	public String innDelete(int innNo, HttpServletRequest request,Model model){
		 HttpSession session = request.getSession(false);
			if(session==null||(MemberVO)session.getAttribute("mvo")==null){
				return "member_session_fail";
			}
		MemberVO vo= (MemberVO)session.getAttribute("mvo");	
		try {
			innService.innDelete(new DeleteVO(innNo,vo.getMemberId()));
		} catch (ChildBookTableException e) {
			model.addAttribute("message", e.getMessage());
			return "member_delete_fail";	
		}	
		return "redirect:get_myinnlist.do";
	}
	/*거래내역목록*/
	@RequestMapping(value="tradedelete.do")
	public String tradeDelete(int tradeNo,HttpServletRequest request){
		System.out.println("tradeNo: "+tradeNo);
		HttpSession session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		//예약이든 등록이든 trade에서는 전부 삭제
		innService.tradeDelete(new DeleteVO(tradeNo,vo.getMemberId()));		
		System.out.println("거래삭제: "+vo.getMemberId());
//////스프링 스케줄러 적용하여 예약 테이블에서 삭제 하지 않아도 된다.////
/*		if(memberId.equals(vo.getMemberId())){
			//현재 사용자와 거래내역의 예약자와 같으면
			// --> 즉 사용자의 예약 거래내역을 삭제시에는 예약 테이블에서도 삭제
					innService.bookDelete(new DeleteVO(bookNo,vo.getMemberId()));		
					System.out.println("예약 테이블 에서도 삭제");
				}*/
		return "redirect:get_mytradelist.do";
	}
	/** 끝**/
	
	@RequestMapping(value="get_innReservation_list.do")
	public String getInnReservationList(String pageNo, HttpServletRequest request, Model model){
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		InnReservationListVO irlvo=innService.getMyInnReservationList(vo, pageNo);
		ProfilePicVO pvo = memberService.selectProfilePic(vo.getMemberId());
		 if(pvo!=null){
			 vo.setProfilePicVO(pvo);
		     }
		     else{
		    	 vo.setProfilePicVO(new ProfilePicVO(vo.getMemberId(),  "http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
		     }
		 model.addAttribute("member", vo);
		model.addAttribute("irlvo", irlvo);
		return "member_innReservation_list";
	}

	
	//6/17일 추가
	@RequestMapping(value="searchCityAuto.do")
	@ResponseBody
	public List<InnVO> findCityAuto(SearchVO vo) {
//		System.out.println("컨트롤러에서 FilterVO내용확인인데 에이젝스야 : "+vo); //확인완료
//		System.out.println(vo.getInnCity()+"가 나와야해");
		List<InnVO> list=null;
		list = innService.findInnCityListByInnCityCharacter(vo);
		System.out.println("컨트롤러 내용 : "+list);
		/*for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getInnCity() + " searchCity");
		}	*/
		return list;
	}

	@RequestMapping(value="inn_in_show.do")
	public String inShow(HttpServletRequest request, Model model){
		String innNo = (String)request.getParameter("innNo");
		int innNo2=Integer.parseInt(innNo);
		HashMap<String, Object> map=new HashMap<String, Object>();		
		try {
			map = (HashMap<String, Object>)innService.selectInn(innNo);
		} catch (NoInnException e) {
			model.addAttribute("message", e.getMessage());
			return "inn_in_show_fail";	
		}		
		System.out.println("상세글보기 컨트롤러 메서드");
		HttpSession session=request.getSession(false);
		MemberVO mvo=null;
		mvo=(MemberVO)session.getAttribute("mvo");
		if(mvo!=null){
			WishListVO wishVO = new WishListVO(innNo2, mvo.getMemberId());
			int count = innService.wishCheck(wishVO);
			if (count > 0) {//위시리스트 이미 등록
				model.addAttribute("wishFlag", "ok");
			} else {//위시리스트 없음
				model.addAttribute("wishFlag", "no");
			}
		}else{
			model.addAttribute("wishFlag", "no");
		}
		
		InnVO ivo = (InnVO)map.get("innVO");
		List<InnPicCompVO> picList = innService.selectByInnNo(innNo);
		ProfilePicVO pvo=innService.selectByProfilePic(ivo.getMemberId());
		AvailableDateVO avo = innService.selectByAvailableDateInnNo(innNo);
	
		map.put("picList", picList);
		map.put("pvo", pvo);
		map.put("avo", avo);
		model.addAttribute("VOMap", map);
		return "inn_in_show";
	}
	
	
	@RequestMapping("innupdateform.do")
	public String innupdateform(int innNo, HttpServletRequest request, Model model){
		 HttpSession session = request.getSession(false);
			if(session==null||(MemberVO)session.getAttribute("mvo")==null){
				return "member_session_fail";
			}
		String innNo2=Integer.toString(innNo);
		HashMap<String, Object> map= new HashMap<String, Object>();
		try {
			map = (HashMap<String, Object>) innService.selectInn(innNo2);
		} catch (NoInnException e) {
			model.addAttribute("message", e.getMessage());
			return "inn_in_show_fail";	
		}
		InnVO ivo = (InnVO)map.get("innVO");
		List<InnPicCompVO> picList = innService.selectByInnNo(innNo2);
		List<InnPicCompVO> list=innService.selectFilePathByInnNo(innNo);
		ProfilePicVO pvo=innService.selectByProfilePic(ivo.getMemberId());
		AmenityVO avo=innService.selectAmenity(innNo2);
		AvailableDateVO avvo=innService.selectAvailableDate(innNo);
		map.put("picList", picList);
		map.put("pvo", pvo);
		model.addAttribute("VOMap", map);
		model.addAttribute("avo", avo);
		model.addAttribute("avvo", avvo);
		model.addAttribute("picList", list);
		return "member_innupdate_form";
	}
	
	@RequestMapping("inn_update.do")
	public String innUpdate(InnVO ivo, AmenityVO avo, InnPicCompVO ipvo, 
			AvailableDateVO avvo, Model model, BindingResult result, HttpServletRequest request){
		HttpSession session=request.getSession(false);
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		String memberId=vo.getMemberId();
		System.out.println("innController 들어옴");
		String innNo2=request.getParameter("innNo");
		String availableDateNo2=request.getParameter("availableDateNo");
		int innNo=Integer.parseInt(innNo2);
		int availableDateNo=Integer.parseInt(availableDateNo2);
		List<MultipartFile> file = ipvo.getFile();
		ivo.setInnNo(innNo);
		ivo.setMemberId(memberId);
		innService.updateInnInfo(ivo);
		avo.setInnNo(innNo);
		avvo.setInnNo(innNo);
		avvo.setAvailableDateNo(availableDateNo);
		ipvo.setInnNo(innNo);
		innService.updateInnEtc(avo, avvo);
		//사진 이름 겹칠까봐
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < file.size(); i++) {

			int a = (int) (Math.random() * 10);
			int b = (int) (Math.random() * 10);
			int c = (int) (Math.random() * 10);
			String fileName = path+a + "" + b + "" + c + "_"
					+ file.get(i).getOriginalFilename();
			if (!fileName.equals("")) {
				try {
					nameList.add(viewPath+a + "" + b + "" + c + "_"
							+file.get(i).getOriginalFilename());
					file.get(i).transferTo(new File(fileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ipvo.setFilePathes(nameList);
		for(int i=0;i<nameList.size();i++){
			ipvo.setFilePath(ipvo.getFilePathes().get(i));
			innService.registerInnPic(ipvo);
		}
		return "redirect:innupdateform.do?innNo="+innNo;
	}
		
	@RequestMapping("deleteInnPic.do")
	@ResponseBody
	public List<InnPicCompVO> deleteInnPic(int innPicNo, int innNo, HttpServletRequest request){
		innService.deleteInnPic(innPicNo);
		List<InnPicCompVO> innPicList=innService.selectFilePathByInnNo(innNo);
		return innPicList;
	}
	
	// 예약
	@RequestMapping(value="inn_book.do", method=RequestMethod.POST )
	public String booking(HttpServletRequest request, Model model, BookVO bvo){
		System.out.println("book starting...");
		String innNo = request.getParameter("innNo");
		String memberId = request.getParameter("memberId");
		HttpSession session = request.getSession(false);
		
		boolean flag = false;
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		bvo.setMemberId(mvo.getMemberId());
		System.out.println(innNo);
		System.out.println(bvo);
		HashMap<String, Object> result = new HashMap();
		result.put("flag",flag);
		try {
			 result = innService.bookInsert(bvo, innNo, memberId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = (Boolean) result.get("flag");
		
		System.out.println("경로 결정");
		model.addAttribute("innNo", innNo);
		System.out.println(flag);
		if(flag==true){
			model.addAttribute("result", result);
			return "inn_book_fail";
		}
		return "inn_book_ok";
	}
	//숙소가능여부 변경 6/19
	@RequestMapping("updateinnAvailability.do")
	public String updateinnAvailability(int innNo,String innAvailability){
		System.out.println("innNo"+innNo+" innAvailability"+innAvailability);
		innService.updateinnAvailability(innNo,innAvailability);
		return "redirect:get_myinnlist.do";
	}
	// 6/19일 추가 위시리스트reg
			@RequestMapping("wishListReg.do")
			public String wishlistreg(HttpServletRequest request, Model model) {
				System.out.println("위시 " + request.getParameter("innNo"));
				int innNO = Integer.parseInt(request.getParameter("innNo"));
				System.out.println(innNO);
				HttpSession session = request.getSession(false);
				MemberVO vo = (MemberVO) session.getAttribute("mvo");
				WishListVO wvo = new WishListVO(0, innNO, vo.getMemberId(), null, null);
				int count = innService.wishCheck(wvo);
				if (count > 0) {
					model.addAttribute("innNo", innNO);
					return "member_wishlist_fail";
				} else {
					innService.wishlistreg(wvo);
					return "redirect:get_mywishlist.do";
				}
			}
			@RequestMapping("changeWishListPic.do")
			@ResponseBody
			public MemberVO changeWishListPic(int innNo, HttpServletRequest request){
				System.out.println("changeWishListPic 컨트롤러 들어옴"+innNo);
				String flag="success";
				HttpSession session=request.getSession(false);
				MemberVO vo= (MemberVO)session.getAttribute("mvo");
				InnVO ivo=new InnVO(innNo, vo.getMemberId());
				System.out.println(ivo+" 새로운 InnVO생성");
				int wishListNo=innService.getWishListNoByInnNo(ivo);
				DeleteVO wdvo=new DeleteVO(wishListNo,vo.getMemberId());
				innService.wishListDelete(wdvo);
				return vo;
			}
			//별점 매기기 
			@RequestMapping("ratingInn.do")
			public String ratingInn(InnRatingVO irv,int tradeNo,HttpServletRequest request){				
				System.out.println("별점 테이블"+irv);
				System.out.println("별점 거래번호: "+tradeNo);
				innService.ratingInn(irv,tradeNo);
				return "redirect:inn_in_show.do?innNo="+irv.getInnNo();
			}
			
}






package org.kosta.dashduowork.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.InnService;
import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.service.ReportService;
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
import org.kosta.dashduowork.model.vo.ReportVO;
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

import Exception.ChildBookTableException;
import Exception.NoInnException;

@Controller
public class InnController {
	
	@Resource(name="innServiceImpl")
	private InnService innService;
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	@Resource(name="reportServiceImpl")
	private ReportService reportService;
	
	@RequestMapping("inn_register_from.do")
	public String innRegister(HttpServletRequest request) {
		System.out.println("registerfrom으로");
		HttpSession session = request.getSession(false);
		if(session==null||(MemberVO)session.getAttribute("mvo")==null){
			return "member_session_fail";
		}
		return "inn_register_from";
	}
	
	@Resource(name = "uploadPath")
	private String path;
	
	@Resource(name="viewPath")
	private String viewPath;
	
	@Transactional
	@RequestMapping(value = "inn_register.do", method = RequestMethod.POST)
	public String register(HttpServletRequest request, InnVO ivo,
			InnPicCompVO ipvo, AmenityVO avo, AvailableDateVO avvo) {
		System.out.println("Inn register start....");
		List<MultipartFile> file = ipvo.getFile();
		System.out.println("notice");
		//세션이 없을때 처리
				HttpSession session = request.getSession(false);
				if(session==null||(MemberVO)session.getAttribute("mvo")==null){
					return "member_session_fail";
				}
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		ivo.setMemberId(mvo.getMemberId());
		System.out.println("ivo : "+ivo);
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
		return "redirect:get_myinnlist.do";
	}
	/**마이페이지 목록 불러오기**/
	@RequestMapping(value="get_myinnlist.do")
	public String getMyInnList(String pageNo,HttpServletRequest request, Model model){		
		HttpSession session = request.getSession(false);
		if(session==null||(MemberVO)session.getAttribute("mvo")==null){
			return "member_session_fail";
		}
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
	public String getMyTradeList(String pageNo,HttpServletRequest request,Model model){		
		HttpSession session=request.getSession(false);
		if(session==null||(MemberVO)session.getAttribute("mvo")==null){
			return "member_session_fail";
		}
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
		 model.addAttribute("tvo", tvo);
		System.out.println("tvo는? "+ tvo);
		return "member_trade_list";
	
	}
	@RequestMapping(value="get_mybooklist.do")
	public String getMyBookList(String pageNo,HttpServletRequest request,Model model){
		HttpSession session=request.getSession(false);
		if(session==null||(MemberVO)session.getAttribute("mvo")==null){
			return "member_session_fail";
		}
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
		HttpSession session=request.getSession(false);
		if(session==null||(MemberVO)session.getAttribute("mvo")==null){
			return "member_session_fail";
		}
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
	
	@RequestMapping(value="get_innReservation_list.do")
	public String getInnReservationList(String pageNo, HttpServletRequest request, Model model){
		HttpSession session=request.getSession(false);
		if(session==null||(MemberVO)session.getAttribute("mvo")==null){
			return "member_session_fail";
		}
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

	/**목록 삭제 컨트롤러--주형윤정**/
	/*위시리스트*/
	@RequestMapping(value="wishlistdelete.do")
	public String wishListDelete(int wishListNo, HttpServletRequest request){
		 HttpSession session = request.getSession(false);
			if(session==null||(MemberVO)session.getAttribute("mvo")==null){
				return "member_session_fail";
			}
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO wdvo=new DeleteVO(wishListNo,vo.getMemberId());
		innService.wishListDelete(wdvo);
		return "redirect:get_mywishlist.do";
	}	
	/*예약숙소취소*/
	@RequestMapping(value="bookdelete.do")
	public String bookDelete(int bookNo, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session==null||(MemberVO)session.getAttribute("mvo")==null){
			return "member_session_fail";
		}
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO bdvo=new DeleteVO(bookNo, vo.getMemberId());
		innService.bookDelete(bdvo);
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
		 HttpSession session = request.getSession(false);
			if(session==null||(MemberVO)session.getAttribute("mvo")==null){
				return "member_session_fail";
			}
		System.out.println("tradeNo: "+tradeNo);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		//예약이든 등록이든 trade에서는 전부 삭제
		innService.tradeDelete(new DeleteVO(tradeNo,vo.getMemberId()));		
		System.out.println("거래삭제: "+vo.getMemberId());
		return "redirect:get_mytradelist.do";
	}
	/** 끝**/
	
	//6/17일 추가(지역명 자동완성)
	@RequestMapping(value="searchCityAuto.do")
	@ResponseBody
	public List<InnVO> findCityAuto(FilterVO fvo) {
//		System.out.println("컨트롤러에서 FilterVO내용확인인데 에이젝스야 : "+vo); //확인완료
//		System.out.println(vo.getInnCity()+"가 나와야해");
		List<InnVO> list=null;
		list = innService.findInnCityListByInnCityCharacter(fvo);
		System.out.println("컨트롤러 내용 : "+list);
		/*for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getInnCity() + " searchCity");
		}	*/
		return list;
	}

	@RequestMapping(value="inn_in_show.do")
	public String inShow(HttpServletRequest request, Model model,CommentListVO cvo, String pageNo){
		System.out.println("상세글보기 컨트롤러 메서드");
		 HttpSession session = request.getSession(false);
			if(session==null){
				return "member_session_fail";
			}
		String innNo = (String)request.getParameter("innNo");
		System.out.println("커멘트 vo : "+cvo);
		int innNo2=Integer.parseInt(innNo);
		HashMap<String, Object> map=new HashMap<String, Object>();		
		try {
			map = (HashMap<String, Object>)innService.selectInn(innNo);
		} catch (NoInnException e) {
			model.addAttribute("message", e.getMessage());
			return "inn_in_show_fail";	
		}		
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
		CommentListVO covo =innService.selectByCommemtInnNo(innNo, pageNo);
		List<AmenityVO> amenityList = innService.selectAmenity(innNo);
		System.out.println("컨트롤러cvo "+covo);
	
		map.put("covo", covo);
		map.put("picList", picList);
		map.put("pvo", pvo);
		map.put("avo", avo);
		map.put("ameList", amenityList);
		model.addAttribute("VOMap", map);
		
		int count= innService.selectInnRating(innNo2); //별점
		int PeopleNum= innService.selectPeopleNum(innNo2); //별점을 매긴 사람수
		System.out.println(innNo2+"번 숙소의 별점은: "+count);
		model.addAttribute("count", count);					//별점
		model.addAttribute("peopleNum",PeopleNum); //별점을 매긴 사람수
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
		List<AmenityVO> avo =innService.selectAmenity(innNo2);
		AvailableDateVO avvo=innService.selectAvailableDate(innNo);
		System.out.println("avvo : "+avvo);
		map.put("picList", picList);
		map.put("pvo", pvo);
		map.put("avo", avo);
		map.put("avvo",avvo);
		model.addAttribute("VOMap", map);
		model.addAttribute("picList", list);
		System.out.println(avo);
		return "member_innupdate_form";
	}
	
	@RequestMapping("inn_update.do")
	public String innUpdate(InnVO ivo, AmenityVO avo, InnPicCompVO ipvo, 
			AvailableDateVO avvo, Model model, BindingResult result, HttpServletRequest request){
		System.out.println("innController 들어옴");
		 HttpSession session = request.getSession(false);
			if(session==null||(MemberVO)session.getAttribute("mvo")==null){
				return "member_session_fail";
			}
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		String memberId=vo.getMemberId();
		String innNo2=request.getParameter("innNo");
		String availableDateNo2=request.getParameter("availableDateNo");
		int innNo=Integer.parseInt(innNo2);
		int availableDateNo=Integer.parseInt(availableDateNo2);
		List<MultipartFile> file = ipvo.getFile();
		System.out.println("test...");
		ivo.setInnNo(innNo);
		ivo.setMemberId(memberId);
		innService.updateInnInfo(ivo);
		System.out.println("test1...");
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
		return "redirect:get_myinnlist.do";
	}
		
	@RequestMapping("deleteInnPic.do")
	@ResponseBody
	public List<InnPicCompVO> deleteInnPic(int innPicNo, int innNo, HttpServletRequest request){
		innService.deleteInnPic(innPicNo);
		List<InnPicCompVO> innPicList=innService.selectFilePathByInnNo(innNo);
		return innPicList;
	}
	
	// 예약
	@RequestMapping(value="book.do")
	public String booking(HttpServletRequest request, Model model){
		System.out.println("book starting...");
		String innNo = request.getParameter("innNo");
		int innNo2=Integer.parseInt(innNo);
		String cardInfo=request.getParameter("cardInfo");
		String checkpayment=request.getParameter("checkpayment");
		String payTotalPrice=request.getParameter("payTotalPrice");
		String bookCheckIn=request.getParameter("bookCheckIn");
		String bookCheckOut=request.getParameter("bookCheckOut");
		String bookCount=request.getParameter("bookCount");
		HttpSession session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		String memberId=vo.getMemberId();
		System.out.println(memberId+vo+"컨트롤러 1");
		BookVO bvo=new BookVO(memberId, innNo2, bookCheckIn, bookCheckOut, bookCount);
		System.out.println(bvo+" 예약하는 숙소 정보");
		//윤정 추가
		//boolean flag = false;
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		bvo.setMemberId(mvo.getMemberId());
		System.out.println(innNo);
		System.out.println(bvo);
	
		innService.bookingInn(bvo);
		List<InnPicCompVO> list=innService.selectByInnNo(innNo);
		InnPicCompVO innMainPic=list.get(0);
		System.out.println(bvo);
		InnVO ivo=innService.getInnByInnNo(innNo2);
		if(cardInfo==null){
			MemberVO masterVO=innService.getMemberAccountByInnNo(innNo);
			model.addAttribute("masterVO", masterVO);
		}
		System.out.println(innMainPic+payTotalPrice);
		System.out.println(ivo);
		System.out.println(bvo);
		model.addAttribute("ivo", ivo);
		model.addAttribute("bookInfo", bvo);
		model.addAttribute("innMainPic", innMainPic);
		model.addAttribute("payTotalPrice", payTotalPrice);	
		return "inn_book_ok";
	}
	//숙소가능여부 변경 6/19
		@RequestMapping("updateinnAvailability.do")
		public String updateinnAvailability(HttpServletRequest request,int innNo,String innAvailability){
			   HttpSession session = request.getSession(false);
				if(session==null||(MemberVO)session.getAttribute("mvo")==null){
					return "member_session_fail";
				}	  
			System.out.println("innNo"+innNo+" innAvailability"+innAvailability);
			innService.updateinnAvailability(innNo,innAvailability);
			return "redirect:get_myinnlist.do";
		}
	// 6/19일 추가 위시리스트reg
		@RequestMapping("wishListReg.do")
		@ResponseBody
		public boolean wishlistreg(int innNo, HttpServletRequest request) {
			 HttpSession session = request.getSession(false);
			 boolean flag=false;
				if(session==null||(MemberVO)session.getAttribute("mvo")==null){
					flag=false;
				}	 
			System.out.println(innNo);
			MemberVO vo = (MemberVO) session.getAttribute("mvo");
			WishListVO wvo = new WishListVO(0, innNo, vo.getMemberId(), null, null);
			int count = innService.wishCheck(wvo);
			if (count > 0) {
				flag=false;
			} else {
				innService.wishlistreg(wvo);
				flag=true;
			}
			return flag;
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
	//별점 매기기 -->6/29 exception 추가
	@RequestMapping("ratingInn.do")
	public String ratingInn(InnRatingVO irv,int tradeNo,HttpServletRequest request,Model model){	
		   HttpSession session = request.getSession(false);
			if(session==null||(MemberVO)session.getAttribute("mvo")==null){
				return "member_session_fail";
			}
			try{
				innService.ratingInn(irv,tradeNo);
			}
			catch (NoInnException e) {
				model.addAttribute("message", e.getMessage());
				return "inn_in_show_fail";	
			}	
		System.out.println("별점 테이블"+irv);
		System.out.println("별점 거래번호: "+tradeNo);
		return "redirect:inn_in_show.do?innNo="+irv.getInnNo();
	}
	
	//6/25 검색메서드 추가
<<<<<<< HEAD
	@RequestMapping(value="searchInnByWordDateNo.do")
	public String searchByCityDateNo(String pageNo, FilterVO fvo, Model model, HttpServletRequest request){
		InnListVO innListVO=new InnListVO();
		System.out.println("컨트롤러에서 fvo 확인 : "+fvo);
		List<InnVO> list=null;
		if(fvo.getMinPrice()==null || fvo.getMaxPrice()==null || fvo.getAmenityItems()==null || fvo.getMinPrice()=="" || fvo.getMaxPrice()==""){
			innListVO=innService.findInnByWordAndAcceptNoAndDate(pageNo, fvo);
		}else{//날짜 들어간경우
			innListVO=innService.findInnByWordAndAcceptNoAndDateWithPrice(fvo);
		}
		List<ReportVO> wordlist=reportService.selectReport();
		System.out.println("InnController 검색어순위 "+wordlist);
		System.out.println("검색결과 확인 : "+innListVO.getInnList());
		model.addAttribute("wordlist", wordlist);
		model.addAttribute("innListVO", innListVO);
		model.addAttribute("filterVO", fvo);
//		HttpSession session = request.getSession(false);
//		MemberVO memberVO = (MemberVO)session.getAttribute("mvo");
//		System.out.println("searchMemberVO : "+memberVO);
		return "inn_search_result";
	}
=======
>>>>>>> branch 'master' of https://github.com/oldtype1/oldtype1.git
	//6/25 검색메서드 추가
		@RequestMapping(value="searchInnByWordDateNo.do")
		public String searchByCityDateNo(String pageNo, FilterVO fvo, Model model, HttpServletRequest request){
			InnListVO innListVO=new InnListVO();
<<<<<<< HEAD
			List<InnVO> list=null;
			if(fvo.getMinPrice()==null || fvo.getMaxPrice()==null){
				innListVO=innService.findInnByWordAndAcceptNoAndDate("1",fvo);
			}else{//날짜 들어간경우
				System.out.println("가격 들어옴!");
				innListVO=innService.findInnByWordAndAcceptNoAndDateWithPrice(fvo);
=======
			System.out.println("컨트롤러에서 fvo 확인 : "+fvo);
			if(fvo.getMinPrice()==null || fvo.getMaxPrice()==null || fvo.getAmenityItems()==null || fvo.getMinPrice()=="" || fvo.getMaxPrice()==""){
				System.out.println("=======controller영역에 입장하셨습니다.=========");
				System.out.println("가격바 움직이지않았어요");
				innListVO=innService.findInnByWordAndAcceptNoAndDate(pageNo, fvo);
			}else{
				System.out.println("=======controller영역에 입장하셨습니다.=========");
				System.out.println("가격바 움직였어요");
				innListVO=innService.findInnByWordAndAcceptNoAndDateWithPrice(pageNo, fvo);
>>>>>>> branch 'master' of https://github.com/oldtype1/oldtype1.git
			}
			System.out.println("검색결과 확인 : "+innListVO.getInnList());
			model.addAttribute("innListVO", innListVO);
			model.addAttribute("filterVO", fvo);
//			HttpSession session = request.getSession(false);
//			MemberVO memberVO = (MemberVO)session.getAttribute("mvo");
//			System.out.println("searchMemberVO : "+memberVO);
			return "inn_search_result";
		}
		//6/25 검색메서드 추가
			@RequestMapping(value="searchInnByWordDateNoWithFilter.do")
			@ResponseBody
			public InnListVO searchInnByWordDateNoWitFilter(String pageNo, FilterVO fvo, Model model, HttpServletRequest request){
				System.out.println("컨트롤러에서 fvo 확인 : "+fvo);
				InnListVO innListVO=new InnListVO();
				if(fvo.getMinPrice()==null || fvo.getMaxPrice()==null){
					System.out.println("=======controller ajax영역에 입장하셨습니다.=========");
					System.out.println("가격바 움직이지않았어요");
					innListVO=innService.findInnByWordAndAcceptNoAndDate(pageNo, fvo);
				}else{//날짜 들어간경우
					System.out.println("=======controller ajax영역에 입장하셨습니다.=========");
					System.out.println("가격바 움직였어요");
					innListVO=innService.findInnByWordAndAcceptNoAndDateWithPrice(pageNo,fvo);
				}
				System.out.println("검색결과 확인 : "+innListVO.getInnList());
				model.addAttribute("innListVO", innListVO);
				model.addAttribute("filterVO", fvo);
//				HttpSession session = request.getSession(false);
//				MemberVO memberVO = (MemberVO)session.getAttribute("mvo");
//				System.out.println("searchMemberVO : "+memberVO);
				return innListVO;
			}
			
		@RequestMapping("paymentForm.do")
		public String paymentForm(int innNo, String memeberId, Model model, BookVO bvo , HttpServletRequest request){
			HttpSession session=request.getSession(false);
			MemberVO vo= (MemberVO)session.getAttribute("mvo");
			String innNo2=Integer.toString(innNo);
			String payTotalPrice=request.getParameter("payTotalPrice");
			System.out.println(payTotalPrice);
			boolean flag = false;
			//예약 정보가 이상할 경우 확인
			bvo.setMemberId(vo.getMemberId());
			HashMap<String, Object> result = new HashMap();
			result.put("flag",flag);
			try {
				 result = innService.bookInsert(bvo, innNo2, vo.getMemberId());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = (Boolean) result.get("flag");
			model.addAttribute("innNo", innNo);
			if(flag==true){
				model.addAttribute("result", result);
				return "inn_book_fail";
			}
			//여기까지 추가해서 	변경
			System.out.println(flag+"   flag가 false이면 이상없으므로 예약 가능");
			List<InnPicCompVO> list=innService.selectByInnNo(innNo2);
			InnPicCompVO innMainPic=list.get(0);
			System.out.println(bvo);
			InnVO ivo=innService.getInnByInnNo(innNo);
			System.out.println(ivo+"InnNo로 찾아온 해당 숙소 정보");
			model.addAttribute("ivo", ivo);
			model.addAttribute("bookInfo", bvo);
			model.addAttribute("innMainPic", innMainPic);
			model.addAttribute("payTotalPrice", payTotalPrice);
			return "member_payment_form";
		}
	@RequestMapping("getInnMasterAccount.do")
	@ResponseBody
	public MemberVO getInnMasterAccount( HttpServletRequest request, Model model){
		HttpSession session=request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		String innNo2=request.getParameter("innNo");
		int innNo=Integer.parseInt(innNo2);
		System.out.println(innNo);
		InnVO ivo=innService.getInnByInnNo(innNo);
		MemberVO masterVO=innService.getMemberAccountByInnNo(innNo2);
		System.out.println(masterVO+"    "+"getInnMasterAccount 컨트롤러 들어옴 찾아온 ");
		model.addAttribute("masterVO", masterVO);
		return masterVO;
	}
	//댓글추가
		@RequestMapping("reply.do")
		public String replyWrite(CommentVO covo, HttpServletRequest request){
			System.out.println("controller replyWrite 들어옴?"+covo);
			HttpSession session = request.getSession(false);
			MemberVO mvo= (MemberVO) session.getAttribute("mvo");
			innService.replyWrite(covo);
			System.out.println(covo);

			return"redirect:inn_in_show.do?innNo="+covo.getInnNo();
		}
		//댓굴삭제
		@RequestMapping("deleteReply.do")
		public String deleteReply(HttpServletRequest request, int commentNo){
			System.out.println(commentNo);
			int innNo = Integer.parseInt(request.getParameter("innNo"));
			System.out.println("commentNo, innNo : "+ commentNo+" , "+ innNo);
			HttpSession session=request.getSession(false);
			MemberVO vo= (MemberVO)session.getAttribute("mvo");
			innService.deleteReply(commentNo);
			System.out.println("삭제~~~");
			return"redirect:inn_in_show.do?innNo="+innNo;
		}
}






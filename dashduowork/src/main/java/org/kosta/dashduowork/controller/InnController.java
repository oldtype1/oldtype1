package org.kosta.dashduowork.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.InnService;
import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.kosta.dashduowork.model.vo.BookListVO;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
		    	 vo.setProfilePicVO(new ProfilePicVO(vo.getMemberId(),  "http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
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
	public String searchByCityDateNo(SearchVO vo, Model model){
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
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO wdvo=new DeleteVO(wishListNo,vo.getMemberId());
		innService.wishListDelete(wdvo);
		return "redirect:get_mywishlist.do";
	}	
	/*예약숙소*/
	@RequestMapping(value="bookdelete.do")
	public String bookDelete(int bookNo, HttpServletRequest request){
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO bdvo=new DeleteVO(bookNo, vo.getMemberId());
		innService.bookDelete(bdvo);
		return "redirect:get_mybooklist.do";
	}
	/*등록숙소*/
	@RequestMapping(value="inndelete.do")
	public String innDelete(int innNo, HttpServletRequest request){
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO idvo = new DeleteVO(innNo,vo.getMemberId());
		innService.innDelete(idvo);
		return "redirect:get_myinnlist.do";
	}
	/*거래내역목록*/
	@RequestMapping(value="tradedelete.do")
	public String tradeDelete(int tradeNo,int bookNo, HttpServletRequest request){
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		DeleteVO tdvo = null;
		if(bookNo!=0){ //예약 번호가 존재하면 예약테이블도 삭제하므로 예약번호를 같이 넘겨준다.
		tdvo = new DeleteVO(tradeNo,vo.getMemberId(),bookNo);
		}
		else{				//예약 번호가 존재하지 않으면 등록이므로 거래번호만 넘겨줌 
		tdvo = new DeleteVO(tradeNo,vo.getMemberId());
		}
		innService.tradeDelete(tdvo);
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
		HashMap<String, Object> map = (HashMap<String, Object>) innService.selectInn(innNo);
		System.out.println(map);
		InnVO ivo = (InnVO)map.get("innVO");
		List<InnPicCompVO> picList = innService.selectByInnNo(innNo);
		ProfilePicVO pvo=innService.selectByProfilePic(ivo.getMemberId());
		System.out.println(picList);
		System.out.println(pvo);
		
		map.put("picList", picList);
		map.put("pvo", pvo);
		model.addAttribute("VOMap", map);
		System.out.println("picList가 실행이 된다?");
		return "inn_in_show";
	}
		
}






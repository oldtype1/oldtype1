package org.kosta.dashduowork.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.InnService;
import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.AvailableDateVO;
import org.kosta.dashduowork.model.vo.BookDeleteVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnPicCompVO;
import org.kosta.dashduowork.model.vo.InnReservationListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.kosta.dashduowork.model.vo.TradeListVO;
import org.kosta.dashduowork.model.vo.WishListDeleteVO;
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
					nameList.add(fileName);
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
		  String filepath=pvo.getFilePath();
		  model.addAttribute("filepath", filepath);
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
		  String filepath=pvo.getFilePath();
		  model.addAttribute("filepath", filepath);
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
	public List<InnVO> selectInnByCheckedAmenity(FilterVO vo, Model model) {
		System.out.println("컨트롤러에서 FilterVO내용확인 : "+vo); //확인완료
		List<InnVO> list=null;
		if(vo.getAmenityBBQ().equals("N")&vo.getAmenityBed().equals("N")&vo.getAmenityKitchen().equals("N")&vo.getAmenityTV().equals("N")&vo.getAmenityWiFi().equals("N")){
			System.out.println("체크값 없어서 컨트롤러에서 다른곳으로 빠짐.");
			SearchVO svo=new SearchVO();
			svo.setAcceptableNo(vo.getFirstSearchPeopleNo());
			svo.setEndDate(vo.getFirstSearchEndDate());
			svo.setInnCity(vo.getFirstSearchCity());
			svo.setStartDate(vo.getFirstSearchStartDate());
			if(svo.getStartDate()==""){
				//날짜 안들어간경우		
				list=innService.findInnByCityAndAcceptableNo(svo);
			}else{//날짜 들어간경우
				list=innService.findInnByCityAndDateAndAcceptableNo(svo);
			}
			model.addAttribute("searchVO", svo);
		}else{
			if(vo.getFirstSearchStartDate()==""){//날짜 없는경우
				//서브쿼리 이용해서 도시+인원검색 후 결과를 filter처리 parameterType으로 새로운 VO를 만들거나 기존 VO에 변수 추가해야할듯
				list=innService.findInnByCityAndAcceptableNoWithFilter(vo);
			}else{//날짜 들어간경우
				//서브쿼리 이용해서 도시+날짜+인원검색 후 결과를 filter처리
				list=innService.findInnByCityAndDateAndAcceptableNoWithFilter(vo);
			}
		}
		//model.addAttribute("list", list);
		return list;
	}
	@RequestMapping(value="searchByCityDateNo.do")
	public String searchByCityDateNo(SearchVO vo, Model model){
		System.out.println(vo);
		List<InnVO> list=null;
		if(vo.getStartDate()==""){
			//날짜 안들어간경우		
			list=innService.findInnByCityAndAcceptableNo(vo);
		}else{//날짜 들어간경우
			list=innService.findInnByCityAndDateAndAcceptableNo(vo);
		}
		model.addAttribute("searchVO", vo);
		model.addAttribute("list", list);
		return "inn_search_result";
	}
	
	@RequestMapping(value="get_mybooklist.do")
	public String getMyBookList(String pageNo,HttpServletRequest request,Model model){
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		BookListVO blvo=innService.getmybooklist(vo, pageNo);
		ProfilePicVO pvo = memberService.selectProfilePic(vo.getMemberId());
		  String filepath=pvo.getFilePath();
		  model.addAttribute("filepath", filepath);
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
		  String filepath=pvo.getFilePath();
		  model.addAttribute("filepath", filepath);
		model.addAttribute("wlvo", wlvo);
		return"member_wish_list";
	}
@RequestMapping(value="wishlistdelete.do")
public String wishListDelete(int wishListNo, HttpServletRequest request){
	HttpSession session=null;
	session = request.getSession(false);
	MemberVO vo= (MemberVO)session.getAttribute("mvo");
	WishListDeleteVO wdvo=new WishListDeleteVO(vo.getMemberId(), wishListNo);
	innService.wishListDelete(wdvo);
	return "redirect:get_mywishlist.do";
}

@RequestMapping(value="bookdelete.do")
public String bookDelete(int bookNo, HttpServletRequest request){
	HttpSession session=null;
	session = request.getSession(false);
	MemberVO vo= (MemberVO)session.getAttribute("mvo");
	BookDeleteVO bdvo=new BookDeleteVO(bookNo, vo.getMemberId());
	innService.bookDelete(bdvo);
	return "redirect:get_mybooklist.do";
}

@RequestMapping(value="get_innReservation_list.do")
public String getInnReservationList(String pageNo, HttpServletRequest request, Model model){
	HttpSession session=null;
	session = request.getSession(false);
	MemberVO vo= (MemberVO)session.getAttribute("mvo");
	InnReservationListVO irlvo=innService.getMyInnReservationList(vo, pageNo);
	ProfilePicVO pvo = memberService.selectProfilePic(vo.getMemberId());
	  String filepath=pvo.getFilePath();
	  model.addAttribute("filepath", filepath);
	model.addAttribute("irlvo", irlvo);
	return "member_innReservation_list";
}

@RequestMapping(value="inn_in_show.do")
public String inShow(HttpServletRequest request, Model model){
   String innNo = (String)request.getParameter("innNo");
   Map<String, Object> map = (HashMap<String, Object>) innService.selectInn(innNo);
   System.out.println(map);
   model.addAttribute("VOMap", map);
   return "inn_in_show";
}

}






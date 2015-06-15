package org.kosta.dashduowork.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.InnService;
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
	public ModelAndView getMyInnList(String pageNo,HttpServletRequest request){
		
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");		
		InnListVO lvo = innService.getmyinnlist(vo,pageNo);
		return new ModelAndView("member_inn_list","lvo",lvo);
	
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
		System.out.println("get_mybooklist.do  콘트롤러왔음!!!!!!!!!!!!!!!!!"+pageNo);
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		System.out.println("get_mybooklist.do 콘트롤러에 들어온 vo!!!!!!!!!!!!!!!!"+vo);
		BookListVO blvo=innService.getmybooklist(vo, pageNo);
		System.out.println("get_mybooklist.do 콘트롤러에서 찾은 bvo!!!!!!!!!!!!!!!"+blvo.getPagingBean().getNowPage());
		model.addAttribute("blvo", blvo);
		return"member_book_list";
	}

	
	@RequestMapping(value="get_mywishlist.do")
	public String getMyWishList(String pageNo,HttpServletRequest request,Model model){
		System.out.println("getMyWishList.do  콘트롤러왔음!!!!!!!!!!!!!!!!!"+pageNo);
		HttpSession session=null;
		session = request.getSession(false);
		MemberVO vo= (MemberVO)session.getAttribute("mvo");
		System.out.println("getMyWishList.do 콘트롤러에 들어온 vo!!!!!!!!!!!!!!!!"+vo);
		WishListListVO wlvo=innService.getmywishlist(vo, pageNo);
		System.out.println("getMyWishList.do 콘트롤러에서 찾은 wlvo!!!!!!!!!!!!!!!"+wlvo.getPagingBean().getNowPage());
		model.addAttribute("wlvo", wlvo);
		return"member_wish_list";
	}
@RequestMapping(value="wishlistdelete.do")
public String wishListDelete(int wishListNo, HttpServletRequest request){
	System.out.println("wishListDelete 컨트롤러 들어옴.");
	HttpSession session=null;
	session = request.getSession(false);
	MemberVO vo= (MemberVO)session.getAttribute("mvo");
	System.out.println(vo+"        찾아들어옴");
	WishListDeleteVO wdvo=new WishListDeleteVO(vo.getMemberId(), wishListNo);
	System.out.println(wdvo+"삭제할 것을 VO에 넣어줌");
	//삭제하고 보내주기
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

}






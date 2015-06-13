package org.kosta.dashduowork.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.InnService;
import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.BookListVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InnController {
	
	@Resource(name="innServiceImpl")
	private InnService innService;
	
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
	public List<InnVO> selectInnByCheckedAmenity(AmenityVO vo, Model model) {
		System.out.println(vo+" 는 내가 전달한 값");
		List<InnVO> list =innService.findInnByCheckedAmenity(vo);
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


}






package org.kosta.dashduowork.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.InnService;
import org.kosta.dashduowork.model.vo.AmenityVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
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

}






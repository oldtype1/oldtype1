package org.kosta.dashduowork.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest request){
		MemberVO mvo=memberService.login(vo);
		System.out.println(vo);
		if(mvo==null)
			return "member_login_fail";
		else{
			HttpSession session=request.getSession();
			session.setAttribute("mvo", mvo);
			return "home";
		}		
	}
	
	@RequestMapping("logout.do")
	public String logoutMember(HttpServletRequest request){
		HttpSession session=null;
		session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		return "home";
	}
}

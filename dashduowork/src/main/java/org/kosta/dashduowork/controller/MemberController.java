package org.kosta.dashduowork.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service.Mode;

import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	   @RequestMapping("member_myprofile.do")
	   public String member_myprofile(String memberId, Model model){
	      MemberVO mvo=memberService.findMemberById(memberId);
	      System.out.println(mvo);
	      model.addAttribute("memberInfo", mvo);
	   return "member_myprofile";
	   }
	   
	   @RequestMapping("member_myprofile_update_form.do")
	   //업데이트하는 폼으로 보낸다. (프로필 보기 와 굉장히 비슷)
	   public String member_myprofile_update_form(String memberId, Model model){
	      MemberVO mvo=memberService.findMemberById(memberId);
	      System.out.println(mvo);
	      model.addAttribute("memberInfo", mvo);
	   return "member_myprofile_update_form";
	   }
	   
	   
	   @RequestMapping("member_updateInfo.do")
	   public String member_updateInfo(MemberVO membervo, Model model,HttpServletRequest request){
	      System.out.println(membervo+"member_updateInfo.do 페이지 들어옴!!!!!!");
	      memberService.updateMemberInfo(membervo);
	      MemberVO updateVO=memberService.findMemberById(membervo.getMemberId());
	      model.addAttribute("memberInfo", updateVO);
	      HttpSession session=request.getSession();
	      session.setAttribute("mvo", updateVO);
	      return "member_myprofile";
	   }
	
	   @RequestMapping("memberSecession.do")
	   public String memberSecession(String memberId, HttpServletRequest request){
	      HttpSession session=request.getSession();
	      memberService.memberSecession(memberId);
	      session.invalidate();
	      return "home";
	   }
	
}

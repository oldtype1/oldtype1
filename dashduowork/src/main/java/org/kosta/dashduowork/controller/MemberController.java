package org.kosta.dashduowork.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	@Resource(name="uploadPath")
	private String path;

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
	      return "member_myprofile_update_result";
	   }
	
	   @RequestMapping("memberSecession.do")
	   public String memberSecession(String memberId, HttpServletRequest request){
	      HttpSession session=request.getSession();
	      memberService.memberSecession(memberId);
	      session.invalidate();
	      return "home";
	   }
	// 6/10 
		@RequestMapping(value="register.do",method=RequestMethod.POST)
		public ModelAndView registerMember(ProfilePicVO pvo, MemberVO mvo){
			
			
			/* 
			 * 	-- 파일 업로드 부분
			 * 
			 *  파일 얻는 메서드  : list.get(i) 을 호출하면 File이 반환 
			 *  실제 디렉토리로 전송(업로드) 메서드 : 파일.transferTo(파일객체)
			 *  ModelAndView 에서 결과 페이지로 업로드한 파일 정보를 문자열배열로
			 *  할당해 jsp에서 사용하도록 한다. 
			 */
			System.out.println("mvo : "+mvo);
			System.out.println("pvo : "+pvo);
			MultipartFile file = pvo.getFile();
			//System.out.println(list.get(i).getOriginalFilename().equals(""));
				String fileName=file.getOriginalFilename();	
				System.out.println(file.isEmpty());
				if(!fileName.equals("")){
					try {
						pvo.setFilePath(path+mvo.getMemberId()+fileName);
						file.transferTo(new File(path+fileName));
						System.out.println("fileupload ok:"+fileName);
					} catch (Exception e) {					
						e.printStackTrace();
					}
				} // if -- 파일 업로드 끝 부분
				
			memberService.memberRegister(mvo, pvo);
				
			return new ModelAndView("home");
		}
}

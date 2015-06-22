package org.kosta.dashduowork.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.kosta.dashduowork.model.service.MemberService;
import org.kosta.dashduowork.model.vo.FilterVO;
import org.kosta.dashduowork.model.vo.InnListVO;
import org.kosta.dashduowork.model.vo.InnVO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.kosta.dashduowork.model.vo.SearchVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
   @Resource(name = "memberServiceImpl")
   private MemberService memberService;
	@Resource(name="uploadPath")
	private String uploadPath;
	@Resource(name="viewPath")
	private String viewPath;
	

   @RequestMapping(value = "login.do", method = RequestMethod.POST)
   public String login(MemberVO vo, HttpServletRequest request) {
      MemberVO mvo = memberService.login(vo);
      System.out.println(vo);
      if (mvo == null)
         return "member_login_fail";
      else {
         HttpSession session = request.getSession();
         session.setAttribute("mvo", mvo);
         MemberVO memberVO = (MemberVO)session.getAttribute("mvo");
         System.out.println("memberVO : "+memberVO);
         return "home";
      }
   }

   @RequestMapping("logout.do")
   public String logoutMember(HttpServletRequest request) {
      HttpSession session = null;
      session = request.getSession(false);
      if (session != null) {
         session.invalidate();
      }
      return "home";
   }

   @RequestMapping("member_myprofile.do")
   public String member_myprofile(String memberId, Model model){
      MemberVO mvo=memberService.findMemberById(memberId);
     ProfilePicVO pvo = memberService.selectProfilePic(memberId);
     if(pvo!=null){
      mvo.setProfilePicVO(pvo);
     }
     else{
     mvo.setProfilePicVO(new ProfilePicVO(memberId,"http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
     }
     model.addAttribute("memberInfo", mvo); 
      //mvo.getProfilePicVO().getFilePath()
      	return "member_myprofile";
   }

   @RequestMapping("member_myprofile_update_form.do")
   // 업데이트하는 폼으로 보낸다. (프로필 보기 와 굉장히 비슷)
   public String member_myprofile_update_form(String memberId, Model model) {
      MemberVO mvo = memberService.findMemberById(memberId);
      ProfilePicVO pvo = memberService.selectProfilePic(memberId);
      if(pvo!=null){
          mvo.setProfilePicVO(pvo);
         }
         else{
         mvo.setProfilePicVO(new ProfilePicVO(memberId,"http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png"));
         }
      model.addAttribute("memberInfo", mvo);
      return "member_myprofile_update_form";    
   }

 //업데이트 컨트롤러
   @RequestMapping("member_updateInfo.do")
   public String member_updateInfo(ProfilePicVO pvo,MemberVO mvo, Model model, BindingResult result,
         HttpServletRequest request) {
	   	System.out.println("mvo : " + mvo);
	      System.out.println("pvo : " + pvo);	      
	      MultipartFile file = pvo.getFile();
	      String fileName = mvo.getMemberId()+"_"+file.getOriginalFilename();
	      System.out.println(file.isEmpty());	      
	     
	      if(file.getOriginalFilename().equals("")){
	    	  pvo.setFilePath("http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png");
	      }
	      else if (!fileName.equals("")) {
	         try {
	        	 pvo.setFilePath(viewPath+fileName);
	        	 //mvo.getProfilePicVO().setFilePath(viewPath+fileName);
				file.transferTo(new File(uploadPath+fileName));
				System.out.println("fileupload ok:"+fileName);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	      else {
	    	  //mvo.getProfilePicVO().setFilePath("http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png");
	    	  pvo.setFilePath("none");     
	      }  
      memberService.updateMemberInfo(mvo,pvo);
      
      MemberVO updateVO = memberService.findMemberById(mvo.getMemberId());
      HttpSession session = request.getSession();
      session.setAttribute("mvo", updateVO);
      
      if (result.hasErrors()) {
          return "member_myprofile_update_form"; // 유효성 검사에 에러가 있으면 가입폼으로 다시 보낸다.
       }
      return "redirect:member_myprofile.do?memberId="+mvo.getMemberId();
      
   }


   @RequestMapping("memberPasswordcheck.do")
   public String memberPasswordcheck(String memberPass, String memberId, Model model){
      String memberPassword=memberService.memberPasswordcheck(memberId);
      System.out.println(memberPassword);
      System.out.println(memberId+"  "+memberPass+"memberPasswordcheck컨트롤러 들어옴");
      if(!(memberPass.equals(memberPassword))){
         return "member_memberCheckPassword_fail";
      }else{
         //비밀번호 재확인 시 비번 맞을경우
         model.addAttribute("memberId", memberId);
         return "redirect:memberSecession.do";
      }
   }
      
   @RequestMapping("memberSecession.do")
   public String memberSecession(String memberId, HttpServletRequest request){
      System.out.println("memberSecession콘트롤러들어옴");
      HttpSession session=request.getSession();
      memberService.memberSecession(memberId);
      session.invalidate();
      return "member_memberSecession_result";
   }

   // 6/11 - 밸리데이션 적용
   @RequestMapping(value = "member_register_form.do", method = RequestMethod.GET)
   public ModelAndView registerForm() {
      // Validation 을 위해 register_form.jsp 에서 사용할 수 있도록 객체를 생성해 전달한다.
      // <form:form action="register.do" commandName="memberVO">
      return new ModelAndView("member_register_form", "memberVO", new MemberVO());

   }

   // 6/10
   @RequestMapping(value = "member_register.do", method = RequestMethod.POST)
   public String registerMember(ProfilePicVO pvo, @Valid MemberVO mvo,
         BindingResult result) {

      /*
       * -- 파일 업로드 부분
       * 
       * 파일 얻는 메서드 : list.get(i) 을 호출하면 File이 반환 실제 디렉토리로 전송(업로드) 메서드 :
       * 파일.transferTo(파일객체) ModelAndView 에서 결과 페이지로 업로드한 파일 정보를 문자열배열로 할당해
       * jsp에서 사용하도록 한다.
       */
      System.out.println("mvo : " + mvo);
      System.out.println("pvo : " + pvo);
      MultipartFile file = pvo.getFile();
      // System.out.println(list.get(i).getOriginalFilename().equals(""));
      String fileName = mvo.getMemberId()+"_"+file.getOriginalFilename();

      System.out.println(file.isEmpty());
      
      if(file.getOriginalFilename().equals("")){
    	  pvo.setFilePath("http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png");
      }
      else if (!fileName.equals("")) {
         try {
        	 pvo.setFilePath(viewPath+fileName);
			file.transferTo(new File(uploadPath+fileName));
			System.out.println("fileupload ok:"+fileName);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else {
         pvo.setFilePath("none");
            }         
         if (result.hasErrors()) {
            return "member_register_form"; // 유효성 검사에 에러가 있으면 가입폼으로 다시 보낸다.
         }
         memberService.memberRegister(mvo, pvo);
         return "home";// 문제 없으면 결과 페이지로 이동한다.
      }
   
   @RequestMapping("member_idcheck.do")
		@ResponseBody
		public String memberIdCheck(String memberId){
		System.out.println("들어옴???"+memberId);
	String id=memberService.memberIdcheck(memberId);
	System.out.println("아이디 중복1"+id);
	System.out.println("아이디 중복2"+memberId);
	String info="fail";
	if(!(memberId.equals(id))){
			info="ok";			
	}
	System.out.println(info);
	return info;	
		}
     
      /*
       * @RequestMapping(value="member_register_form.do",method=RequestMethod.GET
       * ) public ModelAndView registerForm(){ // Validation 을 위해
       * register_form.jsp 에서 사용할 수 있도록 객체를 생성해 전달한다. //<form:form
       * action="register.do" commandName="memberVO"> return new
       * ModelAndView("member_register_form","memberVO",new MemberVO()); }
       */
      /*
       * @RequestMapping(value="register.do",method=RequestMethod.POST) public
       * String register(@Valid MemberVO memberVO,BindingResult result){
       * if(result.hasErrors()){ return "member_register_form"; // 유효성 검사에 에러가
       * 있으면 가입폼으로 다시 보낸다. } return "home";// 문제 없으면 결과 페이지로 이동한다. }
       */

    @RequestMapping(value ="searchPass.do", method=RequestMethod.POST )
	public String searchPass(MemberVO vo, Model model) {
	   System.out.println("controller에서 seachPass 실행 : "+vo);
	   MemberVO mvo = memberService.searchPass(vo);
	   System.out.println("mvo 결과 : "+mvo);
	   model.addAttribute("mvo", mvo);
	   return "member_searchPassResult";
	}
   
}

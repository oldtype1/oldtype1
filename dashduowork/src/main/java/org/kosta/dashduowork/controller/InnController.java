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
	
	/**
	 *  코드 제작 : 은수, 정은
	 *  숙소 등록 메서드로서 
	 * @param request
	 * @param ivo
	 * @param ipvo
	 * @param avo
	 * @param avvo
	 * @return
	 */
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
	/** 
	 * @작성자:주형
	 * @Method설명: 마이페이지 등록숙소 - 사용자가 등록한 숙소 리스트 불러오기
	 * @param pageNo : 페이징빈을 적용하여 보여줄 페이지 번호가 넘어옴
	 * @param request: 로그인한 멤버(세션)을 받음 
	 * @param model: vo: 로그인 사용자 lvo: 사용자의 숙소리스트+페이징빈 정보
	 * @return
	 */
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
	/** 
	 * @작성자:주형
	 * @Method설명: 마이페이지 거래내역 - 사용자가 예약했던 숙소와 
	 * 예약된 사용자의 숙소 리스트 불러오기
	 * @param pageNo : 페이징빈을 적용하여 보여줄 페이지 번호가 넘어옴
	 * @param request: 로그인한 멤버(세션)을 받음 
	 * @param model: vo: 로그인 사용자 tvo: 사용자 거래내역+페이징빈 정보
	 * @return
	 */
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
	/** 작성자: 김윤정
	    * @param pageNo : 페이징빈을 적용하여 보여줄 페이지 번호가 넘어온다.
	    * @param request: 로그인한 멤버(세션)을 받는다.
	    * @param model: vo: 로그인 사용자 blvo: 사용자의 예약리스트+페이징빈 정보
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					그 이외에는 return "member_book_list"으로 보내준다.
	    * @Method 설명 : 사용자가 현재 예약한 숙소 리스트를 불러온다.
	    */
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

	/** 작성자: 김윤정
	    * @param pageNo : 페이징빈을 적용하여 보여줄 페이지 번호가 넘어옴
	    * @param request: 로그인한 멤버(세션)을 받음 
	    * @param model: vo: 로그인 사용자 wlvo: 사용자의 위시리스트+페이징빈 정보
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					그 이외에는 return "member_wish_list"으로 보내준다.
	    * @Method 설명 : 현재 사용자의 위시리스트에 있는 숙소 목록을 불러온다.
	    */
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
	/** 작성자: 김윤정
	    * @param pageNo : 페이징빈을 적용하여 보여줄 페이지 번호가 넘어옴
	    * @param request: 로그인한 멤버(세션)을 받음 
	    * @param model: vo: 로그인 사용자 irlvo: 사용자가 등록한 숙소의 예약 리스트+페이징빈 정보
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					그 이외에는 return "member_innReservation_list"으로 보내준다.
	    * @Method 설명 : 현재 사용자가 등록한 숙소의 예약 목록을 불러온다.
	    */
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
	/** 작성자: 김윤정
	    * @param wishListNo : 위시리스트 번호를 받아온다.
	    * @param request: 로그인한 멤버(세션)을 받아온다.
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					그 이외에는 return "redirect:get_mywishlist.do"으로 보내준다.
	    * 	->  작업을 완료한 후 redirect로 get_mywishlist.do로 이동해 
	    * 		해당 숙소가 삭제된 사용자의 위시리스트를 불러온다.
	    * @Method 설명 : 위시리스트 목록에서 받아온 위시리스트 번호로 
	    * 							해당 숙소를 위시리스트목록에서 삭제한다.
	    */
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
	/** 작성자: 김윤정
	    * @param bookNo : 예약 번호를 받아온다.
	    * @param request: 로그인한 멤버(세션)을 받아온다.
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					그 이외에는 return "redirect:get_mybooklist.do"으로 보내준다.
	    * 	->  작업을 완료한 후 redirect로 get_mybooklist.do로 이동해 
	    * 		해당 숙소가 삭제된 사용자의 예약리스트를 불러온다.
	    * @Method 설명 : 예약 목록에서 받아온 위시리스트 번호로 
	    * 							해당 숙소를 예약 목록에서 삭제한다.
	    */
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
	/** 작성자: 김윤정
	    * @param innNo : 숙소 번호를 받아온다.
	    * @param request: 로그인한 멤버(세션)을 받아온다.
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 				   삭제하려는 숙소가 다른 사용자에게 예약되어 삭제가 불가한
	    * 					경우에는 return "member_delete_fail"로 보내주며 message를 보여준다.
	    * 					그 이외에는 return "redirect:get_myinnlist.do"으로 보내준다.
	    * 	->  작업을 완료한 후 redirect로 get_myinnlist.do로 이동해 
	    * 		해당 숙소가 삭제된 등록 숙소 목록을 불러온다.
	    * @Method 설명 : 등록 숙소 목록에서 받아온 숙소 번호로 
	    * 							해당 숙소를 숙소 목록에서 삭제한다.
	    */
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
	/** 작성자: 김윤정
	    * @param tradeNo : 거래 번호를 받아온다.
	    * @param request: 로그인한 멤버(세션)을 받아온다.
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					그 이외에는 return "redirect:get_mytradelist.do"으로 보내준다.
	    * 	->  작업을 완료한 후 redirect로 get_mytradelist.do로 이동해 
	    * 		해당 거래가 삭제된 거래 목록을 불러온다.
	    * @Method 설명 : 거래 목록에서 받아온 거래 번호로 해당 거래를 거래내역 목록에서 삭제한다.
	    */
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
	
	/** 작성자: 김윤정
	    * @param innNo : 숙소 번호를 받아온다.
	    * @param request: 로그인한 멤버(세션)을 받아온다.
	    * @param model: map, avo, avvo, list : 해당 숙소의 상세 정보
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					해당숙소가 존재안하는등 에러상황에는 return "inn_in_show_fail"으로 보내준다
	    * 					그 이외에는 return "member_innupdate_form"으로 보내준다.
	    * @Method 설명 : 숙소번호로 받아온 숙소 정보를 "member_innupdate_form"으로 보내주어
	    * 							숙소 기존 정보를 사용자에게 나타내준다.
	    */
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
	/** 작성자: 김윤정
	    * @param innNo, ivo, avo, ipvo , avvo : 변경할 숙소 기본 정보, 숙소 ammenity, 숙소 사진, 가능 날짜를 받아온다.
	    * @param request: 로그인한 멤버(세션)을 받아온다.
	    * @param model: map, avo, avvo, list : 해당 숙소의 상세 정보
	    * @return : 세션이 만료되거나 예외상황에는 return "member_session_fail"으로 보내준다
	    * 					그 이외에는"redirect:innupdateform.do?innNo="+innNo로 보내준다.
	    * -> 받아온 InnPicCompVO의 파일 이름을 중복을 예방을 위해 Math.random() 붙여준다
	    * @Method 설명 : 받아온 변경할 숙소의 정보를 수정한다.
	    */
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
	/** 작성자: 김윤정
	    * @param innNo, innPicNo : 숙소 수정 페이지에서 숙소 번호와 삭제할 사진 번호를 받아온다
	    * @param request: 로그인한 멤버(세션)을 받아온다
	    * @return : List<InnPicCompVO>로 return 해준다.
	    * @Method 설명 : innPicNo에 해당하는 사진을 삭제한다.
	    * @ResponseBody : Ajax 방식으로 보내준다.
	    */
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
		// TODO booking안에서 검사와 예약을 나눌 것
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
	/**
	 *  @작성자:주형
	 * @Method설명: 숙소가능여부 변경 6/19
	 * @param request : 로그인한 멤버 받아옴-->없으면 세션만료
	 * @param innNo: 숙소 번호 받아옴
	 * @param innAvailability: 숙소가능여부 컬럼 --> y면 n로 n면 y로 바꿔준다.
	 * @return redirect로 등록숙소 리스트로 보낸다.
	 */
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
		/** 작성자: 김윤정
		    * @param innNo : 해당 숙소 번호를 받아온다.
		    * @param request: 로그인한 멤버(세션)을 받아온다.
		    * @return : MemberVO 
		    * @Method 설명 : inn_in_show.jsp에서 위시리스트클릭시 해당 이미지를 바꿔준다. 
		    * @ResponseBody : Ajax 방식으로 보내준다.
		    */
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
	/**
	 * @작성자:주형
	 * @Method설명: 거래목록에서 별점 매기는 메서드
	 * @param irv: 별점객체로 숙소번호,총별점,(이용자들이)매긴횟수 3가지가 있다.
	 * @param tradeNo: 거래번호--> 별점을 매긴 거래내역은 별점체크 확인을 N으로 바꿔준다.
	 * (최초에 별점을 매기지 않은 경우에만 별점을 주고 그다음부터는 체크 안함)
	 * @param request: 로그인한 멤버 받아옴-->없으면 세션만료 
	 * @param model: 별점을 매기는 숙소가 없을 경우 exception메세지 주기 위함
	 * @return: 별점메서드(ratinginn)을 실행한 후 redirect로 숙소 상세페이지로 이동
	 */
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
	
	
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	// 
	// 									검색 관련 메서드
	//
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	//6/17일 추가(지역명 자동완성)
	/**
	 * @param fvo : 검색어를 받아오기 위해 사용.
	 * @return : DB에서 받아온 데이터 리스트를 view에 전달하기 위해 사용.
	 * @작성자 은식,동원
	 * @Method 설명 : @ResponseBody 처리하여 입력받은 값에 따른 결과를 실시간으로 view에 전달하는 역할을 하는 메서드.
	 * 							  view에 전달하는 값은 입력값이 포함된 모든 주소의 리스트
	 */
	@RequestMapping(value="searchCityAuto.do")
	@ResponseBody
	public List<InnVO> findCityAuto(FilterVO fvo) {
		List<InnVO> list=null;
		list = innService.findInnCityListByInnCityCharacter(fvo);
		return list;
	}
	//6/25 검색메서드 추가
	/**
	 * @param pageNo : 페이지 넘버를 받아오기 위해 사용. 
	 * @param fvo : 숙소 검색 요구 조건을 받아오기 위해 사용.
	 * @param model : (숙소 정보 + 페이징 정보)를 view에 전달하기 위해 사용. 
	 * @return : tiles-inn.xml에서 경로 설정 -> search_result.jsp로 이동   
	 * @작성자 은식,동원
	 * @Method 설명 : 1) 초기화면 숙소 검색 요소 세가지(숙소 지역명 / 숙소 사용 희망일(시작 && 끝) / 숙소 사용 인원수).
	 * 						    	 위 세가지 조건에 해당되는 숙소 검색 메서드. 
	 *                       	 2) 필터이용 검색에는 페이징을 추가하지 않음.
	 * 					    
	 */
	@RequestMapping(value="searchInnByWordDateNo.do")
	public String searchInnByCityDateNo(String pageNo, FilterVO fvo, Model model){
		System.out.println("컨트롤러에서 pageNo확인 : "+pageNo);
		InnListVO innListVO=new InnListVO();
		List<InnVO> list=null;
		if(fvo.getMinPrice()==null || fvo.getMaxPrice()==null || fvo.getAmenityItems()==null || fvo.getMinPrice()=="" || fvo.getMaxPrice()==""){
			innListVO=innService.findInnByWordAndAcceptNoAndDate(pageNo, fvo);
		}else{//날짜 들어간경우
			innListVO=innService.findInnByWordAndAcceptNoAndDateWithPrice(fvo);
		}
		List<ReportVO> wordlist=reportService.selectReport();
		model.addAttribute("wordlist", wordlist);
		model.addAttribute("innListVO", innListVO);
		model.addAttribute("filterVO", fvo);
		return "inn_search_result";
	}
	//6/25 검색메서드 추가
	/**
	 * @param fvo : 숙소 검색 요구 조건을 받아오기 위해 사용.
	 * @return InnListVO : ajax로 요청 -> 해당 리스트(기본조건+필터 검색)를 보냄.
	 * @작성자 은식,동원
	 * @Method 설명 : 1) 초기화면 숙소 검색 요소 세가지(숙소 지역명 / 숙소 사용 희망일(시작 && 끝) / 숙소 사용 인원수)
	 *                          	 에 필터검색 조건을 추가한 메서드(ajax로 보여준다).
	 *                       	 2) 필터이용 검색에는 페이징을 추가하지 않음.
	 * 					    
	 */
	@RequestMapping(value="searchInnByWordDateNoWithFilter.do")
	@ResponseBody
	public InnListVO searchInnByWordDateNoWithFilter(FilterVO fvo, Model model){
		InnListVO innListVO=new InnListVO();
		List<InnVO> list=null;
		if(fvo.getMinPrice()==null || fvo.getMaxPrice()==null){
			innListVO=innService.findInnByWordAndAcceptNoAndDate("1",fvo);
		}else{
			innListVO=innService.findInnByWordAndAcceptNoAndDateWithPrice(fvo);
		}
		model.addAttribute("innListVO", innListVO);
		model.addAttribute("filterVO", fvo);
		return innListVO;
	}
	/** 작성자: 김윤정
	    * @param innNo, memberId, bvo : 해당 숙소 번호와 사용자 아이디 해당 숙소 예약정보를 받아온다.
	    * @param request : 로그인한 멤버(세션)을 받아온다.
	    * @param model : ivo, bvo, innMainPic, payTotalPrice : 결제페이지로 예약 정보, 숙소 사진, 결제 금액을 보내준다.
	    * @return : bvo로 받아온 예약 정보의 날짜가 예약가능 한지 검사하는 메서드를 거친 후  예약 불가시 
	    * 					return "inn_book_fail" 보내준다. 
	    * 					예약가능시에는 return "member_payment_form"으로 보내준다.
	    * @Method 설명 : 숙소 상세페이지에서 받아온 정보가 예약가능한지 검사하는 메서드를
	    * 							거친 후 예약 가능 유무에 따라 다른 페이지를 return 해준다.
	    */
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
				 result = innService.bookOptionCheck(bvo, innNo2, vo.getMemberId());
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
		/** 작성자: 김윤정
		    * @param request : 로그인한 멤버(세션)을 받아온다.
		    * @param model : 해당 숙소를 등록한 사용자(숙소 주인)의 계좌번호를 보내준다.
		    * @Method 설명 : "member_payment_form"의 select에서 무통장 입금을 누를 경우 
		    * 							해당숙소의 번호를 받아와 해당 숙소를 등록한 사용자(숙소 주인)의 계좌번호를
		    * 							보내준다.
		    * @ResponseBody : Ajax 방식으로 보내준다
		    * 							
		    */
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
	/**
	 * @param covo : 페이지에서 댓글 쓴 정보를 담기 위한 그릇이다.
	 * @param request : 로그인 세션을 받아오기 위해서 썼다
	 * @return : "redirect:inn_in_show.do?innNo="+covo.getInnNo();
	 * -> insert 작업에 재반복을 막기 위해 redirect로 접근해 
	 * innNo를 정보를 가지고 inn_in_show.do로 이동해서 정보를 뿌려준다. 
	 * @작성자 : 은수, 정은
	 * @Method설명 : 상세보기에 댓글추가하는 메서드
	 */
	//댓글추가
		@RequestMapping("reply.do")
		public String replyWrite(CommentVO covo, HttpServletRequest request){
			System.out.println("controller replyWrite 들어옴?"+covo);
			//현재 session이 존재하면 기존 session 리턴하고 존재하지 않으면 null 리턴
			HttpSession session = request.getSession(false);
			MemberVO mvo= (MemberVO) session.getAttribute("mvo");//로그인세션
			innService.replyWrite(covo); //innService의  replyWrite(covo)를 호출하여 댓글를 추가 한다.
			System.out.println(covo);
			return"redirect:inn_in_show.do?innNo="+covo.getInnNo();//inn_in_show.do로 리턴한다.
		}
		/**
		 * @param request : 로그인 세션을 받아오기 위해서 썼다
		 * @param commentNo : 댓글번호를 받아온다
		 *@return : "redirect:inn_in_show.do?innNo="+innNo;
		 * -> insert 작업에 재반복을 막기 위해 redirect로 접근해 
	 	 * innNo를 정보를 가지고 inn_in_show.do로 이동해서 정보를 뿌려준다. 
		 * @작성자 : 은수, 정은
		 * @Method설명 : 상세보기에서 댓글을 삭제하는 메서드이며  자신이 쓴 댓글만 삭제할 수 있다.
		 */
		//댓굴삭제
		@RequestMapping("deleteReply.do")
		public String deleteReply(HttpServletRequest request, int commentNo){
			System.out.println(commentNo); 
			int innNo = Integer.parseInt(request.getParameter("innNo"));//숙소넘버를 받아온다.
			System.out.println("commentNo, innNo : "+ commentNo+" , "+ innNo);
			//현재 session이 존재하면 기존 session 리턴하고 존재하지 않으면 null 리턴
			HttpSession session=request.getSession(false);
			MemberVO vo= (MemberVO)session.getAttribute("mvo");//로그인세션
			innService.deleteReply(commentNo);//innService의  deleteReply(commentNo)를 호출하여 삭제 한다.
			System.out.println("삭제~~~");
			return"redirect:inn_in_show.do?innNo="+innNo;//inn_in_show.do로 리턴한다.
		}
}






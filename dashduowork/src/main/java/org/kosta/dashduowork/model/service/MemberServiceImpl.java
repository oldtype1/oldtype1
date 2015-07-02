package org.kosta.dashduowork.model.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.dao.BookDAO;
import org.kosta.dashduowork.model.dao.MemberDAO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import Exception.ChildBookTableException;

@Service
public class MemberServiceImpl implements MemberService {
	@Resource(name = "memberDAOImpl")
	private MemberDAO memberDAO;
	@Resource(name = "bookDAOImpl")
	private BookDAO bookDAO;

	/*
	 * @Resource(name="uploadPath") private String uploadPath;
	 * 
	 * @Resource(name="viewPath") private String viewPath;
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kosta.dashduowork.model.service.MemberService#login(org.kosta.dashduowork
	 * .model.vo.MemberVO)
	 */
	/**
	 * @param vo
	 *            : 회원정보를 가져오는 객체이다
	 * @return
	 * @작성자 : 은수, 정은
	 * @Method설명 : 회원 로그인 하는 메서드
	 */
	@Override
	public MemberVO login(MemberVO vo) {
		return memberDAO.login(vo); // memberDAO에서 login(vo)을 호출
	}

	@Override
	public MemberVO findMemberById(String memberId) {
		return memberDAO.findMemberById(memberId);
	}

	@Override
	public void updateMemberInfo(MemberVO mvo, ProfilePicVO pvo,
			String uploadPath, String viewPath) throws Exception {

		System.out.println("mvo : " + mvo);
		System.out.println("pvo : " + pvo);
		MultipartFile file = pvo.getFile();
		String fileName = mvo.getMemberId() + "_" + file.getOriginalFilename();
		System.out.println(file.isEmpty());

		if (file.getOriginalFilename().equals("")) {
			pvo.setFilePath("http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png");
		} else if (!fileName.equals("")) {
			pvo.setFilePath(viewPath + fileName);
			file.transferTo(new File(uploadPath + fileName));
			System.out.println("fileupload ok:" + fileName);
		} else {
			pvo.setFilePath("none");
		}
		memberDAO.updateMemberInfo(mvo);
		ProfilePicVO vo = memberDAO.selectProfilePic(mvo.getMemberId());
		if (vo != null) {
			memberDAO.updateProfilePic(pvo);
		} else {
			memberDAO.insertProfilePic(pvo);
		}
	}

	@Override
	public void memberSecession(String memberId) throws ChildBookTableException {
		int count = bookDAO.checkChildBookTablebymemberId(memberId);
		System.out.println("book 자식테이블" + count);
		if (count > 0) {// 참조하는 자식테이블이 있으므로 에러난다.
			throw new ChildBookTableException("고객님께 예약된 숙소가 있어 탈퇴할 수 없습니다!");
		} else {// 참조하는 자식테이블이 없다.
			System.out.println("자식테이블이 없으므로 회원탈퇴합니다.");
			memberDAO.memberSecession(memberId);
		}
	}

	@Override
	public String memberPasswordcheck(String memberId) {
		return memberDAO.memberPasswordcheck(memberId);
	}

	/**
	 * 
	 * @param pvo
	 *            : 가입 페이지에서 선택된 사진을 저장하기 위한 VO객체
	 * @param mvo
	 *            : 가입 페이지에서 멤버 정보를 저장하기 위한 VO 객체, 밸리데이션이 적용되었다.
	 * @param viewPath
	 *            : 서버에서 사진을 찾기 위한 상대경로
	 * @param uploadPath
	 *            : 서버에 직접 사진을 저장하기 위한 절대경로
	 * @return : String \n- member_register_form : 회원가입 시 밸리데이션에 위배되는 상황 발생 시,
	 *         회원 가입 폼으로 돌려보내고 오류메세지를 표시한다.\n- home : 회원가입에 문제 없을 시 회원가입을 끝내고
	 *         홈으로 돌려보낸다.
	 * @작성자 : 은수, 정은
	 * @Method설명 : memberService의 회원 가입 메서드.
	 */
	@Transactional
	// 트랜잭션 적용
	@Override
	public void memberRegister(MemberVO mvo, ProfilePicVO pvo, String viewPath,
			String uploadPath) throws Exception {

		/*
		 * -- 파일 업로드 부분
		 * 
		 * 파일 얻는 메서드 : list.get(i) 을 호출하면 File이 반환 실제 디렉토리로 전송(업로드) 메서드 :
		 * 파일.transferTo(파일객체) ModelAndView 에서 결과 페이지로 업로드한 파일 정보를 문자열배열로 할당해
		 * jsp에서 사용하도록 한다.
		 * 
		 * -- MultiPartFile 클래스의 주요 메서드
		 * 
		 * - String getOriginalFilename() : 담겨 있는 파일의 원래 이름을 반환한다. - long
		 * getSize() : 파일의 크기를 볼 수 있다. - boolean isEmpty() : 파일 내용이 비어있으면 true,
		 * 있으면 false 반환 - void transferTo(File dest) : 지정된 대상 파일에 수신 된 파일을 전송.
		 */
		System.out.println("mvo : " + mvo);
		System.out.println("pvo : " + pvo);

		MultipartFile file = pvo.getFile(); // MultipartFile 클래스 객체에 사진 파일을 저장
		// System.out.println(list.get(i).getOriginalFilename().equals(""));
		// 서버에 올라갈 사진 이름이 중복되지 않도록 PK인 memberId와 혼합하여 지음
		String fileName = mvo.getMemberId() + "_" + file.getOriginalFilename();

		System.out.println(file.isEmpty());

		if (file.getOriginalFilename().equals("")) { // 만약 파일의 원래 이름이 비어있다면 ==
														// if(file.isEmpty())
			pvo.setFilePath("http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png");
		} // if
		else if (!fileName.equals("")) { // 사진파일이 존재하면
			pvo.setFilePath(viewPath + fileName);
			file.transferTo(new File(uploadPath + fileName)); // 설정된 path로 사진을
																// 전송한다
			System.out.println("fileupload ok:" + fileName);
		} else {
			pvo.setFilePath("none");
		} // else
		memberDAO.insertMember(mvo);
		memberDAO.insertProfilePic(pvo);
	} // end of method memberRegister

	public ProfilePicVO selectProfilePic(String memberId) {
		ProfilePicVO pvo = memberDAO.selectProfilePic(memberId);
		System.out.println("service에서 pvo:" + pvo);
		return pvo;
	}

	@Override
	public String memberIdcheck(String memberId) {
		System.out.println("service에서 idcheck" + memberId);
		return memberDAO.memberIdcheck(memberId);
	}

	public MemberVO searchPass(MemberVO vo) {
		System.out.println("service에서 searchPass" + vo);
		return memberDAO.selectPasswordByAnswer(vo);
	}

}

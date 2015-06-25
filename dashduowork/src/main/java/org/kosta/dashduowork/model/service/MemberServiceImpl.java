package org.kosta.dashduowork.model.service;

import javax.annotation.Resource;

import org.kosta.dashduowork.model.dao.BookDAO;
import org.kosta.dashduowork.model.dao.MemberDAO;
import org.kosta.dashduowork.model.vo.MemberVO;
import org.kosta.dashduowork.model.vo.ProfilePicVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Exception.ChildBookTableException;

@Service
public class MemberServiceImpl implements MemberService {
   @Resource(name="memberDAOImpl")
   private MemberDAO memberDAO;
   @Resource(name="bookDAOImpl")
   private BookDAO bookDAO;
   
   /* (non-Javadoc)
    * @see org.kosta.dashduowork.model.service.MemberService#login(org.kosta.dashduowork.model.vo.MemberVO)
    */
   @Override
   public MemberVO login(MemberVO vo){
      return memberDAO.login(vo);
   }
      @Override
      public MemberVO findMemberById(String memberId) {
         return memberDAO.findMemberById(memberId);
      }

      @Override
      public void updateMemberInfo(MemberVO mvo,ProfilePicVO pvo) {
      memberDAO.updateMemberInfo(mvo);
      ProfilePicVO vo = memberDAO.selectProfilePic(mvo.getMemberId());
     if(vo!=null){
      memberDAO.updateProfilePic(pvo);
     }
     else{
    	 memberDAO.insertProfilePic(pvo);
     }
      }
      
      @Override
      public void memberSecession(String memberId) throws ChildBookTableException{
         int count = bookDAO.checkChildBookTablebymemberId(memberId);
		 System.out.println("book 자식테이블"+count);
			if(count>0){//참조하는 자식테이블이 있으므로 에러난다.
				throw new ChildBookTableException("고객님께 예약된 숙소가 있어 회원탈퇴할 수 없습니다!");
			}
			else{//참조하는 자식테이블이 없다.
				System.out.println("자식테이블이 없으므로 회원탈퇴합니다.");
				 memberDAO.memberSecession(memberId);  
			}	
      }

      @Override
      public String memberPasswordcheck(String memberId) {
         return memberDAO.memberPasswordcheck(memberId);
      }
      @Transactional      // 트랜잭션 적용
      @Override
      public void memberRegister(MemberVO mvo, ProfilePicVO pvo) {
         memberDAO.insertMember(mvo);
         memberDAO.insertProfilePic(pvo);
         
      }
      public ProfilePicVO selectProfilePic(String memberId) {
  		ProfilePicVO pvo =  memberDAO.selectProfilePic(memberId);
  		System.out.println("service에서 pvo:"+pvo);
  		return pvo;
  	}
      @Override
  	public String memberIdcheck(String memberId) {
  		System.out.println("service에서 idcheck"+memberId);
  		return memberDAO.memberIdcheck(memberId);
  	}
      
    public MemberVO searchPass(MemberVO vo){
    	System.out.println("service에서 searchPass"+ vo);
    	return memberDAO.selectPasswordByAnswer(vo);
    }
    

}
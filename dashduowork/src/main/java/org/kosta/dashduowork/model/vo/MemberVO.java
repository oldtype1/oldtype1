package org.kosta.dashduowork.model.vo;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class MemberVO {
	@Email
	@NotEmpty
	private String memberId;
	@NotEmpty
	private String memberPass;
	@NotEmpty
	private String memberName;
	@NumberFormat(style = Style.NUMBER)
	@NotEmpty
	private String memberTel;
	@NotEmpty
	private String memberInfo;
	private String memberAnswerType;
	@NotEmpty
	private String memberPassAnswer;
	private String memberBank;
	@NotEmpty
	@NumberFormat(style = Style.NUMBER)
	private String memberBankAcount;
	private ProfilePicVO profilePicVO;
	private List<WishListVO> wishList;
	private List<BookVO> book;
	private List<InnVO> inn;

	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String memberId, String memberPass, String memberName,
			String memberTel, String memberInfo, String memberAnswerType,
			String memberPassAnswer, String memberBank,
			String memberBankAcount, ProfilePicVO profilePicVO,
			List<WishListVO> wishList, List<BookVO> book, List<InnVO> inn) {
		super();
		this.memberId = memberId;
		this.memberPass = memberPass;
		this.memberName = memberName;
		this.memberTel = memberTel;
		this.memberInfo = memberInfo;
		this.memberAnswerType = memberAnswerType;
		this.memberPassAnswer = memberPassAnswer;
		this.memberBank = memberBank;
		this.memberBankAcount = memberBankAcount;
		this.profilePicVO = profilePicVO;
		this.wishList = wishList;
		this.book = book;
		this.inn = inn;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPass() {
		return memberPass;
	}

	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public String getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(String memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getMemberAnswerType() {
		return memberAnswerType;
	}

	public void setMemberAnswerType(String memberAnswerType) {
		this.memberAnswerType = memberAnswerType;
	}

	public String getMemberPassAnswer() {
		return memberPassAnswer;
	}

	public void setMemberPassAnswer(String memberPassAnswer) {
		this.memberPassAnswer = memberPassAnswer;
	}

	public String getMemberBank() {
		return memberBank;
	}

	public void setMemberBank(String memberBank) {
		this.memberBank = memberBank;
	}

	public String getMemberBankAcount() {
		return memberBankAcount;
	}

	public void setMemberBankAcount(String memberBankAcount) {
		this.memberBankAcount = memberBankAcount;
	}

	public ProfilePicVO getProfilePicVO() {
		return profilePicVO;
	}

	public void setProfilePicVO(ProfilePicVO profilePicVO) {
		this.profilePicVO = profilePicVO;
	}

	public List<WishListVO> getWishList() {
		return wishList;
	}

	public void setWishList(List<WishListVO> wishList) {
		this.wishList = wishList;
	}

	public List<BookVO> getBook() {
		return book;
	}

	public void setBook(List<BookVO> book) {
		this.book = book;
	}

	public List<InnVO> getInn() {
		return inn;
	}

	public void setInn(List<InnVO> inn) {
		this.inn = inn;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberPass=" + memberPass
				+ ", memberName=" + memberName + ", memberTel=" + memberTel
				+ ", memberInfo=" + memberInfo + ", memberAnswerType="
				+ memberAnswerType + ", memberPassAnswer=" + memberPassAnswer
				+ ", memberBank=" + memberBank + ", memberBankAcount="
				+ memberBankAcount + ", profilePicVO=" + profilePicVO
				+ ", wishList=" + wishList + ", book=" + book + ", inn=" + inn
				+ "]";
	}

}

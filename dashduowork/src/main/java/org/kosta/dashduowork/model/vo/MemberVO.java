package org.kosta.dashduowork.model.vo;

import java.util.List;

public class MemberVO {
	private String memberId;
	private String memberPass;
	private String memberName;
	private String memberTel;
	private String memberInfo;
	private List<ProfilePicVO> profilePic;
	private List<WishListVO> wishList;
	private List<BookVO> book;
	private List<InnVO> inn;

	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String memberId, String memberPass, String memberName,
			String memberTel, String memberInfo, List<ProfilePicVO> profilePic,
			List<WishListVO> wishList, List<BookVO> book, List<InnVO> inn) {
		super();
		this.memberId = memberId;
		this.memberPass = memberPass;
		this.memberName = memberName;
		this.memberTel = memberTel;
		this.memberInfo = memberInfo;
		this.profilePic = profilePic;
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

	public List<ProfilePicVO> getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(List<ProfilePicVO> profilePic) {
		this.profilePic = profilePic;
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
				+ ", memberInfo=" + memberInfo + ", profilePic=" + profilePic
				+ ", wishList=" + wishList + ", book=" + book + "]";
	}

}

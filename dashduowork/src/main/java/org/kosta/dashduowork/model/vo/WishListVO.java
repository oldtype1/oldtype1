package org.kosta.dashduowork.model.vo;

public class WishListVO {
	private int wishListNo;
	private int innNo;
	private int memberId;

	public WishListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WishListVO(int wishListNo, int innNo, int memberId) {
		super();
		this.wishListNo = wishListNo;
		this.innNo = innNo;
		this.memberId = memberId;
	}

	public int getWishListNo() {
		return wishListNo;
	}

	public void setWishListNo(int wishListNo) {
		this.wishListNo = wishListNo;
	}

	public int getInnNo() {
		return innNo;
	}

	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "WishListVO [wishListNo=" + wishListNo + ", innNo=" + innNo
				+ ", memberId=" + memberId + "]";
	}

}

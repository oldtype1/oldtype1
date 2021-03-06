package org.kosta.dashduowork.model.vo;

public class WishListVO {
	private int wishListNo;
	private int innNo;
	private String memberId;
	private String innName;
	private InnPicCompVO wishlistMainPic;
	
	public WishListVO() {
		super();
	}

	public WishListVO(int wishListNo, int innNo, String memberId,
			String innName, InnPicCompVO wishlistMainPic) {
		super();
		this.wishListNo = wishListNo;
		this.innNo = innNo;
		this.memberId = memberId;
		this.innName = innName;
		this.wishlistMainPic = wishlistMainPic;
	}

	
	public WishListVO(int innNo, String memberId) {
		super();
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getInnName() {
		return innName;
	}

	public void setInnName(String innName) {
		this.innName = innName;
	}

	public InnPicCompVO getWishlistMainPic() {
		return wishlistMainPic;
	}

	public void setWishlistMainPic(InnPicCompVO wishlistMainPic) {
		this.wishlistMainPic = wishlistMainPic;
	}

	@Override
	public String toString() {
		return "WishListVO [wishListNo=" + wishListNo + ", innNo=" + innNo
				+ ", memberId=" + memberId + ", innName=" + innName
				+ ", wishlistMainPic=" + wishlistMainPic + "]";
	}
	

}

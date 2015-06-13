package org.kosta.dashduowork.model.vo;

public class WishListDeleteVO {
	private String memberId;
private int wishListNo;
public WishListDeleteVO() {
	super();
}
public WishListDeleteVO(String memberId, int wishListNo) {
	super();
	this.memberId = memberId;
	this.wishListNo = wishListNo;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public int getWishListNo() {
	return wishListNo;
}
public void setWishListNo(int wishListNo) {
	this.wishListNo = wishListNo;
}
@Override
public String toString() {
	return "WishListDeleteVO [memberId=" + memberId + ", wishListNo="
			+ wishListNo + "]";
} 



}

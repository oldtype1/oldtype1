package org.kosta.dashduowork.model.vo;

public class BookDeleteVO {
	private int bookNo;
	private String memberId;

	public BookDeleteVO() {
		super();
	}
	
	public BookDeleteVO(int bookNo, String memberId) {
		super();
		this.bookNo = bookNo;
		this.memberId = memberId;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "bookDeleteVO [bookNo=" + bookNo + ", memberId=" + memberId
				+ "]";
	}
	
}

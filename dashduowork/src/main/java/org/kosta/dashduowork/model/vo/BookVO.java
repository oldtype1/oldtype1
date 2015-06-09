package org.kosta.dashduowork.model.vo;

public class BookVO {
	private int bookNo;
	private String memberId;
	private int innNo;
	private String bookCheckIn;
	private String bookCheckOut;
	private String bookCount;

	public BookVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookVO(int bookNo, String memberId, int innNo, String bookCheckIn,
			String bookCheckOut, String bookCount) {
		super();
		this.bookNo = bookNo;
		this.memberId = memberId;
		this.innNo = innNo;
		this.bookCheckIn = bookCheckIn;
		this.bookCheckOut = bookCheckOut;
		this.bookCount = bookCount;
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

	public int getInnNo() {
		return innNo;
	}

	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}

	public String getBookCheckIn() {
		return bookCheckIn;
	}

	public void setBookCheckIn(String bookCheckIn) {
		this.bookCheckIn = bookCheckIn;
	}

	public String getBookCheckOut() {
		return bookCheckOut;
	}

	public void setBookCheckOut(String bookCheckOut) {
		this.bookCheckOut = bookCheckOut;
	}

	public String getBookCount() {
		return bookCount;
	}

	public void setBookCount(String bookCount) {
		this.bookCount = bookCount;
	}

	@Override
	public String toString() {
		return "BookVO [bookNo=" + bookNo + ", memberId=" + memberId
				+ ", innNo=" + innNo + ", bookCheckIn=" + bookCheckIn
				+ ", bookCheckOut=" + bookCheckOut + ", bookCount=" + bookCount
				+ "]";
	}

}

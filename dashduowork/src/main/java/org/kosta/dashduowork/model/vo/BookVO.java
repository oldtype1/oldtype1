package org.kosta.dashduowork.model.vo;

public class BookVO {
	private int bookNo;
	private String memberId;
	private int innNo;
	private String bookCheckIn;
	private String bookCheckOut;
	private String bookCount;
	private String innName;
	private InnPicCompVO bookMainPic;
	
	
	public BookVO() {
		super();
	}

	

	public BookVO(String memberId, int innNo, String bookCheckIn,
			String bookCheckOut, String bookCount) {
		super();
		this.memberId = memberId;
		this.innNo = innNo;
		this.bookCheckIn = bookCheckIn;
		this.bookCheckOut = bookCheckOut;
		this.bookCount = bookCount;
	}



	public BookVO(int bookNo, String memberId, int innNo, String bookCheckIn,
			String bookCheckOut, String bookCount, String innName,
			InnPicCompVO bookMainPic) {
		super();
		this.bookNo = bookNo;
		this.memberId = memberId;
		this.innNo = innNo;
		this.bookCheckIn = bookCheckIn;
		this.bookCheckOut = bookCheckOut;
		this.bookCount = bookCount;
		this.innName = innName;
		this.bookMainPic = bookMainPic;
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



	public String getInnName() {
		return innName;
	}



	public void setInnName(String innName) {
		this.innName = innName;
	}



	public InnPicCompVO getBookMainPic() {
		return bookMainPic;
	}



	public void setBookMainPic(InnPicCompVO bookMainPic) {
		this.bookMainPic = bookMainPic;
	}



	@Override
	public String toString() {
		return "BookVO [bookNo=" + bookNo + ", memberId=" + memberId
				+ ", innNo=" + innNo + ", bookCheckIn=" + bookCheckIn
				+ ", bookCheckOut=" + bookCheckOut + ", bookCount=" + bookCount
				+ ", innName=" + innName + ", bookMainPic=" + bookMainPic + "]";
	}



}

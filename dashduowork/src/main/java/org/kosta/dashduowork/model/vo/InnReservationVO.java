package org.kosta.dashduowork.model.vo;

public class InnReservationVO {
	private String memberId;
	private String master;
	private int bookNo;
	private int innNo;
	private String innName;
	private String bookCheckIn;
	private String bookCheckOut;
	private String bookCount;
	public InnReservationVO() {
		super();
	}
	public InnReservationVO(String memberId, String master, int bookNo,
			int innNo, String innName, String bookCheckIn, String bookCheckOut,
			String bookCount) {
		super();
		this.memberId = memberId;
		this.master = master;
		this.bookNo = bookNo;
		this.innNo = innNo;
		this.innName = innName;
		this.bookCheckIn = bookCheckIn;
		this.bookCheckOut = bookCheckOut;
		this.bookCount = bookCount;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getInnNo() {
		return innNo;
	}
	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}
	public String getInnName() {
		return innName;
	}
	public void setInnName(String innName) {
		this.innName = innName;
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
		return "InnReservationVO [memberId=" + memberId + ", master=" + master
				+ ", bookNo=" + bookNo + ", innNo=" + innNo + ", innName="
				+ innName + ", bookCheckIn=" + bookCheckIn + ", bookCheckOut="
				+ bookCheckOut + ", bookCount=" + bookCount + "]";
	}
	
	
	
	
}

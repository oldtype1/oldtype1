package org.kosta.dashduowork.model.vo;

public class TradeVO {
	private int tradeNo;
	private String innName; //숙소명
	private String bookCheckIn; 
	private String bookCheckOut; 
	private int price;
	private String memberId; //예약한 사람
	private String master;	//숙소 주인
	private int innNo;
	private String ratingCheck; //별점체크유무
	
	public TradeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TradeVO(int tradeNo, String innName, String bookCheckIn,
			String bookCheckOut, int price, String memberId, String master,
			int innNo, String ratingCheck) {
		super();
		this.tradeNo = tradeNo;
		this.innName = innName;
		this.bookCheckIn = bookCheckIn;
		this.bookCheckOut = bookCheckOut;
		this.price = price;
		this.memberId = memberId;
		this.master = master;
		this.innNo = innNo;
		this.ratingCheck = ratingCheck;
	}

	public int getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public int getInnNo() {
		return innNo;
	}

	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}

	public String getRatingCheck() {
		return ratingCheck;
	}

	public void setRatingCheck(String ratingCheck) {
		this.ratingCheck = ratingCheck;
	}

	@Override
	public String toString() {
		return "TradeVO [tradeNo=" + tradeNo + ", innName=" + innName
				+ ", bookCheckIn=" + bookCheckIn + ", bookCheckOut="
				+ bookCheckOut + ", price=" + price + ", memberId=" + memberId
				+ ", master=" + master + ", innNo=" + innNo + ", ratingCheck="
				+ ratingCheck + "]";
	}
	
	
}

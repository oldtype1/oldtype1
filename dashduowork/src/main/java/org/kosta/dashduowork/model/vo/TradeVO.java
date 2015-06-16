package org.kosta.dashduowork.model.vo;

public class TradeVO {
	private int tradeNo;
	private String memberId; //등록또는 예약한 사람
	private String innorbook; //예약인지 등록인지?
	private int innNo;		  //숙소넘버
	private String innName; //숙소명
	private String bookCheckIn; //예약시- 체크인
	private String bookCheckOut; //예약시- 체크아웃
	private String innAvailability; //등록시- 사용가능여부
	public TradeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TradeVO(int tradeNo, String memberId, String innorbook, int innNo,
			String innName, String bookCheckIn, String bookCheckOut,
			String innAvailability) {
		super();
		this.tradeNo = tradeNo;
		this.memberId = memberId;
		this.innorbook = innorbook;
		this.innNo = innNo;
		this.innName = innName;
		this.bookCheckIn = bookCheckIn;
		this.bookCheckOut = bookCheckOut;
		this.innAvailability = innAvailability;
	}


	public int getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getInnorbook() {
		return innorbook;
	}
	public void setInnorbook(String innorbook) {
		this.innorbook = innorbook;
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
	
	public String getInnAvailability() {
		return innAvailability;
	}

	public void setInnAvailability(String innAvailability) {
		this.innAvailability = innAvailability;
	}

	@Override
	public String toString() {
		return "TradeVO [tradeNo=" + tradeNo + ", memberId=" + memberId
				+ ", innorbook=" + innorbook + ", innNo=" + innNo
				+ ", innName=" + innName + ", bookCheckIn=" + bookCheckIn
				+ ", bookCheckOut=" + bookCheckOut + ", innAvailability="
				+ innAvailability + "]";
	}
	

}

package org.kosta.dashduowork.model.vo;

public class DeleteVO {
	private int innNo; 
	// 등록숙소-숙소번호//예약-예약번호
	//위시리스트-위시리스트번호//거래내역-거래번호//
	private String memberId; 
	private int trade_bookno; //거래내역-예약 삭제시 실제 예약테이블을 없애기 위해 예약변호 필요

	public DeleteVO() {
		super();
	}
	
	public DeleteVO(int bookNo, String memberId) {
		super();
		this.innNo = bookNo;
		this.memberId = memberId;
	}
	

	public DeleteVO(int innNo, String memberId, int trade_bookno) {
		super();
		this.innNo = innNo;
		this.memberId = memberId;
		this.trade_bookno = trade_bookno;
	}

	public int getTrade_bookno() {
		return trade_bookno;
	}

	public void setTrade_bookno(int trade_bookno) {
		this.trade_bookno = trade_bookno;
	}

	public int getInnNo() {
		return innNo;
	}

	public void setInnNo(int bookNo) {
		this.innNo = bookNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "DeleteVO [innNo=" + innNo + ", memberId=" + memberId
				+ ", trade_bookno=" + trade_bookno + "]";
	}

	
}

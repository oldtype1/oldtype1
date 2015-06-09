package org.kosta.dashduowork.model.vo;

public class AvailableDateVO {
	private int availableDateNo;
	private int innNo;
	private String  availableDateSt;
	private String  availableDateEnd;
	public AvailableDateVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AvailableDateVO(int availableDateNo, int innNo,
			String availableDateSt, String availableDateEnd) {
		super();
		this.availableDateNo = availableDateNo;
		this.innNo = innNo;
		this.availableDateSt = availableDateSt;
		this.availableDateEnd = availableDateEnd;
	}
	public int getAvailableDateNo() {
		return availableDateNo;
	}
	public void setAvailableDateNo(int availableDateNo) {
		this.availableDateNo = availableDateNo;
	}
	public int getInnNo() {
		return innNo;
	}
	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}
	public String getAvailableDateSt() {
		return availableDateSt;
	}
	public void setAvailableDateSt(String availableDateSt) {
		this.availableDateSt = availableDateSt;
	}
	public String getAvailableDateEnd() {
		return availableDateEnd;
	}
	public void setAvailableDateEnd(String availableDateEnd) {
		this.availableDateEnd = availableDateEnd;
	}
	@Override
	public String toString() {
		return "AvailableDateVO [availableDateNo=" + availableDateNo
				+ ", innNo=" + innNo + ", availableDateSt=" + availableDateSt
				+ ", availableDateEnd=" + availableDateEnd + "]";
	}
	
	
}

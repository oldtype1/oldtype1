package org.kosta.dashduowork.model.vo;

public class InnRatingVO {
	private int innNo;
	private int point;
	private int peopleNumber;
	public InnRatingVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InnRatingVO(int innNo, int point, int peopleNumber) {
		super();
		this.innNo = innNo;
		this.point = point;
		this.peopleNumber = peopleNumber;
	}
	public int getInnNo() {
		return innNo;
	}
	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	@Override
	public String toString() {
		return "InnRatingVO [innNo=" + innNo + ", point=" + point
				+ ", peopleNumber=" + peopleNumber + "]";
	}
	
	

}

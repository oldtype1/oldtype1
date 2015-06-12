package org.kosta.dashduowork.model.vo;

public class SearchVO {
	private String innCity;
	private String startDate;
	private String endDate;
	private String acceptableNo;
	public SearchVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchVO(String innCity, String startDate, String endDate,
			String acceptableNo) {
		super();
		this.innCity = innCity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.acceptableNo = acceptableNo;
	}
	
	public SearchVO(String innCity, String startDate, String endDate) {
		super();
		this.innCity = innCity;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getInnCity() {
		return innCity;
	}
	public void setInnCity(String innCity) {
		this.innCity = innCity;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAcceptableNo() {
		return acceptableNo;
	}
	public void setAcceptableNo(String acceptableNo) {
		this.acceptableNo = acceptableNo;
	}
	@Override
	public String toString() {
		return "SearchVO [innCity=" + innCity + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", acceptableNo=" + acceptableNo
				+ "]";
	}
	
	
}

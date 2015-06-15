package org.kosta.dashduowork.model.vo;

public class FilterVO {
	private String firstSearchCity;
	private String firstSearchStartDate;
	private String firstSearchEndDate;
	private String firstSearchPeopleNo;
	private String amenityWiFi;
	private String amenityBed;
	private String amenityTV;
	private String amenityKitchen;
	private String amenityBBQ;
	public FilterVO() {
		super();
		this.amenityWiFi = "N";
		this.amenityBed = "N";
		this.amenityTV = "N";
		this.amenityKitchen = "N";
		this.amenityBBQ = "N";
	}
	public FilterVO(String firstSearchCity, String firstSearchStartDate,
			String firstSearchEndDate, String firstSearchPeopleNo,
			String amenityWiFi, String amenityBed, String amenityTV,
			String amenityKitchen, String amenityBBQ) {
		super();
		this.firstSearchCity = firstSearchCity;
		this.firstSearchStartDate = firstSearchStartDate;
		this.firstSearchEndDate = firstSearchEndDate;
		this.firstSearchPeopleNo = firstSearchPeopleNo;
		this.amenityWiFi = amenityWiFi;
		this.amenityBed = amenityBed;
		this.amenityTV = amenityTV;
		this.amenityKitchen = amenityKitchen;
		this.amenityBBQ = amenityBBQ;
	}
	public String getFirstSearchCity() {
		return firstSearchCity;
	}
	public void setFirstSearchCity(String firstSearchCity) {
		this.firstSearchCity = firstSearchCity;
	}
	public String getFirstSearchStartDate() {
		return firstSearchStartDate;
	}
	public void setFirstSearchStartDate(String firstSearchStartDate) {
		this.firstSearchStartDate = firstSearchStartDate;
	}
	public String getFirstSearchEndDate() {
		return firstSearchEndDate;
	}
	public void setFirstSearchEndDate(String firstSearchEndDate) {
		this.firstSearchEndDate = firstSearchEndDate;
	}
	public String getFirstSearchPeopleNo() {
		return firstSearchPeopleNo;
	}
	public void setFirstSearchPeopleNo(String firstSearchPeopleNo) {
		this.firstSearchPeopleNo = firstSearchPeopleNo;
	}
	public String getAmenityWiFi() {
		return amenityWiFi;
	}
	public void setAmenityWiFi(String amenityWiFi) {
		this.amenityWiFi = amenityWiFi;
	}
	public String getAmenityBed() {
		return amenityBed;
	}
	public void setAmenityBed(String amenityBed) {
		this.amenityBed = amenityBed;
	}
	public String getAmenityTV() {
		return amenityTV;
	}
	public void setAmenityTV(String amenityTV) {
		this.amenityTV = amenityTV;
	}
	public String getAmenityKitchen() {
		return amenityKitchen;
	}
	public void setAmenityKitchen(String amenityKitchen) {
		this.amenityKitchen = amenityKitchen;
	}
	public String getAmenityBBQ() {
		return amenityBBQ;
	}
	public void setAmenityBBQ(String amenityBBQ) {
		this.amenityBBQ = amenityBBQ;
	}
	@Override
	public String toString() {
		return "FilterVO [firstSearchCity=" + firstSearchCity
				+ ", firstSearchStartDate=" + firstSearchStartDate
				+ ", firstSearchEndDate=" + firstSearchEndDate
				+ ", firstSearchPeopleNo=" + firstSearchPeopleNo
				+ ", amenityWiFi=" + amenityWiFi + ", amenityBed=" + amenityBed
				+ ", amenityTV=" + amenityTV + ", amenityKitchen="
				+ amenityKitchen + ", amenityBBQ=" + amenityBBQ + "]";
	}
	
}

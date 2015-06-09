package org.kosta.dashduowork.model.vo;

public class AmenityVO {
	private int innNo;
	private String amenityWiFi;
	private String amenityBed;
	private String amenityTV;
	private String amenityKitchen;
	private String amenityBBQ;
	public AmenityVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AmenityVO(int innNo, String amenityWiFi, String amenityBed,
			String amenityTV, String amenityKitchen, String amenityBBQ) {
		super();
		this.innNo = innNo;
		this.amenityWiFi = amenityWiFi;
		this.amenityBed = amenityBed;
		this.amenityTV = amenityTV;
		this.amenityKitchen = amenityKitchen;
		this.amenityBBQ = amenityBBQ;
	}
	public int getInnNo() {
		return innNo;
	}
	public void setInnNo(int innNo) {
		this.innNo = innNo;
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
		return "AmenityVO [innNo=" + innNo + ", amenityWiFi=" + amenityWiFi
				+ ", amenityBed=" + amenityBed + ", amenityTV=" + amenityTV
				+ ", amenityKitchen=" + amenityKitchen + ", amenityBBQ="
				+ amenityBBQ + "]";
	}
	
}

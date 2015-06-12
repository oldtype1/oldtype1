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
		this.amenityWiFi = "N";
		this.amenityBed = "N";
		this.amenityTV = "N";
		this.amenityKitchen = "N";
		this.amenityBBQ = "N";
		// TODO Auto-generated constructor stub
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

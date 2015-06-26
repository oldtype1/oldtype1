package org.kosta.dashduowork.model.vo;

import java.util.ArrayList;

public class AmenityVO {
	private int innNo;
	private String amenityItem;
	private ArrayList<String> amenityItems;

	public AmenityVO(int innNo, String amenityItem,
			ArrayList<String> amenityItems) {
		super();
		this.innNo = innNo;
		this.amenityItem = amenityItem;
		this.amenityItems = amenityItems;
	}

	public AmenityVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getInnNo() {
		return innNo;
	}

	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}


	public String getAmenityItem() {
		return amenityItem;
	}

	public void setAmenityItem(String amenityItem) {
		this.amenityItem = amenityItem;
	}

	public ArrayList<String> getAmenityItems() {
		return amenityItems;
	}

	public void setAmenityItems(ArrayList<String> amenityItems) {
		this.amenityItems = amenityItems;
	}
	
	@Override
	public String toString() {
		return "AmenityVO [innNo=" + innNo + ", amenityItem=" + amenityItem
				+ ", amenityItems=" + amenityItems + "]";
	}



}

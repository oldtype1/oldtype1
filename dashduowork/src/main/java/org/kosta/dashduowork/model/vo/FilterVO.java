package org.kosta.dashduowork.model.vo;

import java.util.List;

public class FilterVO {
	private String searchWord;
	private String searchStartDate;
	private String searchEndDate;
	private String searchPeopleNo;
	private String minPrice;
	private String maxPrice;
	private List<String> amenityItems;
	public FilterVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FilterVO(String searchWord, String searchStartDate,
			String searchEndDate, String searchPeopleNo, String minPrice,
			String maxPrice, List<String> amenityItems) {
		super();
		this.searchWord = searchWord;
		this.searchStartDate = searchStartDate;
		this.searchEndDate = searchEndDate;
		this.searchPeopleNo = searchPeopleNo;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.amenityItems = amenityItems;
	}
	public FilterVO(String searchWord, String searchStartDate,
			String searchEndDate, String searchPeopleNo) {
		super();
		this.searchWord = searchWord;
		this.searchStartDate = searchStartDate;
		this.searchEndDate = searchEndDate;
		this.searchPeopleNo = searchPeopleNo;
	}
	public FilterVO(String searchWord, String searchPeopleNo) {
		super();
		this.searchWord = searchWord;
		this.searchPeopleNo = searchPeopleNo;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchStartDate() {
		return searchStartDate;
	}
	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}
	public String getSearchEndDate() {
		return searchEndDate;
	}
	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	public String getSearchPeopleNo() {
		return searchPeopleNo;
	}
	public void setSearchPeopleNo(String searchPeopleNo) {
		this.searchPeopleNo = searchPeopleNo;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public List<String> getAmenityItems() {
		return amenityItems;
	}
	public void setAmenityItems(List<String> amenityItems) {
		this.amenityItems = amenityItems;
	}
	@Override
	public String toString() {
		return "FilterVO [searchWord=" + searchWord + ", searchStartDate="
				+ searchStartDate + ", searchEndDate=" + searchEndDate
				+ ", searchPeopleNo=" + searchPeopleNo + ", minPrice="
				+ minPrice + ", maxPrice=" + maxPrice + ", amenityItems="
				+ amenityItems + "]";
	}
	
}

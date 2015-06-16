package org.kosta.dashduowork.model.vo;

import java.util.List;

public class InnVO {
	private int innNo;
	private String innName;
	private String innCity;
	private String innArea;
	private String innAddress;
	private String innType;
	private String acceptableNo;
	private int innPrice;
	private String innInfo;
	private String innAvailability;
	private String memberId;
	private InnPicCompVO innMainPic;
/*	private List<AvailableDateVO> availableDateVO;
	private AmenityVO amenityVO;
	private List<InnPicCompVO> innPicCompVO;
	private List<CommentVO> commentVO;*/
	public InnVO() {
		super();
		// TODO Auto-generated constructor stub
	}
public InnVO(int innNo, String innName, String innCity, String innArea,
		String innAddress, String innType, String acceptableNo, int innPrice,
		String innInfo, String innAvailability, String memberId,
		InnPicCompVO innMainPic) {
	super();
	this.innNo = innNo;
	this.innName = innName;
	this.innCity = innCity;
	this.innArea = innArea;
	this.innAddress = innAddress;
	this.innType = innType;
	this.acceptableNo = acceptableNo;
	this.innPrice = innPrice;
	this.innInfo = innInfo;
	this.innAvailability = innAvailability;
	this.memberId = memberId;
	this.innMainPic = innMainPic;
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
public String getInnCity() {
	return innCity;
}
public void setInnCity(String innCity) {
	this.innCity = innCity;
}
public String getInnArea() {
	return innArea;
}
public void setInnArea(String innArea) {
	this.innArea = innArea;
}
public String getInnAddress() {
	return innAddress;
}
public void setInnAddress(String innAddress) {
	this.innAddress = innAddress;
}
public String getInnType() {
	return innType;
}
public void setInnType(String innType) {
	this.innType = innType;
}
public String getAcceptableNo() {
	return acceptableNo;
}
public void setAcceptableNo(String acceptableNo) {
	this.acceptableNo = acceptableNo;
}
public int getInnPrice() {
	return innPrice;
}
public void setInnPrice(int innPrice) {
	this.innPrice = innPrice;
}
public String getInnInfo() {
	return innInfo;
}
public void setInnInfo(String innInfo) {
	this.innInfo = innInfo;
}
public String getInnAvailability() {
	return innAvailability;
}
public void setInnAvailability(String innAvailability) {
	this.innAvailability = innAvailability;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public InnPicCompVO getInnMainPic() {
	return innMainPic;
}
public void setInnMainPic(InnPicCompVO innMainPic) {
	this.innMainPic = innMainPic;
}
@Override
public String toString() {
	return "InnVO [innNo=" + innNo + ", innName=" + innName + ", innCity="
			+ innCity + ", innArea=" + innArea + ", innAddress=" + innAddress
			+ ", innType=" + innType + ", acceptableNo=" + acceptableNo
			+ ", innPrice=" + innPrice + ", innInfo=" + innInfo
			+ ", innAvailability=" + innAvailability + ", memberId=" + memberId
			+ ", innMainPic=" + innMainPic + "]";
}

	
}

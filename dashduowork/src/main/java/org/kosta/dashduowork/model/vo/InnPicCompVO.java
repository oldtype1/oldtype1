package org.kosta.dashduowork.model.vo;

public class InnPicCompVO {
	private int innPicCompNo;
	private int innNo;
	private String innPicCompFilePath;
	private String innPicCompOgName;
	private String innPicCompCurrName;
	public InnPicCompVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InnPicCompVO(int innPicCompNo, int innNo, String innPicCompFilePath,
			String innPicCompOgName, String innPicCompCurrName) {
		super();
		this.innPicCompNo = innPicCompNo;
		this.innNo = innNo;
		this.innPicCompFilePath = innPicCompFilePath;
		this.innPicCompOgName = innPicCompOgName;
		this.innPicCompCurrName = innPicCompCurrName;
	}
	public int getInnPicCompNo() {
		return innPicCompNo;
	}
	public void setInnPicCompNo(int innPicCompNo) {
		this.innPicCompNo = innPicCompNo;
	}
	public int getInnNo() {
		return innNo;
	}
	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}
	public String getInnPicCompFilePath() {
		return innPicCompFilePath;
	}
	public void setInnPicCompFilePath(String innPicCompFilePath) {
		this.innPicCompFilePath = innPicCompFilePath;
	}
	public String getInnPicCompOgName() {
		return innPicCompOgName;
	}
	public void setInnPicCompOgName(String innPicCompOgName) {
		this.innPicCompOgName = innPicCompOgName;
	}
	public String getInnPicCompCurrName() {
		return innPicCompCurrName;
	}
	public void setInnPicCompCurrName(String innPicCompCurrName) {
		this.innPicCompCurrName = innPicCompCurrName;
	}
	@Override
	public String toString() {
		return "InnPicCompVO [innPicCompNo=" + innPicCompNo + ", innNo="
				+ innNo + ", innPicCompFilePath=" + innPicCompFilePath
				+ ", innPicCompOgName=" + innPicCompOgName
				+ ", innPicCompCurrName=" + innPicCompCurrName + "]";
	}
	
	
}

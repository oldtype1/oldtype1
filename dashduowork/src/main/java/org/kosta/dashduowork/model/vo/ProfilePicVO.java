package org.kosta.dashduowork.model.vo;

public class ProfilePicVO {
	private int profilePicNo;
	private String profilePicFilepath;
	private String profilePicOgName;
	private String profilePicCurrName;

	public ProfilePicVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfilePicVO(int profilePicNo, String profilePicFilepath,
			String profilePicOgName, String profilePicCurrName) {
		super();
		this.profilePicNo = profilePicNo;
		this.profilePicFilepath = profilePicFilepath;
		this.profilePicOgName = profilePicOgName;
		this.profilePicCurrName = profilePicCurrName;
	}

	public int getProfilePicNo() {
		return profilePicNo;
	}

	public void setProfilePicNo(int profilePicNo) {
		this.profilePicNo = profilePicNo;
	}

	public String getProfilePicFilepath() {
		return profilePicFilepath;
	}

	public void setProfilePicFilepath(String profilePicFilepath) {
		this.profilePicFilepath = profilePicFilepath;
	}

	public String getProfilePicOgName() {
		return profilePicOgName;
	}

	public void setProfilePicOgName(String profilePicOgName) {
		this.profilePicOgName = profilePicOgName;
	}

	public String getProfilePicCurrName() {
		return profilePicCurrName;
	}

	public void setProfilePicCurrName(String profilePicCurrName) {
		this.profilePicCurrName = profilePicCurrName;
	}

	@Override
	public String toString() {
		return "ProfilePicVO [profilePicNo=" + profilePicNo
				+ ", profilePicFilepath=" + profilePicFilepath
				+ ", profilePicOgName=" + profilePicOgName
				+ ", profilePicCurrName=" + profilePicCurrName + "]";
	}

}

package org.kosta.dashduowork.model.vo;



import org.springframework.web.multipart.MultipartFile;

public class ProfilePicVO {
	private String memberId;
	private MultipartFile file;
	private String filePath;
	
	
	public ProfilePicVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProfilePicVO(String memberId, MultipartFile file, String filePath) {
		super();
		this.memberId = memberId;
		this.file = file;
		this.filePath = filePath;
	}

	public ProfilePicVO(String memberId, String filePath) {
		super();
		this.memberId = memberId;
		this.filePath = filePath;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	@Override
	public String toString() {
		return "ProfilePicVO [memberId=" + memberId + ", file=" + file
				+ ", filePath=" + filePath + "]";
	}

	
}

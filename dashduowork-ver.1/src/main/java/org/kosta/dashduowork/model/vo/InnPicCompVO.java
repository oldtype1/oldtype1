package org.kosta.dashduowork.model.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class InnPicCompVO {

	private int innNo;
	private int innPicNo;
	private List<MultipartFile> file;
	private List<String> filePathes;
	private String filePath;

	public InnPicCompVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InnPicCompVO(int innNo, int innPicNo, List<MultipartFile> file,
			List<String> filePathes, String filePath) {
		super();
		this.innNo = innNo;
		this.innPicNo = innPicNo;
		this.file = file;
		this.filePathes = filePathes;
		this.filePath = filePath;
	}

	public int getInnNo() {
		return innNo;
	}

	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}

	public int getInnPicNo() {
		return innPicNo;
	}

	public void setInnPicNo(int innPicNo) {
		this.innPicNo = innPicNo;
	}

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public List<String> getFilePathes() {
		return filePathes;
	}

	public void setFilePathes(List<String> filePathes) {
		this.filePathes = filePathes;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "InnPicCompVO [innNo=" + innNo + ", innPicNo=" + innPicNo
				+ ", file=" + file + ", filePathes=" + filePathes
				+ ", filePath=" + filePath + "]";
	}

}

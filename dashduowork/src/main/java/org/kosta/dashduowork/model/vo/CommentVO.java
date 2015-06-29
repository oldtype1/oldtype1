package org.kosta.dashduowork.model.vo;
public class CommentVO {
	private int commentNo;
	private String memberId;
	private int innNo;
	private String commentDate;
	private String commentContent;
	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentVO(int commentNo, String memberId, int innNo,
			String commentDate, String commentContent) {
		super();
		this.commentNo = commentNo;
		this.memberId = memberId;
		this.innNo = innNo;
		this.commentDate = commentDate;
		this.commentContent = commentContent;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getInnNo() {
		return innNo;
	}
	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	@Override
	public String toString() {
		return "CommentVO [commentNo=" + commentNo + ", memberId=" + memberId
				+ ", innNo=" + innNo + " commentDate=" + commentDate + ", commentContent="
				+ commentContent + "]";
	}
	
}

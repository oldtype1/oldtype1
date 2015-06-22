package org.kosta.dashduowork.model.vo;

public class CommentVO {
	private int commentNo;
	private int innNo;
	private String commentWriter;
	private String commentDate;
	private String commentContent;
	private String commentPoint;
	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentVO(int commentNo, int innNo, String commentWriter,
			String commentDate, String commentContent, String commentPoint) {
		super();
		this.commentNo = commentNo;
		this.innNo = innNo;
		this.commentWriter = commentWriter;
		this.commentDate = commentDate;
		this.commentContent = commentContent;
		this.commentPoint = commentPoint;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getInnNo() {
		return innNo;
	}
	public void setInnNo(int innNo) {
		this.innNo = innNo;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
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
	public String getCommentPoint() {
		return commentPoint;
	}
	public void setCommentPoint(String commentPoint) {
		this.commentPoint = commentPoint;
	}
	@Override
	public String toString() {
		return "CommentVO [commentNo=" + commentNo + ", innNo=" + innNo
				+ ", commentWriter=" + commentWriter + ", commentDate="
				+ commentDate + ", commentContent=" + commentContent
				+ ", commentPoint=" + commentPoint + "]";
	}
	
	
}

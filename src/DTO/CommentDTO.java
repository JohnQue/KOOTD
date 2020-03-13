package DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int commentNum;
	String userID;
	String userName;
	String userMajor;
	String userComment;
	int boardNum;
	int commentGroup;
	int commentStep;
	int commentIndent;
	Timestamp commentDate;
	
	public CommentDTO(int commentNum, String userID, String userName, String userMajor, String userComment, int boardNum, int commentGroup, int commentStep, int commentIndent, Timestamp commentDate) {
		super();
		this.commentNum = commentNum;
		this.userID = userID;
		this.userName = userName;
		this.userMajor = userMajor;
		this.userComment = userComment;
		this.boardNum = boardNum;
		this.commentGroup = commentGroup;
		this.commentStep = commentStep;
		this.commentIndent = commentIndent;
		this.commentDate = commentDate;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMajor() {
		return userMajor;
	}

	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getCommentGroup() {
		return commentGroup;
	}

	public void setCommentGroup(int commentGroup) {
		this.commentGroup = commentGroup;
	}

	public int getCommentStep() {
		return commentStep;
	}

	public void setCommentStep(int commentStep) {
		this.commentStep = commentStep;
	}

	public int getCommentIndent() {
		return commentIndent;
	}

	public void setCommentIndent(int commentIndent) {
		this.commentIndent = commentIndent;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
}

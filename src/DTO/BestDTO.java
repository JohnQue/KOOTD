package DTO;

import java.sql.Timestamp;

public class BestDTO {
	int boardNum;
	String title;
	String userName;
	String userMajor;
	String content;
	String boardKind;
	Timestamp date;
	public BestDTO(int boardNum, String title, String userName, String userMajor, String content, String boardKind, Timestamp date) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.userName = userName;
		this.userMajor = userMajor;
		this.content = content;
		this.boardKind = boardKind;
		this.date = date;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBoardKind() {
		return boardKind;
	}
	public void setBoardKind(String boardKind) {
		this.boardKind = boardKind;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setTimestamp(Timestamp date) {
		this.date = date;
	}
}

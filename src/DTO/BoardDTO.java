package DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class BoardDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int boardNum;
	String boardTitle;
	String boardUserID;
	String boardUserName;
	String boardUserMajor;
	String boardContent;
	Timestamp boardDate;
	int boardHit;
	int boardLike;
	int boardDislike;
	String boardKind;
	String cap;
	String shirts;
	String jacket;
	String pants; 
	String shoes; 
	String bag; 
	String acce;
	public BoardDTO(int boardNum, String boardTitle, String boardUserID, String boardUserName, String boardUserMajor, String boardContent, Timestamp boardDate,
			int boardHit, int boardLike, int boardDislike, String boardKind, String cap, String shirts, String jacket, String pants, String shoes, String bag, String acce) {
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardUserID = boardUserID;
		this.boardUserName = boardUserName;
		this.boardUserMajor = boardUserMajor;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardHit = boardHit;
		this.boardLike = boardLike;
		this.boardDislike = boardDislike;
		this.boardKind = boardKind;
		this.cap = cap;
		this.shirts = shirts;
		this.jacket = jacket;
		this.pants = pants;
		this.shoes = shoes;
		this.bag = bag;
		this.acce = acce;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardUserID() {
		return boardUserID;
	}
	public void setBoardUserID(String boardUserID) {
		this.boardUserID = boardUserID;
	}
	public String getBoardUserName() {
		return boardUserName;
	}
	public void setBoardUserName(String boardUserName) {
		this.boardUserName = boardUserName;
	}
	public String getBoardUserMajor() {
		return boardUserMajor;
	}
	public void setBoardUserMajor(String boardUserMajor) {
		this.boardUserMajor = boardUserMajor;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Timestamp getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Timestamp boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(int boardHit) {
		this.boardHit = boardHit;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}
	public int getBoardDislike() {
		return boardDislike;
	}
	public void setBoardDislike(int boardDislike) {
		this.boardDislike = boardDislike;
	}
	public String getBoardKind() {
		return boardKind;
	}
	public void setBoardKind(String boardKind) {
		this.boardKind = boardKind;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getShirts() {
		return shirts;
	}
	public void setShirts(String shirts) {
		this.shirts = shirts;
	}
	public String getJacket() {
		return jacket;
	}
	public void setJacket(String jacket) {
		this.jacket = jacket;
	}
	public String getPants() {
		return pants;
	}
	public void setPants(String pants) {
		this.pants = pants;
	}
	public String getShoes() {
		return shoes;
	}
	public void setShoes(String shoes) {
		this.shoes = shoes;
	}
	public String getBag() {
		return bag;
	}
	public void setBag(String bag) {
		this.bag = bag;
	}
	public String getAcce() {
		return acce;
	}
	public void setAcce(String acce) {
		this.acce = acce;
	}
}
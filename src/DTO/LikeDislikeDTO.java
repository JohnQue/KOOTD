package DTO;
/* 해당 유저가 해당 글 추천 버튼을 눌렀는지 판단 */
public class LikeDislikeDTO {
	String userID;
	String boardNum;
	LikeDislikeDTO(String userID, String boardNum){
		this.userID = userID;
		this.boardNum = boardNum;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(String boardNum) {
		this.boardNum = boardNum;
	}
}

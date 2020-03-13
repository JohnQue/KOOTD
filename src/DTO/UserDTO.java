package DTO;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String userID;
	String userUniv;
	String userMajor;
	String userPassword;
	String userName;
	int userAge;
	String userGender;
	String userEmail;
	int totalLike;
	int totalDislike;
	String userProfile;
	
	public UserDTO(String userID, String userUniv, String userMajor, String userPassword, String userName, int userAge, String userGender,
			String userEmail, int totalLike, int totalDislike, String userProfile) {
		super();
		this.userID = userID;
		this.userUniv = userUniv;
		this.userMajor = userMajor;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userEmail = userEmail;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
		this.userProfile = userProfile;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserUniv() {
		return userUniv;
	}
	public void setUserUniv(String userUniv) {
		this.userUniv = userUniv;
	}
	public String getUserMajor() {
		return userMajor;
	}
	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getTotalLike() {
		return totalLike;
	}
	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}
	public int getTotalDislike() {
		return totalDislike;
	}
	public void setTotalDislike(int totalDislike) {
		this.totalDislike = totalDislike;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
}

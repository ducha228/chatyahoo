package model;

import java.io.Serializable;

public class 
User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7990569900710753084L;
	private String userId, userName, userpassword, userFirstName, userLastName,
			userNickName, userEmail, userPhoneNumber, userDateofBirth,
			userStatusUpdate, userHost, userAvartarURL;
	private int gender,isOnline,userPort;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String userId, String userName, String userpassword,
			String userFirstName, String userLastName, String userNickName,
			String userEmail, String userPhoneNumber, String userDateofBirth,
			String userStatusUpdate, String userHost, String userAvartarURL,
			int gender, int isOnline, int userPort) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userpassword = userpassword;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userNickName = userNickName;
		this.userEmail = userEmail;
		this.userPhoneNumber = userPhoneNumber;
		this.userDateofBirth = userDateofBirth;
		this.userStatusUpdate = userStatusUpdate;
		this.userHost = userHost;
		this.userAvartarURL = userAvartarURL;
		this.gender = gender;
		this.isOnline = isOnline;
		this.userPort = userPort;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUserDateofBirth() {
		return userDateofBirth;
	}
	public void setUserDateofBirth(String userDateofBirth) {
		this.userDateofBirth = userDateofBirth;
	}
	public String getUserStatusUpdate() {
		return userStatusUpdate;
	}
	public void setUserStatusUpdate(String userStatusUpdate) {
		this.userStatusUpdate = userStatusUpdate;
	}
	public String getUserHost() {
		return userHost;
	}
	public void setUserHost(String userHost) {
		this.userHost = userHost;
	}
	public String getUserAvartarURL() {
		return userAvartarURL;
	}
	public void setUserAvartarURL(String userAvartarURL) {
		this.userAvartarURL = userAvartarURL;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public int getUserPort() {
		return userPort;
	}
	public void setUserPort(int userPort) {
		this.userPort = userPort;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

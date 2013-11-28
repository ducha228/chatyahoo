package model;

import java.io.Serializable;

public class ChatHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1702834914107495087L;

	public ChatHistory(String message, String time, User userA, User userB,
			User userSender) {
		super();
		this.message = message;
		this.time = time;
		this.userA = userA;
		this.userB = userB;
		this.userSender = userSender;
	}

	private String id, message, time;
	private User userA, userB, userSender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getUserA() {
		return userA;
	}

	public void setUserA(User userA) {
		this.userA = userA;
	}

	public User getUserB() {
		return userB;
	}

	public void setUserB(User userB) {
		this.userB = userB;
	}

	public User getUserSender() {
		return userSender;
	}

	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	public ChatHistory(String id, String message, String time, User userA,
			User userB, User userSender) {
		super();
		this.id = id;
		this.message = message;
		this.time = time;
		this.userA = userA;
		this.userB = userB;
		this.userSender = userSender;
	}

	public ChatHistory() {
		// TODO Auto-generated constructor stub
	}

}

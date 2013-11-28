package model;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type;
	private Object obj;
	private String sender;
	private String recipient;
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public Message(int type, Object obj, String sender, String recipient) {
		super();
		this.type = type;
		this.obj = obj;
		this.sender = sender;
		this.recipient = recipient;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
}

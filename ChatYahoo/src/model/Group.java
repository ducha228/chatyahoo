package model;

import java.io.Serializable;

public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupId, userId, groupName;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public Group(String groupId, String userId, String groupName) {
		super();
		this.groupId = groupId;
		this.userId = userId;
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}

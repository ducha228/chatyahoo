package model;

import java.io.Serializable;

public class FriendList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7230702407201675026L;
	private String id, useridA, useridB, displayNameA, displayNameB, groupidA,
			groupidB;
	private int isAOnline, isBOnline;

	public FriendList() {
		// TODO Auto-generated constructor stub
	}

	public FriendList(String id, String useridA, String useridB,
			String displayNameA, String displayNameB, String groupidA,
			String groupidB, int isAOnline, int isBOnline) {
		super();
		this.id = id;
		this.useridA = useridA;
		this.useridB = useridB;
		this.displayNameA = displayNameA;
		this.displayNameB = displayNameB;
		this.groupidA = groupidA;
		this.groupidB = groupidB;
		this.isAOnline = isAOnline;
		this.isBOnline = isBOnline;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseridA() {
		return useridA;
	}

	public void setUseridA(String useridA) {
		this.useridA = useridA;
	}

	public String getUseridB() {
		return useridB;
	}

	public void setUseridB(String useridB) {
		this.useridB = useridB;
	}

	public String getDisplayNameA() {
		return displayNameA;
	}

	public void setDisplayNameA(String displayNameA) {
		this.displayNameA = displayNameA;
	}

	public String getDisplayNameB() {
		return displayNameB;
	}

	public void setDisplayNameB(String displayNameB) {
		this.displayNameB = displayNameB;
	}

	public String getGroupidA() {
		return groupidA;
	}

	public void setGroupidA(String groupidA) {
		this.groupidA = groupidA;
	}

	public String getGroupidB() {
		return groupidB;
	}

	public void setGroupidB(String groupidB) {
		this.groupidB = groupidB;
	}

	public int getIsAOnline() {
		return isAOnline;
	}

	public void setIsAOnline(int isAOnline) {
		this.isAOnline = isAOnline;
	}

	public int getIsBOnline() {
		return isBOnline;
	}

	public void setIsBOnline(int isBOnline) {
		this.isBOnline = isBOnline;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

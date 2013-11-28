package model;

import java.io.Serializable;

public class SmileIcon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2511770036491633256L;

	private String id,iconImage,shortKey,iconName;

	public SmileIcon(String id, String iconImage, String shortKey,
			String iconName) {
		super();
		this.id = id;
		this.iconImage = iconImage;
		this.shortKey = shortKey;
		this.iconName = iconName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

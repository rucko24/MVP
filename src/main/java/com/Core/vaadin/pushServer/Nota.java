package com.Core.vaadin.pushServer;

import java.io.Serializable;

public class Nota implements Serializable {
	
	private int id;
	private int positionX;
	private int positionY;
	private String caption;
	private String text = "";
	private int lockedByUser = -1;
	
	
	public Nota( int id ) {
		this.id = id;
		caption = "Note " + id;
	}
	
	public Nota(int id, int positionX, int positionY, String text) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		this.text = text;
	}
	public void setId( int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPositionX( int positionX) {
		this.positionX = positionX;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionY( int positionY) {
		this.positionY = positionY;
	}
	public int getPositionY() {
		return positionY;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getCaption() {
		return caption;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setLockedByUser(int lockedByUser) {
		this.lockedByUser = lockedByUser;
	}

	public int getLockedByUser() {
		return lockedByUser;
	}

	
}

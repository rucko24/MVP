package com.Core.vaadin.pushServer.ventanaEditablePush;

import java.io.Serializable;

public class Nota implements Serializable {
	
	/*java bean class
	 * cada nota tienes un ID unico, y 
	 */
	private int id;
	private int posicionX;
	private int posicionY;
	private String caption;
	private String texto = "";
	private int cerradoPorUsuario = -1;
	
	public Nota(int id) {
		this.id = id;
		caption = "Note "+ id;
	}
	public Nota(int id, int posicionX, int posicionY, String texto) {
		this.id = id;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.texto = texto;
	}
	
	public void setId(int id) { this.id = id; }
	public int getId() { return this.id; }
	public void setPosicionX(int posicionX) { this.posicionX = posicionX; }
	public int getPosicionX() { return this.posicionX; }
	public void setPosicionY(int posicionY) { this.posicionY = posicionY; }
	public int getPosicionY() { return this.posicionY;}
	public void setCaption(String caption) { this.caption = caption;}
	public String getCaption() { return this.caption;}
	public void setTexto(String texto) { this.texto = texto; }
	public String getTexto() { return this.texto; }
	public void setCerradoPorUsuario( int cerradoPorUsuario ) { this.cerradoPorUsuario = cerradoPorUsuario; }
	public int getCerradoPorUsuario() { return this.cerradoPorUsuario; }
	
}

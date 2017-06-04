package com.Core.vaadin.pushServer.ejemploPushMarkus;

public class Switches {
	
	public enum Estado {
		ON,OFF
	}
	public Estado estado;
	
	private boolean value = false;
	public Switches(){}
	public Switches(Estado estado,boolean value) {
		this.estado = estado;
		this.value = value;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
		
	}
	public Estado getEstado() {
		return estado;
	}
	public boolean getValue() {
		return value;
	}
}

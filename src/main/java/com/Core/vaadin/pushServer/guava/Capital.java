package com.Core.vaadin.pushServer.guava;

public enum Capital {
	WASHINGTON("Washington"), PARIS("Paris"), HELSINKI("Helsinki");

	private String label;
	
	public String getLabel() {
	
		return label;
	}

	Capital(String label) {
		
		this.label = label;
	}
}

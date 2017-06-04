package com.Core.vaadin.pushServer.guava;

public enum Country {
	US("United States"), FR("France"), FI("Finland");

	private String label;
	
	public String getLabel() {
	
		return label;
	}

	Country(String label) {
		
		this.label = label;
	}
}

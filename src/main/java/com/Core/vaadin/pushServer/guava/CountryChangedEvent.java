package com.Core.vaadin.pushServer.guava;

public class CountryChangedEvent {
	
	private final Country country;

	public CountryChangedEvent(Country country) {

		this.country = country;
	}

	public Country getCountry() {
	
		return country;
	}
}

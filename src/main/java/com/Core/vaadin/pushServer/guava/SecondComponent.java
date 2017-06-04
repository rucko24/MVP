package com.Core.vaadin.pushServer.guava;

import java.util.Arrays;

import com.google.common.eventbus.Subscribe;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class SecondComponent extends VerticalLayout {
	
	private ComboBox cb = new ComboBox("Locale");
	private CheckBox ch = new CheckBox("Locale");
	public SecondComponent() {

		BeanItemContainer<Capital> container = new BeanItemContainer<Capital>(Capital.class);

		for (Capital capital : Capital.values()) {

			container.addBean(capital);
		}

		cb.setContainerDataSource(container);
		cb.setItemCaptionPropertyId("label");

		addComponents(cb,ch);
	}

	/*@Subscribe
	public void changeLocaleOnCountryChange(CountryChangedEvent event) {

		Country country = event.getCountry();

		int index = Arrays.asList(Country.values()).indexOf(country);

		if (index > -1) {

			cb.select(Capital.values()[index]);

		} else {

			cb.select(null);
		}
	}*/
	
	@Subscribe
	public void checkBox(boolean value) {
		
		Notification.show("valor second: "+value);
		if(value) {
			ch.setValue(value);
		}else{
			ch.setValue(value);
		}
	}
}

package com.Core.vaadin.pushServer.guava;

import com.google.common.eventbus.EventBus;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class FirstComponent extends VerticalLayout {

	private ComboBox cb = new ComboBox("Country");
	private CheckBox ch = new CheckBox("Country");
	
	public FirstComponent(final EventBus bus , UI ui) {

		BeanItemContainer<Country> container = new BeanItemContainer<Country>(Country.class);

		for (Country country : Country.values()) {

			container.addBean(country);
		}
		
		cb.setContainerDataSource(container);
		cb.setItemCaptionPropertyId("label");
		cb.setNullSelectionAllowed(false);
		cb.setImmediate(true);
		ch.setImmediate(true);
		
		addComponents(cb,ch);

		cb.addValueChangeListener(e -> {

			CountryChangedEvent event = new CountryChangedEvent((Country) e.getProperty().getValue());
			
			ui.access(() -> {
				bus.post(event);
				Notification.show("Estado");
			});
			

		});
		
		ch.addValueChangeListener( e -> {
			
			boolean value = (boolean)e.getProperty().getValue();
			bus.post(value);
			Notification.show("Valor: "+value);
		});
	}

}

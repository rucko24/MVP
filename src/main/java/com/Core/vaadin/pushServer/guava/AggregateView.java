package com.Core.vaadin.pushServer.guava;

import com.google.common.eventbus.EventBus;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class AggregateView extends VerticalLayout {
	
	public AggregateView(UI ui) {
		EventBus bus = new EventBus();

		FormLayout layout = new FormLayout();

		FirstComponent first = new FirstComponent(bus,ui);

		SecondComponent second = new SecondComponent();

		bus.register(second);

		layout.addComponent(first);
		layout.addComponent(second);

		addComponent(layout);
	}
}

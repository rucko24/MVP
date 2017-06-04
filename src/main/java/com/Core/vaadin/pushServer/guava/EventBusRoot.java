package com.Core.vaadin.pushServer.guava;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class EventBusRoot extends VerticalLayout {
	
	
	public EventBusRoot(UI ui) {
		addComponent(new AggregateView(ui));
	}
}

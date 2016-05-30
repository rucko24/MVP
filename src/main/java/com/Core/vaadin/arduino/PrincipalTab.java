package com.Core.vaadin.arduino;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTab extends TabSheet {
	
	private static final int HEIGHT = 500;
	
	public PrincipalTab() {
	
		addTab(createBotonArduino());
	}
	
	private Component createBotonArduino() {
		VerticalLayout layout = createLayout("ON/OFF");
		BotonSwitch btnSwitch = new BotonSwitch();
		layout.addComponent(btnSwitch);
		
		return layout;
	}
	
	private VerticalLayout createLayout(String caption) {
		VerticalLayout layout = new VerticalLayout();
		layout.setCaption(caption);
		layout.setHeight(HEIGHT , Unit.PIXELS);
		
		return layout;
	}
}

package com.Core.vaadin.arduino;

import com.Core.vaadin.arduino.bombilla.BotonSwitch;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTabArduino extends TabSheet {
	
	private static final int HEIGHT = 500;
	
	public PrincipalTabArduino() {
		
	
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
		layout.setMargin(true);
		layout.setCaption(caption);
		layout.setHeight(HEIGHT , Unit.PIXELS);
		
		return layout;
	}
}

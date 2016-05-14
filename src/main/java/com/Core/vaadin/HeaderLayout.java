package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class HeaderLayout extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ThemeResource logo = new ThemeResource("img/ArdunioLOgo.svg");
	private HorizontalLayout horizontalLogo = new HorizontalLayout();
	public HeaderLayout() {
		setWidth("100%");
		setHeight("49px");
		setSpacing(true);
		addStyleName("header");
		
		Label label = new Label("<strong>Bienvenido: Rubén</strong>",ContentMode.HTML);
		label.setSizeUndefined();
		
		addComponents(label);
		setComponentAlignment(label, Alignment.MIDDLE_LEFT);
	
		//setExpandRatio(logoArduino, 1);
	}
	
}

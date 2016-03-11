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
	private ThemeResource logo = new ThemeResource("img/ArdunioLOgo.svg");
	
	public HeaderLayout() {
		setWidth("100%");
		setHeight("52px");
		addStyleName("header");
		
		Label label = new Label("<strong>Bienvenido: Rubén</strong>",ContentMode.HTML);
		label.setSizeUndefined();
		label.addStyleName("label");
		
		Label logoVaadin = new Label();
		logoVaadin.setSizeUndefined();
		logoVaadin.setIcon(logo);
		
		addComponents(label,logoVaadin);
		setComponentAlignment(label, Alignment.MIDDLE_LEFT);
		setComponentAlignment(logoVaadin, Alignment.MIDDLE_RIGHT);
	}
	
}

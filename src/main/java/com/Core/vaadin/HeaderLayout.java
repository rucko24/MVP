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
	
	private HorizontalLayout horizontalLogo = new HorizontalLayout();
	public HeaderLayout() {
		
		addStyleName("valo-menu-title");
		
		Label label = new Label("<strong>Bienvenido</strong>",ContentMode.HTML);
		label.setSizeUndefined();
		
		addComponents(label);
		
	
	}
	
}

package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

public class BodyLayout extends HorizontalSplitPanel {
	
	private ContentLayout contendLayout = new ContentLayout();
	private MenuLayout menuLayout = new MenuLayout();
	
	
	public BodyLayout() {
		
		
	//	this.setWidth("100%");
		this.setHeight("100%");
		this.setLocked(true);
		
		float ancho = 18f;
		
		menuLayout.addStyleName("menu");
		
		this.setFirstComponent(menuLayout);
		this.setSecondComponent(contendLayout);
		
		setSplitPosition(ancho);
		
	}
	
	public ContentLayout getContentLayout() {
		
		return contendLayout;
	}
}

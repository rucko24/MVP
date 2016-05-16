package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

public class BodyLayout extends HorizontalSplitPanel {
	
	private ContentLayout contendLayout = new ContentLayout();
	private MenuLayout menuLayout = new MenuLayout();
	
	
	public BodyLayout() {
		
		this.setSizeFull();
		float ancho = 18f;
		addStyleName("large");
		
		setHeight("500px");
		setFirstComponent(menuLayout);
		setSecondComponent(contendLayout);
		setLocked(true);
		setSplitPosition(ancho);
		
	}
	
	public ContentLayout getContentLayout() {
		
		return contendLayout;
	}
}

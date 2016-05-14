package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

public class BodyLayout extends HorizontalSplitPanel {
	
	private ContentLayout contendLayout = new ContentLayout();
	private MenuLayout menuLayout = new MenuLayout();
	
	
	public BodyLayout() {
		
		addStyleName("v-scrollable");
		float ancho = 18f;
		
		setFirstComponent(menuLayout);
		setSecondComponent(contendLayout);
		
		setSplitPosition(ancho);
	
	}
	
	public ContentLayout getContentLayout() {
		
		return contendLayout;
	}
}

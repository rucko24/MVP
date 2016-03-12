package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

public class BodyLayout extends HorizontalSplitPanel {
	
	private ContentLayout contendLayout = new ContentLayout();
	private MenuLayout menuLayout = new MenuLayout();
	
	
	public BodyLayout() {
		
		float ancho = 18f;
		
		addComponents(menuLayout,contendLayout);
		setSplitPosition(ancho);
	
	}
	
	public ContentLayout getContentLayout() {
		
		return contendLayout;
	}
}

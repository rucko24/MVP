package com.Core.vaadin;

import com.vaadin.ui.HorizontalSplitPanel;

public class BodyLayout extends HorizontalSplitPanel {
	
	private ContentLayout contendLayout = new ContentLayout();
	private MenuLayout menuLayout = new MenuLayout();
	
	public BodyLayout() {
		
		float ancho = 22.5f;
		addComponents(menuLayout,contendLayout);
		setSplitPosition(ancho);
	
	}
	
	public ContentLayout getContentLayout() {
		
		return contendLayout;
	}
}

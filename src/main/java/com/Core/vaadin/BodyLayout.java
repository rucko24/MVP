package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

public class BodyLayout extends HorizontalSplitPanel {
	
	private ContentLayout contendLayout = new ContentLayout();
	private MenuLayout menuLayout = new MenuLayout();
	private static final float ANCHO = 19f;
	
	public BodyLayout() {
		
		this.setFirstComponent(menuLayout);
		this.setSecondComponent(contendLayout);
		
		this.setSplitPosition(ANCHO);
		
	}
	
	public ContentLayout getContentLayout() {
		
		return contendLayout;
	}
}

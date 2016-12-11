package com.Core.vaadin.pageLayout.bodyLayout;

import com.Core.vaadin.pageLayout.bodyLayout.contentLayout.ContentLayout;
import com.Core.vaadin.pageLayout.bodyLayout.menuLayout.MenuLayout;
import com.vaadin.ui.HorizontalSplitPanel;

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

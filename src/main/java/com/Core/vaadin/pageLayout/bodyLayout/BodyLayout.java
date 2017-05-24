package com.Core.vaadin.pageLayout.bodyLayout;

import com.Core.vaadin.pageLayout.bodyLayout.contentLayout.ContentLayout;
import com.Core.vaadin.pageLayout.bodyLayout.menuLayout.MenuLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;

public class BodyLayout extends HorizontalSplitPanel {
	
	private ContentLayout contendLayout = new ContentLayout();
	private MenuLayout menuLayout;
	
	private static final float ANCHO = 19f;
	private UI ui;
	
	
	public BodyLayout(UI ui) {
		this.ui = ui;
		 menuLayout = new MenuLayout(ui);
		
		this.setFirstComponent(menuLayout);
		this.setSecondComponent(contendLayout);
		
		this.setSplitPosition(ANCHO);
		
	}
	
	public ContentLayout getContentLayout() {
		
		return contendLayout;
	}
	
}

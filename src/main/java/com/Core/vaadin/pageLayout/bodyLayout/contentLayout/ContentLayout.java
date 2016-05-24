package com.Core.vaadin.pageLayout.bodyLayout.contentLayout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContentLayout extends VerticalLayout {
	
	private  Label label = new Label("<h1>Welcome<h1>", ContentMode.HTML);
	private static VerticalLayout LAYOUT = new VerticalLayout();
	public ContentLayout() {
		
		LAYOUT.setMargin(true);
		label.addStyleName("labelMenu");
		LAYOUT.addComponent(label);
		addComponent(LAYOUT);
	}
	
	public static Component getLayout() {
		return LAYOUT;
	}
}

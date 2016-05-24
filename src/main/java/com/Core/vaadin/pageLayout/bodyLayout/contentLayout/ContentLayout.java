package com.Core.vaadin.pageLayout.bodyLayout.contentLayout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContentLayout extends VerticalLayout {
	
	private static final Label label = new Label("<h1>Welcome<h1>", ContentMode.HTML);
	private static VerticalLayout layout = new VerticalLayout();
	public ContentLayout() {
		
		
	}
	
	public static Component getLayout() {
		layout.setMargin(true);
		label.addStyleName("labelMenu");
		layout.addComponent(label);
		
		return layout;
	}
}

package com.Core.vaadin.pageLayout.bodyLayout.contentLayout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContentLayout extends VerticalLayout {
	
	private Label label = new Label("<h1>Welcome<h1>", ContentMode.HTML);
	
	public ContentLayout() {
		
		addComponent(label);
	}
}

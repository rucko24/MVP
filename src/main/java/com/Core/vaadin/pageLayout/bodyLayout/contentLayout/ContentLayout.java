package com.Core.vaadin.pageLayout.bodyLayout.contentLayout;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContentLayout extends VerticalLayout {
	
	private Label label = new Label("<h1>Bienvenido<h1>", ContentMode.HTML);
	private VerticalLayout verticalLayout = new VerticalLayout();
	private ThemeResource moderFoca = new ThemeResource("img/moderFOca.jpg");
	private Label moderFoca2 = new Label();
	
	public ContentLayout() {
		
		Component area = getLayout();
		
		addComponent(area);
		setComponentAlignment(area, Alignment.MIDDLE_CENTER);
	}
	
	public Component getLayout() {
		
		label.setSizeUndefined();
		label.addStyleName("labelMenu");
		moderFoca2.setIcon(moderFoca);
		moderFoca2.setSizeUndefined();
		
		verticalLayout.setSizeFull();
		verticalLayout.setMargin(true);
		verticalLayout.addComponents(label, moderFoca2);
		verticalLayout.setComponentAlignment(label, Alignment.BOTTOM_CENTER);
		verticalLayout.setComponentAlignment(moderFoca2, Alignment.BOTTOM_CENTER);

		
		return verticalLayout;
	}
}

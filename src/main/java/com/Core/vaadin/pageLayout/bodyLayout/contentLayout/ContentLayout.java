package com.Core.vaadin.pageLayout.bodyLayout.contentLayout;

import com.Core.vaadin.pageLayout.bodyLayout.menuLayout.MenuLayout;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ContentLayout extends VerticalLayout {
	
	private Label label = new Label("<h1>Bienvenido<h1>", ContentMode.HTML);
	private CssLayout csslayout = new CssLayout();
	private HorizontalLayout top = new HorizontalLayout();
	private ThemeResource moderFoca = new ThemeResource("img/moderFOca.jpg");
	private Label moderFoca2 = new Label();
	
	public ContentLayout() {
	
		addComponent(getLayout());
	}
	
	public Component getLayout() {
		
		label.addStyleName("labelMenu");
		moderFoca2.setIcon(moderFoca);
		csslayout.addComponents(label,moderFoca2);
		csslayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		
		top.addComponent(csslayout);
		
		return top;
	}
}

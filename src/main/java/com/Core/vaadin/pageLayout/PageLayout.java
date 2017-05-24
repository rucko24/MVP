package com.Core.vaadin.pageLayout;

import com.Core.vaadin.Core;
import com.Core.vaadin.pageLayout.bodyLayout.BodyLayout;
import com.Core.vaadin.pageLayout.headerLayout.HeaderLayout;
import com.vaadin.data.util.converter.ConverterUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PageLayout extends VerticalLayout implements View {

	
	private static final long serialVersionUID = 1L;
	private HeaderLayout headerLayout = new HeaderLayout();
	private BodyLayout bodyLayout;
	public static final String PAGELAYOUT_VIEW = "MAIN";
	private UI ui;
	
	public PageLayout(Core ui) {
		this.ui = ui;
		bodyLayout = new BodyLayout(ui) ;
		setSizeFull();
		
		
		addComponents(headerLayout,bodyLayout);
		setExpandRatio(bodyLayout, 1);
	}
	
	public HeaderLayout getHeaderLayout() {
		
		return headerLayout;
	}
	public BodyLayout getBodyLayout() {
		
		return bodyLayout;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Page.getCurrent().setTitle("MAIN");
		
	}

}

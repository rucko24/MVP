package com.Core.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.VerticalLayout;

public class PageLayout extends VerticalLayout implements View {

	
	private HeaderLayout headerLayout = new HeaderLayout();
	private BodyLayout bodyLayout = new BodyLayout();
	public static final String PAGELAYOUT_VIEW = "MAIN";
	private Core core = Core.getCurrent();
	
	public PageLayout() {
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

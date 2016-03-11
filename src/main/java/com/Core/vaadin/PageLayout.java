package com.Core.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class PageLayout extends VerticalLayout implements View {

	
	private HeaderLayout headerLayout = new HeaderLayout();
	private BodyLayout bodyLayout = new BodyLayout();
	
	
	public PageLayout() {
		setSizeFull();
		
	//	Core ui = Core.getCurrent();
		//BodyLayout layout = ui.getPageLayout().getBodyLayout();
		
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
		// TODO Auto-generated method stub
		
	}

}

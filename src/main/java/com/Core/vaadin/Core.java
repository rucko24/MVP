package com.Core.vaadin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.Core.vaadin.arduino.bombilla.PanelArduinoOnOff;
import com.Core.vaadin.pageLayout.PageLayout;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Push
@SuppressWarnings("serial")
@Theme("mytheme")
public class Core extends UI {

	private PageLayout pageLayout;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {

		pageLayout = new PageLayout();
		
		
		Navigator navigator = new Navigator(this, this);

		navigator.addView(Login.LOGIN_VIEW, new Login());
		navigator.addView(PageLayout.PAGELAYOUT_VIEW, pageLayout);

		navigator.navigateTo(Login.LOGIN_VIEW);

		setContent(pageLayout);

	}

	public PageLayout getPageLayout() {
		return pageLayout;
	}

	public static Core getCurrent() {

		return (Core) UI.getCurrent();

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = Core.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}

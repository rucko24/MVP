package com.Core.vaadin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.Core.vaadin.arduino.arduino_2.PrincipalArduino2;
import com.Core.vaadin.arduino.bombilla.PanelArduinoOnOff;
import com.Core.vaadin.pageLayout.PageLayout;
import com.Core.vaadin.tables.juegoPalabras.JuegoDePalabrasConTabla;
import com.Core.vaadin.testSistema.SingUpForm;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
		navigator.addView(SingUpForm.SINGUPFORM, new SingUpForm());
		navigator.addView(PageLayout.PAGELAYOUT_VIEW, pageLayout);

		navigator.navigateTo(Login.LOGIN_VIEW);
		
		//comentar para usar el navigator e ir al login View
		

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

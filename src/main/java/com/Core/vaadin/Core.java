package com.Core.vaadin;

import javax.servlet.annotation.WebServlet;

import com.Core.vaadin.arduino.broadcaster.Broadcaster;
import com.Core.vaadin.pageLayout.PageLayout;
import com.Core.vaadin.testSistema.SingUpForm;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;


@Push
@SuppressWarnings("serial")
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("mytheme")
public class Core extends UI implements Broadcaster.BroadcasterListener{

	private PageLayout pageLayout;
	
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		pageLayout = new PageLayout(this);
		
		
		Navigator navigator = new Navigator(this, this);

		navigator.addView(Login.LOGIN_VIEW, new Login());
		navigator.addView(SingUpForm.SINGUPFORM, new SingUpForm());
		navigator.addView(PageLayout.PAGELAYOUT_VIEW, pageLayout);

		navigator.navigateTo(Login.LOGIN_VIEW);
		
		//comentar para usar el navigator e ir al login View
		Broadcaster.register(this);
		
	}

	public PageLayout getPageLayout() {
		return pageLayout;
	}

	public static Core getCurrent() {

		return (Core) UI.getCurrent();

	}
	
	@Override
	public Core getUI() {
		return (Core) super.getUI();
	}
	
	

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = Core.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
	
	public void mensaje(String message, boolean value) {
		Broadcaster.broadcast(message, value);
	}
	
	@Override
	public void detach() {
		super.detach();
		Broadcaster.unregister(this);
	}

	@Override
	public void recibirBroadcast(String message, boolean value) {
		access(() -> {
			Notification.show("Estado: "+message);
			
		});
		
	}

}

package com.Core.vaadin.tabSheet.obtenerRecursos;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class PaginaWebExterna extends VerticalLayout {
	
	private BrowserFrame frameFromUrl; 

	public PaginaWebExterna() {
		setSizeFull();
		Component pagina = getPaginaWebExtena();
		
		addComponent(pagina);
	}
	
	private Component getPaginaWebExtena() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		frameFromUrl = new BrowserFrame(null, new ExternalResource("http://alejandrodu.com"));
		frameFromUrl.setWidth("100%");
		frameFromUrl.setHeight("650px");
		layout.addComponent(frameFromUrl);
		layout.setComponentAlignment(frameFromUrl, Alignment.MIDDLE_CENTER);
		
		return layout;
	}
	private Component getLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		return layout;
	}
}

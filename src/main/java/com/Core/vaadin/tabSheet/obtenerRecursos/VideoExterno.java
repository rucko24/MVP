package com.Core.vaadin.tabSheet.obtenerRecursos;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Flash;
import com.vaadin.ui.VerticalLayout;

public class VideoExterno extends VerticalLayout {
	
	/*
	 * probar tambien con la clase video
	 */
	private Flash flashFromUrl = new Flash(null, new ExternalResource("http://www.youtube.com/v/ALgCDkZvzeY&hl=en_US&fs=1"));
	
	public VideoExterno() {
		setMargin(true);
		
		flashFromUrl.setWidth("580px");
		flashFromUrl.setHeight("400px");
		
		addComponent(flashFromUrl);
		setComponentAlignment(flashFromUrl, Alignment.BOTTOM_CENTER);
	}
	
}

package com.Core.vaadin.tabSheet.obtenerRecursos;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ImagenDesdeSistema extends VerticalLayout {

	
	public ImagenDesdeSistema() {
		
		addComponent(getImagenDesdeSystema());
	}
	
	private Component getImagenDesdeSystema() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		BrowserFrame frameFromFileSystem  = new BrowserFrame(null, new FileResource(new File("/home/rubn/Readme")));
		layout.addComponent(frameFromFileSystem);
		
		return layout;
	}
	private Component getLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		return layout;
	}
}

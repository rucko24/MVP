package com.Core.vaadin.arduino.grafico;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class GraficaLuminosidad extends VerticalLayout {
	
	private Arduino2 arduino2;
	private Button buttonPlay = new Button(FontAwesome.PLAY);
	private Button buttonStop = new Button(FontAwesome.STOP);
	
	public GraficaLuminosidad() {
		
		arduino2 = new Arduino2();
		
		iniciarGrafica(arduino2);
		addComponent(arduino2);
	}
	
	public void iniciarGrafica( final Arduino2 arduino ) {
		
		arduino.init();
		
	}
	
	public void paraConexion( final Arduino2 arduino ) {
		
		arduino.stopConexion();
	}
}

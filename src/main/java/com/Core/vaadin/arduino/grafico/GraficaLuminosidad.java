package com.Core.vaadin.arduino.grafico;

import com.Core.vaadin.Core;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class GraficaLuminosidad extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	
	private Core UI = Core.getCurrent();
	
	
	public GraficaLuminosidad() {
	
		
		Component header = getHeader();
		Component mainArea = getMainInitArea();
		
		addComponents(header,mainArea);
		setComponentAlignment(header, Alignment.MIDDLE_CENTER);
		setComponentAlignment(mainArea, Alignment.MIDDLE_CENTER);
		
	}
	
	public Component getHeader() {
		
		HorizontalLayout rowBotones = (HorizontalLayout) getLayout();
		
		Button buttonPlay = new Button(FontAwesome.PLAY);
		buttonPlay.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		Button buttonStop = new Button(FontAwesome.STOP);
		buttonStop.addStyleName(ValoTheme.BUTTON_DANGER);
		
		rowBotones.addComponents(buttonPlay,buttonStop);
		return rowBotones;
	}
	
	public Component getMainInitArea() {
		
		HorizontalLayout mainLabels = (HorizontalLayout) getLayout();
		
		Label labelDataLuminosidad = new Label();
		Label labelNombreSensor = new Label("<h2>Datos Sensor LDR<h2>",ContentMode.HTML);
		
		mainLabels.addComponents(labelNombreSensor,labelDataLuminosidad);
		return mainLabels;
	}
	
	public Component getLayout() {
		
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		
		return layout; 
	}
	
	/*
	 *  @Push aqui
	 */
	public static void setArduinoPrintMessage( int tiempo , String data ) {
		
	}
	
	public void iniciarConexionArduino( ArduinoListenerSerial arduino ) {
		
	}
	
	public void finalizarConexionArduino( ArduinoListenerSerial arduino ) {	
		
	}
}

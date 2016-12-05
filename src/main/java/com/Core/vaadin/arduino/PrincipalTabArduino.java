package com.Core.vaadin.arduino;

import com.Core.vaadin.arduino.bombilla.PanelArduinoOnOff;
import com.Core.vaadin.arduino.grafico.ArduinoGraficoJfreeChart;
import com.Core.vaadin.arduino.grafico.GraficaLuminosidad;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTabArduino extends TabSheet {
	
	private static final int HEIGHT = 700;
	
	public PrincipalTabArduino() {
		
	
		addTab(createBotonArduino());
		addTab(crearGraficoLuminosidad());
	}
	
	private Component createBotonArduino() {
		
		VerticalLayout layout = createLayout("ON/OFF -> Foco");
		PanelArduinoOnOff btnSwitch = new PanelArduinoOnOff();
		layout.setSizeFull();
		layout.addComponent(btnSwitch);
		
		return layout;
	}
	
	private Component crearGraficoLuminosidad() {
		
		VerticalLayout layout = createLayout("Luminosidad Sensor LDR");
		GraficaLuminosidad g = new GraficaLuminosidad();
		layout.setSizeFull();
		layout.addComponent(g);
		
		
		return layout;
	}
	private VerticalLayout createLayout(String caption) {
		
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setCaption(caption);
		layout.setHeight(HEIGHT , Unit.PIXELS);
		
		return layout;
	}
}

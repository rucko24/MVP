package com.Core.vaadin.arduino;

import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.arduino.bombilla.PanelArduinoOnOff;
import com.Core.vaadin.arduino.grafica.GraficaLuminosidad;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTabArduino extends TabSheet {

	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = 700;
	
	public PrincipalTabArduino() {
		setImmediate(true);
		
		Component grafica = crearGraficoLuminosidad();
		
		addTab(createBotonArduino());
		addTab(grafica);
		setSelectedTab(grafica);
		
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
		layout.setMargin(true);
		GraficaLuminosidad g = new GraficaLuminosidad();
		layout.setSizeFull();
		layout.addComponent(g);

		return layout;
	}

	private VerticalLayout createLayout(String caption) {

		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setCaption(caption);
		layout.setHeight(HEIGHT, Unit.PIXELS);

		return layout;
	}
	
	// metodo para cambiar de estado la variable switchOn basta con un solo
	
}

package com.Core.vaadin.arduino.grafico;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.bombilla.ArduinoJSSC;
import com.Core.vaadin.arduino.broadcaster.Broadcaster;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Arrays;
import java.util.List;

import org.vaadin.highcharts.HighChart;

public class GraficaLuminosidad extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final int PAUSA = 3000;
	private Core UI = Core.getCurrent();
	private final HighChart highChart = new HighChart();
	private static Label label = new Label();
	private ComboBox comboPuertosDisponibles = new ComboBox("Puertos disponibles");
	private boolean estado;
	private ArduinoJSSC arduino;

	public GraficaLuminosidad() {

		setSpacing(true);
		arduino = new ArduinoJSSC(highChart);

		Component mainArea = mainArea();
		highChart.addValue(0);
		addComponents(mainArea);
		setComponentAlignment(mainArea, Alignment.MIDDLE_CENTER);

	}

	public Component mainArea() {

		HorizontalLayout rowBotones = (HorizontalLayout) getLayout();
		rowBotones.setSpacing(true);
		rowBotones.setMargin(true);
		Button buttonPlay = new Button("Iniciar", FontAwesome.PLAY);
		Button buttonStop = new Button("Sensor", FontAwesome.STOP);
		buttonStop.setEnabled(false);
		
		/*
		 * ====================== PLAY
		 */
		buttonPlay.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonPlay.setWidth("155px");
		buttonPlay.addClickListener(e -> {
			estado = !estado;
			
			if (iniciar()) {
				
				try {
					
					buttonPlay.setCaption("Parada");
					buttonStop.setEnabled(true);
					buttonPlay.setIcon(FontAwesome.STOP);
					
				} catch (Exception ex) {
					Notification.show(ex.getMessage(), Type.ERROR_MESSAGE);
				}
			} else {
				buttonPlay.setCaption("Iniciar");
				buttonPlay.setIcon(FontAwesome.PLAY);
				arduino.desconectarArduino();
			}

		});
		/*
		 * ======================= STOP
		 */
		
		buttonStop.addStyleName(ValoTheme.BUTTON_DANGER);
		buttonStop.setWidth("155px");
		buttonStop.addClickListener(e -> {
			
			arduino.desconectarArduino();
			
		});

		/*
		 * =========================Combo de puertos
		 */
		comboPuertosDisponibles.setWidth("155px");
		comboPuertosDisponibles.setNullSelectionAllowed(false);
		comboPuertosDisponibles.setImmediate(true);

		label.addStyleName(ValoTheme.LABEL_H2);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		label.setSizeUndefined();
	
		VerticalLayout menu = new VerticalLayout(comboPuertosDisponibles,buttonPlay,buttonStop);
		menu.setWidth("200px");
		menu.setSpacing(true);
		rowBotones.addComponents(menu, highChart);
		rowBotones.setExpandRatio(highChart, 1);
		
		return rowBotones;
	}
	
	public boolean iniciar() {
		boolean valor = false;
		
		for( String tmp : arduino.getPortsList()) {
			comboPuertosDisponibles.addItem(tmp);
		}
		if("/dev/ttyACM0".equals(comboPuertosDisponibles.getValue())) {
			arduino.conectarArduino((String)comboPuertosDisponibles.getValue());
			valor = true;
		}
		return valor;
	}
	
	public Component getLayout() {

		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		return layout;
	}

	@Override
	public void detach() {
		UI.access(() -> {
			Broadcaster.unregister(e -> {
			});
		});
		super.detach();
	}
}
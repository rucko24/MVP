package com.Core.vaadin.arduino.grafico;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.bombilla.ArduinoJSSC;
import com.Core.vaadin.arduino.broadcaster.Broadcaster;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
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

import jssc.SerialPortException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.vaadin.highcharts.HighChart;

public class GraficaLuminosidad extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final int PAUSA = 3000;
	private Core UI = Core.getCurrent();
	private final HighChart highChart = new HighChart();
	private final Label labelLx = new Label();
	private ComboBox comboPuertosDisponibles = new ComboBox("Puertos disponibles");
	private boolean estado;
	private ArduinoJSSC arduinoInstance = ArduinoJSSC.getInstance();
	private Button buttonPlay = new Button("Iniciar", FontAwesome.PLAY);
	private Button buttonStop = new Button("Parar", FontAwesome.STOP);
	private Button escanearPuertos = new Button("Puertos", FontAwesome.SEARCH);
	private boolean botonPlay;
	
	public GraficaLuminosidad() {

		setSpacing(true);

		Component mainArea = mainArea();
		highChart.addValue(0);
		addComponents(mainArea);
		setComponentAlignment(mainArea, Alignment.MIDDLE_CENTER);

	}

	public Component mainArea() {

		HorizontalLayout rowBotones = (HorizontalLayout) getLayout();
		rowBotones.setSpacing(true);
		rowBotones.setMargin(true);

		buttonPlay.setEnabled(false);
		buttonStop.setEnabled(false);

		/**
		 * Escanear puertos
		 */
		escanearPuertos.setWidth("155px");
		escanearPuertos.addClickListener(e -> {

			escanearPuertos();
			
		});

		/**
		 * Play
		 */
		buttonPlay.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonPlay.setWidth("155px");
		buttonPlay.addClickListener(e -> {

			if (iniciar()) {

				buttonPlay.setCaption("Reiniciar");
				buttonStop.setEnabled(true);
				buttonPlay.setEnabled(false);
				
				/**
				 * valor de highChart y de labelLx(unidad de medida candelas, 
				 * lummens bla bla)
				 */
				arduinoInstance.setValorGrafica(highChart);
				arduinoInstance.setValorLabel(labelLx);
				
			} else {
				buttonPlay.setCaption("Iniciar");
				buttonPlay.setIcon(FontAwesome.PLAY);
				arduinoInstance.desconectarArduino();
			}

		});
		/**
		 * Stop
		 */
		buttonStop.addStyleName(ValoTheme.BUTTON_DANGER);
		buttonStop.setWidth("155px");
		buttonStop.addClickListener(e -> {

			if (detener()) {
				try {

					notificar("Captura pausada", Type.ERROR_MESSAGE);
					buttonStop.setEnabled(false);
				} catch (Exception e1) {

					notificar("Error al detener captura " + e1.getMessage(), Type.ERROR_MESSAGE);
				}
			}

		});

		comboPuertosDisponibles.setWidth("155px");
		comboPuertosDisponibles.setImmediate(true);
		comboPuertosDisponibles.setNullSelectionAllowed(false);

		comboPuertosDisponibles.addValueChangeListener(e -> {
			
			String puerto = (String) e.getProperty().getValue();
			/*
			 * FIXME mosc@ con estado boton Play habilitar 
			 * ,solo cuando no se este graficando 
			 */
			botonPlay = !botonPlay;
			
			if("/dev/ttyACM0".equals(puerto) && !buttonPlay.isEnabled()) {
				buttonPlay.setEnabled(true);
			}
		});
		
		labelLx.addStyleName(ValoTheme.LABEL_H2);
		labelLx.addStyleName(ValoTheme.LABEL_COLORED);
		labelLx.setWidth("155px");;
	
		VerticalLayout menu = new VerticalLayout(comboPuertosDisponibles, escanearPuertos, buttonPlay, buttonStop,
				labelLx);
		menu.setWidth("200px");
		menu.setSpacing(true);
		menu.setComponentAlignment(labelLx, Alignment.MIDDLE_RIGHT);
		rowBotones.addComponents(menu, highChart);
		rowBotones.setExpandRatio(highChart, 1);

		return rowBotones;
	}

	public boolean iniciar() {

		estado = false;
		try {
			if ("/dev/ttyACM0".equals(comboPuertosDisponibles.getValue())) {
				arduinoInstance.conectarArduino((String) comboPuertosDisponibles.getValue());
				
				estado = !estado;
			}
		} catch (Exception e) {
			notificar("error al conectar con arduino " + e.getMessage(), Type.ERROR_MESSAGE);
		}
		return estado;

	}

	public boolean escanearPuertos() {

		boolean estado = false;
		try {
			System.out.println("escaneando puertos");
			for (String tmp : arduinoInstance.getPortsList()) {
				comboPuertosDisponibles.addItem(tmp);
				System.out.println(tmp);
				System.out.println("boton play dentron de escanearPuerto "+botonPlay);
				estado = !estado;
				
			}

		} catch (Exception ex) {
			notificar("Puerto no disponible", Type.ERROR_MESSAGE);
		}
		return estado;
	}

	public boolean detener() {
		boolean estado = false;

		if (arduinoInstance.desconectarArduino()) {
			estado = !estado;
		}
		return estado;
	}

	/***
	 *  
	 */
	public Component getLayout() {

		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		return layout;
	}

	public Notification notificar(String msg, Type error) {
		Notification n = new Notification(msg, error);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.setIcon(FontAwesome.WARNING);
		n.show(Page.getCurrent());

		return n;
	}

	public class Hilo extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(5000);
					UI.access(() -> {

					});
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
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
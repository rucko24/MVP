package com.Core.vaadin.arduino.grafica;

import org.vaadin.highcharts.HighChart;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.clasesSerialArduino.ArduinoJSSC;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class GraficaLuminosidad extends CssLayout {

	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "hh:mm:ss a";

	private Core coreUI = Core.getCurrent();
	//private HighChart highChart = new HighChart();
	
	private final Label labelLx = new Label();
	private final Label labelLx2 = new Label();
	private boolean estado;
	private ArduinoJSSC arduinoInstance = ArduinoJSSC.getInstance();
	private Button buttonPlay = new Button("Iniciar", FontAwesome.PLAY);
	private Button buttonStop = new Button("Parar", FontAwesome.STOP);
	private Button escanearPuertos = new Button("Puertos", FontAwesome.SEARCH);
	private NativeSelect comboPuertosDisponibles = new NativeSelect();
	private UI ui;
	
	public GraficaLuminosidad(UI ui) {
		this.ui = ui;
		
		//setSpacing(true);
		setSizeFull();
		
		Component mainArea = mainArea();
		addComponents(mainArea);
		//setComponentAlignment(mainArea, Alignment.MIDDLE_CENTER);

	}

	public Component mainArea() {

		HorizontalLayout rowBotones = (HorizontalLayout) getLayout();
		rowBotones.setSpacing(true);
		rowBotones.setMargin(true);
		buttonPlay.setEnabled(false);
		buttonStop.setEnabled(false);

		/**
		 * Escanear puertos retorna true si hay puertos disponbles
		 */
		escanearPuertos.focus();
		escanearPuertos.setWidth("100%");
		escanearPuertos.addClickListener(e -> escanearPuertos());

		/**
		 * Play
		 */
		buttonPlay.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonPlay.setWidth("100%");
		buttonPlay.addClickListener(e -> {
			buttonStop.setEnabled(true);
			if (iniciar()) {
				if (e.getButton().getCaption().contains("Iniciar")) {

					e.getButton().setCaption("Reiniciar");
					//e.getButton().
					
					//arduinoInstance.setValorGrafica(this.vaadinChart);
					//arduinoInstance.setValorLabel(this.labelLx);
					
				}

			}

		});
		/**
		 * Stop
		 */
		buttonStop.addStyleName(ValoTheme.BUTTON_DANGER);
		buttonStop.setWidth("100%");
		buttonStop.addClickListener(e -> {
			// buttonPlay.setEnabled(true);
			if (detener()) {
				try {

					notificar("Captura pausada ", "puede escojer un puerto para reiniciar", Type.ERROR_MESSAGE);
					e.getButton().setEnabled(false);

				} catch (Exception e1) {

					notificar("Error al detener captura ", " " + e1.getMessage(), Type.ERROR_MESSAGE);
				}
			}

		});

		/**
		 * Lista de puertos serie
		 */
		comboPuertosDisponibles.setWidth("100%");
		comboPuertosDisponibles.setImmediate(true);
		comboPuertosDisponibles.setNullSelectionAllowed(false);
		comboPuertosDisponibles.addValueChangeListener(e -> {
			coreUI.access(() -> {
				notificar("Conexion establecida", "", Type.ASSISTIVE_NOTIFICATION);

				buttonPlay.setEnabled(true);
				buttonStop.setEnabled(true);
			});
		});

		labelLx.addStyleName(ValoTheme.LABEL_H2);
		labelLx.addStyleName(ValoTheme.LABEL_COLORED);
		labelLx.setWidth("100%");

		labelLx2.addStyleName(ValoTheme.LABEL_BOLD);
		labelLx2.setSizeUndefined();

		VerticalLayout menu = new VerticalLayout(comboPuertosDisponibles, escanearPuertos, buttonPlay, buttonStop,
				labelLx, labelLx2);
		menu.setWidth("200px");
		menu.setSpacing(true);
		menu.setComponentAlignment(labelLx, Alignment.MIDDLE_RIGHT);
		menu.setComponentAlignment(labelLx2, Alignment.MIDDLE_CENTER);
		
		
		//addComponents(comboPuertosDisponibles,escanearPuertos,buttonPlay,buttonStop,vaadinChart);
		
		rowBotones.addComponents(menu);
		//rowBotones.setExpandRatio(vaadinChart, 1);

		return rowBotones;
	}

	/*
	 * 
	 */
	public void estadoGrafica() {

		if (PushRArduino.isChange()) {

		} else {

		}
	}

	/**
	 * Metodo que lo ejecuta el boton play, conectarArduino iniciando lectura de
	 * puerto serial
	 */
	public synchronized boolean iniciar() {

		try {
			arduinoInstance.conectarArduino((String) comboPuertosDisponibles.getValue());
			estado = !estado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	/**
	 * escanear puertos
	 */
	public synchronized boolean escanearPuertos() {

		boolean estado = false;

		System.out.println("escaneando puertos");
		try {
			for (String tmp : arduinoInstance.getPortsList()) {
				comboPuertosDisponibles.addItem(tmp);
				System.out.println(tmp);
				estado = !estado;
			}
		} catch (Exception e) {
			notificar("Puerto serie ocupado", " " + e.getMessage(), Type.ASSISTIVE_NOTIFICATION);

		}
		return estado;
	}

	/**
	 * stop
	 */
	public synchronized boolean detener() {
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

	public Notification notificar(String descripcion, String msg, Type error) {
		Notification n = new Notification(descripcion, msg, error);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.setIcon(FontAwesome.WARNING);
		n.show(Page.getCurrent());

		return n;
	}

	@Override
	public void detach() {

		super.detach();
	}
}
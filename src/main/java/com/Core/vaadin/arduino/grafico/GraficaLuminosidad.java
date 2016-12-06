package com.Core.vaadin.arduino.grafico;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.broadcaster.Broadcaster;
import com.panamahitek.PanamaHitek_Arduino;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.highcharts.HighChart;

public class GraficaLuminosidad extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private Core UI = Core.getCurrent();
	private ArduinoListenerSerial arduinoListener = new ArduinoListenerSerial();
	private PanamaHitek_Arduino arduinoPanamaHitek = new PanamaHitek_Arduino();
	private final HighChart highChart = new HighChart();
	private static Label label = new Label();

	public GraficaLuminosidad() {

		setSpacing(true);

		Component header = getHeader();

		addComponents(header, highChart);
		setComponentAlignment(header, Alignment.MIDDLE_CENTER);
		setComponentAlignment(highChart, Alignment.MIDDLE_CENTER);

	}

	public Component getHeader() {

		HorizontalLayout rowBotones = (HorizontalLayout) getLayout();
		rowBotones.setSpacing(true);
		Button buttonPlay = new Button(FontAwesome.PLAY);
		Button buttonStop = new Button(FontAwesome.STOP);

		/*
		 * ====================== PLAY
		 */
		buttonPlay.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonPlay.setWidth("100px");
		buttonPlay.addClickListener(e -> {
			buttonStop.setEnabled(true);
			buttonPlay.setEnabled(false);
			try {

				iniciarConexionArduino(arduinoListener);

			} catch (Exception ex) {
				Notification.show(ex.getMessage(), Type.ERROR_MESSAGE);
			}
		});

		/*
		 * ======================= STOP
		 */
		buttonStop.setEnabled(false);
		buttonStop.addStyleName(ValoTheme.BUTTON_DANGER);
		buttonStop.setWidth("100px");
		buttonStop.addClickListener(e -> {
			finalizarConexionArduino(arduinoListener);
			buttonStop.setEnabled(false);
			buttonPlay.setEnabled(true);
		});

		label.addStyleName(ValoTheme.LABEL_H2);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		label.setSizeUndefined();
		rowBotones.addComponents(buttonPlay, buttonStop);
		rowBotones.setComponentAlignment(buttonPlay, Alignment.MIDDLE_RIGHT);

		return rowBotones;
	}

	public Component getLayout() {

		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		return layout;
	}

	/*
	 * INICIA CONEXION CON ARDUINO
	 */
	public void iniciarConexionArduino(ArduinoListenerSerial arduino) {
		
		boolean valido = true;
		if(valido) { 
			arduino.iniciarConexionSerial(arduinoPanamaHitek);
			valido = false;
			
		}
		/*
		 * @Push aqui
		 */

		arduino.iniciarTomaDeDatosPuertoSerie(highChart);
		

	}

	/*
	 * Finaliza la toma de datos,,,pero no CIERRA el puero serie
	 */
	public void finalizarConexionArduino(ArduinoListenerSerial arduino) {

		arduino.finalizarConexionSerial();

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
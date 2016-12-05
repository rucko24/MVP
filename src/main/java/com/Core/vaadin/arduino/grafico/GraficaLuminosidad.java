package com.Core.vaadin.arduino.grafico;

import com.Core.vaadin.Core;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.highcharts.HighChart;

public class GraficaLuminosidad extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	private Core UI = Core.getCurrent();

	
	public GraficaLuminosidad() {


		Component header = getHeader();
		Component mainArea = getMainInitArea();

        final HighChart highChart = new HighChart();
        highChart.addValue(355);

		addComponents(header,mainArea,highChart);
		setComponentAlignment(header, Alignment.MIDDLE_CENTER);
		setComponentAlignment(mainArea, Alignment.MIDDLE_CENTER);

	}
	
	public Component getHeader() {
		
		HorizontalLayout rowBotones = (HorizontalLayout) getLayout();
		rowBotones.setSpacing(true);
		
		Button buttonPlay = new Button(FontAwesome.PLAY);
		buttonPlay.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonPlay.setSizeUndefined();
		
		Button buttonStop = new Button(FontAwesome.STOP);
		buttonStop.addStyleName(ValoTheme.BUTTON_DANGER);
		buttonStop.setSizeUndefined();
		
		rowBotones.addComponents(buttonPlay,buttonStop);
		return rowBotones;
	}

	public Component getMainInitArea() {

		HorizontalLayout mainLabels = (HorizontalLayout) getLayout();

		Label labelDataLuminosidad = new Label();
		Label labelNombreSensor = new Label("<h2>Datos Sensor LDR<h2>", ContentMode.HTML.HTML);

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

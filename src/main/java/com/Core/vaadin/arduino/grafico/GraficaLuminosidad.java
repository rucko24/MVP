package com.Core.vaadin.arduino.grafico;

import com.Core.vaadin.Core;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.highcharts.HighChart;

import java.util.Random;

public class GraficaLuminosidad extends VerticalLayout {
	
	
	private Button buttonPlay = new Button(FontAwesome.PLAY);
	private Button buttonStop = new Button(FontAwesome.STOP);
	
	public GraficaLuminosidad() {
		
		Component grafica = iniciarGrafica();
		addComponent(grafica);
		setComponentAlignment(grafica, Alignment.BOTTOM_CENTER);
	}
	
	public Component iniciarGrafica() {
		
		ArduinoGraficoJfreeChart arduino2 = new ArduinoGraficoJfreeChart();
		arduino2.init();
		VerticalLayout layout = new VerticalLayout(arduino2);
		return layout;
	}
	
	public void paraConexion(  ArduinoGraficoJfreeChart arduino ) {
		
		arduino.stopConexion();
	}
}

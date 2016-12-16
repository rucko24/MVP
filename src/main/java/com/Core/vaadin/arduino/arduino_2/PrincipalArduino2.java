package com.Core.vaadin.arduino.arduino_2;

import com.Core.vaadin.arduino.grafica.HighChartsPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

import jssc.SerialPortException;



public class PrincipalArduino2 extends VerticalLayout {
	
	private JSSC_AC a;
	public static Label labelEstadoArduino = new Label();
	public static HighChartsPanel panel = new HighChartsPanel();
	
	public PrincipalArduino2() {
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		
		init();
		addComponents(labelEstadoArduino,panel);
		setExpandRatio(panel, 1);
	}
	
	private void init()  {
		try {
			a = new JSSC_AC("/dev/ttyS0");
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			Notification.show("Error al iniciar Conexion: "+e.getMessage(),Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}

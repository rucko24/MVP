package com.Core.vaadin.arduino.clasesSerialArduino;

import org.vaadin.highcharts.HighChart;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public class ArduinoListenerSerial {

	private SerialPortEventListener serialPortE;

	private int f;
	private String panamaHytek;
	private boolean estado;

	// Inicializa puerto serial a 96002
	public boolean iniciarConexionSerial() {

		estado = !estado;
		if (estado) {
			
			try {
			//	panamaHytek.arduinoRX("/dev/ttyS0", 9600, serialPortE);
				Notification.show("Conexion iniciada");
				estado = true;
			} catch (Exception e) {
				// Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE,
				// null, e);
				Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
			}
		}
		return estado;

	}
	public boolean getEstadoPuertoSerie() {
		return estado;
	}
	/*
	 * Finaliza puerto serial
	 */
	public void finalizarConexionSerial() {
		
		try {
		//	panamaHytek.killArduinoConnection();
			
		} catch (Exception e) {
			Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
			// Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE,
			// null, e);
		}

	}

	/*
	 * lectura desde arduino a través del SerialPortEventListener de gnu.io
	 */
	public void iniciarTomaDeDatosPuertoSerie(HighChart highChart) {

		this.f = 0;

		serialPortE = new SerialPortEventListener() {
			public void serialEvent(SerialPortEvent arg0) {

				try {
				//	if (panamaHytek.isMessageAvailable() == true) {

						f++;
						UI.getCurrent().access(() -> {

						//	highChart.addValue(Integer.parseInt(panamaHytek.printMessage()));
						//	System.out.println(panamaHytek.printMessage());
						});

					
				} catch (Exception ex) {
					// Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE,
					// null, ex);
					Notification.show(ex.getMessage(), Type.ERROR_MESSAGE);
				}

			}
		};
	}
}
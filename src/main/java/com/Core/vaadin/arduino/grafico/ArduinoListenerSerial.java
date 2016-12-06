package com.Core.vaadin.arduino.grafico;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.highcharts.HighChart;
import com.panamahitek.PanamaHitek_Arduino;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ArduinoListenerSerial {

	private SerialPortEventListener serialPortE;

	private int f;
	private PanamaHitek_Arduino arduino;
	
	// Inicializa puerto serial a 96002
	public void iniciarConexionSerial(PanamaHitek_Arduino panaArduino) {

		this.arduino = panaArduino;

		try {
			
			panaArduino.arduinoRX("/dev/ttyS0", 9600, serialPortE);
			Notification.show("Conexion iniciada");
		} catch (Exception e) {
			//Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE, null, e);
			Notification.show(e.getMessage(),Type.ERROR_MESSAGE);
		}

	}

	/*
	 * Finaliza puerto serial
	 */
	public void finalizarConexionSerial() {

		try {
			arduino.killArduinoConnection();
			Notification.show("Conexion Finalizada");
		} catch (Exception e) {
			Notification.show(e.getMessage(),Type.ERROR_MESSAGE);
			//Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	/*
	 * lectura desde arduino a través del SerialPortEventListener de gnu.io
	 */
	public void iniciarTomaDeDatosPuertoSerie( HighChart highChart) {

		this.f = 0;
		
		serialPortE = new SerialPortEventListener() {
			public void serialEvent(SerialPortEvent arg0) {

				try {
					if (arduino.isMessageAvailable() == true) {

						f++;
						UI.getCurrent().access(() -> {
							
							highChart.addValue(Integer.parseInt(arduino.printMessage()));
							
						});

					}
				} catch (Exception ex) {
					//Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE, null, ex);
					Notification.show(ex.getMessage(),Type.ERROR_MESSAGE);
				}

			}
		};
	}
}
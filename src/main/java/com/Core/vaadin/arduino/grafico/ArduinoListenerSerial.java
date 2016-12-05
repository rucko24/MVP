package com.Core.vaadin.arduino.grafico;


import java.util.logging.Level;
import java.util.logging.Logger;
import com.panamahitek.PanamaHitek_Arduino;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ArduinoListenerSerial {
	
	private SerialPortEventListener serialPortE;

	private int f;
	private PanamaHitek_Arduino arduino;
	
	/*
	 * Inicializa puerto serial a 9600
	 */
	public void iniciarConexionSerial( PanamaHitek_Arduino arduino ) {
		
		this.arduino = arduino;
		
		try {
			arduino.arduinoRX("/dev/ttyS0", 9600, serialPortE);

		} catch (Exception e) {
			Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE, null,e );
			
		}
		
	}
	/*
	 * Finaliza puerto serial
	 */
	public void finalizarConexionSerial() {

		try {
			arduino.killArduinoConnection();
		} catch (Exception e) {

			Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE, null,e );
		}

	}

	/*
	 * lectura desde arduino a través del SerialPortEventListener de gnu.io
	 */
	public void iniciarTomaDeDatosSerialPortEventListener() {
		
		this.f = 0;
		
		serialPortE = new SerialPortEventListener() {
			public void serialEvent(SerialPortEvent arg0) {

				try {
					if (arduino.isMessageAvailable() == true) {
						
						f++;
						GraficaLuminosidad.setArduinoPrintMessage( f ,arduino.printMessage());			

					}
				} catch (Exception ex) {
					Logger.getLogger(ArduinoListenerSerial.class.getName()).log(Level.SEVERE, null, ex );

				}

			}
		};
	}
	
	
}

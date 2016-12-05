package com.Core.vaadin.arduino.bombilla;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import jssc.SerialPort;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ArduinoJSSC {

	public SerialPort serialPort;
	public static boolean valido;
	
	public ArduinoJSSC() {

		serialPort = new SerialPort("/dev/ttyACM0");
		
		try {
			
			valido = serialPort.openPort();
			
			if(valido) {
				System.out.println("Puerto Abierto: Correcto");
				System.out.println("Parametros establecidos: " + serialPort.setParams(9600, 8, 1, 0));
				notification("Puerto Abierto: Correcto, Parametros establecidos: Correcto");
			}
		} catch (SerialPortException ex) {
			notification("Error: " + ex.getMessage());
			System.out.println(ex.getMessage());
			System.out.println(ex.getExceptionType());
		}
		
		
	}
	
	public void enviarDato(String datos) {

		try {
			serialPort.writeBytes(datos.getBytes());
		} catch (SerialPortException ex) {
			notification("Error al enviar dato: " + ex.getMessage());
			System.out.println("ERRORES METODO ENVIAR DATO");
			System.out.println("Metodo getMessage(): " + ex.getMessage());
			System.out.println("Metodo getExceptionType(): " + ex.getExceptionType());
		}
	}

	public void closePort() {

		try {
			
			if(serialPort != null && serialPort.openPort()) {
				serialPort.purgePort(1);
				serialPort.purgePort(2);
				notification("Puerto cerrado " + serialPort.closePort());
				System.out.println("Puerto cerrado: Correcto");
			}
		} catch (SerialPortException ex) {
			notification("Error al cerrar puerto: "+ex.getMessage());
			System.out.println("Error al cerrar puerto: "+ex.getMessage());
		}
	}

	public void notification(String msg) {

		Notification n = new Notification(msg);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());
		n.show("", Notification.Type.ASSISTIVE_NOTIFICATION);

	}
	public SerialPort getSerialPortEnArduino() {
		return serialPort;
	}
}
/*
 * private static final String AMARILLO_OFF = "0"; //APAGAR private static final
 * String AMARILLO_ON = "1"; //ENCENDER
 * 
 * private static final String ROJO_OFF = "2"; //APAGAR private static final
 * String ROJO_ON = "3"; //ENCENDER
 * 
 * //variable de conexcion con RX/TX
 * 
 * private static OutputStream outPut = null; SerialPort serialPort; private
 * static String PUERTO = "/dev/ttyS0";
 * 
 * private static final int TIME_OUT = 2000; // milisegundos; private static
 * final int DATA_RATE = 9600; private static final int ERROR = 0;
 * 
 * 
 * Scanner teclado = new Scanner(System.in);
 * 
 * public Arduino() {
 * 
 * initComponentes(); }
 * 
 * public void initComponentes() {
 * 
 * 
 * CommPortIdentifier puertoID = null; Enumeration<?> puertoEnum =
 * CommPortIdentifier.getPortIdentifiers();
 * 
 * try { while( puertoEnum.hasMoreElements()) { CommPortIdentifier
 * actualPuertoID = (CommPortIdentifier) puertoEnum.nextElement();
 * 
 * if(PUERTO.equals(actualPuertoID.getName())) { puertoID = actualPuertoID;
 * break; } } }catch(UnsatisfiedLinkError e) { Notification.show(e.getMessage(),
 * Notification.Type.ERROR_MESSAGE); }catch(ExceptionInInitializerError e) {
 * Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE); } if(
 * puertoID == null ) { Notification.show("Puerto en uso",
 * Notification.Type.ERROR_MESSAGE); return; } try { serialPort = (SerialPort)
 * puertoID.open( this.getClass().getName(), TIME_OUT );
 * serialPort.setSerialPortParams(DATA_RATE,SerialPort.DATABITS_8,
 * SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); outPut =
 * serialPort.getOutputStream(); } catch( Exception e) {
 * Notification.show(e.getMessage()); } }
 * 
 * public void enviarDato( String datos ) {
 * 
 * try {
 * 
 * outPut.write( datos.getBytes() ); }catch( Exception e ) {
 * Notification.show(e.getMessage());
 * 
 * } }
 */

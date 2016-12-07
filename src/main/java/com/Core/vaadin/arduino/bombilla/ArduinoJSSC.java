package com.Core.vaadin.arduino.bombilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.highcharts.HighChart;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import static jssc.SerialPort.MASK_RXCHAR;
import jssc.SerialPort;
import jssc.SerialPortEvent;

import jssc.SerialPortException;
import jssc.SerialPortList;

public class ArduinoJSSC {

	private SerialPort arduinoSerialPort = null;
	private List<String> listaDePuertos;
	private boolean estado;
	private HighChart highChart;
	private ArduinoJSSC arduino;
	
	public ArduinoJSSC getArduino() {
		return arduino;
	}
	
	public ArduinoJSSC(HighChart highChart) {
		this.highChart = highChart;
	
	}
	/***
	 * =============== OPEN PORT
	 */
	public boolean conectarArduino(String port) {
		
		estado = false;
		SerialPort serialPort = new SerialPort(port);
		try {
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600,
										SerialPort.DATABITS_8, 
										SerialPort.STOPBITS_1, 
										SerialPort.PARITY_NONE); 
			serialPort.setEventsMask(MASK_RXCHAR);
			notification("tomando datos", Type.ERROR_MESSAGE);
			serialPort.addEventListener((SerialPortEvent e) -> {
				if(e.isRXCHAR()) {
					try {
						byte[] b = serialPort.readBytes();
						int valor = b[0] & 0xff;
						String st = String.valueOf(valor);
						/**
						 * pintamos highCharts con webSockets
						 */
						
						UI.getCurrent().access(() -> {
							highChart.addValue((valor*5)/255);
							System.out.println(valor);
						});
						
					}catch(SerialPortException ex) {
						notification(ex.getMessage(),Type.ERROR_MESSAGE);
					}
				}
				
			});
			arduinoSerialPort = serialPort;
			estado = true;
		}catch(Exception e) {
			notification("Puerto ocupado ",Type.ERROR_MESSAGE);
			 System.out.println("SerialPortException: " + e.toString());
		}
		
		
		return estado;
	}
	/***
	 * =============== CLOSE PORT
	 */
	public void desconectarArduino() {
		
		//notification("Desconectar arduino",Type.ASSISTIVE_NOTIFICATION);
		if (arduinoSerialPort != null) {
			try {
				arduinoSerialPort.removeEventListener();
				if(arduinoSerialPort.isOpened()) {
					arduinoSerialPort.closePort();
				}
				arduinoSerialPort = null;
			}catch(SerialPortException e) {
				notification("No se desconecto el arduino "+e.getMessage(),Type.ERROR_MESSAGE);
			}
		}
	}
	
	/***
	 * ==================== PORT LIST,,,NIGG@
	 */
	public List<String> getPortsList() {
		listaDePuertos = new ArrayList<String>();
		try {
			String serialPortsNames[] = SerialPortList.getPortNames();
			for (String tmpPuertos : serialPortsNames) {
				listaDePuertos.add(tmpPuertos);
			}
		} catch (UnsatisfiedLinkError e) {
			notification("puerto serie ocupado: " + arduinoSerialPort.getPortName(), Type.ERROR_MESSAGE);
		} catch(NoClassDefFoundError ee) {
			notification("Puerto no disponible, revisar permisos", Type.ERROR_MESSAGE);
		}

		return listaDePuertos;
	}
	
	public void notification(String msg, Type error) {

		Notification n = new Notification(msg, error);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());

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

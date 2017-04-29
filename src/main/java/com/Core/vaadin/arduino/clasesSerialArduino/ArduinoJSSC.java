package com.Core.vaadin.arduino.clasesSerialArduino;

import static jssc.SerialPort.MASK_RXCHAR;
import static jssc.SerialPort.MASK_TXEMPTY;

import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.grafica.HighChartsPanel;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPortTimeoutException;

public class ArduinoJSSC {

	private SerialPort arduinoSerialPort = null;
	private List<String> listaDePuertos;
	private boolean estado;
	private boolean estadoRXCHAR;
	private HighChartsPanel highChart;
	private static ArduinoJSSC arduino;
	private int valorGrafica;
	private Label labelCelsius;
	private String sValor;
	private int valor;
	private SerialPortException exception;

	/**
	 * Singleton
	 */
	private ArduinoJSSC() {
	}

	public static synchronized ArduinoJSSC getInstance() {
		if (arduino == null) {
			arduino = new ArduinoJSSC();
		}
		return arduino;
	}

	public boolean getEstadoPuerto() {
		return estado;
	}

	public void setValorGrafica(HighChartsPanel highChart) {
		this.highChart = highChart;
	}

	public void setValorLabel(Label labelLx) {
		this.labelCelsius = labelLx;
	}
	/*
	 * datos desde puerto serie
	 */
	public void setValor(String valor) {
		this.sValor = valor;
	}

	public String getValor() {
		return sValor;
	}
	
	
	
	/***
	 * OPEN PORT
	 */
	public synchronized boolean conectarArduino(String port) {

		SerialPort serialPort = new SerialPort(port);
		
		try {
			serialPort.openPort();
			// establecer parametros
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			serialPort.setEventsMask(MASK_RXCHAR + MASK_TXEMPTY);

			// purgar puerto serie
			serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
			serialPort.purgePort(SerialPort.PURGE_TXCLEAR);

			/**
			 * Notificar al usuario que se graficara.
			 */
			notification("Tomando datos", "", Type.ASSISTIVE_NOTIFICATION);

			serialPort.addEventListener(e -> {
				if (e.isRXCHAR()) {

					System.out.println("metodo getReply() " + getReply());
					
					/**
					 * graficar highCharts con webSockets
					 */
					UI.getCurrent().access(() -> {
						highChart.setValue(Integer.valueOf(getReply().trim()));
						labelCelsius.setValue(getReply() + " °C");
						labelCelsius.addStyleName("h1");
					});
				}

			});

			arduinoSerialPort = serialPort;
			estado = true;

		} catch (SerialPortException e) {
			notification("Puerto ocupado", "", Type.ERROR_MESSAGE);
			System.out.println("SerialPortException: " + e.toString());
		}

		return estado;
	}
	
	/**
	 * getReply datos enviados desde el arduino 
	 */
	public String getReply() {

		int receiveState = 0;
		byte[] recvdBytes; // bytes received
		byte[] oneByte;
		int byteCounter = 0;

		recvdBytes = new byte[80];
		oneByte = new byte[80];

		// initialize array
		for (int i = 0; i < 80; i++) {
			recvdBytes[i] = 0;
		}

		/* wait for reply */
		oneByte[0] = 0;

		// keep collecting data until newline is received
		while ((oneByte[0] != ('\n'))) {
			try {

			while (receiveState == 0) {
					receiveState = arduinoSerialPort.getEventsMask();
					receiveState &= SerialPort.MASK_RXCHAR;
				}

				// wait up until 20 seconds for data
				// when we get data, put it into the buffer
				oneByte = arduinoSerialPort.readBytes(1, 20000);
				recvdBytes[byteCounter] = oneByte[0];
				byteCounter++;
				// arduinoReply += oneChar;

			} catch (SerialPortException | SerialPortTimeoutException ex) {
				System.err.println(" " + ex);
				System.exit(0);
			}
		}
		
		// put the bytes into string format
		String arduinoReply = new String(recvdBytes, 0, byteCounter);

		char[] charArray = arduinoReply.toCharArray();

		// send the reply back to caller
		return arduinoReply.trim();
	}

	
	/**
	 *  enviar Data a arduino
	 */
	public synchronized void onOff(String value) {

		try {

			if (value.equals("1")) {
				arduinoSerialPort.writeBytes("1".getBytes());
			} else if (value.equals("5")) {
				arduinoSerialPort.writeBytes("5".getBytes());
			}

		} catch (SerialPortException e) {

			notification("No se ha iniciado el arduino", "", Type.ERROR_MESSAGE);
		}

	}

	/**
	 * CLOSE PORT
	 */
	public synchronized boolean desconectarArduino() {
		boolean estado = false;

		if (arduinoSerialPort != null) {
			try {
				arduinoSerialPort.removeEventListener();
				if (arduinoSerialPort.isOpened()) {
					arduinoSerialPort.closePort();
					estado = !estado;
				}
				arduinoSerialPort = null;
			} catch (SerialPortException e) {
				notification("No se desconecto el arduino ", " " + e.getMessage(), Type.ERROR_MESSAGE);
			}
		}
		return estado;
	}

	/***
	 * obtener todos los puertos seriales disponibles
	 */
	public synchronized List<String> getPortsList() {
		listaDePuertos = new ArrayList<String>();
		try {
			String serialPortsNames[] = SerialPortList.getPortNames();
			for (String tmpPuertos : serialPortsNames) {
				listaDePuertos.add(tmpPuertos);
			}

			if (!listaDePuertos.isEmpty())
				Notification.show("Puertos escaneados correcto");
			else
				notification("Puertos escaneados incorrecto, revisar conexiones", "", Type.ERROR_MESSAGE);

		} catch (UnsatisfiedLinkError e) {
			notification("Puerto serie ocupado ", " ", Type.ERROR_MESSAGE);
		} catch (NoClassDefFoundError ee) {
			notification("Puerto no disponible, revisar servicios", "", Type.ERROR_MESSAGE);
		}

		return listaDePuertos;
	}
	
	/**
	 * purgar puerto serie
	 */
	public void flushSerialPorts() throws SerialPortException {
		arduinoSerialPort.purgePort(SerialPort.PURGE_RXCLEAR);
		arduinoSerialPort.purgePort(SerialPort.PURGE_TXCLEAR);
	}

	public void notification(String descripcion, String msg, Type error) {

		Notification n = new Notification(descripcion, msg, error);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());
		n.setIcon(FontAwesome.WARNING);
	}

}

package com.Core.vaadin.arduino.clasesSerialArduino;

import static jssc.SerialPort.MASK_RXCHAR;
import static jssc.SerialPort.MASK_TXEMPTY;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

public class ArduinoJSSC implements Serializable {

	private SerialPort arduinoSerialPort = null;
	private List<String> listaDePuertos;
	private boolean estado;
	private boolean estadoRXCHAR;
	private HighChartsPanel highChart;
	private static ArduinoJSSC arduino;
	private int valorGrafica;
	private Label labelLx;
	private String valor;
	private SerialPortException exception;
	private InputStream in = null;
	private DateFormat dt = DateFormat.getDateInstance(DateFormat.MEDIUM);
	private String fecha;

	/**
	 * Singlenton
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
		this.labelLx = labelLx;
	}

	/*
	 * datos desde puerto serie
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
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
				if (e.isRXCHAR() && e.getEventValue() > 0 ) {

					try {
						
						String dataRecibida = serialPort.readString(e.getEventValue());
						System.out.println("dato Serial.println() " + dataRecibida);

						//byte[] buffer = serialPort.readBytes();
						//int intBuffer = buffer[0];
						//System.out.println("dato byte[] buffer " + intBuffer);
						
						setValor(dataRecibida);

						/**
						 * graficar highCharts con webSockets
						 */
						UI.getCurrent().access(() -> {
							highChart.setValue(Integer.valueOf(dataRecibida));
							labelLx.setValue(dataRecibida+" lx");
							
						});

					} catch (SerialPortException ex) {
						notification("Error al graficar", "", Type.ERROR_MESSAGE);
					}
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

	public synchronized void onOff(boolean value) throws SerialPortException {

		if (value) {
			arduinoSerialPort.writeBytes("1".getBytes());
		} else {
			arduinoSerialPort.writeBytes("3".getBytes());
		}
	}

	/***
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
	 * obtener todos los puertos seriales disponible
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

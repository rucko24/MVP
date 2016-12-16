package com.Core.vaadin.arduino.arduino_2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.grafica.HighChartsPanel;
import com.vaadin.ui.Label;

//import org.apache.log4j.Logger;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public class JSSC_AC implements JSSC_Interface, SerialPortEventListener {

	// private static final Logger LOG = Logger.getLogger(JSSC_AC.class);
	private static final int DEFAULT_BAUD_RATE = SerialPort.BAUDRATE_9600;

	private SerialPort serialPort;
	private OutputStream outputStream;
	private InputStream inputStream;

	/**
	 * singlenton FIXME private static JSSC_AC jssAC; private JSSC_AC() {}
	 * public static synchronized JSSC_AC getInstance() { if(jssAC == null) {
	 * jssAC = new JSSC_AC(); } return jssAC; }
	 */
	public JSSC_AC(String serialPortName) throws SerialPortException {
		this(serialPortName, DEFAULT_BAUD_RATE);
	}

	public JSSC_AC(String serialPortName, int baudRate) throws SerialPortException {
		if (serialPortName == null || "".equals(serialPortName)) {
			throw new IllegalArgumentException("Se requiere el nombre del puerto");
		}
		serialPort = new SerialPort(serialPortName);
		serialPort.openPort();
		serialPort.setParams(baudRate, 8, 1, 0);
		int mask = SerialPort.MASK_RXCHAR;
		serialPort.setEventsMask(mask);
		serialPort.addEventListener(this);
		inputStream = new UnsignedByteSerialInputStream(serialPort);
		outputStream = new BufferedOutputStream(new SerialOutputStream(serialPort));

	}

	@Override
	public void serialEvent(SerialPortEvent e) {
		if (e.isRXCHAR()) {
			// LOG.trace(String.format("RXCHAR event received, value %d",
			// event.getEventValue()));
			try {
				if (inputStream.available() > 0) {

					System.out.println("Salida desde Ardu: " + getReply());

					Core.getCurrent().access(() -> {

						PrincipalArduino2.labelEstadoArduino.setValue("Dato desde Arduino: " + getReply());
						PrincipalArduino2.panel.setValue(Integer.valueOf(getReply()));

					});

					synchronized (this) {
						this.notify();
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				Notification.show("Erro " + ex.getMessage(), Type.ERROR_MESSAGE);
			}
		} else {
			// LOG.warn(String.format("Unexpected event type %d received",
			// e.getEventType()));
			System.err.println("Error");
		}

	}

	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}

	@Override
	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public void close() {
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			e.printStackTrace();
		}

	}

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
					receiveState = serialPort.getEventsMask();
					receiveState &= SerialPort.MASK_RXCHAR;
				}

				// wait up until 20 seconds for data
				// when we get data, put it into the buffer
				oneByte = serialPort.readBytes(1, 20000);
				recvdBytes[byteCounter] = oneByte[0];
				byteCounter++;
				// arduinoReply += oneChar;

			} catch (SerialPortException | SerialPortTimeoutException ex) {
				Notification.show("error " + ex.getMessage());
				System.err.println(" " + ex);
				System.exit(0);
			}
		}
		// put the bytes into string format
		String arduinoReply;
		arduinoReply = new String(recvdBytes, 0, byteCounter);

		char[] charArray;
		charArray = arduinoReply.toCharArray();

		// send the reply back to caller
		return arduinoReply.trim();
	}

}

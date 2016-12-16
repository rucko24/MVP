package com.Core.vaadin.arduino.arduino_2;

import java.io.IOException;
import java.io.OutputStream;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialOutputStream extends OutputStream {
	
	private SerialPort serialPort;
	
	/**
	 * 
	 */
	public SerialOutputStream(SerialPort serialPort) {
		this.serialPort = serialPort;
	}
	
	@Override
	public void write(int b) throws IOException {
		try {
			serialPort.writeInt(b);
		}catch(SerialPortException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		write(b, 0 , b.length);
	}
	
	@Override
	public void write(byte[] b, int offset, int length) throws IOException {
		byte[] buffer = new byte[length];
		System.arraycopy(b, offset, buffer, 0, length);
		try {
			serialPort.writeBytes(buffer);
		}catch(SerialPortException e) {
			throw new IOException(e);
		}
	}
}

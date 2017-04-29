package com.Core.vaadin.arduino.clasesSerialArduino.rArduino;

import java.io.IOException;

import jssc.SerialPort;

public class UnsignedByteSerialInputStream extends SerialInputStream {

	public UnsignedByteSerialInputStream(SerialPort serialPort) {
		super(serialPort);

	}

	@Override
	public int read(int tiempoDeEspera) throws IOException {
		return (super.read(tiempoDeEspera) & 0xff);
	}

	@Override
	public int read(byte[] buffer, int offset, int length) throws IOException {
		int bytesRead = super.read(buffer, offset, length);
		return bytesRead;
	}

	@Override
	public int blockingRead(byte[] buffer, int offset, int length, int tiempoDeEspera) throws IOException {
		int bytesRead = super.blockingRead(buffer, offset, length, tiempoDeEspera);
		unsignArray(buffer, bytesRead);

		return bytesRead;
	}

	private void unsignArray(byte[] inputArray, int length) throws IOException {
		//si el largo es mayor a cero
		if(length > 0 ) {
			byte[] unsignedBytes = new byte[length];
			for(int f=0; f < length; f++) {
				int s = inputArray[f] & 0xf;
				unsignedBytes[f] = (byte) s;
			}
			System.arraycopy(unsignedBytes, 0, inputArray, 0, length);
		}
	}
}

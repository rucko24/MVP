package com.Core.vaadin.arduino.arduino_2;

import java.io.IOException;
import java.io.InputStream;

import jssc.SerialPort;

public class SerialInputStream extends InputStream {

	private SerialPort serialPort = null;
	private int tiempoDeEsperaPorDefecto = 0;

	/**
	 * Instancia para crear el puerto serie a transmitir
	 * 
	 * @param serialPort
	 *            to Stream
	 */
	public SerialInputStream(SerialPort serialPort) {
		this.serialPort = serialPort;

	}

	/**
	 * Establece el tiempo de espera en milisegundos(ms) de este
	 * SerialInputStream esto afecta a las llamadas posteriores a
	 * {@link #read()}} , {@link #blockingRead(int[])} , and
	 * {@link #blockingRead(int [], int, int)} el tiempoDeEsperaPorDefecto puede
	 * ser desestablecido, configurandolo a 0.
	 * 
	 * @param tiempo,
	 *            el tiempo de espera en milisegundos
	 */
	public void setTiempoDeEspera(int tiempoDeEspera) {
		this.tiempoDeEsperaPorDefecto = tiempoDeEspera;
	}

	/**
	 * lee el siguiente byte del puerto. Si el tiempo de espera de este
	 * stream(flujo) a sido establecido, entonces este metodo bloquea hasta que
	 * hallan datos disponibles o
	 * 
	 */
	@Override
	public int read() throws IOException {

		return read(tiempoDeEsperaPorDefecto);
	}

	// timeOut en miliseconds
	public int read(int tiempoDeEspera) throws IOException {
		byte[] buffer = new byte[1];

		try {
			if (tiempoDeEspera > 0) {
				buffer = serialPort.readBytes(1, tiempoDeEspera);

			} else {
				buffer = serialPort.readBytes(1);
			}
			return buffer[0];
		} catch (Exception e) {
			throw new IOException(e);
		}

	}

	@Override
	public int read(byte[] buffer) throws IOException {
		return read(buffer, 0, buffer.length);
	}

	public int read(byte[] buffer, int offset, int length) throws IOException {

		if (buffer.length < offset + length) {
			length = buffer.length - offset;
		}

		int available = this.available();

		if (available > length) {
			available = length;
		}

		try {

			byte[] readBuffer = serialPort.readBytes(available);
			System.arraycopy(readBuffer, 0, buffer, offset, length);
			
			return readBuffer.length;

		} catch (Exception e) {
			throw new IOException(e);
		}

	}

	/**
	 * bloquear mientrar el largo del buffer es leido,
	 * 
	 */
	public int blockingRead(byte[] buffer) throws IOException {
		return blockingRead(buffer, 0, buffer.length, tiempoDeEsperaPorDefecto);
	}

	/**
	 * 
	 */
	public int blokingRead(byte[] buffer, int tiempoDeEspera) throws IOException {
		return blockingRead(buffer, 0, buffer.length, tiempoDeEspera);
	}

	/**
	 * 
	 */
	public int blockingRead(byte[] buffer, int offset, int length) throws IOException {
		return blockingRead(buffer, offset, length, tiempoDeEsperaPorDefecto);
	}

	/**
	 * 
	 */
	public int blockingRead(byte[] buffer, int offset, int length, int tiempoDeEspera) throws IOException {
		if (buffer.length < offset + length) {
			throw new IOException("No hay suficiente espacio en el búfer para los datos en serie");
		}

		if (tiempoDeEspera < 1) {
			return read(buffer, offset, length);
		}

		try {
			byte[] readBuffer = serialPort.readBytes(length, tiempoDeEspera);
			System.arraycopy(readBuffer, 0, buffer, offset, length);
			return readBuffer.length;
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public int available() throws IOException {
		int ret;
		try {
			ret = serialPort.getInputBufferBytesCount();
			if (ret >= 0) {
				return ret;
			}
			throw new IOException("Error al comprobar los bytes disponibles del puerto serie.");
		} catch (Exception e) {
			throw new IOException("Error al comprobar los bytes disponibles del puerto serie.");
		}
	}
}

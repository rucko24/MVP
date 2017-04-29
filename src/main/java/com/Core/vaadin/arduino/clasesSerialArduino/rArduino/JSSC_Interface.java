package com.Core.vaadin.arduino.clasesSerialArduino.rArduino;

import java.io.InputStream;
import java.io.OutputStream;

public interface JSSC_Interface {
	OutputStream getOutputStream();
	InputStream getInputStream();
	void close();

}

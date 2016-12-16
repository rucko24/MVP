package com.Core.vaadin.arduino.arduino_2;

import java.io.InputStream;
import java.io.OutputStream;

public interface JSSC_Interface {
	OutputStream getOutputStream();
	InputStream getInputStream();
	void close();
}

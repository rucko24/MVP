package com.Core.vaadin.arduino;

import com.panamahitek.PanamaHitek_Arduino;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Arduino2 {
	
	//private PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
	
	public Arduino2() {
		
		SerialPortEventListener serial = new SerialPortEventListener() {
			
			@Override
			public void serialEvent(SerialPortEvent arg0) {
				
				
			}
		};
		
	}
	
}

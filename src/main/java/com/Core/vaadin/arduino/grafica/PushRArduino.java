package com.Core.vaadin.arduino.grafica;

import java.util.LinkedList;

import com.Core.vaadin.arduino.clasesSerialArduino.rArduino.SerialRArduino;

public class PushRArduino {
	
	private static boolean estado = false;
	private static LinkedList<SerialRArduino> graficas = new LinkedList<SerialRArduino>();
	
	public static boolean isChange() {
		return estado;
	}
	
	/***
	 * Broadcast graficaLuminosidad
	 */
	public static synchronized void broadcast() {
		estado = !estado;
		for( SerialRArduino tmp : graficas) {
			
		}
	}
	public static synchronized void register(SerialRArduino grafica) {
		graficas.add(grafica);
	}
	public static synchronized void unregister(SerialRArduino grafica) {
		graficas.remove(grafica);
	}
}

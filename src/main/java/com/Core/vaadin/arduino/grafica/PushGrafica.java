package com.Core.vaadin.arduino.grafica;

import java.util.LinkedList;

public class PushGrafica {
	
	private static boolean estado = false;
	private static LinkedList<GraficaLuminosidad> graficas = new LinkedList<GraficaLuminosidad>();
	
	public static boolean isChange() {
		return estado;
	}
	
	/***
	 * Broadcast graficaLuminosidad
	 */
	public static synchronized void broadcast() {
		estado = !estado;
		for( GraficaLuminosidad tmp : graficas) {
			
		}
	}
	public static synchronized void register(GraficaLuminosidad grafica) {
		graficas.add(grafica);
	}
	public static synchronized void unregister(GraficaLuminosidad grafica) {
		graficas.remove(grafica);
	}
}

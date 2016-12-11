package com.Core.vaadin.arduino.bombilla;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.w3c.dom.events.UIEvent;

import com.Core.vaadin.Core;

public class PushBombilla {
	
	private static boolean estadoBotones = false;
	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	private static LinkedList<PanelArduinoOnOff> arduinos = new LinkedList<PanelArduinoOnOff>();
	private static Core ui = Core.getCurrent();
	
	
	
	//estado actual del cambio de algun bombillo
	public static boolean isChange() {
		return estadoBotones;
	}
	
	/***
	 * Broadcast panel Bombillo
	 */
	public static synchronized void broadcast() {
		estadoBotones = !estadoBotones;
		executor.execute(() -> {
			for( PanelArduinoOnOff tmpArduinos : arduinos ) {
				tmpArduinos.cambiarBotones();
				
			}
		});
		
	}
		
	public static synchronized void register(PanelArduinoOnOff arduino) {
		arduinos.add(arduino);
		
	}
	public static synchronized void unregister(PanelArduinoOnOff arduino) {
		arduinos.remove(arduino);
	}
	
}

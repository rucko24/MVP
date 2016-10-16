package com.Core.vaadin.pushServer.compartirDatosVariasUI;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BroadCaster {
	
	private static final List<BroadCastListener> listeners = new CopyOnWriteArrayList<BroadCastListener>();
	
	public BroadCaster() {
		
	}
	
	public static void register(BroadCastListener listener) {
		listeners.add(listener);
	}
	public static void unregister(BroadCastListener listener) {
		listeners.remove(listener);
	}
	
	public static void broadcast(final String mensaje) {
		
		for(BroadCastListener tmpListener : listeners) {
			tmpListener.recibiendoBroadCast(mensaje);
		}
	}
}

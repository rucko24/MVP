package com.Core.vaadin.arduino.broadcaster;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.Core.vaadin.arduino.bombilla.PanelArduinoOnOff;
import com.vaadin.ui.UI;

public class Broadcaster implements Serializable {
	
	static ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	public interface BroadcasterListener {
		void recibirBroadcast( String message , boolean value);
	}
	
	static LinkedList<BroadcasterListener> listeners = new LinkedList<BroadcasterListener>();
	
	public static synchronized void register(BroadcasterListener listener) {
		listeners.add(listener);
	}
	
	public static synchronized void unregister(BroadcasterListener listener) {
		listeners.remove(listener);
	}

	public static synchronized void broadcast( final String menssage, boolean value) {
		for(final BroadcasterListener listener : listeners ) {
			executorService.execute(() -> {
				
				 listener.recibirBroadcast(menssage, value);
			
			});
		}
	}
	
}

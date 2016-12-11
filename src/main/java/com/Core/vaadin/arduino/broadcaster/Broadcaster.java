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
		void recibirBroadcast( UI ui , String listener );
	}
	
	static Map<UI ,BroadcasterListener> listeners = new HashMap<>();
	
	public static synchronized void register(UI ui , BroadcasterListener listener) {
		listeners.put(ui ,listener);
	}
	
	public static synchronized void unregister(UI ui) {
		listeners.remove(ui);
	}

	public static synchronized void broadcast( final String valor) {
		for(final Map.Entry<UI, BroadcasterListener> listener : listeners.entrySet() ) {
			executorService.execute(() -> {
				 listener.getValue().recibirBroadcast(listener.getKey() , valor);
			});
		}
	}
	
}

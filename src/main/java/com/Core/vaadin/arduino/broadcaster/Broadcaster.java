package com.Core.vaadin.arduino.broadcaster;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.Core.vaadin.Core;

public class Broadcaster implements Serializable {
	
	static ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	public interface BroadcasterListener {
		void recibirBroadcast( boolean estado );
	}
	
	static LinkedList<BroadcasterListener> listeners = new LinkedList<BroadcasterListener>();
	
	public static synchronized void register(BroadcasterListener listener) {
		listeners.add(listener);
	}
	
	public static synchronized void unregister(BroadcasterListener listener) {
		listeners.remove(listener);
	}

	public static synchronized void broadcast( final boolean estado) {
		for(final BroadcasterListener listener : listeners ) {
			executorService.execute(() -> {
				listener.recibirBroadcast(estado);
			});
		}
	}
	
}

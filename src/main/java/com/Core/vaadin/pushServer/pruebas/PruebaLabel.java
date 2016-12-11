package com.Core.vaadin.pushServer.pruebas;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class PruebaLabel extends VerticalLayout {

	private Core ui = Core.getCurrent();

	private Label labelHora = new Label("Ahora: ");
	private static List<Label> labels = new ArrayList<Label>();
	private Refresher refreh = new Refresher();
	private static final int INTERVALO = 10000;
	
	
	public PruebaLabel() {
		
		setMargin(true);
		setSpacing(true);
		refreh.setRefreshInterval(INTERVALO);
		
		
		
		addComponents(labelHora);
		addExtension(refreh);
		
		
		new Hilo().start();
		
	}

	/*
	 * ha este metodo se le hace push con access()
	 */
	public void dimeLaHora() {
		 labelHora.setValue("Ahora: " + Instant.now());
		 
	}

	public class Hilo extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(INTERVALO);

					// actualizando la data mientras
					ui.access(new Runnable() {
						@Override
						public void run() {
							dimeLaHora();
						}
					});
					
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		}
	}

}

package com.Core.vaadin.pushServer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class PruebaLabel extends VerticalLayout {

	private Label label = new Label("Ahora: ");
	private static List<Label> labels = new ArrayList<Label>();
	private Core ui = Core.getCurrent();
	private Refresher refreh = new Refresher();
	private static final int INTERVALO = 1500;
	
	public PruebaLabel() {
		setMargin(true);
			
		refreh.setRefreshInterval(INTERVALO);
		labels.add(label);
		addComponent(label);
		addExtension(refreh);
		new Hilo().start();
	}

	public void dimeLaHora() {
		//label.setValue("Ahora: " + Instant.now());
		for(Label tmpLabel : labels) {
			tmpLabel.setValue("Ahora: "+Instant.now());
		}
	}

	public class Hilo extends Thread {

		@Override
		public void run() {
			try {
				// actualizando la data mientras
				while (true) {
					Thread.sleep(INTERVALO);

					ui.access(new Runnable() {

						@Override
						public void run() {
							
							dimeLaHora();
						}
					});
				}
			

			} catch (InterruptedException ex) {

				Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
		}
	}

}

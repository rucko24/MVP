package com.Core.vaadin.pushServer.pruebas;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class PruebaLabel extends VerticalLayout {

	private Core ui = Core.getCurrent();

	private Label labelHora = new Label("Ahora: ");
	private static List<Label> labels = new ArrayList<Label>();
	private Refresher refreh = new Refresher();
	private static final int INTERVALO = 1000;
	private Grid grid = new Grid();

	public PruebaLabel() {

		setMargin(true);
		setSpacing(true);
		
		grid.addColumn("Name", String.class);
		grid.addColumn("Born", Number.class);
		grid.addRow("Nicolas ", getNum(100));
		grid.addRow("Newton", getNum(250));

		addComponents(labelHora, grid);
		setExpandRatio(grid, 1);
		new Hilo().start();

	}

	/*
	 * ha este metodo se le hace push con access()
	 */
	public void dimeLaHora() {
		labelHora.setValue("Ahora: " + Instant.now());
		
		Notification.show("Update label ", Notification.Type.TRAY_NOTIFICATION);

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
							//dimeLaHora();
							labelHora.setValue("Ahora: " + Instant.now());
						}
					});

				} catch (InterruptedException ex) {
					ex.printStackTrace();
					Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		}
	}

	public int getNum(int x) {
		return 1 + new Random().nextInt(x);
	}

}

package com.Core.vaadin.pushServer.icePush;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class IcePushServerAddOn extends VerticalLayout {

	private Core ui = Core.getCurrent();
	private Button boton = new Button("click");
	private static List<Button> botones = new ArrayList<Button>();
	private Refresher refresher = new Refresher();
	private static int INTERVALO = 1000;
	private Label label = new Label("Ahora: ");

	public IcePushServerAddOn() {
		setMargin(true);
		// setSpacing(true);
		// aqui agregamos el push componente
		refresher.setRefreshInterval(INTERVALO);
		boton.addClickListener(e -> {
			
			new SubProceso().start();
		});
		
		botones.add(boton);
		addComponents(boton, label);
		addExtension(refresher);

	}

	public void cambiarEstilo() {
		for (Button tmpBoton : botones) {
			tmpBoton.setCaption("hola "+mensaje());
		}
	}

	public String mensaje() {
		return "multiples Usuarios";
	}
	
	public class SubProceso extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(INTERVALO);
					ui.access(new Runnable() {
						@Override
						public void run() {
							cambiarEstilo();
						}
					});

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}

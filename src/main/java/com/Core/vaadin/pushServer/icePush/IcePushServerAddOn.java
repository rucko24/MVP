package com.Core.vaadin.pushServer.icePush;


import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class IcePushServerAddOn extends VerticalLayout {
	
	private Core ui = Core.getCurrent();
	private Button boton = new Button("click");
	private List<Button> botones = new ArrayList<Button>();
	private Refresher refresher = new Refresher();
	private static int INTERVALO = 6000;
	
	public IcePushServerAddOn() {
		
		setMargin(true);
		setSpacing(true);
		//aqui agregamos el push componente
		refresher.setRefreshInterval(INTERVALO);
		botones.add(boton);
		
		//agregar el boton para empesar el trabajo detras
		
		
		
		addComponent(boton);
		addExtension(refresher);
	}
	
	public void cambiarEjecutar() {
		
		for( Button tmpBotones : botones) {
			tmpBotones.addBlurListener( e -> {
				addComponent(new Label("Esperando que el"
						+ "proceso detras se complete"));
				new HiloTrasero().start();
				tmpBotones.addStyleName("danger");
			});
		}
	}
	
	public class HiloTrasero extends Thread {
		
		@Override
		public void run() {
			//aqui simulamos el trabajo detras
			try {
				Thread.sleep(INTERVALO);
				
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			
			Core.getCurrent().access(new Runnable() {
				@Override
				public void run() {
					addComponent(new Label("All done"));
					
				}
			});
		}
	}
}

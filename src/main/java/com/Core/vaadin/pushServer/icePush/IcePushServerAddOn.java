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
	private static int INTERVALO = 1500;
	
	public IcePushServerAddOn() {
		setMargin(true);
		setSpacing(true);
		//aqui agregamos el push componente
		refresher.setRefreshInterval(INTERVALO);
		/*boton.addClickListener(e -> {
			
			new SubProceso().start();
		});*/
		botones.add(boton);
		
		addComponent(boton);
		addExtension(refresher);
		new SubProceso().start();
	}
	
	public void cambiarEstilo() {
		for( Button tmpBoton : botones) {
			tmpBoton.setCaption("danger");
		}
	}
	
	public class SubProceso extends Thread {
		
		@Override
		public void run() {
			//aqui simulamos el trabajo detras
			try {
				while(true) {
					Thread.sleep(INTERVALO);
					ui.access(new Runnable() {
						@Override
						public void run() {
							cambiarEstilo();
							addComponent(new Label("All done"));
							
						}
					});
				}
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

package com.Core.vaadin.pushServer.icePush;

import org.vaadin.artur.icepush.ICEPush;

import com.Core.vaadin.Core;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class IcePushServerAddOn extends VerticalLayout {
	
	private ICEPush pusher = new ICEPush();
	private Core ui = Core.getCurrent();
	private Button boton = new Button("click");
	
	public IcePushServerAddOn() {
		setMargin(true);
		setSpacing(true);
		//aqui agregamos el push componente
		pusher.extend(ui);
		
		//agregar el boton para empesar el trabajo detras
		boton.addClickListener( e -> {
			addComponent(new Label("Esperando que el"
					+ "proceso detras se complete"));
			new HiloTrasero(IcePushServerAddOn.this).start();
		});
		
	}
	public static class HiloTrasero extends Thread {
		private IcePushServerAddOn ui;
		
		public HiloTrasero(IcePushServerAddOn ui) {
			this.ui  = ui;
		}
		
		@Override
		public void run() {
			//aqui simulamos el trabajo detras
			try {
				Thread.sleep(5000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			
			Core.getCurrent().access(new Runnable() {
				@Override
				public void run() {
					ui.addComponent(new Label("All done"));
					ui.pusher.push();
				}
			});
		}
	}
}

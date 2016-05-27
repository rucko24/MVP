package com.Core.vaadin.pushServer;


import java.time.Instant;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class PruebaLabel extends VerticalLayout {
	
	private Label label = new Label("Ahora: ");
	
	public PruebaLabel() {
		
		setMargin(true);
		addComponent(label);
		new Hilo().start();
	}
	
	public void dimeLaHora() {
		label.setValue("Ahora: "+Instant.now());
	}
	
	public class Hilo extends Thread {
		
		int c = 0;
		
		@Override
		public void run() {
			try {
				
				//actualizando la data mientras
				while(c < 2) {
					Thread.sleep(1000);
					
					Core.getCurrent().access( new Runnable () {
						
						@Override
						public void run() {
							c++;
							dimeLaHora();
						}
						
					});
					
				}
				
				Core.getCurrent().access(new Runnable() {
					
					@Override
					public void run() {
						label.setValue("DONE");
					}
				});
				
			}catch(InterruptedException ex) {
				
				Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
		}
	}
	
}


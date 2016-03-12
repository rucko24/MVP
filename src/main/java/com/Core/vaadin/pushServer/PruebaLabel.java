package com.Core.vaadin.pushServer;


import java.time.Instant;
import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PruebaLabel extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	private Refresher refresher = new Refresher();
	private TextArea texto = new TextArea("");
	private ThemeResource spinner = new ThemeResource("img/712.gif");
	private Label label = new Label("Now: ");
	
	
	public PruebaLabel() {
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		
		addComponent(label);
		addExtension(refresher);
		
		new Hilo().start();
	}
	
	public void tellTime() {
		
		label.setValue("Now: "+Instant.now());
	}
	class Hilo extends Thread {
		
		int count = 0;
		@Override
		public void run() {
			try {
				
				while( count < 100 ) {
					Thread.sleep(2000);
					
					UI.getCurrent().access(new Runnable () {

						@Override
						public void run() {
							
							count ++;
							tellTime();
						}
						
					});
				}
				
				UI.getCurrent().access(new Runnable () {
					
					@Override
					public void run() {
						
						label.setValue("Done.");
					}
				});
				
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		
	}
	
}


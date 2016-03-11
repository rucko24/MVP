package com.Core.vaadin.barraCarga;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

public class BarraCarga extends VerticalLayout {
	
	private Button btn = new Button("Start");
	private ProgressIndicator barra = new ProgressIndicator();
	
	public BarraCarga() {
		
		btn.addClickListener( e -> {
			new Algoritmo().start();
		});
		addComponents(btn,barra);
	}
	
	private class Algoritmo extends Thread {
		public void run() {
			for( int i=0; i<10; i++ ) {
				try {
					sleep(1000);
					barra.setValue(i * 0.1f);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}

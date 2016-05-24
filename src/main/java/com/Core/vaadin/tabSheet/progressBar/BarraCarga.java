package com.Core.vaadin.tabSheet.progressBar;

import com.vaadin.ui.Button;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;

public class BarraCarga extends VerticalLayout {
	
	private Button btn = new Button("Start");
	private ProgressBar barra = new ProgressBar();
	
	public BarraCarga() {
		
		setMargin(true);
		setSpacing(true);
		barra.setIndeterminate(true);
		
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

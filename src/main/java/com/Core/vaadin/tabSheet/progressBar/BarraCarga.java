package com.Core.vaadin.tabSheet.progressBar;

import com.Core.vaadin.Core;
import com.vaadin.ui.Button;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class BarraCarga extends VerticalLayout {

	private Button btn = new Button("Start");
	private ProgressBar barra = new ProgressBar();
	private UI ui;

	public BarraCarga(UI ui) {
		this.ui = ui;
		
		setSpacing(true);
		setMargin(true);

		btn.addClickListener(e -> {
			new Algoritmo().start();

		});
		addComponents(btn, barra);
	}

	private class Algoritmo extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(1000);
					ui.access(() -> {

						int f = 0;
						f++;
						barra.setValue(f * 0.1f);

					});

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
	}

}

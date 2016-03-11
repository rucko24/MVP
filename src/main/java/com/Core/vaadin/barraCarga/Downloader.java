package com.Core.vaadin.barraCarga;

import com.vaadin.server.ClassResource;
import com.vaadin.server.FileDownloader;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Downloader extends VerticalLayout {
	
	private ClassResource resource = new ClassResource("miPdf.pdf");
	private Button btn = new Button("Descargar");
	
	public Downloader() {
		
			setSizeFull();
			setMargin(true);
			
			btn.addStyleName(ValoTheme.BUTTON_LINK);
			
			FileDownloader downloader = new FileDownloader(resource);
			downloader.extend(btn);
			
			addComponent(btn);
	}
}

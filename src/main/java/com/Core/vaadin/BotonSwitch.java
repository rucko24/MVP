package com.Core.vaadin;

import com.Core.vaadin.pushServer.NoticeBoard;
import com.Core.vaadin.tables.Arduino;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;

import com.vaadin.ui.Button;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;



public class BotonSwitch extends VerticalLayout {
	
	private Button btnSwitch = new Button();
	private HorizontalLayout row = new HorizontalLayout();
	private HorizontalLayout cssSwitch = new HorizontalLayout();
	
	private HorizontalLayout rowlabelUdo = new HorizontalLayout();
	private HorizontalLayout logoUdoDerecha = new HorizontalLayout();
	
	private ThemeResource rojoON = new ThemeResource("img/on.png");
	private ThemeResource rojoOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	
	private Refresher refresher = new Refresher();
	
	public BotonSwitch() {
		
		
		setMargin(true);
		setSpacing(true);
		
		Label label = new Label("<h1>Arduino-WEB</h1>",ContentMode.HTML);
		label.setSizeUndefined();
		Label udoLogo = new Label();
		udoLogo.setIcon(udo);
		udoLogo.setSizeUndefined();
	
		
		logoUdoDerecha.addStyleName("logoUdo");
		logoUdoDerecha.addComponent(udoLogo);
		
		//rowlabelUdo.setSizeFull();
		rowlabelUdo.setWidth("50%");
		rowlabelUdo.setHeight("40%");
		rowlabelUdo.addComponents(label,logoUdoDerecha);

		
		Label ledRojo = new Label();
		//ledRojo.setSizeUndefined();
		ledRojo.setIcon(rojoOFF);
		
		btnSwitch.setPrimaryStyleName("switchOff");
		btnSwitch.setReadOnly(true);
		btnSwitch.setSizeUndefined();
		
		btnSwitch.addClickListener( e -> {
			
			//Core ui = Core.getCurrent();
			//Arduino ardu = ui.getArduino();  //linea fundamental para conectar con ardu, multiples veces. 
				
			boolean valido = btnSwitch.isReadOnly();
			
			if(valido) {
				e.getButton().addStyleName("switchOn");
				ledRojo.setIcon(rojoON);
				//ardu.enviarDato("3");
				
				btnSwitch.setReadOnly(false);
			}else {
			    ledRojo.setIcon(rojoOFF);
			  //  ardu.enviarDato("2");
				e.getButton().removeStyleName("switchOn");
				btnSwitch.setReadOnly(true);
			}
		});
		cssSwitch.addComponent(btnSwitch); // este cssSwitch position: relative; top: 20px;
		
		
		
		row.setSpacing(true);
		row.addComponents(cssSwitch,ledRojo);
		addComponents(rowlabelUdo,row);
		addExtension(refresher);
		
		
	}
	
	
	
}

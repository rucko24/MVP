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
	
	private Core ui = Core.getCurrent();
	private final Button btnSwitch = new Button();
	private HorizontalLayout row = new HorizontalLayout();
	private HorizontalLayout cssSwitch = new HorizontalLayout();
	
	private HorizontalLayout rowlabelUdo = new HorizontalLayout();
	private HorizontalLayout logoUdoDerecha = new HorizontalLayout();
	
	private ThemeResource rojoON = new ThemeResource("img/on.png");
	private ThemeResource rojoOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	private Label ledRojo = new Label();	
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

		if(Core.isSwitchOn()){
			ledRojo.setIcon(rojoON);
			btnSwitch.setPrimaryStyleName("switchOn");
		}else{
			ledRojo.setIcon(rojoOFF);
			btnSwitch.setPrimaryStyleName("switchOff");
		}
			btnSwitch.setSizeUndefined();
			//Arduino arduino = ui.getArduino();
			
			//linea fundamental para conectar con ardu, multiples veces. 
		btnSwitch.addClickListener( e -> {
			Core.changeSwitch();
			if(Core.isSwitchOn()) {
			//	arduino.enviarDato("255");
				
			}else {
			 //   arduino.enviarDato("0");
			}
		});
		cssSwitch.addComponent(btnSwitch); // este cssSwitch position: relative; top: 20px;
		row.setSpacing(true);
		row.addComponents(cssSwitch,ledRojo);
		addComponents(rowlabelUdo,row);
		addExtension(refresher);
		Core.atachListening(this);		
	}



	//este metodo cambia el estilo del boton, pero se ejecutara en la clase Core
	// para todos los botones atachados
	public void changeButtonOnOff(){
		
			if(Core.isSwitchOn()) {
				btnSwitch.addStyleName("switchOn");
				btnSwitch.removeStyleName("switchOff");
				ledRojo.setIcon(rojoON);
			}else {
			    ledRojo.setIcon(rojoOFF);
				btnSwitch.addStyleName("switchOff");
				btnSwitch.removeStyleName("switchOn");
			}
	}

	@Override
	public void detach(){
		super.detach();
		Core.detachListening(this);		
	}	
	
	
}

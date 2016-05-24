package com.Core.vaadin;

import org.vaadin.teemu.switchui.Switch;

import com.github.wolfie.refresher.Refresher;
import com.panamahitek.PanamaHitek_Arduino;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;



public class BotonSwitch extends VerticalLayout {
	
	private Core ui = Core.getCurrent();
	private Label label = new Label("<h1><strong>Testing-Arduino</strong></h1>",ContentMode.HTML);
	private Button btnSwitch = new Button();
	private HorizontalLayout row = new HorizontalLayout();
	
	private HorizontalLayout rowlabelUdo = new HorizontalLayout();
	private HorizontalLayout logoUdoDerecha = new HorizontalLayout();
	
	private ThemeResource bombillaON = new ThemeResource("img/on.png");
	private ThemeResource bombillaOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	private Label bombilla = new Label();	
	
	private TabSheet tab = new TabSheet();
	private Switch botonSwitch = new Switch();
	
	public BotonSwitch() {
		
		setSpacing(true);
		
		label.addStyleName("labelMenu");
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
		
		//btnSwitch.setPrimaryStyleName("switchOff");
		
		if(Core.isSwitchOn()){
			bombilla.setIcon(bombillaON);
			btnSwitch.setPrimaryStyleName("switchOn");
		}else{
			bombilla.setIcon(bombillaOFF);
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
		
		
		//bombillo mas botonSwitch
		Component getArea1 = getArea1();
		
		VerticalLayout vLayout = new VerticalLayout(getHeader(),getArea1);
		
		tab.addTab(vLayout,"ON/OFF");
		botonSwitch.setAnimationEnabled(true);
		botonSwitch.setStyleName("v-switch");
		botonSwitch.addValueChangeListener( e -> {
			
			Notification.show("Hola "+e.getProperty().getType());
			
		});
		botonSwitch.setImmediate(true);
		
		this.addComponents(botonSwitch);
		Core.atachListening(this);		
		
	}

	private Component getHeader() {
		
		//retorna el titulo del Ardu
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.addComponent(label);
		layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		return layout;
		
	}
	
	private Component getArea1() {
		//bombillo mas switch
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setWidth("250px");
		layout.setHeight("250px");
		bombilla.setSizeUndefined();
		layout.addComponents(bombilla, botonSwitch);
		layout.setComponentAlignment(bombilla, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(botonSwitch, Alignment.BOTTOM_CENTER);
		
		return layout;
	}
	
	private Component getArea2() {
		//Logo de la UDO
		VerticalLayout layout = new VerticalLayout();
		
		return layout;
		
	}
	
	//este metodo cambia el estilo del boton, pero se ejecutara en la clase Core
	// para todos los botones atachados
	public void changeButtonOnOff(){
		
			if(Core.isSwitchOn()) {
				btnSwitch.addStyleName("switchOn");
				btnSwitch.removeStyleName("switchOff");
				bombilla.setIcon(bombillaON);
			}else {
			    bombilla.setIcon(bombillaOFF);
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

package com.Core.vaadin.arduino;

import org.vaadin.teemu.switchui.Switch;

import com.Core.vaadin.Core;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;



public class BotonSwitch extends TabSheet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private  Arduino2 arduino = Core.getArduino();
	
	private Label label = new Label("<h1><strong>Testing-the-foc@</strong></h1>",ContentMode.HTML);
	private Button btnSwitch = new Button();
	private HorizontalLayout row = new HorizontalLayout();
	
	private HorizontalLayout rowlabelUdo = new HorizontalLayout();
	private HorizontalLayout logoUdoDerecha = new HorizontalLayout();
	
	private ThemeResource bombillaON = new ThemeResource("img/on.png");
	private ThemeResource bombillaOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	private ThemeResource logoArduino = new ThemeResource("img/ardu2.png");
	private Label labelArduino = new Label();
	private Label bombilla = new Label();	
	
	private Switch botonSwitch = new Switch();
	
	public BotonSwitch() {
		
		setSizeFull();
		
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
		
		Core.changeSwitch();
		if(Core.isSwitchOn()) {
		//	arduino.enviarDato("255");
			
		}else {
		 //   arduino.enviarDato("0");
		}
		
		//bombillo mas botonSwitch
		Component getHeader = getHeader();
		Component getArea1 = getArea1();
		
		VerticalLayout vLayout = new VerticalLayout(getHeader,getArea1);
		vLayout.setHeight("600px");;
		vLayout.setSpacing(true);
		vLayout.setComponentAlignment(getHeader, Alignment.BOTTOM_CENTER);
		vLayout.setComponentAlignment(getArea1, Alignment.TOP_CENTER);
		vLayout.setExpandRatio(getArea1, 1);
		
		botonSwitch.setAnimationEnabled(true);
		
		botonSwitch.addValueChangeListener( e -> {
			boolean isValid = (boolean) e.getProperty().getValue();
			if(isValid) 	
				Notification.show("ON");
			else 
				Notification.show("OFF");
		});
		botonSwitch.setImmediate(true);
		
		/*VerticalLayout layoutArdu = new VerticalLayout( getresourceIdHeader, arduino);
		layoutArdu.setSpacing(true);
		layoutArdu.setSizeFull();
		layoutArdu.setComponentAlignment(getHeader, Alignment.BOTTOM_CENTER);
		//layoutArdu.setComponentAlignment(arduino, Alignment.BOTTOM_CENTER);*/
		
		addTab(vLayout,"ON/OFF");
		//addTab(layoutArdu, "Gráfico-LM35");
		
		
		Core.atachListening(this);		
		
	}

	private Component getHeader() {
		
		//retorna el titulo del Ardu
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		layout.setWidth("70%");
		labelArduino.setIcon(logoArduino);
		labelArduino.setSizeUndefined();
		layout.addComponents(labelArduino);
		//layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(labelArduino, Alignment.MIDDLE_RIGHT);
		
		return layout;
		
	}
	
	private Component getArea1() {
		//bombillo mas switch
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		bombilla.setSizeUndefined();
		layout.addComponents(bombilla, botonSwitch);
		layout.setComponentAlignment(bombilla, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(botonSwitch, Alignment.BOTTOM_CENTER);
		
		Panel panel = new Panel();
		panel.setCaption(" ");
		panel.setWidth("30%");
		panel.setHeight("70%");
		panel.setContent(layout);
		
		return panel;
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

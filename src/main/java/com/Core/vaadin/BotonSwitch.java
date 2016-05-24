package com.Core.vaadin;

import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;



public class BotonSwitch extends VerticalLayout {
	
	private Core ui = Core.getCurrent();
	private final Button btnSwitch = new Button();
	private HorizontalLayout row = new HorizontalLayout();
	
	private HorizontalLayout rowlabelUdo = new HorizontalLayout();
	private HorizontalLayout logoUdoDerecha = new HorizontalLayout();
	
	private ThemeResource bombillaON = new ThemeResource("img/on.png");
	private ThemeResource bombillaOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	private Label bombilla = new Label();	
	private Refresher refresher = new Refresher();
	
	private TabSheet tab = new TabSheet();
	
	public BotonSwitch() {
	
		setMargin(true);
		setSpacing(true);
		
		Label label = new Label("<h1>Arduino-WEB</h1>",ContentMode.HTML);
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
		
		
		Component getArea1 = getArea1();
		
		VerticalLayout vLayout = new VerticalLayout(getArea1);
	
		tab.addTab(vLayout,"Arduino");
		
		addComponents(tab);
		Core.atachListening(this);		
	}

	private Component getHeader() {
		
		//retorna el titulo del Ardu
		HorizontalLayout layout = new HorizontalLayout();
		
		return layout;
		
	}
	
	private Component getArea1() {
		//bombillo mas switch
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setHeight("100%");
		layout.addComponents(bombilla, btnSwitch); 
		
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

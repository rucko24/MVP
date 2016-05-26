package com.Core.vaadin.tabSheet;

import com.Core.vaadin.Core;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;


public class Deslisador extends VerticalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Slider slider = new Slider();
	private Core ui = Core.getCurrent();
	//private Arduino ardu;
	public Deslisador() {
		
		setMargin(true);
		setSpacing(true);
		
		slider.setCaption("Drag the point");
		slider.setMin(0.0);
		slider.setMax(100.0);
		
		slider.setValue(0.0);
		
		slider.addValueChangeListener(e -> {
			
			Notification.show("Atencion el valor a cambiado a: "+e.getProperty().getType());
			 
			//Double valor = (Double)slider.getValue();
			
			//ardu.enviarDato("3");
					
		});
		
		ColorPicker color = new ColorPicker();
		color.addColorChangeListener(e -> {
			Notification.show("color: "+e.getColor().getRed());
			
		});
		
		
		addComponents(slider,color);
	}
}

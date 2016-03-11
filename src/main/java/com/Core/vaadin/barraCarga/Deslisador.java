package com.Core.vaadin.barraCarga;

import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;


public class Deslisador extends VerticalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Slider slider = new Slider();
	
	public Deslisador() {
		
		setMargin(true);
		setSpacing(true);
		
		slider.setCaption("Drag the point");
		slider.setMin(0.0);
		slider.setMax(100.0);
		
		slider.setValue(0.0);
		
		slider.addValueChangeListener(e -> {
			
			Notification.show("Atencion el valor a cambiado a: "+e.getProperty().getValue());
		});
		
		ColorPicker color = new ColorPicker();
		color.addColorChangeListener(e -> {
			Notification.show("color: "+e.getColor().getRed());
		
			
		});
		
		
		addComponents(slider,color);
	}
}

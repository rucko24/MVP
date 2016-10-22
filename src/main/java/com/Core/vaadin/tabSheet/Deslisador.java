package com.Core.vaadin.tabSheet;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.bombilla.Arduino;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;

import gnu.io.CommPortIdentifier;

public class Deslisador extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Slider slider = new Slider();
	//private Arduino arduino = Core.getArduino();
	
	//private Arduino ardu = new Arduino();

	public Deslisador() {

		setMargin(true);
		setSpacing(true);

		slider.setCaption("Ajustar intensidad de led");
		slider.setMin(0.0);
		slider.setMax(100.0);

		slider.setValue(0.0);
		slider.setImmediate(true);
		slider.setOrientation(SliderOrientation.VERTICAL);
		slider.addValueChangeListener(e -> {

			Notification.show("ajustando");

			Double vDouble = (Double) slider.getValue();

			Integer entero = vDouble.intValue();

		//	arduino.enviarDato(String.valueOf(entero));

		});

		ColorPicker color = new ColorPicker();
		color.addColorChangeListener(e -> {
			Notification.show("color: " + e.getColor().getRed());

		});

		addComponents(slider, color);
	}
	
}

package com.Core.vaadin.arduino.clasesSerialArduino.rArduino;

import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.arduino.grafica.HighChartsPanel;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import jssc.SerialPortException;



public class MainRArduino extends VerticalLayout {
	
	/**
	 * @author rubn
	 */
	private static final long serialVersionUID = 1L;
	private SerialRArduino arduino = SerialRArduino.getInstance();
	public static Label labelEstadoArduino = new Label();
	public static HighChartsPanel panel = new HighChartsPanel();
	public ComboBox combo = new ComboBox("Puertos disponibles");
	
	
	public MainRArduino() {
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		combo.setRequired(true);
		combo.setImmediate(true);
		combo.setNullSelectionAllowed(false);
		
		
		init();
		final Button button = new Button("iniciar",FontAwesome.PLAY_CIRCLE);
		button.addClickListener( e -> {
			
			try {
				arduino.init((String)combo.getValue());
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}
			
		});
		addComponents(combo,labelEstadoArduino,panel);
		setExpandRatio(panel, 1);
	}
	
	private void init()  {
		
		//List<String> puertos = (ArrayList<String>)RArduino.getListaDePuertos();
		for(String tmp : arduino.getListaDePuertos()) {
			combo.addItem(tmp);
			System.out.println("MainRaduino "+tmp);
		}
		
		combo.addValueChangeListener( e -> {
			try {
				arduino.init((String)e.getProperty().getValue());
				
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}
		});
		
		
	}
	
	@Override
	public void detach() {
		
		super.detach();
	}
}

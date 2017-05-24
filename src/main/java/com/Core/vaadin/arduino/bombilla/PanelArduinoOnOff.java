package com.Core.vaadin.arduino.bombilla;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.teemu.switchui.Switch;

import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.broadcaster.Broadcaster.BroadcasterListener;
import com.Core.vaadin.arduino.clasesSerialArduino.ArduinoJSSC;
import com.Core.vaadin.arduino.broadcaster.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.UIDetachedException;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import jssc.SerialPortException;

public class PanelArduinoOnOff extends VerticalLayout implements Broadcaster.BroadcasterListener {

	private static final long serialVersionUID = 1L;

	private Button botonBombilla = new Button("Off");
	private Button btnDetenerConexion = new Button("Detener Conexión");
	private Button btnIniciarConexion = new Button("Iniciar Conexión");
	private ThemeResource bombillaON = new ThemeResource("img/on.png");
	private ThemeResource bombillaOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	private ThemeResource logoArduino = new ThemeResource("img/ardu2.png");
	private Label bombilla = new Label();
	private UI ui;
	private Switch s = new Switch();
	private Core core = (Core) UI.getCurrent();
	
	public PanelArduinoOnOff(UI ui) {
		this.ui = ui;
		Component main = getMainArea();

		addComponent(main);
		PushBombilla.register(this);
		Broadcaster.register(this);
	}

	/**
	 * Parte Main, contiene el panel con todo
	 */
	private Component getMainArea() {

		HorizontalLayout top = new HorizontalLayout(btnIniciarConexion, btnDetenerConexion);
		top.setSpacing(true);

		Component content = getContentArea();

		VerticalLayout vLayout = new VerticalLayout(top, content);
		vLayout.setHeight("500px");
		vLayout.setSpacing(true);
		vLayout.setMargin(true);
		vLayout.setComponentAlignment(top, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
		vLayout.setExpandRatio(content, 1);

		return vLayout;
	}

	/**
	 * Panel dentro un verticalLayout
	 */
	private Component getContentArea() {
		/**
		 * Bombillo mas boton
		 */
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setHeight("78%");

		bombilla.setSizeUndefined();
		layout.addComponents(bombilla, botonBombilla , s);
		layout.setComponentAlignment(bombilla, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(botonBombilla, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(s, Alignment.BOTTOM_CENTER);
		
		Panel panel = new Panel();

		panel.setWidth("40%");
		panel.setHeight("100%");
		panel.setContent(layout);

		bombillos();

		return panel;
	}

	/*
	 * bombillos y boton
	 */
	public void bombillos() {

		bombilla.setIcon(bombillaOFF);
		/*
		 * isChange, retorna el estado del cambio en botones y bombilla
		 */
		cambiarBotones();
		botonBombilla.addClickListener(e -> {

			try {
				if (ui != null) {
					ui.access(() -> {
						PushBombilla.broadcast();
						
						if (PushBombilla.isChange()) {
							// ArduinoJSSC.getInstance().onOff("1");
						} else {
							// ArduinoJSSC.getInstance().onOff("5");
						}
					});
				}

			} catch (UIDetachedException ex) {
				Notification.show("error cliente no acoplado aun" + ex.getMessage());
			}
		});
		
	}

	/*
	 * metodo que lo ejecuta la clases ArduinoPush con una lista
	 */
	public void cambiarBotones() {

		if (PushBombilla.isChange()) {
			botonBombilla.setCaption("On");
			bombilla.setIcon(bombillaON);
			//Broadcaster.broadcast("ON");
			core.mensaje("ON",true);
			s.setValue(true);
		} else {
			botonBombilla.setCaption("off");
			bombilla.setIcon(bombillaOFF);
			//Broadcaster.broadcast("OFF");
			core.mensaje("OFF",false);
			s.setValue(false);
		}
	}

	@Override
	public void detach() {
		PushBombilla.unregister(this);
		Broadcaster.unregister(this);
		
		super.detach();
	}

	@Override
	public void recibirBroadcast(String message, boolean value) {
		
		ui.access(() -> {
			Notification.show("Estado: "+message);
		});
		
	}

}

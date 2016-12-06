package com.Core.vaadin.arduino.bombilla;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.teemu.switchui.Switch;
import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.broadcaster.Broadcaster;
import com.github.wolfie.refresher.Refresher;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import jssc.SerialPortException;

public class PanelArduinoOnOff extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Label label = new Label("<h1><strong>Testing-the-foc@</strong></h1>", ContentMode.HTML);
	private final Button btnSwitch = new Button();
	private final Button btnDetenerConexion = new Button("Detener Conexión");
	private final Button btnIniciarConexion = new Button("Iniciar Conexión");

	private HorizontalLayout row = new HorizontalLayout();

	private ThemeResource bombillaON = new ThemeResource("img/on.png");
	private ThemeResource bombillaOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	private ThemeResource logoArduino = new ThemeResource("img/ardu2.png");
	private Label labelArduino = new Label();

	//////////////////////
	private final Label bombilla = new Label();
	private static List<Label> bombillas = new ArrayList<Label>();
	private final Switch botonSwitch = new Switch();
	private static List<Switch> botoneSwitches = new ArrayList<Switch>();
	private static final int INTERVALO = 100;
	private Refresher refresh = new Refresher();
	
	private Core ui = Core.getCurrent();
	//private ArduinoJSSC arduino = ui.getArduino();
	/////////////////////
	private int c = 0;

	public PanelArduinoOnOff() {
		
		
		botonSwitch.setEnabled(false);
		bombilla.setIcon(bombillaOFF);
		
		/*
		 * 	INICIALIZAR CONEXION
		 */
		btnIniciarConexion.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnIniciarConexion.setIcon(FontAwesome.PLAY);
		btnIniciarConexion.addClickListener(e -> {
		
			try {
				
				iniciarConexion();
				
			} catch (UnsatisfiedLinkError ex) {
				Notification.show("Reiniciar server, " + ex.getMessage(), Type.ERROR_MESSAGE);
			} catch (NoClassDefFoundError ex) {
				Notification.show("Reiniciar server, " + ex.getMessage(), Type.ERROR_MESSAGE);
			}

		});
		
		/*
		 * 	DETENER CONEXION
		 */		
		btnDetenerConexion.setEnabled(false);
		btnDetenerConexion.setIcon(FontAwesome.STOP);
		btnDetenerConexion.addStyleName("danger");
		btnDetenerConexion.addClickListener(e -> {
			
			try {
			//	arduino.enviarDato("2");
				boolean s = (boolean) botonSwitch.getValue();
				
				if(s) {
					Broadcaster.broadcast(s);
					
				} else {
					Broadcaster.broadcast(s);
					notification("boton detener: "+s);
				}
				
				btnDetenerConexion.setEnabled(false);
			} catch (Exception ex) {
				
				Notification.show("Error al cerrar puerto: " + ex.getMessage());
			}
		});

		Component getArea1 = getArea1();

		HorizontalLayout top = new HorizontalLayout(btnIniciarConexion, btnDetenerConexion);
		top.setSpacing(true);

		VerticalLayout vLayout = new VerticalLayout(top, getArea1);
		vLayout.setHeight("500px");
		vLayout.setSpacing(true);
		vLayout.setMargin(true);
		vLayout.setComponentAlignment(top, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(getArea1, Alignment.MIDDLE_CENTER);
		vLayout.setExpandRatio(getArea1, 1);
		
		/*
		 *  PUSH A TODAS LAS UI
		 */
		Broadcaster.register( estado -> {
			ui.access(() -> {
				
				botonSwitch.setValue(estado);
				Notification.show("Estado: "+estado);
				if(estado) { 
					bombilla.setIcon(bombillaON);
					btnDetenerConexion.setEnabled(true);
				} else {
					bombilla.setIcon(bombillaOFF);
					btnDetenerConexion.setEnabled(false);
				}
				
			});
		});
		
		//addExtension(refresh);
		addComponent(vLayout);
		
	}
	
	/*
	 *  BOTON SWITCH ON- off 
	 */
	public void iniciarConexion() {
	// boton que enciende el led
		
		btnDetenerConexion.setEnabled(true);
		
		botonSwitch.setEnabled(true);
		
		botonSwitch.addValueChangeListener( e -> {
			boolean estado = (boolean) e.getProperty().getValue();
			if(estado) {
				
			//	arduino.enviarDato("1");
				Broadcaster.broadcast(estado);
				bombilla.setIcon(bombillaON);
				
			} else {
				//arduino.enviarDato("2");
				Broadcaster.broadcast(estado);
				bombilla.setIcon(bombillaOFF);
			}
		});
		botonSwitch.setImmediate(true);
	
	}
	/*
	 * Area panal con bombillo
	 */
	private Component getArea1() {
		// bombillo mas switch
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setHeight("78%");
		bombilla.setSizeUndefined();
		layout.addComponents(bombilla, botonSwitch);
		layout.setComponentAlignment(bombilla, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(botonSwitch, Alignment.BOTTOM_CENTER);

		Panel panel = new Panel();
		
		panel.setWidth("40%");
		panel.setHeight("100%");
		panel.setContent(layout);

		return panel;
	}
	
	public static void agregarBoton( Switch boton) {
		botoneSwitches.add(boton);
	}
	
	public static void quitarBoton( Switch boton) {
		botoneSwitches.remove(boton);
	}
	
	private Notification notification(String msg) {

		Notification n = new Notification(msg, Notification.Type.WARNING_MESSAGE);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());

		return n;
	}

	@Override
	public void detach() {
		Broadcaster.unregister( e -> {
		});
		super.detach();
	}

}

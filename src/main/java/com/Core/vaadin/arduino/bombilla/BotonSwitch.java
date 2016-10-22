package com.Core.vaadin.arduino.bombilla;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.teemu.switchui.Switch;
import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.client.metadata.Type;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class BotonSwitch extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static Arduino arduino = Core.getArduino();

	private Label label = new Label("<h1><strong>Testing-the-foc@</strong></h1>", ContentMode.HTML);
	private final Button btnSwitch = new Button();
	private HorizontalLayout row = new HorizontalLayout();

	private HorizontalLayout rowlabelUdo = new HorizontalLayout();
	private HorizontalLayout logoUdoDerecha = new HorizontalLayout();

	private ThemeResource bombillaON = new ThemeResource("img/on.png");
	private ThemeResource bombillaOFF = new ThemeResource("img/off.png");
	private ThemeResource udo = new ThemeResource("img/udo.png");
	private ThemeResource logoArduino = new ThemeResource("img/ardu2.png");
	private Label labelArduino = new Label();
	
	//////////////////////
	private Label bombilla = new Label();
	private static List<Label> bombillas = new ArrayList<Label>();
	private Switch botonSwitch = new Switch();
	private static List<Switch> botoneSwitches = new ArrayList<Switch>();
	private static final int INTERVALO = 100;
	private Refresher refresh = new Refresher();
	/////////////////////
		
	public BotonSwitch() {

		setSizeFull();

		refresh.setRefreshInterval(INTERVALO);
		label.addStyleName("labelMenu");
		label.setSizeUndefined();

		 btnSwitch.setIcon(FontAwesome.PLAY);

		Core.changeSwitch();
		
		if (Core.isSwitchOn()) {
			// arduino.enviarDato("255");

		} else {
			// arduino.enviarDato("0");
		}

		// bombillo mas botonSwitch
		
		btnSwitch.addClickListener( e -> {
			Core.changeSwitch();
			
  			if(Core.isSwitchOn()) {
  		//	arduino.enviarDato("1");
  			} else {
  		//	arduino.enviarDato("2");
  			}
  			
		});
		
		bombilla.setIcon(bombillaOFF);
		
		Component getArea1 = getArea1();

		VerticalLayout vLayout = new VerticalLayout(getArea1);
		vLayout.setHeight("600px");
		
		vLayout.setSpacing(true);
		vLayout.setComponentAlignment(getArea1, Alignment.BOTTOM_CENTER);
		vLayout.setExpandRatio(getArea1, 1);
		
		addExtension(refresh);
		Core.atachListening(this);
		
		addComponent(getArea1);
		setHeight("600px");
	}

	private Component getHeader() {

		// retorna el titulo del Ardu
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		layout.setWidth("70%");
		labelArduino.setIcon(logoArduino);
		labelArduino.setSizeUndefined();

		//Component iniciar = botonIniciar();
		//Component parar = botonParar();

		//layout.addComponents(iniciar, parar);

		return layout;

	}
	
	private Notification notification( String msg ) {
		
		Notification n = new Notification(msg, Notification.Type.WARNING_MESSAGE );
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());
		
		return n;
	}
	
	private Component getArea1() {
		// bombillo mas switch
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setHeight("75%");
		bombilla.setSizeUndefined();
		layout.addComponents(bombilla, btnSwitch);
		layout.setComponentAlignment(bombilla, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(btnSwitch, Alignment.BOTTOM_CENTER);

		Panel panel = new Panel();
		panel.setCaption(" ");
		panel.setWidth("30%");
		panel.setHeight("70%");
		panel.setContent(layout);

		return panel;
	}
	
	// este metodo cambia el estilo del boton, pero se ejecutara en la clase
	// Core
	// para todos los botones atachados
	public void changeButtonOnOff() {

		if (Core.isSwitchOn()) {
			btnSwitch.setIcon(FontAwesome.STOP);
			bombilla.setIcon(bombillaON);
			
		} else {
			btnSwitch.setIcon(FontAwesome.PLAY);
			bombilla.setIcon(bombillaOFF);
		}
	}

	@Override
	public void detach() {
		super.detach();
		Core.detachListening(this);
	}

}

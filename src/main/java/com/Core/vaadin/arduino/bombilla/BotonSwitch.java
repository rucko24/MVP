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

	private Label label = new Label("<h1><strong>Testing-the-foc@</strong></h1>", ContentMode.HTML);
	private final Button btnSwitch = new Button();
	private final Button btnStopConexion = new Button("Detener Conexión");
	private final Button btnIniciarConexcion = new Button("Iniciar Conexión");

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
	private static Arduino arduino;
	/////////////////////

	public BotonSwitch() {

		Core.changeSwitch();

		bombilla.setEnabled(false);
		btnSwitch.setEnabled(false);
		btnStopConexion.setEnabled(false);
		bombilla.setIcon(bombillaOFF);
		
		btnIniciarConexcion.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnIniciarConexcion.setIcon(FontAwesome.PLAY);
		btnIniciarConexcion.addClickListener( e -> {
			try {
				
				arduino = new Arduino();
				btnSwitch.setEnabled(true);
			}catch(UnsatisfiedLinkError ex) {
				Notification.show(ex.getMessage());
				
			}catch(NoClassDefFoundError ex) {
				Notification.show(ex.getMessage());
			}
		});
		
		btnSwitch.setIcon(FontAwesome.PLAY);
		btnSwitch.addClickListener(e -> {
			Core.changeSwitch();

			if (Core.isSwitchOn()) {

				arduino.enviarDato("1");

			} else {
				arduino.enviarDato("2");
			}

		});

		btnStopConexion.setIcon(FontAwesome.STOP);
		btnStopConexion.addStyleName(ValoTheme.BUTTON_DANGER);
		btnStopConexion.addClickListener(e -> {
			
			arduino.enviarDato("2");
			bombilla.setIcon(bombillaOFF);
			btnSwitch.setIcon(FontAwesome.PLAY);
			arduino.closePort();
		});

		Component getArea1 = getArea1();

		HorizontalLayout top = new HorizontalLayout(btnIniciarConexcion, btnStopConexion);
		top.setSpacing(true);
		
		VerticalLayout vLayout = new VerticalLayout(top, getArea1);
		vLayout.setHeight("500px");
		vLayout.setSpacing(true);
		vLayout.setMargin(true);
		vLayout.setComponentAlignment(top, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(getArea1, Alignment.MIDDLE_CENTER);
		vLayout.setExpandRatio(getArea1, 1);

		addExtension(refresh);
		Core.atachListening(this);
		addComponent(vLayout);

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
		panel.setCaption("");
		panel.setWidth("40%");
		panel.setHeight("100%");
		panel.setContent(layout);

		return panel;
	}

	private Notification notification(String msg) {

		Notification n = new Notification(msg, Notification.Type.WARNING_MESSAGE);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());

		return n;
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

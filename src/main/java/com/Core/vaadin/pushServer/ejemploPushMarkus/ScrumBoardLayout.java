package com.Core.vaadin.pushServer.ejemploPushMarkus;

import com.vaadin.data.Property;
import com.vaadin.server.SizeWithUnit;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.teemu.switchui.Switch;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ScrumBoardLayout extends VerticalLayout implements ScrumBoard.SwitchesListener {

	private ThemeResource bombillaON = new ThemeResource("img/on.png");
	private ThemeResource bombillaOFF = new ThemeResource("img/off.png");
	private Label bombilla = new Label();
	private Switch switchTemu = new Switch();
	private List<Switch> switchesField = new ArrayList<Switch>();
	
	public ScrumBoardLayout() {
		setMargin(true);
		setSpacing(true);
		addHeader();
		//addBoard();
		//fillBoard();
		ScrumBoard.addSwitchesListener(this);
	}

	private void addHeader() {
		
		switchTemu.setImmediate(true);

		bombilla.setIcon(bombillaOFF);
		bombilla.setSizeUndefined();
		switchTemu.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				boolean estado = (boolean) event.getProperty().getValue();
				ScrumBoard.addSwitch(estado);
				switchTemu.removeValueChangeListener(this);
				//switchTemu.clear();
				if(estado == Boolean.TRUE) {
					bombilla.setIcon(bombillaON);
					//PUSH
					//ScrumBoard.updateSwitchEstado(estado);
				}else {
					bombilla.setIcon(bombillaOFF);
					//PUSH
					//ScrumBoard.updateSwitchEstado(estado);
				}
				switchTemu.addValueChangeListener(this);

			}
		});
	
		VerticalLayout layout = new VerticalLayout();
		layout.setHeight("78%");
		layout.addComponents(bombilla, switchTemu);

		layout.setComponentAlignment(bombilla, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(switchTemu, Alignment.BOTTOM_CENTER);
		layout.setExpandRatio(switchTemu, 1);
		addComponents(layout);
	}
	
	private void addBoard() {
		for(final Switches.Estado estadoTmp : Switches.Estado.values()) {
			
			Switches s = new Switches();
			ScrumBoard.updateSwitchEstado(s, estadoTmp);
		
		}
	}
	private void fillBoard() {
		for (Switches switchTmp : ScrumBoard.getSwitches()) {
			
		}
	}

	@Override
	public void switchUpdate(Switches switches) {
		UI.getCurrent().access(() -> {
			
			if(switches.getValue()) {
				switchTemu.setValue(true);
				Notification.show("ON",Type.ASSISTIVE_NOTIFICATION);
			}else {
				switchTemu.setValue(false);
				Notification.show("OFF",Type.ASSISTIVE_NOTIFICATION);
			}
			
			
		});
	}

	@Override
	public void detach() {
		super.detach();
		ScrumBoard.removeSwitchListener(this);
	}
}

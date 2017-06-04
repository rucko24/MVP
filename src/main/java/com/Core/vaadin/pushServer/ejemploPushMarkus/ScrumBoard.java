package com.Core.vaadin.pushServer.ejemploPushMarkus;

import java.util.List;
import java.util.Vector;

import com.Core.vaadin.pushServer.ejemploPushMarkus.Switches.Estado;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Label;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ScrumBoard {

	private static List<SwitchesListener> switchesList = new Vector<SwitchesListener>();
	private static List<Switches> switches = new Vector<Switches>();
	//private  Switches switchesEstado = new Switches();
	

	public interface SwitchesListener {
		public void switchUpdate(Switches switches);
	}

	public static synchronized void addSwitch(boolean value) {
		Switches s = new Switches(Switches.Estado.OFF, value);
		switches.add(s);

		fireChangeEvent(s);
	}
	
	public static synchronized void updateSwitchEstado(Switches s , Switches.Estado newState) {
		Switches.Estado oldEstado =  s.getEstado();
		s.setEstado(newState);
		
		fireChangeEvent(s);
	}
	
	public static List<Switches> getSwitches() {
		return switches;
	}
	public static synchronized void addSwitchesListener(SwitchesListener listener) {
		System.out.println("Added listener for " + listener);
		switchesList.add(listener);
	}
	public static synchronized void removeSwitchListener(SwitchesListener listener) {
		switchesList.remove(listener);
	}

	private static void fireChangeEvent(Switches estados) {
    	for( SwitchesListener switche : switchesList) {
    		switche.switchUpdate(estados);
    	}
    }

}

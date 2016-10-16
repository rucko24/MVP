package com.Core.vaadin.pushServer.compartirDatosVariasUI;

import com.Core.vaadin.Core;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class ProbandoListener extends VerticalLayout {
	
	private Core UI = Core.getCurrent();
	
	public ProbandoListener() {
		setMargin(true);
		final TextArea mensaje = new TextArea("","sistam down en 10s");
		mensaje.setImmediate(true);
		addComponent(mensaje);
		
		final Button button = new Button("Broadcast");
		addComponent(button);
		button.addClickListener( e -> {
			BroadCaster.broadcast(mensaje.getValue());
			
		});
		
		// Registrando broadCast Listener
		BroadCaster.register(UI);
	}
}

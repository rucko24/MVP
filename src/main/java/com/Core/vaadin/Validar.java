package com.Core.vaadin;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
public class Validar extends VerticalLayout {
	
	private VerticalLayout layout = new VerticalLayout();
	private TextField texto = new TextField("Enter pin code");
	
	public Validar() {
		setMargin(true);
		setSpacing(true);
		
		texto.setImmediate(true);
		texto.addValidator(new PinValidador());
		layout.addComponent(texto);
		
		addComponent(layout);
	}
}

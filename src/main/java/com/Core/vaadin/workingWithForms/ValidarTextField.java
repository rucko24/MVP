package com.Core.vaadin.workingWithForms;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ValidarTextField extends VerticalLayout {
	
	private TextField texto = new TextField("Enter pin code");
	
	public ValidarTextField() {
		setSizeUndefined();
		setMargin(true);
		texto.setImmediate(true);
		texto.addValidator(new PinValidador());
		
		addComponent(texto);
	}
}

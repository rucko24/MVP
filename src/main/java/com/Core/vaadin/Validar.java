package com.Core.vaadin;

import com.Core.vaadin.validadores.CustomValidation;
import com.Core.vaadin.validadores.PinValidador;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
public class Validar extends VerticalLayout {
	
	private VerticalLayout layout = new VerticalLayout();
	private TextField texto = new TextField("Enter pin code");
	private TabSheet tab = new TabSheet();
	
	public Validar() {
		
		setSpacing(true);
		
		texto.setImmediate(true);
		texto.addValidator(new PinValidador());
		layout.addComponent(texto);
		layout.setMargin(true);
		
		CustomValidation validar = new CustomValidation();
		
		tab.addTab(layout, "Pin Validador");
		tab.addTab(validar, "Custom-Validation");
		
		addComponent(tab);
	}
}

package com.Core.vaadin.workingWithForms;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
public class Main extends VerticalLayout {
	
	private VerticalLayout layout = new VerticalLayout();
	private TextField texto = new TextField("Enter pin code");
	private TabSheet tab = new TabSheet();
	
	public Main() {
		
		setSpacing(true);
		
		texto.setImmediate(true);
		texto.addValidator(new PinValidador());
		layout.addComponent(texto);
		layout.setMargin(true);
		
		CustomValidation validar = new CustomValidation();
		MyIconos iconos = new MyIconos();
		
		tab.addTab(layout, "Pin Validador");
		tab.addTab(validar, "Custom-Validation");
		tab.addTab(iconos, "Formulario-iconos-runo");
		addComponent(tab);
	}
}

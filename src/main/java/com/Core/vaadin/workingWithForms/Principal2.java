package com.Core.vaadin.workingWithForms;

import com.Core.vaadin.workingWithForms.jodaTime.Principal;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Principal2 extends TabSheet {
	
	public Principal2() {		
		
		addTab(pinValidador(), "Pin Validador");
		addTab(validar(), "Custom-Validation");
		addTab(myIconos(), "Formulario-iconos-runo");
		addTab(jodaTime() ,"JODA-TIME");
	}
	
	public Layout jodaTime() {
		
		Layout layout = getLayout();
		//joda time
		Principal jodaTime = new Principal();
		
		layout.addComponent(jodaTime);
		return layout;
	}
	
	public Layout pinValidador() {
		
		Layout layout = getLayout();
		TextField texto = new TextField("Enter pin code");
		texto.setImmediate(true);
		texto.addValidator(new PinValidador());
		
		layout.addComponent(texto);
		return layout;
	}
	
	public Layout validar() {
		
		Layout layout = getLayout();
		CustomValidation validar = new CustomValidation();
		
		layout.addComponent(validar);
		return layout;
	}
	
	public Layout myIconos() {
		
		Layout layout = getLayout();
		MyIconos iconos = new MyIconos();
		
		layout.addComponent(iconos);
		return layout;
	}
	
	public Layout getLayout() {
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setSpacing(true);
		layout.setMargin(true);
		
		return layout; 
	}
}

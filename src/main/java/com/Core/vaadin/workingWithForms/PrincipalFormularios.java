package com.Core.vaadin.workingWithForms;


import com.Core.vaadin.workingWithForms.jodaTime.JodaTime;
import com.Core.vaadin.workingWithForms.triangulos.Triangulos;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalFormularios extends TabSheet {
	
	public PrincipalFormularios() {		
		
		addTab(pinValidador(), "Pin Validador");
		addTab(validar(), "Custom-Validation");
		addTab(myIconos(), "Formulario-iconos-runo");
		addTab(jodaTime() ,"Joda-time");
		addTab(getTriangulos(), "Validando Triangulos FIXME");
		addTab(getCalculador(), "Calulador +|-");
		
	}
	
	private Layout getCalculador() {
		Layout layout = getLayout();
		Calculador calc = new Calculador();
		layout.addComponent(calc);
		
		return layout;
	}
	
	public Layout getTriangulos() {
		Layout layout = getLayout();
		Triangulos triangulos = new Triangulos();
		layout.addComponent(triangulos);
		
		return layout;
	}
	
	public Layout jodaTime() {
		Layout layout = getLayout();
		JodaTime jodaTime = new JodaTime();
		layout.addComponent(jodaTime);
		
		return layout;
	}
	
	public Layout pinValidador() {
		Layout layout = getLayout();
		ValidarTextField texto = new ValidarTextField();
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

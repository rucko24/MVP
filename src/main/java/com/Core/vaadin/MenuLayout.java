package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;

public class MenuLayout extends Tree {
	
	private static final long serialVersionUID = 1L;
	public static final String LOGO = "ICONO";
	public static final String MENU = "Opciones";
	public static final String CONTENT = "Boton Switch";
	public static final String CONTENT2 = "Tablas";
	public static final String TREETABLA = "Tree-Tabla + Container";
	public static final String TREETABLA2 = "Tree-Tabla-Familiar";
	public static final String BARRA = "Progress-Indicator";
	public static final String ICONO = "Formulario + Iconos";
	public static final String ICONO2 = "Formulario + Iconos 2";
	public static final String DOWNLOADER = "Downloader";
	public static final String CLICKENTABLE = "Click en tabla";
	public static final String TIPOSDETEMAS = "DashBoard";
	public static final String TRIANGULO = "Triángulos";
	public static final String CALCULADOR = "Calculador Básico";
	public static final String VALIDAR = "PIN VALIDADO";
	public static final String PUSH_SERVER = "PUSH SERVER";
	public static final String PRUEBA_LABEL = "Label Push";
	
	private ThemeResource icono3 = new ThemeResource("img/isosceles");
	private Label label = new Label();
	
	public MenuLayout() {
		setSizeFull();
		
		label.setIcon(icono3);
		
		addStyleName("menu");
	
		addItem(MENU);
		addItem(TREETABLA);
		addItem(TREETABLA2);
		addItem(BARRA);
		addItem(ICONO);
		addItem(ICONO2);
		addItem(DOWNLOADER);
		addItem(CLICKENTABLE);
		addItem(TIPOSDETEMAS);
		addItem(TRIANGULO);
		addItem(CALCULADOR);
		addItem(VALIDAR);
		addItem(PUSH_SERVER);
		addItem(PRUEBA_LABEL);
		
		
		setChildrenAllowed(MENU, true);
		
		addItem(CONTENT);
		addItem(CONTENT2);
		
		setParent(CONTENT, MENU);
		setChildrenAllowed(CONTENT, false);
		
		setParent(CONTENT2, MENU);
		setChildrenAllowed(CONTENT2, false);
		
		setParent(TREETABLA, MENU);
		setChildrenAllowed(TREETABLA, true);
		
		setParent(TREETABLA2, TREETABLA);
		setChildrenAllowed(TREETABLA2, false);
		expandItemsRecursively(TREETABLA);
		
		setParent(BARRA,MENU);
		setChildrenAllowed(BARRA, false);
		
		setParent(ICONO,MENU);
		setChildrenAllowed(ICONO, false);
		
		setParent(ICONO2, MENU);
		setChildrenAllowed(ICONO2, false);
		
		setParent(DOWNLOADER, MENU);
		setChildrenAllowed(DOWNLOADER, false);
		
		setParent(CLICKENTABLE, MENU);
		setChildrenAllowed(CLICKENTABLE, false);
		
		//setParent(CALCULADOR, MENU);
		setChildrenAllowed(CALCULADOR, false);
		
		// TIPOSDETEMAS 
		setChildrenAllowed(TIPOSDETEMAS, true);
		
		setChildrenAllowed(TRIANGULO, true);
		
		setChildrenAllowed(VALIDAR, false);
		
		//SERVER PUSH
		setChildrenAllowed(PUSH_SERVER, true);
		
		setParent(PRUEBA_LABEL, PUSH_SERVER);
		setChildrenAllowed(PRUEBA_LABEL, false);
		
		expandItemsRecursively(MENU);
		setNullSelectionAllowed(false);
		
		
		OpcionesDeMenu opciones = new OpcionesDeMenu();
		addItemClickListener(opciones);
		
	}
}

package com.Core.vaadin.pageLayout.bodyLayout.menuLayout;

import com.Core.vaadin.Core;
import com.Core.vaadin.pageLayout.bodyLayout.contentLayout.ContentLayout;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;


public class MenuLayout extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	public static final String LOGO = "ICONO";
	public static final String HOME = "HOME";
	public static final String ARDUINO = "ARDUINO";
	public static final String CONTENT2 = "Tablas";
	public static final String TREETABLA = "Tree-Tabla + Container";
	public static final String TREETABLA2 = "Tree-Tabla-Familiar";
	public static final String BARRA = "Progress-Indicator";
	public static final String ICONO2 = "TabSheets";
	public static final String DOWNLOADER = "Downloader";
	public static final String CLICKENTABLE = "Click en tabla";
	public static final String TIPOSDETEMAS = "DashBoard";
	public static final String TRIANGULO = "Triángulos";
	public static final String CALCULADOR = "Calculador Básico";
	public static final String WORKINGWITHFORM = "Workin with forms";
	public static final String PUSH_SERVER = "PUSH SERVER";
	public static final String PRUEBA_LABEL = "Label Push";
	public static final String PRUEBA_LABEL2 = "Label Push";
	public static final String PRUEBA_LABEL3 = "Label Push";
	public static final String PRUEBA_LABEL4 = "Label Push";
	public static final String LISTADO = "Listado";
	
	public static final String ITEMS[] = {"PRUEBA_LABEL5","PRUEBA_LABEL6","PRUEBA_LABEL7"};
	
	private Label label = new Label("شروع جدید",ContentMode.HTML);
	private ThemeResource iconoArdu = new ThemeResource("img/ardu1.svg");
	private Tree tree = new Tree();
	private VerticalLayout vLayout = new VerticalLayout();
	
	public MenuLayout() {
		
		
		label.setSizeUndefined();
		label.addStyleName("labelMenu");
		label.setIcon(iconoArdu);
		vLayout.setMargin(true);
		vLayout.addComponent(label);
		vLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		
		
		
		tree.setWidth("100%");
		tree.addStyleName("arbol"); //estilo de arbol, background caption selected
		tree.addItem(HOME);
		tree.addItem(TREETABLA);
		tree.addItem(TREETABLA2);
		tree.addItem(BARRA);
		tree.addItem(ICONO2);
		tree.addItem(DOWNLOADER);
		tree.addItem(CLICKENTABLE);
		tree.addItem(TIPOSDETEMAS);
		tree.addItem(TRIANGULO);
		tree.addItem(CALCULADOR);
		tree.addItem(WORKINGWITHFORM);
		tree.addItem(PUSH_SERVER);
		tree.addItem(PRUEBA_LABEL);
		tree.addItem(ARDUINO);
		tree.addItem(CONTENT2);
		tree.addItem(LISTADO);
		
		tree.setChildrenAllowed(HOME, false);
		tree.setValue(HOME);
		//setParent(CALCULADOR, MENU);
		tree.setChildrenAllowed(CALCULADOR, false);
		
		tree.setChildrenAllowed(TRIANGULO, true);
		
		tree.setChildrenAllowed(WORKINGWITHFORM, false);
		
		//SERVER PUSH
		tree.setChildrenAllowed(PUSH_SERVER, true);
		
		tree.setParent(PRUEBA_LABEL, PUSH_SERVER);
		tree.setParent(TREETABLA2, PUSH_SERVER);
		tree.setParent(CALCULADOR, PUSH_SERVER);
		tree.setChildrenAllowed(PRUEBA_LABEL, false);
		
		tree.setNullSelectionAllowed(false);		
		
		OpcionesDeMenu opciones = new OpcionesDeMenu();
		tree.addItemClickListener(opciones);
		
		this.addComponents(vLayout,tree);
		//this.setExpandRatio(tree, 1);
	}
}

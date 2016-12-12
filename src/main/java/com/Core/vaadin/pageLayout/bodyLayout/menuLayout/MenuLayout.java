package com.Core.vaadin.pageLayout.bodyLayout.menuLayout;

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
	public static final String SERIALPORT = "Serial Port";
	public static final String TABSHEET = "TabSheets";
	public static final String FORMULARIOS = "Formularios";
	public static final String PUSH_SERVER = "Push";
	public static final String PRUEBA_LABEL = "Label Push";
	public static final String TABLAS = "Tablas";
	public static final String TETRIS = "Tetris";
	
	private Label label = new Label("MENU",ContentMode.HTML);
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
		tree.addItem(SERIALPORT);
		tree.addItem(TABSHEET);
		tree.addItem(FORMULARIOS);
		tree.addItem(PUSH_SERVER);
		tree.addItem(PRUEBA_LABEL);
		tree.addItem(TABLAS);
		tree.addItem(TETRIS);
	
		tree.setChildrenAllowed(HOME, false);
		tree.setValue(HOME);
		
		tree.setChildrenAllowed(FORMULARIOS, true);
		
		//SERVER PUSH
		tree.setChildrenAllowed(PUSH_SERVER, true);
		
		tree.setParent(PRUEBA_LABEL, PUSH_SERVER);
		tree.setChildrenAllowed(PRUEBA_LABEL, false);
	
		
		tree.setNullSelectionAllowed(false);		
		
		final OpcionesDeMenu opciones = new OpcionesDeMenu();
		tree.addItemClickListener(opciones);
		
		this.addComponents(vLayout,tree);
		//this.setExpandRatio(tree, 1);
	}
}

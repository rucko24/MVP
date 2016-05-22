package com.Core.vaadin;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;


public class MenuLayout extends VerticalLayout {
	
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
	public static final String PRUEBA_LABEL2 = "Label Push";
	public static final String PRUEBA_LABEL3 = "Label Push";
	public static final String PRUEBA_LABEL4 = "Label Push";

	
	private Label label = new Label("شروع جدید",ContentMode.HTML);
	private ThemeResource iconoArdu = new ThemeResource("img/ardu1.svg");
	private Tree tree = new Tree();
	private VerticalLayout vLayout = new VerticalLayout();
	
	public MenuLayout() {
		
		this.setSizeFull();
		//this.addStyleName("menu");
		
		label.setSizeUndefined();
		label.addStyleName("labelMenu");
		label.setIcon(iconoArdu);
		vLayout.setMargin(true);
		vLayout.addComponent(label);
		vLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		
		//tree.setWidth("100%");
		tree.addStyleName("arbol"); //estilo de arbol, background caption selected
		tree.addItem(MENU);
		tree.addItem(TREETABLA);
		tree.addItem(TREETABLA2);
		tree.addItem(BARRA);
		tree.addItem(ICONO);
		tree.addItem(ICONO2);
		tree.addItem(DOWNLOADER);
		tree.addItem(CLICKENTABLE);
		tree.addItem(TIPOSDETEMAS);
		tree.addItem(TRIANGULO);
		tree.addItem(CALCULADOR);
		tree.addItem(VALIDAR);
		tree.addItem(PUSH_SERVER);
		tree.addItem(PRUEBA_LABEL);
		
		String labels[] = {PRUEBA_LABEL2,PRUEBA_LABEL3,PRUEBA_LABEL4};
		
		for(int f=1; f<=labels.length; f++) {
			tree.addItem(f);
		}
		
		tree.setChildrenAllowed(MENU, true);
		
		tree.addItem(CONTENT);
		tree.addItem(CONTENT2);
		
		tree.setParent(CONTENT, MENU);
		tree.setChildrenAllowed(CONTENT, false);
		
		tree.setParent(CONTENT2, MENU);
		tree.setChildrenAllowed(CONTENT2, false);
	
		
		tree.setParent(BARRA,MENU);
		tree.setChildrenAllowed(BARRA, false);
		
		tree.setParent(ICONO,MENU);
		tree.setChildrenAllowed(ICONO, false);
		
		tree.setParent(ICONO2, MENU);
		tree.setChildrenAllowed(ICONO2, false);
		
		tree.setParent(DOWNLOADER, MENU);
		tree.setChildrenAllowed(DOWNLOADER, false);
		
		tree.setParent(CLICKENTABLE, MENU);
		tree.setChildrenAllowed(CLICKENTABLE, false);
		
		//setParent(CALCULADOR, MENU);
		tree.setChildrenAllowed(CALCULADOR, false);
		
		tree.setChildrenAllowed(TRIANGULO, true);
		
		tree.setChildrenAllowed(VALIDAR, false);
		
		//SERVER PUSH
		tree.setChildrenAllowed(PUSH_SERVER, true);
		
		tree.setParent(PRUEBA_LABEL, PUSH_SERVER);
		tree.setParent(TREETABLA2, PUSH_SERVER);
		tree.setParent(CALCULADOR, PUSH_SERVER);
		tree.setChildrenAllowed(PRUEBA_LABEL, false);
		
		tree.expandItemsRecursively(MENU);
		tree.setNullSelectionAllowed(false);
		
		
		OpcionesDeMenu opciones = new OpcionesDeMenu();
		tree.addItemClickListener(opciones);
		
		this.addComponents(vLayout,tree);
		this.setExpandRatio(tree, 1);
	}
}

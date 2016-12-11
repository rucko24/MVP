package com.Core.vaadin.tables;

import com.Core.vaadin.tabSheet.EjemploTema;
import com.Core.vaadin.tables.listado.Listado;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTablas extends TabSheet {
	
	private static final long serialVersionUID = 1L;
	
	public PrincipalTablas() {
		
		addTab(getTiposDeTemas());
		addTab(getFieldFactory());
		addTab(getTreeTabla());
		addTab(getTreeTabla2());
		addTab(getContextMenu());
		addTab(getListadoGridCustomers());
	}
	public Component getListadoGridCustomers() {
		VerticalLayout layout = (VerticalLayout) getLayout("Add-Customers");
		Listado listado = new Listado();
		layout.addComponent(listado);
		
		return layout;
	}
	public Component getContextMenu() {
		VerticalLayout layout = (VerticalLayout) getLayout("Tabla con for");
		ContextMenu clickTable = new ContextMenu();
		layout.addComponent(clickTable);
		
		return layout;
	}
	public Component getTreeTabla2() {
		VerticalLayout layout = (VerticalLayout) getLayout("Tree-Tabla 2");
		TreeTabla2 tabla = new TreeTabla2();
		layout.addComponent(tabla);
		
		return layout;
	}
	public Component getTreeTabla() {
		VerticalLayout layout = (VerticalLayout) getLayout("Tree-Tabla");
		TreeTabla tabla = new TreeTabla();
		layout.addComponent(tabla);
		
		return layout;
	}

	public Component getFieldFactory() {
		VerticalLayout layout = (VerticalLayout) getLayout("FieldFactory");
		FieldFactory factory = new FieldFactory();
		layout.addComponent(factory);
		
		return layout;
	}
	
	public Component getTiposDeTemas() {
		VerticalLayout layout = (VerticalLayout) getLayout("Dash-Board");
		EjemploTema  tema = new EjemploTema();
		layout.addComponent(tema);
		
		return layout;
	}
	
	public Component getLayout( String caption) {
		VerticalLayout layout = new VerticalLayout();
		layout.setCaption(caption);
		layout.setSizeFull();
		
		return layout;
	}
}

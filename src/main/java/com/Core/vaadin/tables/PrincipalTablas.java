package com.Core.vaadin.tables;

import com.Core.vaadin.tables.listado.Listado;
import com.Core.vaadin.tables.todoList.TodoList;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTablas extends TabSheet {
	
	private static final long serialVersionUID = 1L;
	
	public PrincipalTablas() {
		setSizeFull();
		setImmediate(true);
		
		Component dash = getTiposDeTemas();
		addTab(dash);
		setSelectedTab(dash);
		
		addTab(getFieldFactory(),"FieldFactory");
		addTab(getTreeTabla(),"Tree-Tabla");
		addTab(getTreeTabla2(),"Tree-Tabla 2");
		addTab(getContextMenu());
		addTab(getListadoGridCustomers());
		
		Component todo  = getTodoList();
		addTab(todo);
		
		
		setSelectedTab(todo);
	}
	
	public Component getTodoList() {
		VerticalLayout layout = (VerticalLayout)getLayout("TodoList");
		layout.setSizeFull();
		TodoList todo = new TodoList();
		layout.addComponent(todo);
		
		return layout;
	}
	
	public Component getListadoGridCustomers() {
		VerticalLayout layout = (VerticalLayout) getLayout("Add-Customers");
		Listado listado = new Listado();
		layout.setSizeFull();
		layout.addComponent(listado);
		
		return layout;
	}
	public Component getContextMenu() {
		VerticalLayout layout = (VerticalLayout) getLayout("For-Click-Tabla");
		ContextMenu clickTable = new ContextMenu();
		layout.setSizeFull();
		layout.addComponent(clickTable);
		
		
		return layout;
	}
	public Component getTreeTabla2() {
		TreeTabla2 tabla = new TreeTabla2();
		tabla.setSizeFull();
		
		return tabla;
	}
	public Component getTreeTabla() {
		TreeTabla tabla = new TreeTabla();
		tabla.setSizeFull();
		
		return tabla;
	}

	public VerticalLayout getFieldFactory() {
		FieldFactory factory = new FieldFactory();
		factory.setSizeFull();
		
		
		return factory;
	}
	
	public Component getTiposDeTemas() {
		VerticalLayout layout = (VerticalLayout) getLayout("Dash-Board");
		EjemploTema  tema = new EjemploTema();
		layout.setSizeFull();
		layout.addComponent(tema);

		
		
		return layout;
	}
	public Component getLayout(String caption) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setCaption(caption);
		layout.setMargin(true);
		layout.setSpacing(true);
		
		return layout;
	}
	
}

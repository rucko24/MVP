package com.Core.vaadin.tabSheet;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class EjemploTema extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	public EjemploTema() {
		
		setMargin(true);
		setSpacing(true);
		
		Component header = getHeader();
		
		Component main = getMainArea();
		
		addComponents(header,main);
		setExpandRatio(main, 1);
	}
	
	private Component getHeader() {
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		Label label = new Label("<h1>DashBoard</h1>",ContentMode.HTML);
		
		layout.addComponent(label);
		
		return layout;
	}
	
	private Component getMainArea() {
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		//layout.setHeight("100%");
		layout.setSpacing(true);
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSpacing(true);
		vLayout.setWidth("120px");
		
		Button btn1 = new Button("DashBoard");
		btn1.setWidth("100%");
		
		Button btn2 = new Button("Services");
		btn1.setWidth("100%");
		
		Button btn3 = new Button("Reports");
		btn1.setWidth("100%");
			
		vLayout.addComponents(btn1,btn2,btn3);
	
		layout.addComponent(vLayout);
		
		Component area = getArea2();
		layout.addComponent(area);
		layout.setExpandRatio(area, 1);
		
		return layout;
		
	}
	
	private Component getArea2() {
		
		GridLayout layout = new GridLayout();
		layout.setRows(3);
		layout.setColumns(2);
		layout.setSpacing(true);
		layout.setRowExpandRatio(2, 1);
		
		layout.addStyleName("borde");
		
		TextField txt = new TextField();
		txt.setInputPrompt("Search");
		txt.setSizeFull();
		layout.addComponent(txt, 0, 0);
		
		Table table = new Table();
		table.setSelectable(true);
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Apellido", String.class, null);
		table.addContainerProperty("Deuda", Double.class, null);
		
		String nombres[] = {"Ruben","Maria","Pedra","Jose","Raul"};
		String apellidos[] = {"Espinoza","Peres","Garcia","Gonzales","Parra"};
		double deuda[] = { 50.2 , 100.85 , 342.3 , 234.55 , 999.99};
		
		for( int f=0; f<5; f++) {
			
			table.addItem( new Object[] {nombres[f] , apellidos[f] , deuda[f] } , new Integer(f));
		}
		
		table.setPageLength(table.size());
		layout.addComponent(table, 0 , 1);
		
		MenuBar menu = new MenuBar();
		menu.addItem("Opcion 1", null);
		menu.addItem("Opcion 2", null);
		menu.addItem("Opcion 3", null);
		layout.addComponent(menu, 1 , 1);
		
		Panel panel = new Panel();
		//panel.setWidth("100%");
		//panel.setHeight("100%");
		layout.addComponent(panel, 1 ,2);
		
		return layout;
	}
	
}

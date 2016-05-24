package com.Core.vaadin.tabSheet;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class EjemploTema extends VerticalLayout {
	
	public EjemploTema() {
		
		setWidth("820px");
		setHeight("610px");
		setSpacing(true);
		setMargin(true);
		
		Component header = getHeader();
		
		Component main = getMain();
		
		addComponents(header,main);
		setExpandRatio(main,1);
	}
		
	private Component getHeader() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setCaption("Header");
		layout.setHeight("50px");
		layout.setWidth("50px");
		
		Label label = new Label("<h1><strong><em>DashBoard</em></strong></h1>",ContentMode.HTML);
		
		
		layout.addComponent(label);
		return layout;
	}
	
	private Component getMain() {
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setCaption("VerticalLayout");
		vLayout.setSpacing(true);
		vLayout.setWidth("130px");
		
		Button btn1 = new Button("Servicios");
		btn1.setWidth("110%");
		
		Button btn2 = new Button("Registros");
		btn2.setWidth("110%");
		
		Button btn3 = new Button("DashBoard");
		btn3.setWidth(110, Unit.PERCENTAGE);
		vLayout.addComponents(btn1,btn2,btn3);
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setCaption("horizontalLAyout = verticalLayout + grid");
		hLayout.setSizeFull();
		hLayout.setSpacing(true);
		
		Component area = getContentArea();
		
		hLayout.addComponents(vLayout,area);
		hLayout.setExpandRatio(area, 1);
		
		return hLayout;
	}
	
	private Component getContentArea() {
		
		GridLayout grid = new GridLayout(2,3);
		grid.setSizeFull();
		grid.setSpacing(true);
		
		grid.setRowExpandRatio(1, 1);
		
		TextField txt = new TextField("Busqueda");
		grid.addComponent(txt, 0, 0);
		
		Table table = new Table();
		table.setWidth("100%");
		table.setHeight("100%");
		table.setSelectable(true);
		table.addContainerProperty("Nombre", String.class, null);
		table.addContainerProperty("Monto", Double.class, null);
		
		Object persona[][] = {{"Maria",500.5},
							  {"Pedro",998.5},
							  {"Rodolfa",5789.5}};
		
		for( int f=0; f<persona.length; f++) {
			table.addItem(persona[f] , new Integer(f));
		}
		
		grid.addComponent(table,0,1);
		
		return grid;
	}
	
}
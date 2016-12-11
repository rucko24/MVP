package com.Core.vaadin.tables;

import java.text.DecimalFormat;

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
import com.vaadin.ui.themes.ValoTheme;

public class EjemploTema extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	public EjemploTema() {
		setSizeFull();
		setMargin(true);
		
		Component header = getHeader();
		
		Component main = getMainArea();
		
		addComponents(header,main);
		setExpandRatio(main, 1);
	}
	
	private Component getHeader() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		
		Label label = new Label("DashBoard");
		label.addStyleName(ValoTheme.LABEL_COLORED);
		label.addStyleName(ValoTheme.LABEL_H1);
		layout.addComponent(label);
		
		return layout;
	}
	
	private Component getMainArea() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setSizeFull();
	
		
		Component menu = getMenuArea();
		Component content = getContentArea();
		
		layout.addComponents(menu,content);
		layout.setExpandRatio(content, 1);
		
		return layout;
	}
	
	private Component getMenuArea() {
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSpacing(true);
		vLayout.setWidth("145px");
		
		Button btn1 = new Button("Dashboard");
		btn1.setWidth("100%");
		
		Button btn2 = new Button("Servicios");
		btn2.setWidth("100%");
		
		Button btn3 = new Button("Reportes");
		btn3.setWidth("100%");
		
		vLayout.addComponents(btn1,btn2,btn3);
		return vLayout;
		
	}
	
	private Component getContentArea() {
		
		GridLayout grid = new GridLayout(2,3);
		grid.setSizeFull();
		grid.setSpacing(true);
		grid.setRowExpandRatio(1, 1);
		
		grid.addStyleName("borde");
		
		TextField txt = new TextField();
		txt.setInputPrompt("Search");
		txt.setWidth("100%");
		grid.addComponent(txt, 0, 0);
		
		Table table = new Table();
		table.setSizeFull();
		table.setPageLength(table.size());
		table.setSelectable(true);
		table.setImmediate(true);
		table.setFooterVisible(true);
		
		table.addContainerProperty("Nombres", String.class, "");
		table.addContainerProperty("Apellidos", String.class, "");
		table.addContainerProperty("Deudas", Double.class, null);
		
		String nombres[] = {"Ruben","Maria","Pedra","Jose","Raul"};
		String apellidos[] = {"Espinoza","Peres","Garcia","Gonzales","Parra"};
		double deuda[] = { 50.2 , 100.85 , 342.3 , 234.55 , 999.99};
		
		for( int f=0; f<deuda.length; f++) {
			
			table.addItem( new Object[] {nombres[f] , apellidos[f] , deuda[f] } , new Integer(f));
		}
		
		double suma = 0;
		for( int f=0; f<deuda.length; f++) {
			
			suma += deuda[f];
		
		}
		
		double promedio = (double) suma / deuda.length;
		
		DecimalFormat dd = new DecimalFormat("0.00");
		table.setColumnFooter("Nombres",String.valueOf(suma));
		table.setColumnFooter("Deudas", String.valueOf(dd.format(promedio)));
		
		grid.addComponent(table, 0 , 1);
		
		MenuBar menu = new MenuBar();
		menu.setWidth("100%");
		menu.addItem("Opcion 1", null);
		menu.addItem("Opcion 2", null);
		menu.addItem("Opcion 3", null);
		grid.addComponent(menu, 1 , 0);
		
		Panel panel = new Panel(new Label("</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>",ContentMode.HTML));
		panel.setHeight("100%");
	
		
	
		grid.addComponent(panel, 1,1);
		
		return grid;
	}
	
}

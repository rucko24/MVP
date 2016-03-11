package com.Core.vaadin.barraCarga;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class EjemploTema extends VerticalLayout {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		public EjemploTema() {
			
			setMargin(true);
			setSizeFull();
			
			Component header = getHeader();
			addComponent(header);
			
			Component contentArea = getContentArea();
			addComponent(contentArea);
			setExpandRatio(contentArea, 1);
			
		}
		
		public Component getHeader() {
			
			VerticalLayout header = new VerticalLayout();
			header.setWidth("50%");
			
			Label titulo = new Label("My Vaadin Application");
			header.addComponent(titulo);
			
			return header;
		}
		
		public Component getContentArea() {
		
			HorizontalLayout content = new HorizontalLayout();
			content.setWidth("50%");
			
			Component menu = getMenu();
			content.addComponent(menu);
			
			Component mainArea = getMainArea();
			content.addComponent(mainArea);
			content.setExpandRatio(mainArea, 1);
			
			return content;
					
		}
		
		public Component getMenu() {
			
			VerticalLayout menu = new VerticalLayout();
			menu.setSpacing(true);
			menu.setSizeFull();
		
			
			Button btn1 = new Button("Dasboard");
			btn1.setSizeUndefined();
			//btn1.setWidth("100%");
			menu.addComponent(btn1);
			
			Button btn2 = new Button("Services");
			//btn2.setWidth("100%");
			menu.addComponent(btn2);
			
			Button btn3 = new Button("Reports");
			//btn3.setWidth("100%");
			menu.addComponent(btn3);
			
			return menu;
			
		}
		
		public Component getMainArea() {
			
			GridLayout main = new GridLayout(2 , 3);
			main.setMargin(true);
			main.setSizeFull();
			main.setSpacing(true);
			main.setRowExpandRatio(1, 1);
			
			TextField textField = new TextField();
			textField.setInputPrompt("Search");
		//	textField.setWidth("100%");
			main.addComponent(textField, 0,0);
			
			MenuBar menuBar = new MenuBar();
			//menuBar.setWidth("100%");
			
			return main;
		}
}

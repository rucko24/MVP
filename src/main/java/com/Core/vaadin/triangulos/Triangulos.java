package com.Core.vaadin.triangulos;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Triangulos extends VerticalLayout {
	
	private Label label = new Label("<h1>Triangulos</h1>",ContentMode.HTML);
	private HorizontalLayout row = new HorizontalLayout();
	private Button btn = new Button("tipo de triangulo?");
	private Window win = new Window();
	
	private ThemeResource imgTriangulo = new ThemeResource("img/triangulos.gif");
	private Label labelTriangulo = new Label();
	
	
	public Triangulos() {
		
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		
		labelTriangulo.setIcon(imgTriangulo);
		
		row.setSpacing(true);
		row.addComponents(label,labelTriangulo);
		
		btn.addClickListener(e -> {
			
			MiVentana ventana = new MiVentana();
			
			UI.getCurrent().addWindow(ventana);
		});
		
		addComponents(row,btn);
		
	}
	
}

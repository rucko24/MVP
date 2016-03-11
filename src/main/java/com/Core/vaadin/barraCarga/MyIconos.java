package com.Core.vaadin.barraCarga;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MyIconos extends VerticalLayout {
	
	private TextField textField = new TextField("Email");
	private TextArea textArea = new TextArea("Details");
	private ComboBox combo = new ComboBox("Type");
	private OptionGroup opciones = new OptionGroup("Prioridad");
	private Button enviar = new Button("Enviar");
	
	private HorizontalLayout top = new HorizontalLayout();
	private HorizontalLayout bottom = new HorizontalLayout();
	
	public MyIconos() {
		
		setMargin(true);
		setSpacing(true);
		
		textField.setWidth("100%");
		textField.setIcon(new ThemeResource("../runo/icons/16/email.png"));
		
		combo.setWidth("100%");
		combo.setIcon(new ThemeResource("../runo/icons/16/note.png"));
		
		textArea.setWidth("50%");
		textArea.setIcon(new ThemeResource("../runo/icons/16/document.png"));
		
		
		top.setSpacing(true);
		top.setWidth("50%");
		top.addComponents(textField,combo);
		top.setComponentAlignment(combo, Alignment.BOTTOM_LEFT);
		
		opciones.setWidth("100%");
		opciones.addItem("Too low");
		opciones.addItem("Extremely high");
		opciones.setItemIcon("Too low",new ThemeResource("../runo/icons/16/attention.png"));
		opciones.setItemIcon("Extremely high",new ThemeResource("../runo/icons/16/error.png"));

	
		enviar.setIcon(new ThemeResource("../runo/icons/16/ok.png"));
		
		bottom.setSpacing(true);
		bottom.setWidth("50%");
		bottom.addComponents(opciones,enviar);
		bottom.setComponentAlignment(enviar, Alignment.BOTTOM_RIGHT);
		
		
		addComponents(top,textArea,bottom);
	}
}

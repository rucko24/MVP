package com.Core.vaadin;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Calculador extends VerticalLayout {
	
	private HorizontalLayout horizontalLayout = new HorizontalLayout();
	private TextField texto1 = new TextField("Numero 1: ");
	private ComboBox combo = new ComboBox("Operacion: ");
	
	private TextField texto2 = new TextField("Numero 2: ");
	private TextField texto3 = new TextField("Resultado: ");
	private Button btnCalcular = new Button("Calcular");
	
	public Calculador() {
		
		horizontalLayout.setMargin(true);
		horizontalLayout.setSpacing(true);
		
		texto1.setId("numero 1");
		texto1.setImmediate(true);
		texto1.setNullSettingAllowed(true);
		texto1.setNullRepresentation("");
		
		texto2.setId("numero 2");
		texto2.setImmediate(true);
		texto2.setNullSettingAllowed(true);
		texto2.setNullRepresentation("");
		
		texto3.setId("resultado");
		texto3.setImmediate(true);
		
		combo.setId("cmbOperador");
		combo.addItem("+");
		combo.addItem("-");
		
		combo.select("+");
		combo.setNullSelectionAllowed(false);
		
		btnCalcular.setId("calcular");
		btnCalcular.setClickShortcut(KeyCode.ENTER);
		
		btnCalcular.addClickListener(e -> {
			
			
			System.out.println("el usuario a hecho click");
			
			String numero1 = texto1.getValue();
			String operador = combo.getValue().toString();
			String numero2 = texto2.getValue();
			
			Integer resultado = 0;
			
			if("".equals(numero1) || "".equals(numero2)) {
				Notification.show("Valor incorrecto");
				
			}else {
				btnCalcular.setComponentError(null);
				
				Integer i1 = Integer.valueOf(numero1);
				Integer i2 = Integer.valueOf(numero2);
				
				if("+".equals(operador)) {
					resultado = i1 + i2;
				}else {
					resultado = i1 - i2;
				}
			}
			texto3.setValue(resultado.toString());
			
		});
		
		
		
		horizontalLayout.addComponents(texto1,combo,texto2,texto3,btnCalcular);
		
		
		horizontalLayout.setComponentAlignment(btnCalcular, Alignment.BOTTOM_RIGHT);
		addComponent(horizontalLayout);
		
	}
	
}

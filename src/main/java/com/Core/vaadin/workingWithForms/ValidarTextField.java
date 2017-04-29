package com.Core.vaadin.workingWithForms;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ValidarTextField extends VerticalLayout {
	
	private TextField texto = new TextField("Enter pin code");
	private Button btn = new Button("validate");
	
	public ValidarTextField() {
		setSizeUndefined();
		setMargin(true);
		setSpacing(true);
		
		texto.focus();
		texto.setNullRepresentation("");
		texto.setNullSettingAllowed(false);
		texto.setValidationVisible(false);
		texto.addValidator(new PinValidador());
		texto.setImmediate(true);
		
		btn.setClickShortcut(KeyCode.ENTER);
		btn.addClickListener( e -> {
			
			try {
				texto.validate();
				Notification.show("Correcto");
			}catch(InvalidValueException ex) {
				texto.setValidationVisible(true);
				Notification.show(ex.getMessage());
				texto.focus();
				//texto.clear();
			}
			
		});
		
		addComponents(texto,btn);
	}
}

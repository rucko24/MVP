package com.Core.vaadin.validadores;

import com.vaadin.data.Validator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;

public class CustomValidation extends FormLayout {
	
	private PasswordField newPassWordField = new PasswordField("new Password:");
	private PasswordField confirmPasswordField = new PasswordField("Confirm new password");
	private Button okButton = new Button("OK");
	private static final String CONFIRM_MESSAGE = "Password are the same";
	private static final String ERROR_MESSAGE = "Password must match";

	public CustomValidation() {
		setSizeUndefined();
		setMargin(true);
		
		confirmPasswordField.addValidator(new Validator() {
				
			@Override
			public void validate(Object value) throws InvalidValueException {
				String pass = (String) value;
				if(!pass.equals(newPassWordField.getValue())) {
					throw new InvalidValueException(ERROR_MESSAGE);
				}
			}
			
		});
		
		okButton.addClickListener( e -> {
			
			try {
				
				
				confirmPasswordField.validate();
				Notification.show(CONFIRM_MESSAGE);
			}catch(Exception ee) {
				Notification.show(ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);;
			}
			
		});
		
		addComponents(newPassWordField, confirmPasswordField, okButton);
	}
	
}

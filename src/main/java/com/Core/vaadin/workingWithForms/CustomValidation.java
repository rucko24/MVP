package com.Core.vaadin.workingWithForms;

import com.vaadin.data.Validator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class CustomValidation extends FormLayout {
	
	private TextField newPassWordField = new TextField("Nombre de Usuario:");
	private PasswordField confirmPasswordField = new PasswordField("Confirm password");
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
		
		
		okButton.setClickShortcut(KeyCode.ENTER);
		okButton.addClickListener( e -> {
			try {
				
				confirmPasswordField.validate();
				notificacion(CONFIRM_MESSAGE, Type.TRAY_NOTIFICATION);
				
			}catch(Exception ee) {
				notificacion(ERROR_MESSAGE, Type.WARNING_MESSAGE);
				confirmPasswordField.focus();
			}
		});
		
		addComponents(newPassWordField, confirmPasswordField, okButton);
	}
	
	public Notification notificacion( String mensaje , Type error) {
		Notification n = new Notification(mensaje , error);
		n.show(Page.getCurrent());
		n.setPosition(Position.BOTTOM_RIGHT);
		return n;
		
	}
	
}

package com.Core.vaadin;

import com.Core.vaadin.tables.MiVentana;
import com.vaadin.data.Validator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class PinValidador implements Validator {


	private static final long serialVersionUID = 1L;
	
	@Override
	public void validate(Object value) throws InvalidValueException {
		
		String text = (String) value;
		
		if( text.isEmpty() && "".equals(text.trim())) {
			
			return ;
		}
		
		if(  !text.matches("\\d*")) {
			
			throw new InvalidValueException("introduce solo numeros");
		}
		
	}

}

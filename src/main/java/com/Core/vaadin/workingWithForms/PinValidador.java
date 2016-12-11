package com.Core.vaadin.workingWithForms;

import com.vaadin.data.Validator;
import com.vaadin.ui.TabSheet;

public class PinValidador implements Validator {


	private static final long serialVersionUID = 1L;
	
	@Override
	public void validate(Object value) throws InvalidValueException {
		
		String text = (String) value;
		
		if( text == null || "".equals(text.trim())) {
			
			return ;
		}
		
		if(  !text.matches("\\d*")) {
			
			throw new InvalidValueException("Introduce solo numeros");
		}
		if( text.length() < 0) {
			throw new InvalidValueException("Introduce un numero");
		}
	}
	
	

}

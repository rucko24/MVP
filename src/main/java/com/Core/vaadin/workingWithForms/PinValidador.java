package com.Core.vaadin.workingWithForms;

import com.vaadin.data.Validator;
import com.vaadin.ui.TabSheet;

public class PinValidador implements Validator {


	private static final long serialVersionUID = 1L;
	private TabSheet tab = new TabSheet();
	
	@Override
	public void validate(Object value) throws InvalidValueException {
		
		String text = (String) value;
		
		if( text == null || "".equals(text.trim())) {
			
			return ;
		}
		
		if(  !text.matches("\\d*")) {
			
			throw new InvalidValueException("introduce solo numeros");
		}
		if( text.length() < 0) {
			throw new InvalidValueException("introduce un numero");
		}
	}
	
	

}

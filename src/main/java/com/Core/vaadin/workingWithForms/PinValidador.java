package com.Core.vaadin.workingWithForms;

import com.vaadin.data.Validator;
import com.vaadin.ui.TabSheet;

public class PinValidador implements Validator {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(Object value) throws InvalidValueException {

		String t = (String) value;
		String text = t.trim();
		
		if (text == null || "".equals(text.trim())) {

			return;
		}

		if (!text.matches("[a-zA-Z-0-9]*")) {

			throw new InvalidValueException("CVE : "+text+" NOT FOUND");
		}
		
	}

}

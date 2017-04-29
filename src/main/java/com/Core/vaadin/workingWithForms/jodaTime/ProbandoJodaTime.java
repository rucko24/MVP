package com.Core.vaadin.workingWithForms.jodaTime;

import org.joda.time.DateTime;

import com.vaadin.ui.VerticalLayout;

public class ProbandoJodaTime {

	private String text;
	private DateTime create;

	public ProbandoJodaTime() {

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DateTime getCreate() {
		return create;
	}

	public void setCreate(DateTime create) {
		this.create = create;
	}

}

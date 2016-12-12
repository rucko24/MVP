package com.Core.vaadin.testSistema;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Core.vaadin.Core;
import com.Core.vaadin.Login;
import com.vaadin.data.Validator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SingUpForm extends VerticalLayout implements View {
	
	public static final String SINGUPFORM = "singupform";
	private Label labelTitulo = new Label("Formulario de ingresp");
	private Label labelHeader = new Label("Informacion Personal");
	private Button buttonConfirmar = new Button("Aceptar");
	private Button buttonCancel = new Button("Cancelar");
	private TextField textFieldNombre = new TextField("Nombre");
	private TextField textFieldID = new TextField("Tx-Id");
	private PasswordField passwordField = new PasswordField("password");
	private DateField dateField = new DateField("Fecha de nacimiento");
	private NativeSelect combo = new NativeSelect("Typo");
	private FormLayout formLayout = new FormLayout();
	private OptionGroup optionGroup = new OptionGroup("Sexo");
	private Core ui = Core.getCurrent();
	
	public SingUpForm()  {
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		
		//ui components
		labelTitulo.addStyleName(ValoTheme.LABEL_H1);
		labelTitulo.addStyleName(ValoTheme.LABEL_BOLD);
		addComponent(labelTitulo);
		
		//Set de form
		formLayout.setWidth("700px");
		formLayout.addStyleName(ValoTheme.LABEL_LIGHT);
		formLayout.addComponents();
		addComponent(formLayout);
		
		labelHeader.addStyleName(ValoTheme.LABEL_H2);
		labelHeader.addStyleName(ValoTheme.LABEL_COLORED);
		formLayout.addComponent(labelHeader);
		
		textFieldID.setRequired(true);
		textFieldID.setValidationVisible(false);
		formLayout.addComponent(textFieldID);
		
		textFieldNombre.setRequired(true);
		textFieldNombre.setValidationVisible(false);
		formLayout.addComponent(textFieldNombre);
		
		passwordField.setRequired(true);
		passwordField.setValidationVisible(false);
		formLayout.addComponent(passwordField);
		
		dateField.setDateFormat("dd-MM-yyyy");
		dateField.setValue(new Date());
		formLayout.addComponent(dateField);
		
		optionGroup.setRequired(true);
		optionGroup.setImmediate(true);
		optionGroup.addItem("Masculino");
		optionGroup.addItem("Femenino");
		optionGroup.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		formLayout.addComponent(optionGroup);
		
		List<String> tipoLista = new ArrayList<String>();
		tipoLista.add("Staff");
		tipoLista.add("Estudiante");
		
		combo.setImmediate(true);
		combo.addItems(tipoLista);
		combo.setRequired(true);
		formLayout.addComponent(combo);
		
		buttonConfirmar.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonConfirmar.addClickListener( e -> aceptar());
		
		buttonCancel.addStyleName(ValoTheme.BUTTON_DANGER);
		buttonCancel.addClickListener( e -> cancelar());
		
		HorizontalLayout row = new HorizontalLayout(buttonConfirmar,buttonCancel);
		row.setSpacing(true);
		row.setMargin(true);
		row.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		formLayout.addComponent(row);
		
		setExpandRatio(formLayout, 1);
	}

	private void aceptar()  {
		ui.getNavigator().navigateTo(Login.LOGIN_VIEW);
	
		Notification.show("Registro Correcto",Type.ASSISTIVE_NOTIFICATION);
	}
	private void cancelar() {
		ui.getNavigator().navigateTo(Login.LOGIN_VIEW);
		
		Notification.show("Registro FALLIDO",Type.ASSISTIVE_NOTIFICATION);
	}
	@Override
	public void enter(ViewChangeEvent event) {
		Core.getCurrent().getPage().setTitle("Sign up");
		
	}
}

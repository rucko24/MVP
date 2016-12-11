package com.Core.vaadin.workingWithForms.triangulos;


import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class MiVentana extends Window {
	
	private HorizontalLayout row = new HorizontalLayout();
	private Label label = new Label("<strong>Ingrese valor de cada lado</strong>",ContentMode.HTML);
	
	private TextField txt1 = new TextField("lado 1");
	private TextField txt2 = new TextField("lado 2");
	private TextField txt3 = new TextField("lado 3");
	
	private Button procesar = new Button("procesar");
	private ThemeResource equilatero = new ThemeResource("img/equilatero.png");
	private ThemeResource isosceles = new ThemeResource("img/isosceles.png");
	private ThemeResource escaleno = new ThemeResource("img/escaleno2.png");
	private Notification notificacion = new Notification("");
	
	public MiVentana() {
		
		setModal(true);
		setWidth("315px");
		setHeight("400px");
		setResizable(false);
		setDraggable(true);
		center();
		
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);
		
		VerticalLayout centrar = new VerticalLayout();
		centrar.setMargin(true);
		centrar.setSpacing(true);
		
		label.addStyleName("colored");
		
		txt1.focus();
		txt1.setImmediate(true);
		txt1.setNullSettingAllowed(true);
		txt1.setNullRepresentation("");
	
		
		txt2.setImmediate(true);
		txt2.setNullSettingAllowed(true);
		txt2.setNullRepresentation("");
		
		
		txt3.setImmediate(true);
		txt3.setNullSettingAllowed(true);
		txt3.setNullRepresentation("");
		
		
		procesar.setClickShortcut(KeyCode.ENTER);
		procesar.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		procesar.addClickListener(e -> {
			
			//setValidacionVisible();
			
			try {
				txt1.validate();
				
			}catch(InvalidValueException ee) {
				Notification.show(ee.getMessage(), Type.ERROR_MESSAGE);
				txt1.setValidationVisible(true);
				
			}
		});
		
		
		Button borrar = new Button("Borrar");
		borrar.setClickShortcut(KeyCode.DELETE);
		borrar.addClickListener(e -> {
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt1.focus();
		});
		
		row.setSpacing(true);
		row.addComponents(procesar, borrar);
		
		centrar.addComponents(label,txt1,txt2,txt3,row);
		
		content.addComponents(centrar);
		content.setComponentAlignment(centrar, Alignment.MIDDLE_CENTER);
		
		setContent(content);
	}
	
	
	public Notification notificacion(String descripcion, ThemeResource icono) {
		
		notificacion.setPosition(Position.BOTTOM_RIGHT);
		notificacion.setDelayMsec(1500);
		notificacion.setDescription(descripcion);
		notificacion.setIcon(icono);
		notificacion.show(Page.getCurrent());
		
		return notificacion;
	}
	
	public void setValidacionVisible() {
		txt1.setValidationVisible(false);
		txt2.setValidationVisible(false);
		txt3.setValidationVisible(false);
	}
	
}

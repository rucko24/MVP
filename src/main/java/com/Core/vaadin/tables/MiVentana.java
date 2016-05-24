package com.Core.vaadin.tables;

import com.Core.vaadin.validadores.PinValidador;
import com.vaadin.client.ui.Icon;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;

public class MiVentana extends Window {
	
	private HorizontalLayout row = new HorizontalLayout();
	private Label label = new Label("<strong>Ingrese valor de cada lado</strong>",ContentMode.HTML);
	private TextField txt1 = new TextField("lado 1");
	

	private TextField txt2 = new TextField("lado 2");
	private TextField txt3 = new TextField("lado 3");
	private Button comparar = new Button("comparar");
	private ThemeResource equilatero = new ThemeResource("img/equilatero.png");
	private ThemeResource isosceles = new ThemeResource("img/isosceles.png");
	private ThemeResource escaleno = new ThemeResource("img/escaleno2.png");
	private Notification notificacion = new Notification("");
	
	public TextField getTxt1() {
		return txt1;
	}

	public TextField getTxt3() {
		return txt3;
	}

	public TextField getTxt2() {
		return txt2;
	}
	
	
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
		txt1.addValidator(new PinValidador());
		
		txt2.setImmediate(true);
		txt2.setNullSettingAllowed(true);
		txt2.setNullRepresentation("");
		txt2.addValidator(new PinValidador());
		
		txt3.setImmediate(true);
		txt3.setNullSettingAllowed(true);
		txt3.setNullRepresentation("");
		txt3.addValidator(new PinValidador());
		
		//comparar.setClickShortcut(KeyCode.ENTER);
		comparar.addClickListener(e -> {
			
			if(isValid()) {
				comparar();
			}
	
		});
		
		
		Button borrar = new Button("Borrar");
		borrar.setClickShortcut(KeyCode.DELETE);
		borrar.addClickListener(e -> {
			txt1.setValue("");
			txt2.setValue("");
			txt3.setValue("");
			txt1.focus();
		});
		
		row.setSpacing(true);
		row.addComponents(comparar, borrar);
		
		centrar.addComponents(label,txt1,txt2,txt3,row);
		
		content.addComponents(centrar);
		content.setComponentAlignment(centrar, Alignment.MIDDLE_CENTER);
		
		setContent(content);
	}
	
	public boolean isValid() {
		
		boolean isValid = true;
		
		
		txt1.setComponentError(null);
		txt2.setComponentError(null);
		txt3.setComponentError(null);
		comparar.setComponentError(null);
		
		if(txt1.getValue().toString().isEmpty() && txt2.getValue().toString().isEmpty() && txt3.getValue().toString().isEmpty()) {
			
			txt1.setComponentError(new UserError("el campo esta vacio"));
			txt2.setComponentError(new UserError("el campo esta vacio"));
			txt3.setComponentError(new UserError("el campo esta vacio"));
			
			isValid = false;
			
		} else if(txt1.getValue().toString().isEmpty() && txt2.getValue().toString().isEmpty() && !txt3.getValue().toString().isEmpty()) {
			txt1.setComponentError(new UserError("el campo esta vacio"));
			txt2.setComponentError(new UserError("el campo esta vacio"));
			
			isValid = false;
		
		} 
		else if(!txt1.getValue().toString().isEmpty() && !txt2.getValue().toString().isEmpty() && txt3.getValue().toString().isEmpty()) {
			
			txt3.setComponentError(new UserError("el campo esta vacio"));
			txt3.focus();
			isValid = false;
		
		}else if(!txt1.getValue().toString().isEmpty() && !txt3.getValue().toString().isEmpty() && txt2.getValue().toString().isEmpty()) {
			
			txt2.focus();
			txt2.setComponentError(new UserError("el campo esta vacio"));
			
			isValid = false;
		
		}
		else if(!txt2.getValue().toString().isEmpty() && !txt3.getValue().toString().isEmpty() && txt1.getValue().toString().isEmpty()) {
			
			txt1.focus();
			txt1.setComponentError(new UserError("el campo esta vacio"));
			
			isValid = false;
		
		}
		else if(!txt1.getValue().toString().isEmpty() && !txt2.getValue().toString().isEmpty() && !txt3.getValue().toString().matches("\\d*")) {
			
			txt3.focus();
			isValid = false;
		
		}else if(!txt2.getValue().toString().isEmpty() && !txt3.getValue().toString().isEmpty() && !txt1.getValue().toString().matches("\\d*")) {
			
			txt1.focus();
			isValid = false;
		
		}else if(!txt1.getValue().toString().isEmpty() && !txt3.getValue().toString().isEmpty() && !txt2.getValue().toString().matches("\\d*")) {
			
			txt2.focus();
			isValid = false;
		
		} else if(txt1.getValue().toString().isEmpty() && txt3.getValue().toString().isEmpty()) {
			txt1.setComponentError(new UserError("el campo esta vacio"));
			txt3.setComponentError(new UserError("el campo esta vacio"));
			isValid = false;
			
		}else if(txt2.getValue().toString().isEmpty() && txt3.getValue().toString().isEmpty()) {
			txt2.setComponentError(new UserError("el campo esta vacio"));
			txt3.setComponentError(new UserError("el campo esta vacio"));
			
			isValid = false;
		
		} 
		
		return isValid;
	}
	
	public void comparar() {
		
		String numero1 = txt1.getValue();
		String numero2 = txt2.getValue();
		String numero3 = txt3.getValue();
		
		Integer num1 = Integer.valueOf(numero1);
		Integer num2 = Integer.valueOf(numero2);
		Integer num3 = Integer.valueOf(numero3);
		
		if (num1.equals(num2) && num1.equals(num3)) {
			
			notificacion("3 lados iguales "+"|"+num1+"|"+num2+"|"+num3+"|",equilatero);
	
			
		} else if (num1.equals(num2) || num2.equals(num3) || num3.equals(num1)) {

			notificacion("2 lados iguales "+"|"+num1+"|"+num2+"|"+num3+"|",isosceles);
		
			
		} else {
			
			notificacion("3 lados diferentes "+"|"+num1+"|"+num2+"|"+num3+"|",escaleno);
		}
	}
	
	public Notification notificacion(String descripcion, ThemeResource icono) {
		
		notificacion.setPosition(Position.BOTTOM_RIGHT);
		notificacion.setDelayMsec(1500);
		notificacion.setDescription(descripcion);
		notificacion.setIcon(icono);
		notificacion.show(Page.getCurrent());
		
		return notificacion;
	}
}

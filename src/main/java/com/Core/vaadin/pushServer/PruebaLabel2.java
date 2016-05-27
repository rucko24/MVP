package com.Core.vaadin.pushServer;

import com.Core.vaadin.Core;
import com.Core.vaadin.Login;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PruebaLabel2 extends VerticalLayout {

	private TextField text = new TextField();
	private Button boton = new Button("entrar");
	private ProgressBar bar = new ProgressBar();
	private Label label = new Label("MAIN");
	private Label label2 = new Label("Por Favor Espere...");
	
	public PruebaLabel2() {

		setMargin(true);
		setSpacing(true);
		text.addValidator(new Validar());
		bar.setIndeterminate(true);
		addComponents(text, boton);

		validar();
	}

	public void validar() {

		text.setImmediate(true);
		text.focus();
		text.setValidationVisible(false);
		boton.setClickShortcut(KeyCode.ENTER);
		boton.addClickListener(e -> {

			try {
				text.validate();
				Notification.show("Bienvenido: " + text.getValue());
				new Hilo().start();
			} catch (InvalidValueException ex) {
				Notification.show(ex.getMessage(), Notification.Type.WARNING_MESSAGE);
				text.setValidationVisible(true);
				text.setValue("");
			}

		});

	}

	public class Validar implements Validator {

		@Override
		public void validate(Object value) throws InvalidValueException {
			String txt = (String) value;

			if (!(txt.equals("ruben"))) {
				throw new InvalidValueException("Acceso denegado " + text.getValue());
			}
		}
	}

	public class Hilo extends Thread {

		int c = 0;

		@Override
		public void run() {
			try {
				while (c < 2) {
					Thread.sleep(1000);

					Core.getCurrent().access(new Runnable() {
						@Override
						public void run() {
							c++;
							removeAllComponents();
							addComponent(bar);
						}
					});
				}
				Core.getCurrent().access(new Runnable() {
					@Override
					public void run() {
						removeAllComponents();
						Core.getCurrent().getNavigator().navigateTo(Login.LOGIN_VIEW);
					}
				});

			} catch (InterruptedException ex) {
				Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
			}

		}
	}

}

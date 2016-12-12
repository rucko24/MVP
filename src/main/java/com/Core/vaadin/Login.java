package com.Core.vaadin;

import com.Core.vaadin.pageLayout.PageLayout;
import com.Core.vaadin.testSistema.SingUpForm;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Login extends VerticalLayout implements View {

	public static final String LOGIN_VIEW = "";
	private Label titulo = new Label("Autorización requerida");
	private Label titulo2 = new Label("Por favor introduzca nombre y contraseña");
	private Button btn;
	private Core ui = Core.getCurrent();
	private ProgressBar bar = new ProgressBar();
	private TextField textfield = new TextField("Nombre de Usuario");
	private PasswordField pass = new PasswordField("Contraseña");
	private HorizontalLayout row = new HorizontalLayout();

	public Login() {
		setSizeFull();
		addStyleName("login");
		
		titulo.addStyleName(ValoTheme.LABEL_H1);
		titulo.addStyleName(ValoTheme.LABEL_COLORED);
		titulo.setSizeUndefined();
		titulo2.setSizeUndefined();
		
		bar.setImmediate(true);
		btn = new Button("entrar");
		textfield.focus();
		textfield.setNullRepresentation("");
		textfield.setNullSettingAllowed(false);
		textfield.addValidator(new Validador());
		textfield.setValidationVisible(false);
		textfield.setImmediate(true);

		pass.addValidator(new Validador());
		pass.setValidationVisible(false);
		pass.setImmediate(true);

		btn.setWidth("185px");
		btn.setClickShortcut(KeyCode.ENTER);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btn.addClickListener(e -> {

			try {

				pass.validate();
				new Hilo().start();

			} catch (InvalidValueException ee) {

				notification(ee.getMessage());
				textfield.setValidationVisible(true);
				textfield.clear();
				pass.clear();
				// pass.setValidationVisible(true);
				textfield.focus();
			}

		});
		
		VerticalLayout layout = new VerticalLayout(titulo,titulo2);
		FormLayout centrar = new FormLayout(layout,textfield, pass, btn);
		centrar.setSpacing(true);
		centrar.setSizeUndefined();
		
		addComponent(centrar);
		setComponentAlignment(centrar, Alignment.MIDDLE_CENTER);

	}

	public Component getBar() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		bar.setIndeterminate(true);
		layout.addComponent(bar);
		layout.setComponentAlignment(bar, Alignment.MIDDLE_CENTER);
		return layout;
	}

	public Notification notification(String mensajeError) {
		Notification n = new Notification(mensajeError, Notification.Type.ERROR_MESSAGE);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());
		n.setDelayMsec(100);
		return n;
	}

	public class Validador implements Validator {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void validate(Object value) throws InvalidValueException {
			String txt = (String) value;

			if (!(txt.equals("1234") && (textfield.getValue().equals("ruben")))) {
				throw new InvalidValueException("datos incorrectos");
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
					ui.access(() -> {

						c++;
						removeAllComponents();
						addComponent(getBar());

					});
				}

				ui.access(() -> {

					ui.getNavigator().navigateTo(SingUpForm.SINGUPFORM);
					removeAllComponents();
					addComponent(new Login());
				
				});

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void enter(ViewChangeEvent event) {
		ui.getPage().setTitle("Login");
	}
}

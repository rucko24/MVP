package com.Core.vaadin;

import com.Core.vaadin.pageLayout.PageLayout;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Login extends VerticalLayout implements View {

	public static final String LOGIN_VIEW = "";
	private Button btn  = new Button("entrar");
	private Core ui = Core.getCurrent();
	private ProgressBar bar = new ProgressBar();
	private TextField textfield = new TextField("Nombre de Usuario");
	private PasswordField pass = new PasswordField("Contraseña");
	private HorizontalLayout row = new HorizontalLayout();
	
	public Login() {			
		setSizeFull();
		
		bar.setImmediate(true);
		
		textfield.focus();
		textfield.setNullRepresentation("");
		textfield.setNullSettingAllowed(false);
		//txt.addValidator(new Validador());
		textfield.setValidationVisible(false);
		textfield.setImmediate(true);
		
		pass.addValidator(new Validador());
		pass.setValidationVisible(false);
		pass.setImmediate(true);
		
		btn.setClickShortcut(KeyCode.ENTER);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btn.addClickListener( e -> {		
			textfield.setValidationVisible(false);
			pass.setValidationVisible(false);
			try {
				
				//txt.validate();
				pass.validate();
				
				ui.getNavigator().navigateTo(PageLayout.PAGELAYOUT_VIEW);
				pass.setValue("");
				textfield.setValue("");
			}catch(InvalidValueException ee) {
				
				notification(ee.getMessage());
				textfield.setValidationVisible(true);
				textfield.setValue("");
				pass.setValue("");
				pass.setValidationVisible(true);
				
			}
			
		});
		
		FormLayout centrar = new FormLayout(textfield,pass,btn);
		centrar.setSpacing(true);
		centrar.setSizeUndefined();
		
		addComponent(centrar);
		setComponentAlignment(centrar, Alignment.MIDDLE_CENTER);
		
		
	}
	
	public Notification notification(String mensajeError) {
		Notification n = new Notification(mensajeError,Notification.Type.ERROR_MESSAGE);
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
			
			if(!(txt.equals("1234") && (textfield.getValue().equals("ruben")))){
				throw new InvalidValueException("datos incorrectos");
			}
		}
		
	}
	@Override
	public void enter(ViewChangeEvent event) {
		ui.getPage().setTitle("Login");
	}
}

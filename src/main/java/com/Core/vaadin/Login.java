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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
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
	private TextField txt = new TextField("Nombre de Usuario");
	private PasswordField pass = new PasswordField();
	private HorizontalLayout row = new HorizontalLayout();
	
	public Login() {			
		setSizeFull();

		txt.setNullRepresentation("");
		txt.setNullSettingAllowed(false);
		txt.focus();
		txt.addValidator(new Validador());
		txt.setValidationVisible(false);
		txt.setImmediate(true);
		
		/*pass.focus();
		pass.addValidator(new Validador());
		pass.setValidationVisible(false);
		pass.setImmediate(true);*/
		
		btn.setClickShortcut(KeyCode.ENTER);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btn.addClickListener( e -> {		
			txt.setValidationVisible(false);
			//pass.setValidationVisible(false);
			try {
				
				txt.validate();
				//pass.validate();
				ui.getNavigator().navigateTo(PageLayout.PAGELAYOUT_VIEW);
				pass.setValue("");
				
			}catch(InvalidValueException ee) {
				
				notification(ee.getMessage());
				txt.setValidationVisible(true);
				txt.setValue("");
				//pass.setValidationVisible(true);
				//pass.se
			}
			
		});
		
		VerticalLayout centrar = new VerticalLayout(txt,btn);
		centrar.setSpacing(true);
		centrar.setComponentAlignment(txt, Alignment.MIDDLE_CENTER);
		centrar.setComponentAlignment(btn, Alignment.MIDDLE_CENTER);
		
		addComponent(centrar);
		setComponentAlignment(centrar, Alignment.MIDDLE_CENTER);
		
		
	}
	
	public Notification notification(String mensajeError) {
		Notification n = new Notification(mensajeError,Notification.Type.WARNING_MESSAGE);
		n.setPosition(Position.BOTTOM_RIGHT);
		n.show(Page.getCurrent());
		
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
			
			if(!(txt instanceof String && txt.equals("ruben"))){
				throw new InvalidValueException("usuario incorrecto");
			}
		}
		
	}
	@Override
	public void enter(ViewChangeEvent event) {
		ui.getPage().setTitle("Login");
	}
}

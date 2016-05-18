package com.Core.vaadin;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Login extends VerticalLayout implements View {

	public static final String LOGIN_VIEW = "";
	private Button btn  = new Button("entrar");
	private Core ui = Core.getCurrent();
	private ProgressBar bar = new ProgressBar();
	
	public Login() {
			
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		bar.setIndeterminate(true);
		
		btn.setClickShortcut(KeyCode.ENTER);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btn.addClickListener( e -> {		
			//navega hasta el layout MAIN
			
			ui.getNavigator().navigateTo(PageLayout.PAGELAYOUT_VIEW);
		});
		
		addComponent(btn);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		ui.getPage().setTitle("Login");

	}

}

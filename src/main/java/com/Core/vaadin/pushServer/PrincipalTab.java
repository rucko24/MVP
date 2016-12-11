package com.Core.vaadin.pushServer;

import com.Core.vaadin.pushServer.icePush.IcePushServerAddOn;
import com.Core.vaadin.pushServer.icePush.MenuMessageBar;
import com.Core.vaadin.pushServer.pruebas.PruebaLabel;
import com.Core.vaadin.pushServer.ventanaEditablePush.NoticeBoard;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTab extends TabSheet{
	
	private static final int HEIGHT = 300;
	
	public PrincipalTab() {
		
		addTab(createIcePushAddon());
		addTab(createMenuMensajes());
		addTab(crearTableroDeAnuncios());
		addTab(createLabelHora());
		addTab(createTestForo());
	}
	
	
	private Component createIcePushAddon() {
		VerticalLayout layout =  (VerticalLayout) createLayout("IcePush Add-on");
		IcePushServerAddOn icePush = new IcePushServerAddOn();
		icePush.setSizeFull();
		layout.addComponent(icePush);
		
		return layout;
	}
	
	private Component createMenuMensajes() {
		VerticalLayout layout = createLayout("Message Bar");
		MenuMessageBar menu = new MenuMessageBar();
		layout.addComponent(menu);
		
		return layout;
	}
	
	private Component crearTableroDeAnuncios() {
		VerticalLayout layout =  createLayout("Tablero-Anuncios");
		NoticeBoard noticia = new NoticeBoard();
		layout.addComponent(noticia);
		
		return layout;
	}
	
	private Component createLabelHora() {
		VerticalLayout layout = createLayout("Label con Hora");
		PruebaLabel label = new PruebaLabel();
		layout.addComponent(label);
		
		return layout;
	}
	
	private Component createTestForo() {
		VerticalLayout layout = createLayout("Test-Foro-Vaadin");
		//TestForo foro = new TestForo();
		//layout.addComponent(foro);
		
		return layout;
	}
	
	private VerticalLayout createLayout(String caption) {
		VerticalLayout layout = new VerticalLayout();
		layout.setCaption(caption);
		layout.setHeight(HEIGHT , Unit.PIXELS);
		
		return layout;
	}
}

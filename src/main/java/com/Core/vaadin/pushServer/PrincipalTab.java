package com.Core.vaadin.pushServer;

import com.Core.vaadin.pushServer.icePush.IcePushServerAddOn;
import com.Core.vaadin.pushServer.push.NoticeBoard;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class PrincipalTab extends TabSheet{
	
	private static final int HEIGHT = 300;
	
	public PrincipalTab() {
		
		
		addTab(createIcePushAddon());
	}
	
	private Layout createIcePushAddon() {
		Layout layout =  createLayout("IcePush Add-on");
		IcePushServerAddOn icePush = new IcePushServerAddOn();
		icePush.setSizeFull();
		layout.addComponent(icePush);
		return layout;
	}
	
	private Layout createLayout(String caption) {
		Layout layout = new VerticalLayout();
		layout.setCaption(caption);
		layout.setHeight(HEIGHT , Unit.PIXELS);
		return layout;
	}
}

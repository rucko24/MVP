package com.Core.vaadin.barraCarga;



import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ContextMenu extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Action action = new Action("Click derecho en esta tabla");
	private Table table = new Table();
	
	public ContextMenu() {
		setSizeFull();
		setMargin(true);
		
		table.setPageLength(0);
		table.setEditable(true);
		
		table.addContainerProperty("NOMBRE", String.class, "");
		table.addContainerProperty("APELLIDO", String.class, "");
		table.addContainerProperty("ciudad", String.class, "");
		table.addContainerProperty("Cedula", Integer.class, 0);
		
		table.addItem();
		table.addItem();
		table.addItem();
		table.addItem();
		table.addItem();
		table.addItem();
		
		
		table.addActionHandler( new Handler() {

			public void handleAction(Action action, Object sender, Object target) {
				
				Notification.show("OK, aqui vamos hola");
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				// TODO Auto-generated method stub
				return new Action[] { action };
			}
			
			
		});
		
		
		addComponent(table);
	}
	
	
}
